package tk.okou.vertx.sdk.bilibili.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.AbstractApi;
import tk.okou.vertx.sdk.bilibili.BilibiliGameApi;
import tk.okou.vertx.sdk.bilibili.BilibiliMiniApiOptions;
import tk.okou.vertx.sdk.bilibili.BilibiliMiniGameApiUrlSupplier;

public class BilibiliGameApiImpl extends AbstractApi implements BilibiliGameApi, BilibiliMiniGameApiUrlSupplier {
    public BilibiliGameApiImpl(Vertx vertx, BilibiliMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public BilibiliGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2session(appId, secret, jsCode, "authorization_code", handler);
    }

    @Override
    public BilibiliGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        String url = getUrlOfCode2session(appId, secret, jsCode, grantType);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public BilibiliGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }

    @Override
    public BilibiliGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        String url = getUrlOfGetAccessToken(grantType, appId, secret);
        getWithJsonResponse(url, handler);
        return this;
    }
}
