package tk.okou.vertx.sdk.baidu.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.baidu.AbstractBaiduMiniGameApi;
import tk.okou.vertx.sdk.baidu.BaiduMiniApiOptions;
import tk.okou.vertx.sdk.baidu.BaiduMiniGameApi;

public class BaiduMiniGameApiImpl extends AbstractBaiduMiniGameApi implements BaiduMiniGameApi {
    public BaiduMiniGameApiImpl(Vertx vertx, BaiduMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public BaiduMiniGameApiImpl code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, null, handler);
        return this;
    }

    @Override
    public BaiduMiniGameApiImpl getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public BaiduMiniGameApiImpl getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }
}
