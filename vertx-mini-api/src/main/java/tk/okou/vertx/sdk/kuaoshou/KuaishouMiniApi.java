package tk.okou.vertx.sdk.kuaoshou;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApi;

public interface KuaishouMiniApi extends BaseMiniApi {
    @Fluent
    KuaishouMiniApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    KuaishouMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    KuaishouMiniApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

}
