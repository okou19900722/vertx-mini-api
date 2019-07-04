package tk.okou.vertx.sdk.tencent.wechat.impl;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.tencent.wechat.AbstractWechatMiniGameApi;
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniApiOptions;
import tk.okou.vertx.sdk.model.KVData;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi;

import java.util.List;

public class WechatMiniGameApiImpl extends AbstractWechatMiniGameApi implements WechatMiniGameApi {
    public WechatMiniGameApiImpl(Vertx vertx, WechatMiniApiOptions options) {
        super(vertx, options);
    }

    @Fluent
    public WechatMiniGameApiImpl code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, handler);
        return this;
    }

    @Fluent
    public WechatMiniGameApiImpl code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, grantType, handler);
        return this;
    }

    @Fluent
    public WechatMiniGameApiImpl getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }

    @Fluent
    public WechatMiniGameApiImpl getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public WechatMiniGameApiImpl setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList, handler);
        return this;
    }

    @Override
    public WechatMiniGameApiImpl setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, kvList, handler);
        return this;
    }

    @Override
    public WechatMiniGameApiImpl removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key, handler);
        return this;
    }

    @Override
    public WechatMiniGameApiImpl removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, keys, handler);
        return this;
    }
}
