package tk.okou.vertx.sdk.baidu;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.baidu.impl.BaiduMiniGameApiImpl;

public interface BaiduMiniGameApi extends BaiduMiniApi {
    static BaiduMiniGameApi create(Vertx vertx) {
        return new BaiduMiniGameApiImpl(vertx, new BaiduMiniApiOptions());
    }

    static BaiduMiniGameApi create(Vertx vertx, BaiduMiniApiOptions options) {
        return new BaiduMiniGameApiImpl(vertx, options);
    }

    @Fluent
    BaiduMiniGameApi code2session(String clientId, String sk, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaiduMiniGameApi code2session(String clientId, String sk, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaiduMiniGameApi getAccessToken(String clientId, String sk, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaiduMiniGameApi getAccessToken(String grantType, String clientId, String sk, Handler<AsyncResult<JsonObject>> handler);

}
