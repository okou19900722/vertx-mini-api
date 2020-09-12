package tk.okou.vertx.sdk;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.model.KVData;
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
        setUserStorage(accessToken, openId, sessionKey, kvList).onComplete(handler);
        return this;
    }

    @Override
    public Future<JsonObject> setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList) {
        return this.setUserStorage(accessToken, openId, sessionKey, SignatureMethod.HMAC_SHA256, kvList);
    }

    @Override
    public Future<JsonObject> setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList) {
        JsonObject data = new JsonObject();
        data.put("kv_list", new JsonArray(kvList));
        return doSignature(sessionKey, signatureMethod, data, signature -> getUrlOfSetUserStorage(accessToken, signature, openId, signatureMethod.signatureMethod));
    }

    @Override
    public AbstractMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList).onComplete(handler);
        return this;
    }

    private Future<JsonObject> doSignature(String sessionKey, SignatureMethod signatureMethod, JsonObject data, Function<String, String> urlSupplier) {
        try {
            String postBody = data.encode();
            String signature = signatureMethod.signature(postBody, sessionKey);
            String url = urlSupplier.apply(signature);
            return this.postJsonObject(url, postBody);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            return Future.failedFuture(e);
        }
    }

    @Override
    public AbstractMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        removeUserStorage(accessToken, openId, sessionKey, keys).onComplete(handler);
        return this;
    }

    @Override
    public Future<JsonObject> removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys) {
        return this.removeUserStorage(accessToken, openId, sessionKey, SignatureMethod.HMAC_SHA256, keys);
    }

    @Override
    public Future<JsonObject> removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key) {
        JsonObject data = new JsonObject();
        data.put("key", new JsonArray(key));
        return doSignature(sessionKey, signatureMethod, data, signature -> getUrlOfRemoveUserStorage(accessToken, signature, openId, signatureMethod.signatureMethod));
    }

    @Override
    public AbstractMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key).onComplete(handler);
        return this;
    }
}
