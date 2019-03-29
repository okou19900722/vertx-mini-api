package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.lippen.wechat.api.impl.MiniProgramApiImpl;

public interface MiniProgramApi extends BaseMiniApi {
    static MiniProgramApi create(Vertx vertx) {
        return new MiniProgramApiImpl(vertx, new MiniProgramOptions());
    }

    static MiniProgramApi create(Vertx vertx, MiniProgramOptions miniGameOptions) {
        return new MiniProgramApiImpl(vertx, miniGameOptions);
    }
    @Fluent
    default MiniProgramApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2accessToken(appId, secret, jsCode, "authorization_code", handler);
    }

    @Fluent
    MiniProgramApi code2accessToken(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    MiniProgramApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    default MiniProgramApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }
}
