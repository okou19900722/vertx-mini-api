package tk.okou.vertx.sdk.kuaoshou;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.AbstractMiniApi;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

import java.net.URLEncoder;
import java.util.stream.Collectors;

public abstract class AbstractKuaishouMiniGameApi extends AbstractMiniApi implements KuaishouMiniGameApi, KuaishouMiniGameApiUrlSupplier {

    public AbstractKuaishouMiniGameApi(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public AbstractKuaishouMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        this.code2session(appId, secret, jsCode, null, handler);
        return this;
    }
    @Fluent
    public AbstractKuaishouMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        JsonObject json = new JsonObject();
        json.put("app_id", appId);
        json.put("app_secret", secret);
        json.put("js_code", jsCode);
        String data = json.stream().map(it -> it.getKey() + "=" + URLEncoder.encode(it.getValue().toString())).collect(Collectors.joining("&"));
//        String data = json.encode();
        System.out.println(data);
        postWithJsonResponse(CODE_2_SESSION_URL, data, "application/x-www-form-urlencoded", handler);
        return this;
    }

    @Override
    public AbstractKuaishouMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public AbstractKuaishouMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        getAccessToken("client_credentials", appId, secret, handler);
        return this;
    }

}
