package tk.okou.vertx.sdk;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.BaseApi;

@VertxGen(concrete = false)
public interface BaseMiniApi extends BaseApi {
	@Fluent
	BaseMiniApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

	@Fluent
	BaseMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);
}
