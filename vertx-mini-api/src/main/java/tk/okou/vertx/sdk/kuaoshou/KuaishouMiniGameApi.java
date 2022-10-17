package tk.okou.vertx.sdk.kuaoshou;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApi;
import tk.okou.vertx.sdk.kuaoshou.impl.KuaishouMiniGameApiImpl;

public interface KuaishouMiniGameApi extends BaseMiniApi {
    static KuaishouMiniGameApi create(Vertx vertx) {
        return new KuaishouMiniGameApiImpl(vertx, new KuaishouMiniApiOptions());
    }

    static KuaishouMiniGameApi create(Vertx vertx, KuaishouMiniApiOptions options) {
        return new KuaishouMiniGameApiImpl(vertx, options);
    }

//    @Fluent
//    KuaishouMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    KuaishouMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    KuaishouMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

}
