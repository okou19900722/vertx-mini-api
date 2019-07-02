package tk.okou.vertx.sdk.tencent.wechat;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.tencent.BaseMiniProgramApi;
import tk.okou.vertx.sdk.tencent.wechat.impl.WechatMiniProgramApiImpl;
@VertxGen
public interface WechatMiniProgramApi extends WechatMiniApi, BaseMiniProgramApi {
    static WechatMiniProgramApi create(Vertx vertx, WechatMiniApiOptions options) {
        return new WechatMiniProgramApiImpl(vertx, options);
    }

    static WechatMiniProgramApi create(Vertx vertx) {
        return new WechatMiniProgramApiImpl(vertx, new WechatMiniApiOptions());
    }

    @Fluent
    WechatMiniProgramApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniProgramApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniProgramApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniProgramApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

}
