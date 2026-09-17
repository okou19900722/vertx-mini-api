package tk.okou.vertx.sdk.my.impl;

import io.vertx.core.*;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import tk.okou.sdk.AbstractApi;
import tk.okou.sdk.exception.Not200Exception;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.BaseMiniApiOptions;
import tk.okou.vertx.sdk.my.MyMiniGameApi;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class MyMiniGameApiImpl extends AbstractApi implements MyMiniGameApi {

    private static final Logger logger = LoggerFactory.getLogger(MyMiniGameApiImpl.class);
    public MyMiniGameApiImpl(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public MyMiniGameApi code2token(String appId, String jsCode, String grantType, String privateKey, Handler<AsyncResult<JsonObject>> handler) {
        signAndPostWithJsonResponse(appId, "alipay.system.oauth.token", privateKey, handler, data -> {
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
            Handler<AsyncResult<JsonObject>> handler,
            Consumer<Map<String, String>> postBodyConsumer
    ) {
        signAndPostWithJsonResponse(appId, method, "UTF-8", "RSA2", "1.0", privateKey, handler, postBodyConsumer);
    }
    private void signAndPostWithJsonResponse(
            String appId,
            String method,
            String charset,
            String signType,
            String version,
            String privateKey,
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
            sign = SignatureMethod.SHA256_WITH_RSA.signature(signContent, privateKey);
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
                    String jsonStr = body.toString(responseCharset != null ? responseCharset : "UTF-8");
                    JsonObject json = new JsonObject(jsonStr);
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
}
