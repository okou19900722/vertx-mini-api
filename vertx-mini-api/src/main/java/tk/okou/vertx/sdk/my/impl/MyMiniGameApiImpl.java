package tk.okou.vertx.sdk.my.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.SignSourceData;
import io.vertx.core.*;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import tk.okou.sdk.AbstractApi;
import tk.okou.sdk.exception.Not200Exception;
import tk.okou.vertx.sdk.BaseMiniApiOptions;
import tk.okou.vertx.sdk.my.MyMiniGameApi;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class MyMiniGameApiImpl extends AbstractApi implements MyMiniGameApi {
    /**
     * 老版本失败节点
     */
    public static final String ERROR_RESPONSE = "error_response";

    /**
     * 新版本节点后缀
     */
    public static final String RESPONSE_SUFFIX = "_response";

    private static final Logger logger = LoggerFactory.getLogger(MyMiniGameApiImpl.class);
    public MyMiniGameApiImpl(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public MyMiniGameApi code2token(String appId, String jsCode, String grantType, String privateKey, String publicKey, Handler<AsyncResult<JsonObject>> handler) {
        String method = "alipay.system.oauth.token";
        Handler<AsyncResult<JsonObject>> prevActionHandler = async -> {
            if (async.failed()) {
                handler.handle(async);
            } else {
                JsonObject response = async.result();
                JsonObject data = response.getJsonObject("error_response");
                if (data == null) {
                    data = response.getJsonObject("alipay_system_oauth_token_response");
                }
                handler.handle(Future.succeededFuture(data));
            }
        };
        signAndPostWithJsonResponse(appId, method, privateKey, publicKey, prevActionHandler, data -> {
            // 业务参数直接平铺，没有 biz_content 包裹
            data.put("grant_type", grantType);
            data.put("code", jsCode);
        });
        return this;
    }

    protected void signAndPostWithJsonResponse(
            String appId,
            String method,
            String privateKey,
            String publicKey,
            Handler<AsyncResult<JsonObject>> handler,
            Consumer<Map<String, String>> postBodyConsumer
    ) {
        signAndPostWithJsonResponse(appId, method, "UTF-8", "RSA2", "1.0", privateKey, publicKey, handler, postBodyConsumer);
    }
    private void signAndPostWithJsonResponse(
            String appId,
            String method,
            String charset,
            String signType,
            String version,
            String privateKey,
            String publicKey,
            Handler<AsyncResult<JsonObject>> handler,
            Consumer<Map<String, String>> postBodyConsumer
    ) {
        Map<String, String> params = new TreeMap<>();
        params.put("app_id", appId);
        params.put("method", method);
        params.put("charset", charset);
        params.put("sign_type", signType);
        params.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        params.put("version", version);
        postBodyConsumer.accept(params);

        // 3. 生成签名
        String signContent = buildSignContent(params);
        String sign;
        try {
            sign = AlipaySignature.sign(signContent, privateKey, charset, signType);
        } catch (Throwable e) {
            handler.handle(Future.failedFuture(e));
            return;
        }
        params.put("sign", sign); // 签名最后加进去
        // 4. 用 Vert.x MultiMap 组装表单数据
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        params.forEach(form::add);


// 3. 手动拼接表单字符串
        String formBody = buildFormBody(params);

        // 4. 发送 POST 请求
        HttpClientRequest request = httpClient.post("/gateway.do");
        request.handler(response -> {
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                response.bodyHandler(body -> {
                    String contentType = response.getHeader("Content-Type");
                    String responseCharset = parseCharset(contentType); // 从 "application/json;charset=GBK" 中提取 GBK
                    responseCharset = responseCharset != null ? responseCharset : "UTF-8";

                    String jsonStr = body.toString(responseCharset);
                    JsonObject json = new JsonObject(jsonStr);
                    String responseSign = json.getString("sign");

                    try {
                        String signSourceData = getSignSourceData(method, jsonStr);
                        if (signSourceData == null || !AlipaySignature.verify(signSourceData, responseSign, publicKey, charset, signType)) {
                            handler.handle(Future.failedFuture("response sign does not match, " + response));
                            return;
                        }
                    } catch (Exception e) {
                        handler.handle(Future.failedFuture(e));
                        return;
                    }

                    success(handler, json);
                });
                response.exceptionHandler(e -> logger.error("response handler fail", e));
            } else {
                fail(handler, new Not200Exception(statusCode));
            }
        });
        request.putHeader("Content-Type", "application/x-www-form-urlencoded");
        request.end(formBody);  // 这一步才真正发送
    }

    private String parseCharset(String contentType) {
        if (contentType == null) return null;
        for (String part : contentType.split(";")) {
            part = part.trim();
            if (part.toLowerCase().startsWith("charset=")) {
                return part.substring("charset=".length()).trim();
            }
        }
        return null;
    }

    private static String buildSignContent(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 构建表单 body 字符串（key=value&key=value）
    private static String buildFormBody(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : params.entrySet()) {
            if (sb.length() > 0) sb.append("&");
            try {
                sb.append(URLEncoder.encode(e.getKey(), "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(e.getValue(), "UTF-8"));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return sb.toString();
    }

    private String getSignSourceData(String method, String body) throws AlipayApiException {
        // 加签源串起点
        String rootNode = method.replace('.', '_')
                + RESPONSE_SUFFIX;
        String errorRootNode = ERROR_RESPONSE;

        int indexOfRootNode = body.indexOf(rootNode);
        int indexOfErrorRoot = body.indexOf(errorRootNode);

        // 成功或者新版接口
        if (indexOfRootNode > 0) {

            return parseSignSourceData(body, rootNode, indexOfRootNode);

            // 老版本失败接口
        } else if (indexOfErrorRoot > 0) {

            return parseSignSourceData(body, errorRootNode, indexOfErrorRoot);
        } else {
            return null;
        }
    }

    /**
     * 获取签名源串内容
     */
    private String parseSignSourceData(String body, String rootNode, int indexOfRootNode) throws AlipayApiException {

        //第一个字母+长度+冒号+引号
        int signDataStartIndex = indexOfRootNode + rootNode.length() + 2;

        int indexOfSign = body.indexOf("\"" + AlipayConstants.SIGN + "\"");
        if (indexOfSign < 0) {
            return null;
        }

        SignSourceData signSourceData = AlipaySignature.extractSignContent(body, signDataStartIndex);

        //如果提取的待验签原始内容后还有root
        if (body.lastIndexOf(rootNode) > signSourceData.getEndIndex()) {
            throw new AlipayApiException("检测到响应报文中有重复的" + rootNode + "，验签失败。");
        }

        return signSourceData.getSourceData();
    }

}
