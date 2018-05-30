package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.lippen.wechat.api.impl.WeChatApiImpl;

@VertxGen
public interface WeChatApi extends BaseApi{
    static WeChatApi create(Vertx vertx) {
        return new WeChatApiImpl(vertx, new WeChatOptions());
    }
    static WeChatApi create(Vertx vertx, WeChatOptions weChatOptions) {
        return new WeChatApiImpl(vertx, weChatOptions);
    }
    @Fluent
    WeChatApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);
}
