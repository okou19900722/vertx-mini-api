package tk.okou.vertx.sdk.toutiao.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.model.KVData;
import tk.okou.vertx.sdk.toutiao.AbstractToutiaoMiniGameApi;
import tk.okou.vertx.sdk.toutiao.ToutiaoMiniApiOptions;
import tk.okou.vertx.sdk.toutiao.ToutiaoMiniGameApi;

import java.util.List;

public class ToutiaoMiniGameApiImpl extends AbstractToutiaoMiniGameApi implements ToutiaoMiniGameApi {
    public ToutiaoMiniGameApiImpl(Vertx vertx, ToutiaoMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public ToutiaoMiniGameApiImpl code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, handler);
        return this;
    }

    @Override
    public ToutiaoMiniGameApiImpl code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, grantType, handler);
        return this;
    }

    @Override
    public ToutiaoMiniGameApiImpl getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public ToutiaoMiniGameApiImpl getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }

    @Override
    public ToutiaoMiniGameApiImpl setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList, handler);
        return this;
    }

    @Override
    public ToutiaoMiniGameApiImpl setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, kvList, handler);
        return this;
    }

    @Override
    public ToutiaoMiniGameApiImpl removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, keys, handler);
        return this;
    }

    @Override
    public ToutiaoMiniGameApiImpl removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key, handler);
        return this;
    }
}
