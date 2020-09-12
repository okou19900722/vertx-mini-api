package tk.okou.vertx.sdk;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.BaseApi;

@VertxGen(concrete = false)
public interface BaseMiniApi extends BaseApi {
    @Fluent
    BaseMiniApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    Future<JsonObject> code2session(String appId, String secret, String jsCode);

    @Fluent
    BaseMiniApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    Future<JsonObject> code2session(String appId, String secret, String jsCode, String grantType);

    @Fluent
    BaseMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    Future<JsonObject> getAccessToken(String appId, String secret);

    @Fluent
    BaseMiniApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    Future<JsonObject> getAccessToken(String grantType, String appId, String secret);

}
