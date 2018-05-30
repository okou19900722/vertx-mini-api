package tk.okou.lippen.wechat.api.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import tk.okou.lippen.wechat.api.Not200Exception;
import tk.okou.lippen.wechat.api.WeChatApi;
import tk.okou.lippen.wechat.api.WeChatOptions;
import tk.okou.lippen.wechat.api.model.KVData;
import tk.okou.lippen.wechat.api.util.DigestUtil;
import tk.okou.lippen.wechat.api.util.SignatureMethod;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.List;
import java.util.function.BiFunction;

import static tk.okou.lippen.wechat.api.util.MessageFormatUtils.format;

public class WeChatApiImpl implements WeChatApi {
    private static final Logger logger = LoggerFactory.getLogger(WeChatApiImpl.class);
    private static final MessageFormat CODE_2_ACCESS_TOKER_FORMATTER = new MessageFormat("/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code");
    private static final MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}");
    public static final MessageFormat SET_USER_STORAGE = new MessageFormat("/wxa/set_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    public static final MessageFormat REMOVE_USER_STORAGE = new MessageFormat("/wxa/remove_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    private final Vertx vertx;
    private final WeChatOptions weChatOptions;
    private final HttpClient httpClient;

    public WeChatApiImpl(Vertx vertx, WeChatOptions weChatOptions) {
        this.vertx = vertx;
        this.weChatOptions = weChatOptions;
        this.httpClient = vertx.createHttpClient(weChatOptions.getApiHttpsClientOptions());
    }

    public WeChatApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        get(format(GET_ACCESS_TOKEN, appId, secret), handler);
        return this;
    }

    @Override
    public WeChatApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        get(format(CODE_2_ACCESS_TOKER_FORMATTER, appId, secret, jsCode), handler);
        return this;
    }

    public WeChatApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) throws NoSuchAlgorithmException, InvalidKeyException {
        return setUserStorage(accessToken, openId, appId, sessionKey, signatureMethod, new JsonArray(kvList), handler);
    }

    private WeChatApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, JsonArray kvList, Handler<AsyncResult<JsonObject>> handler) throws NoSuchAlgorithmException, InvalidKeyException {
        JsonObject data = new JsonObject();
        data.put("kv_list", kvList);
        String signature = signatureMethod.signature(data.encode(), sessionKey);
        get(format(SET_USER_STORAGE, accessToken, signature, signatureMethod.getSignatureMethod()), handler);
        return this;
    }

    public WeChatApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, String kvList, Handler<AsyncResult<JsonObject>> handler) throws NoSuchAlgorithmException, InvalidKeyException {
        return setUserStorage(accessToken, openId, appId, sessionKey, signatureMethod, new JsonArray(kvList), handler);
    }

    public WeChatApi removeUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, String key, Handler<AsyncResult<JsonObject>> handler) throws InvalidKeyException, NoSuchAlgorithmException {
        return removeUserStorage(accessToken, openId, appId, sessionKey, signatureMethod, new JsonArray(key), handler);
    }

    private WeChatApi removeUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, JsonArray key, Handler<AsyncResult<JsonObject>> handler) throws NoSuchAlgorithmException, InvalidKeyException {
        JsonObject data = new JsonObject();
        data.put("key", key);
        String signature = signatureMethod.signature(data.encode(), sessionKey);
        get(format(REMOVE_USER_STORAGE, accessToken, signature, signatureMethod.getSignatureMethod()), handler);
        return this;
    }

    public WeChatApi removeUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) throws InvalidKeyException, NoSuchAlgorithmException {
        return removeUserStorage(accessToken, openId, appId, sessionKey, signatureMethod, new JsonArray(key), handler);
    }


    private void get(String uri, Handler<AsyncResult<JsonObject>> handler) {
        httpClient.get(uri, response -> {
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                response.bodyHandler(data -> {
                    succes(handler, data.toJsonObject());
                });
                response.exceptionHandler(e -> {
                    logger.error("response handler fail", e);
                });
            } else {
                fail(handler, new Not200Exception(statusCode));
            }
        })/*.setTimeout(1000)*/.exceptionHandler(e -> {
            fail(handler, e);
        }).end();
    }
}
