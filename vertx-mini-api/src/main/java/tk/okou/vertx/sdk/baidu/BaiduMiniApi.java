package tk.okou.vertx.sdk.baidu;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApi;

public interface BaiduMiniApi extends BaseMiniApi {
    @Fluent
    BaiduMiniApi code2session(String clientId, String sk, String code, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaiduMiniApi code2session(String clientId, String sk, String code, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaiduMiniApi getAccessToken(String clientId, String sk, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    BaiduMiniApi getAccessToken(String grantType, String clientId, String sk, Handler<AsyncResult<JsonObject>> handler);

}
