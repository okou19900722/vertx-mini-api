package tk.okou.vertx.sdk.tencent.wechat;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.tencent.BaseMiniApi;

public interface WechatMiniApi {
    @Fluent
    WechatMiniApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniApi code2accessToken(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

}
