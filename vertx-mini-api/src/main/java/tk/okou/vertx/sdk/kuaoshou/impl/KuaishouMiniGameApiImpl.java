package tk.okou.vertx.sdk.kuaoshou.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.kuaoshou.AbstractKuaishouMiniGameApi;
import tk.okou.vertx.sdk.kuaoshou.KuaishouMiniApiOptions;
import tk.okou.vertx.sdk.kuaoshou.KuaishouMiniGameApi;

public class KuaishouMiniGameApiImpl extends AbstractKuaishouMiniGameApi implements KuaishouMiniGameApi {
    public KuaishouMiniGameApiImpl(Vertx vertx, KuaishouMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public KuaishouMiniGameApiImpl code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
//        super.code2session(appId, secret, jsCode, handler);
        super.code2session(appId, secret, jsCode, null, handler);
        return this;
    }

    @Override
    public KuaishouMiniGameApiImpl getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public KuaishouMiniGameApiImpl getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }
}
