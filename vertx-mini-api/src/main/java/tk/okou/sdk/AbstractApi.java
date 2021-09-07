package tk.okou.sdk;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import tk.okou.sdk.exception.Not200Exception;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

public abstract class AbstractApi implements BaseApi {
    private static final Logger logger = LoggerFactory.getLogger(AbstractApi.class);
    protected final BaseMiniApiOptions options;
    protected final HttpClient httpClient;

    public AbstractApi(Vertx vertx, BaseMiniApiOptions options) {
        this.options = options;
        this.httpClient = vertx.createHttpClient(options);
    }

    protected void get(String uri, Handler<AsyncResult<JsonObject>> handler) {
        HttpClientRequest request = httpClient.get(uri).handler(responseHandler(handler));
        if (options.getTimeout() != null) {
            request.setTimeout(options.getTimeout());
        }
        request.exceptionHandler(e -> fail(handler, e))
                .end();
    }

    private Handler<HttpClientResponse> responseHandler(Handler<AsyncResult<JsonObject>> handler) {
        return response -> {
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
        };
    }

    protected void post(String uri, String data, Handler<AsyncResult<JsonObject>> handler) {
        this.post(uri, data, null, handler);
    }

    protected void post(String uri, String data, String contentType, Handler<AsyncResult<JsonObject>> handler) {
        HttpClientRequest request = httpClient.post(uri, responseHandler(handler));
        if (contentType != null) {
            request.putHeader(HttpHeaders.CONTENT_TYPE, contentType);
        }
        if (options.getTimeout() != null) {
            request.setTimeout(options.getTimeout());
        }
        request.exceptionHandler(e -> fail(handler, e))
                .end(data);
    }

    protected void doSignature(String secretKey, SignatureMethod signatureMethod, JsonObject data, Function<String, String> urlSupplier, Handler<AsyncResult<JsonObject>> handler) {
        try {
            String postBody = data.encode();
            String signature = signatureMethod.signature(postBody, secretKey);
            String url = urlSupplier.apply(signature);
            post(url, postBody, handler);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            fail(handler, e);
        }
    }
}
