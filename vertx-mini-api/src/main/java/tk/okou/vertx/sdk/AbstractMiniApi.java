package tk.okou.vertx.sdk;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.AbstractApi;

public abstract class AbstractMiniApi extends AbstractApi implements BaseMiniApi, BaseMiniApiUrlSupplier {

    public AbstractMiniApi(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    @Fluent
    public AbstractMiniApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2session(appId, secret, jsCode, "authorization_code", handler);
    }

    @Fluent
    public AbstractMiniApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        String url = getUrlOfCode2session(appId, secret, jsCode, grantType);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Fluent
    public AbstractMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }

    @Fluent
    public AbstractMiniApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        String url = getUrlOfGetAccessToken(grantType, appId, secret);
        getWithJsonResponse(url, handler);
        return this;
    }


}
