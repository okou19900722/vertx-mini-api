package tk.okou.vertx.sdk;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.exception.Not200Exception;

import java.util.function.Function;

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
        code2session(appId, secret, jsCode).onComplete(handler);
        return this;
    }

    @Override
    public Future<JsonObject> code2session(String appId, String secret, String jsCode) {
        return this.code2session(appId, secret, jsCode, "authorization_code");
    }

    @Fluent
    public AbstractMiniApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        this.code2session(appId, secret, jsCode, grantType).onComplete(handler);
        return this;
    }

    @Override
    public Future<JsonObject> code2session(String appId, String secret, String jsCode, String grantType) {
        String url = getUrlOfCode2session(appId, secret, jsCode, grantType);
        return getJsonObject(url);
    }

    @Fluent
    public AbstractMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        getAccessToken(appId, secret).onComplete(handler);
        return this;
    }

    @Override
    public Future<JsonObject> getAccessToken(String appId, String secret) {
        return getAccessToken(appId, secret, "client_credential");
    }

    @Fluent
    public AbstractMiniApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        this.getAccessToken(grantType, appId, secret).onComplete(handler);
        return this;
    }

    @Override
    public Future<JsonObject> getAccessToken(String grantType, String appId, String secret) {
        String url = getUrlOfGetAccessToken(grantType, appId, secret);
        return getJsonObject(url);
    }

    private Future<JsonObject> getJsonObject(String uri) {
        return this.request(uri, HttpMethod.GET, HttpClientRequest::send).compose(this.wrapToJson());
    }

    protected Future<JsonObject> postJsonObject(String uri, String data) {
        return this.post(uri, data).compose(this.wrapToJson());
    }

    protected Future<HttpClientResponse> post(String uri, String data) {
        return this.request(uri, HttpMethod.POST, request -> request.send(data));
    }

    private Function<HttpClientResponse, Future<JsonObject>> wrapToJson() {
        return response -> {
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return response.body().map(body -> {
                    JsonObject json = body.toJsonObject();
                    Integer errcode = json.getInteger("errcode");
                    if (errcode != null && errcode != 0) {
                        if (errcode == 40163) {
                            logger.warn("code been used");
                        } else {
                            logger.error(response.request().getURI() + " - " + json);
                        }
                    }
                    return json;
                });
            } else {
                return Future.failedFuture(new Not200Exception(statusCode));
            }
        };
    }

    protected Future<HttpClientResponse> request(String uri, HttpMethod httpMethod, Function<HttpClientRequest, Future<HttpClientResponse>> sender) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setMethod(httpMethod);
        requestOptions.setURI(uri);
        if (options.getTimeout() != null) {
            requestOptions.setTimeout(options.getTimeout());
        }
        return httpClient.request(requestOptions).compose(sender);
    }
}
