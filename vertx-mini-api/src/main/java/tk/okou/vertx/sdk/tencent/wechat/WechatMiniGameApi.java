package tk.okou.vertx.sdk.tencent.wechat;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.BaseMiniGameApi;
import tk.okou.vertx.sdk.tencent.model.KVData;
import tk.okou.vertx.sdk.tencent.wechat.impl.WechatMiniGameApiImpl;

import java.text.MessageFormat;
import java.util.List;

@VertxGen
public interface WechatMiniGameApi extends BaseMiniGameApi, WechatMiniApi {
    @GenIgnore
    MessageFormat QRCODE_A = new MessageFormat("/wxa/getwxacode?access_token={0}");
    @GenIgnore
    MessageFormat QRCODE_B = new MessageFormat("/wxa/getwxacodeunlimit?access_token={0}");
    @GenIgnore
    MessageFormat QRCODE_C = new MessageFormat("/cgi-bin/wxaapp/createwxaqrcode?access_token={0}");

    static WechatMiniGameApi create(Vertx vertx, WechatMiniApiOptions options) {
        return new WechatMiniGameApiImpl(vertx, options);
    }

    static WechatMiniGameApi create(Vertx vertx) {
        return new WechatMiniGameApiImpl(vertx, new WechatMiniApiOptions());
    }

    @Fluent
    default WechatMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2session(appId, secret, jsCode, "authorization_code", handler);
    }

    @Fluent
    WechatMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    default WechatMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }

    @Fluent
    WechatMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi getWXACode(String accessToken, String path, Integer width, Boolean autoColor, Color lineColor, Boolean isHyaline, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler);

    @Fluent
    WechatMiniGameApi getWXACodeUnlimited(String accessToken, String scene, String page, Integer width, Boolean autoColor, Color lineColor, Boolean isHyaline, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler);

    @Fluent
    WechatMiniGameApi createWXAQRCode(String accessToken, String path, String width, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler);

}
