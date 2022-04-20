package tk.okou.vertx.sdk.toutiao;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.AbstractApi;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.BaseMiniApiOptions;
import tk.okou.vertx.sdk.model.KVData;

import java.util.List;

public class AbstractToutiaoMiniGameApi extends AbstractApi implements ToutiaoMiniGameApi, ToutiaoMiniGameApiUrlSupplier {

    public AbstractToutiaoMiniGameApi(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    public AbstractToutiaoMiniGameApi code2session(String appId, String secret, String jsCode, String anonymousCode, Handler<AsyncResult<JsonObject>> handler) {
        String url = getUrlOfCode2session(appId, secret, jsCode, anonymousCode);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public AbstractToutiaoMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        String url = getUrlOfGetAccessToken(grantType, appId, secret);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public AbstractToutiaoMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }

    @Override
    public AbstractToutiaoMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        return setUserStorage(accessToken, openId, sessionKey, SignatureMethod.HMAC_SHA256, kvList, handler);
    }

    @Override
    public AbstractToutiaoMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        return setUserStorage(accessToken, openId, sessionKey, signatureMethod, new JsonArray(kvList), handler);
    }

    private AbstractToutiaoMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, JsonArray kvList, Handler<AsyncResult<JsonObject>> handler) {
        JsonObject data = new JsonObject();
        data.put("kv_list", kvList);
        doSignature(sessionKey, signatureMethod, data, signature -> getUrlOfSetUserStorage(accessToken, signature, openId, signatureMethod.signatureMethod), handler);
        return this;
    }

    private AbstractToutiaoMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, JsonArray key, Handler<AsyncResult<JsonObject>> handler) {
        JsonObject data = new JsonObject();
        data.put("key", key);
        doSignature(sessionKey, signatureMethod, data, signature -> getUrlOfRemoveUserStorage(accessToken, signature, openId, signatureMethod.signatureMethod), handler);
        return this;
    }

    @Override
    public AbstractToutiaoMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        return removeUserStorage(accessToken, openId, sessionKey, SignatureMethod.HMAC_SHA256, keys, handler);
    }

    @Override
    public AbstractToutiaoMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        return removeUserStorage(accessToken, openId, sessionKey, signatureMethod, new JsonArray(key), handler);
    }

}
