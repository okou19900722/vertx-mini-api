package tk.okou.vertx.sdk.tencent.qq;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.tencent.qq.impl.QQMiniGameApiImpl;
import tk.okou.vertx.sdk.tencent.model.KVData;
import tk.okou.sdk.util.SignatureMethod;

import java.util.List;

@VertxGen
public interface QQMiniGameApi {
    static QQMiniGameApi create(Vertx vertx, QQMiniApiOptions options) {
        return new QQMiniGameApiImpl(vertx, options);
    }

    static QQMiniGameApi create(Vertx vertx) {
        return new QQMiniGameApiImpl(vertx, new QQMiniApiOptions());
    }
    @Fluent
    QQMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    QQMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    QQMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    QQMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);


    QQMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @GenIgnore
    QQMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    QQMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler);

    @GenIgnore
    QQMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler);


}
