package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

@VertxGen
public interface BaseApi {
    default void fail(Handler<AsyncResult<JsonObject>> handler, Throwable throwable) {
        handler.handle(Future.failedFuture(throwable));
    }

    default void fail(Handler<AsyncResult<JsonObject>> handler, String message) {
        handler.handle(Future.failedFuture(message));
    }

    default <T> void succes(Handler<AsyncResult<T>> handler, T result) {
        handler.handle(Future.succeededFuture(result));
    }
}
