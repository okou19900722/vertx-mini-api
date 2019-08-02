package tk.okou.vertx.sdk;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import tk.okou.sdk.exception.Not200Exception;

public abstract class AbstractMiniApi implements BaseMiniApi, BaseMiniApiUrlSupplier {
    private static final Logger logger = LoggerFactory.getLogger(AbstractMiniApi.class);
    protected final BaseMiniApiOptions options;
    protected final HttpClient httpClient;

    public AbstractMiniApi(Vertx vertx, BaseMiniApiOptions options) {
        this.options = options;
        this.httpClient = vertx.createHttpClient(options);
    }

    @Fluent
    public AbstractMiniApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2session(appId, secret, jsCode, "authorization_code", handler);
    }

    @Fluent
    public AbstractMiniApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        String url = getUrlOfCode2session(appId, secret, jsCode, grantType);
        get(url, handler);
        return this;
    }

    @Fluent
    public AbstractMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }

    @Fluent
    public AbstractMiniApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        String url = getUrlOfGetAccessToken(grantType, appId, secret);
        get(url, handler);
        return this;
    }

    private void get(String uri, Handler<AsyncResult<JsonObject>> handler) {
        HttpClientRequest request = httpClient.get(uri, responseHandler(handler));
        if (options.getTimeout() != null) {
            request.setTimeout(options.getTimeout());
        }
        request.exceptionHandler(e -> fail(handler, e))
                .end();
    }

    private Handler<AsyncResult<HttpClientResponse>> responseHandler(Handler<AsyncResult<JsonObject>> handler){
        return ar -> {
            if (ar.failed()) {
                fail(handler, ar.cause());
            } else {
                HttpClientResponse response = ar.result();
                int statusCode = response.statusCode();
                if (statusCode == 200) {
                    response.bodyHandler(body -> {
                        JsonObject data = body.toJsonObject();
                        Integer errcode = data.getInteger("errcode");
                        if (errcode != null && errcode != 0) {
                            if (errcode == 40163) {
                                logger.warn("code been used");
                            } else {
                                logger.error(response.request().uri() + " - " + data);
                            }
                        }
                        success(handler, body.toJsonObject());
                    });
                    response.exceptionHandler(e -> logger.error("response handler fail", e));
                } else {
                    fail(handler, new Not200Exception(statusCode));
                }
            }
        };
    }

    protected void post(String uri, String data, Handler<AsyncResult<JsonObject>> handler) {
        HttpClientRequest request = httpClient.post(uri, responseHandler(handler));
        if (options.getTimeout() != null) {
            request.setTimeout(options.getTimeout());
        }
        request.exceptionHandler(e -> fail(handler, e))
                .end(data);
    }
}
