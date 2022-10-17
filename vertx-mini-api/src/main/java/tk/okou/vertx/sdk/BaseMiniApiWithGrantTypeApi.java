package tk.okou.vertx.sdk;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

public interface BaseMiniApiWithGrantTypeApi extends BaseMiniApi {
	@Fluent
	BaseMiniApiWithGrantTypeApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

	@Fluent
	BaseMiniApiWithGrantTypeApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

	@Fluent
	BaseMiniApiWithGrantTypeApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

	@Fluent
	BaseMiniApiWithGrantTypeApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);
}
