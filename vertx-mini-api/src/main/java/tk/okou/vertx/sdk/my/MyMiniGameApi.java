package tk.okou.vertx.sdk.my;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.BaseApi;
import tk.okou.vertx.sdk.my.impl.MyMiniGameApiImpl;

public interface MyMiniGameApi extends BaseApi {
    static MyMiniGameApi create(Vertx vertx) {
        return new MyMiniGameApiImpl(vertx, new MyMiniGameApiOptions());
    }
    @Fluent
    default MyMiniGameApi code2token(String appId, String jsCode, String privateKey, Handler<AsyncResult<JsonObject>> handler) {
        return code2token(appId, jsCode, privateKey, "authorization_code", handler);
    }
    @Fluent
    MyMiniGameApi code2token(String appId, String jsCode, String grantType, String privateKey, Handler<AsyncResult<JsonObject>> handler);
}
