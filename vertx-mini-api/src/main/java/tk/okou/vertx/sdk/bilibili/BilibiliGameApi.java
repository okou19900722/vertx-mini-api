package tk.okou.vertx.sdk.bilibili;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApi;
import tk.okou.vertx.sdk.bilibili.impl.BilibiliGameApiImpl;

@VertxGen
public interface BilibiliGameApi extends BaseMiniApi {
    static BilibiliGameApi create(Vertx vertx) {
        return new BilibiliGameApiImpl(vertx, new BilibiliMiniApiOptions());
    }

    static BilibiliGameApi create(Vertx vertx, BilibiliMiniApiOptions options) {
        return new BilibiliGameApiImpl(vertx, options);
    }

    @Fluent
    BilibiliGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BilibiliGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BilibiliGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BilibiliGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

}
