package tk.okou.lippen.wechat.api.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import tk.okou.lippen.wechat.api.MiniGameOptions;
import tk.okou.lippen.wechat.api.Not200Exception;
import tk.okou.lippen.wechat.api.MiniGameApi;
import tk.okou.lippen.wechat.api.model.KVData;
import tk.okou.lippen.wechat.api.util.SignatureMethod;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.List;

import static tk.okou.lippen.wechat.api.util.MessageFormatUtils.format;

public class MiniGameApiImpl implements MiniGameApi {
    private static final Logger logger = LoggerFactory.getLogger(MiniGameApiImpl.class);
    //    private final Vertx vertx;
//    private final MiniGameOptions weChatOptions;
    private final HttpClient httpClient;
//    private final WebClient client;

    public MiniGameApiImpl(Vertx vertx, MiniGameOptions miniGameOptions) {
//        this.vertx = vertx;
//        this.miniGameOptions = miniGameOptions;
        HttpClientOptions httpClientOptions = miniGameOptions.getApiHttpsClientOptions();
        this.httpClient = vertx.createHttpClient(httpClientOptions);
//        WebClientOptions options = new WebClientOptions();
//        options.setDefaultHost(httpClientOptions.getDefaultHost());
//        options.setDefaultPort(httpClientOptions.getDefaultPort());
//        options.setSsl(httpClientOptions.isSsl());
//        options.setVerifyHost(false);
//        options.setTrustAll(httpClientOptions.isTrustAll());
//        this.client = WebClient.create(vertx, options);
    }

    @Override
    public MiniGameApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2accessToken(appId, secret, jsCode, "authorization_code", handler);
    }

    @Override
    public MiniGameApi code2accessToken(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        get(format(CODE_2_ACCESS_TOKEN_FORMATTER, appId, secret, jsCode, grantType), handler);
        return this;
    }

    @Override
    public MiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        get(format(GET_ACCESS_TOKEN, appId, secret), handler);
        return this;
    }

    @Override
    public MiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }

    @Override
    public MiniGameApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        return setUserStorage(accessToken, openId, appId, sessionKey, SignatureMethod.HMAC_SHA256, kvList, handler);
    }

    @Override
    public MiniGameApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        return setUserStorage(accessToken, openId, appId, sessionKey, signatureMethod, new JsonArray(kvList), handler);
    }

    private MiniGameApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, JsonArray kvList, Handler<AsyncResult<JsonObject>> handler) {
        JsonObject data = new JsonObject();
        data.put("kv_list", kvList);
        doSignature(accessToken, sessionKey, signatureMethod, handler, data, SET_USER_STORAGE, openId);
        return this;
    }

    private void doSignature(String accessToken, String sessionKey, SignatureMethod signatureMethod, Handler<AsyncResult<JsonObject>> handler, JsonObject data, MessageFormat setUserStorage, String openId) {
        try {
            String postBody = data.encode();
            String signature = signatureMethod.signature(postBody, sessionKey);
            post(format(setUserStorage, accessToken, signature, openId, signatureMethod.getSignatureMethod()), postBody, handler);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            fail(handler, e);
        }
    }

    private MiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, JsonArray key, Handler<AsyncResult<JsonObject>> handler) {
        JsonObject data = new JsonObject();
        data.put("key", key);
        doSignature(accessToken, sessionKey, signatureMethod, handler, data, REMOVE_USER_STORAGE, openId);
        return this;
    }

    @Override
    public MiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        return removeUserStorage(accessToken, openId, sessionKey, SignatureMethod.HMAC_SHA256, keys, handler);
    }

    @Override
    public MiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        return removeUserStorage(accessToken, openId, sessionKey, signatureMethod, new JsonArray(key), handler);
    }

    private void get(String uri, Handler<AsyncResult<JsonObject>> handler) {
        httpClient.get(uri, responseHandler(handler))
                /*.setTimeout(1000)*/
                .exceptionHandler(e -> fail(handler, e))
                .end();
    }

    private Handler<HttpClientResponse> responseHandler(Handler<AsyncResult<JsonObject>> handler) {
        return response -> {
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                response.bodyHandler(body -> {
                    JsonObject data = body.toJsonObject();
                    Integer errcode = data.getInteger("errcode");
                    if (errcode != null && errcode != 0) {
                        logger.error(response.request().uri() + " - " + data);
                    }
                    succes(handler, body.toJsonObject());
                });
                response.exceptionHandler(e -> logger.error("response handler fail", e));
            } else {
                fail(handler, new Not200Exception(statusCode));
            }
        };
    }

    private void post(String uri, String data, Handler<AsyncResult<JsonObject>> handler) {
        httpClient.post(uri, responseHandler(handler))
                /*.setTimeout(1000)*/
                .exceptionHandler(e -> fail(handler, e))
                .end(data);
    }
}
