package tk.okou.vertx.sdk.tencent.qq;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.AbstractMiniGameApi;
import tk.okou.vertx.sdk.BaseMiniApiOptions;
import tk.okou.vertx.sdk.model.KVData;
import tk.okou.sdk.util.SignatureMethod;

import java.util.List;

public abstract class AbstractQQMiniGameApi extends AbstractMiniGameApi implements QQMiniGameApi, QQMiniGameApiUrlSupplier {
    public AbstractQQMiniGameApi(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public AbstractQQMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }

    @Override
    public AbstractQQMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public AbstractQQMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, grantType, handler);
        return this;
    }

    @Override
    public AbstractQQMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, handler);
        return this;
    }

    @Override
    public AbstractQQMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, kvList, handler);
        return this;
    }

    @Override
    public AbstractQQMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList, handler);
        return this;
    }

    @Override
    public AbstractQQMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, keys, handler);
        return this;
    }

    @Override
    public AbstractQQMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key, handler);
        return this;
    }

    @Override
    public QQMiniGameApi sendSubscriptionMessage(String accessToken, String toUserOpenId, String templateId, String page, JsonObject data, String emphasisKeyword, String oacAppId, int useRobot, Handler<AsyncResult<JsonObject>> handler) {
        String url = getSendSubscriptionMessage(accessToken);
        JsonObject postBody = new JsonObject();
        postBody.put("touser", toUserOpenId);
        postBody.put("template_id", templateId);
        if (page != null) {
            postBody.put("page", page);
        }
        if (data != null) {
            postBody.put("data", data);
        }
        if (emphasisKeyword != null) {
            postBody.put("emphasis_keyword", emphasisKeyword);
        }
        if (oacAppId != null) {
            postBody.put("oac_appid", oacAppId);
        }
        if (useRobot > 0) {
            postBody.put("use_robot", useRobot);
        }
        postWithJsonResponse(url, postBody.encode(), "application/json", handler);
        return this;
    }
}
