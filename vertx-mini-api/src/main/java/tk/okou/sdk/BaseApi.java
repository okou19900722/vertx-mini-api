package tk.okou.sdk;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

public interface BaseApi {
    default <T> void fail(Handler<AsyncResult<T>> handler, Throwable throwable) {
        handler.handle(Future.failedFuture(throwable));
    }

    default <T> void fail(Handler<AsyncResult<T>> handler, String message) {
        handler.handle(Future.failedFuture(message));
    }

    default <T> void success(Handler<AsyncResult<T>> handler, T result) {
        handler.handle(Future.succeededFuture(result));
    }
}
