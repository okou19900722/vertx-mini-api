package tk.okou.vertx.sdk.toutiao;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.BaseMiniGameApi;
import tk.okou.vertx.sdk.model.KVData;
import tk.okou.vertx.sdk.toutiao.impl.ToutiaoMiniGameApiImpl;

import java.util.List;

public interface ToutiaoMiniGameApi extends ToutiaoMiniApi, BaseMiniGameApi {
    static ToutiaoMiniGameApi create(Vertx vertx) {
        return new ToutiaoMiniGameApiImpl(vertx, new ToutiaoMiniApiOptions());
    }

    static ToutiaoMiniGameApi create(Vertx vertx, ToutiaoMiniApiOptions options) {
        return new ToutiaoMiniGameApiImpl(vertx, options);
    }

    @Fluent
    ToutiaoMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler);

}
