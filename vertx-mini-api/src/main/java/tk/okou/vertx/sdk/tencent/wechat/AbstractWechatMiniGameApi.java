package tk.okou.vertx.sdk.tencent.wechat;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.AbstractMiniGameApi;
import tk.okou.sdk.exception.Not200Exception;
import tk.okou.vertx.sdk.model.KVData;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.sdk.util.MessageFormatUtils;

import java.text.MessageFormat;
import java.util.List;

public abstract class AbstractWechatMiniGameApi extends AbstractMiniGameApi implements WechatMiniGameApi, WechatMiniApiUrlSupplier, WechatMiniGameApiUrlSupplier {
    public AbstractWechatMiniGameApi(Vertx vertx, WechatMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public AbstractWechatMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public AbstractWechatMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }

    @Override
    public AbstractWechatMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, grantType, handler);
        return this;
    }

    @Override
    public AbstractWechatMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, handler);
        return this;
    }

    @Override
    public AbstractWechatMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList, handler);
        return this;
    }

    @Override
    public AbstractWechatMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, kvList, handler);
        return this;
    }

    @Override
    public AbstractWechatMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key, handler);
        return this;
    }

    @Override
    public AbstractWechatMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, keys, handler);
        return this;
    }

    @Override
    public AbstractWechatMiniGameApi getWXACode(String accessToken, String path, Integer width, Boolean autoColor, Color lineColor, Boolean isHyaline, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler) {
        if (path == null) {
            failHandler.handle(Future.failedFuture(new NullPointerException("path is null")));
            return this;
        }
        JsonObject postParam = new JsonObject();
        postParam.put("path", path);
        return getQrCode(accessToken, width, autoColor, lineColor, isHyaline, successConsumer, failHandler, postParam, QRCODE_A);
    }

    @Override
    public AbstractWechatMiniGameApi getWXACodeUnlimited(String accessToken, String scene, String page, Integer width, Boolean autoColor, Color lineColor, Boolean isHyaline, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler) {
        if (scene == null) {
            failHandler.handle(Future.failedFuture(new NullPointerException("scene is null")));
            return this;
        }
        JsonObject postParam = new JsonObject();
        postParam.put("scene", scene);
        if (page != null) {
            postParam.put("page", page);
        }
        return getQrCode(accessToken, width, autoColor, lineColor, isHyaline, successConsumer, failHandler, postParam, QRCODE_B);
    }

    private AbstractWechatMiniGameApi getQrCode(String accessToken, Integer width, Boolean autoColor, Color lineColor, Boolean isHyaline, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler, JsonObject postParam, MessageFormat urlFormatter) {
        if (width != null) {
            // width 限制见 https://developers.weixin.qq.com/minigame/dev/api-backend/open-api/qr-code/wxacode.get.html
            if (width < 280 || width > 1280) {
                failHandler.handle(Future.failedFuture("最小 280px，最大 1280px"));
                return this;
            }
            postParam.put("width", width);
        }
        if (autoColor != null) {
            postParam.put("auto_color", autoColor);
        }
        if (lineColor != null) {
            postParam.put("line_color", new JsonObject().put("r", lineColor.getRed().toString()).put("g", lineColor.getGreen().toString()).put("b", lineColor.getBlue().toString()).encode());
        }
        if (isHyaline != null) {
            postParam.put("is_hyaline", isHyaline);
        }
        return createQrcode(urlFormatter, accessToken, postParam, successConsumer, failHandler);
    }

    @Override
    public AbstractWechatMiniGameApi createWXAQRCode(String accessToken, String path, String width, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler) {
        if (path == null) {
            failHandler.handle(Future.failedFuture(new NullPointerException("path is null")));
            return this;
        }
        JsonObject postParam = new JsonObject();
        postParam.put("path", path);
        if (width != null) {
            postParam.put("width", width);
        }
        return createQrcode(QRCODE_C, accessToken, postParam, successConsumer, failHandler);
    }

    private AbstractWechatMiniGameApi createQrcode(MessageFormat urlFormatter, String accessToken, JsonObject postParam, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler) {
        if (accessToken == null) {
            failHandler.handle(Future.failedFuture(new NullPointerException("accessToken is null")));
            return this;
        }
        Handler<HttpClientResponse> responseHandler = response -> {
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                String contentType = response.headers().get(HttpHeaders.CONTENT_TYPE);
                if (contentType.contains("application/json")) {
                    response.bodyHandler(body -> success(failHandler, body.toJsonObject()));
                } else {
                    response.bodyHandler(successConsumer);
                }
            } else {
                fail(failHandler, new Not200Exception(statusCode));
            }
        };
        HttpClientRequest request = httpClient.post(MessageFormatUtils.format(urlFormatter, accessToken)).handler(responseHandler);
        if (options.getTimeout() != null) {
            request.setTimeout(options.getTimeout());
        }
        request.exceptionHandler(e -> fail(failHandler, e))
                .end(postParam.toString());
        return this;
    }
}
