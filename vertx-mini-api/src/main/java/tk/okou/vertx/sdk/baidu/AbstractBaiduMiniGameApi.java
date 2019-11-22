package tk.okou.vertx.sdk.baidu;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.AbstractMiniApi;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

public abstract class AbstractBaiduMiniGameApi extends AbstractMiniApi implements BaiduMiniGameApi, BaiduMiniGameApiUrlSupplier {

    public AbstractBaiduMiniGameApi(Vertx vertx, BaseMiniApiOptions options) {
        super(vertx, options);
    }

    @Override
    public AbstractBaiduMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, handler);
        return this;
    }

    @Override
    public AbstractBaiduMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, grantType, handler);
        return this;
    }

    @Override
    public AbstractBaiduMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public AbstractBaiduMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        getAccessToken("client_credentials", appId, secret, handler);
        return this;
    }

}
