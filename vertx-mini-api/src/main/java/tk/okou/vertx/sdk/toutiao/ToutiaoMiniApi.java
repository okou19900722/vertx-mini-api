package tk.okou.vertx.sdk.toutiao;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.BaseApi;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.BaseMiniApi;
import tk.okou.vertx.sdk.model.KVData;

import java.util.List;

@VertxGen(concrete = false)
public interface ToutiaoMiniApi extends BaseApi {

    @Fluent
    ToutiaoMiniApi code2session(String appId, String secret, String jsCode, String anonymousCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    ToutiaoMiniApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler);

}
