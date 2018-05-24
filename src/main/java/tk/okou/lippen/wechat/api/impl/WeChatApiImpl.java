package tk.okou.lippen.wechat.api.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import tk.okou.lippen.wechat.api.Not200Exception;
import tk.okou.lippen.wechat.api.WeChatApi;
import tk.okou.lippen.wechat.api.WeChatOptions;

public class WeChatApiImpl implements WeChatApi {

    private final Vertx vertx;
    private final WeChatOptions weChatOptions;
    private final HttpClient httpClient;

    public WeChatApiImpl(Vertx vertx) {
        this(vertx, new WeChatOptions());
    }

    public WeChatApiImpl(Vertx vertx, WeChatOptions weChatOptions) {
        this.vertx = vertx;
        this.weChatOptions = weChatOptions;
        this.httpClient = vertx.createHttpClient();
    }

    @Override
    public WeChatApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        httpClient.get("", response -> {
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                response.bodyHandler(data -> {
                    succes(handler, data.toJsonObject());
                });
                response.exceptionHandler(e -> {
                    fail(handler, e);
                });
            } else {
                fail(handler, new Not200Exception(statusCode));
            }
        });
        return this;
    }
}
