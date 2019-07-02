package tk.okou.vertx.sdk.tencent.wechat.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.tencent.AbstractMiniApi;
import tk.okou.vertx.sdk.tencent.BaseMiniApiOptions;
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniApiUrlSupplier;
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniProgramApi;

public class WechatMiniProgramApiImpl extends AbstractMiniApi implements WechatMiniProgramApi, WechatMiniApiUrlSupplier {
    public WechatMiniProgramApiImpl(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public WechatMiniProgramApiImpl getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public WechatMiniProgramApiImpl getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }

    @Override
    public WechatMiniProgramApiImpl code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, grantType, handler);
        return this;
    }

    @Override
    public WechatMiniProgramApiImpl code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, handler);
        return this;
    }
}
