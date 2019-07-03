package tk.okou.vertx.sdk.tencent;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.tencent.model.KVData;
import tk.okou.sdk.util.SignatureMethod;

import java.util.List;

public interface BaseMiniGameApi extends BaseMiniApi {
    @Fluent
    BaseMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaseMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaseMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaseMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    BaseMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @GenIgnore
    BaseMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaseMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler);

    @GenIgnore
    BaseMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler);

}
