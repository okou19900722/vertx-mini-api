package tk.okou.vertx.sdk.tencent;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.tencent.model.KVData;
import tk.okou.sdk.util.SignatureMethod;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.function.Function;


public abstract class AbstractMiniGameApi extends AbstractMiniApi implements BaseMiniGameApi, BaseMiniGameApiUrlSupplier {
    public AbstractMiniGameApi(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public AbstractMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public AbstractMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }

    @Override
    public AbstractMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, grantType, handler);
        return this;
    }

    @Override
    public AbstractMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, handler);
        return this;
    }

    @Override
    public AbstractMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        return setUserStorage(accessToken, openId, sessionKey, SignatureMethod.HMAC_SHA256, kvList, handler);
    }

    @Override
    public AbstractMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        return setUserStorage(accessToken, openId, sessionKey, signatureMethod, new JsonArray(kvList), handler);
    }

    private AbstractMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, JsonArray kvList, Handler<AsyncResult<JsonObject>> handler) {
        JsonObject data = new JsonObject();
        data.put("kv_list", kvList);
        doSignature(sessionKey, signatureMethod, data, signature -> getUrlOfSetUserStorage(accessToken, signature, openId, signatureMethod.signatureMethod), handler);
        return this;
    }

    private void doSignature(String sessionKey, SignatureMethod signatureMethod, JsonObject data, Function<String, String> urlSupplier, Handler<AsyncResult<JsonObject>> handler) {
        try {
            String postBody = data.encode();
            String signature = signatureMethod.signature(postBody, sessionKey);
            String url = urlSupplier.apply(signature);
            post(url, postBody, handler);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            fail(handler, e);
        }
    }

    private AbstractMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, JsonArray key, Handler<AsyncResult<JsonObject>> handler) {
        JsonObject data = new JsonObject();
        data.put("key", key);
        doSignature(sessionKey, signatureMethod, data, signature -> getUrlOfRemoveUserStorage(accessToken, signature, openId, signatureMethod.signatureMethod), handler);
        return this;
    }

    @Override
    public AbstractMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        return removeUserStorage(accessToken, openId, sessionKey, SignatureMethod.HMAC_SHA256, keys, handler);
    }

    @Override
    public AbstractMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        return removeUserStorage(accessToken, openId, sessionKey, signatureMethod, new JsonArray(key), handler);
    }
}
