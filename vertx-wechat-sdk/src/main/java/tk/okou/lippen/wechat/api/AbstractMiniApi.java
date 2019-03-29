package tk.okou.lippen.wechat.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.text.MessageFormat;

import static tk.okou.lippen.wechat.api.util.MessageFormatUtils.format;

public class AbstractMiniApi<A extends BaseMiniApi> implements BaseMiniApi {
    private static final Logger logger = LoggerFactory.getLogger(AbstractMiniApi.class);
    protected MessageFormat CODE_2_ACCESS_TOKEN_FORMATTER = new MessageFormat("/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}");
    protected MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}");

    protected final HttpClient httpClient;

    public AbstractMiniApi(Vertx vertx, BaseMiniOptions miniGameOptions) {
        HttpClientOptions httpClientOptions = miniGameOptions.getApiHttpsClientOptions();
        this.httpClient = vertx.createHttpClient(httpClientOptions);
    }

    public A code2accessToken(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        get(format(CODE_2_ACCESS_TOKEN_FORMATTER, appId, secret, jsCode, grantType), handler);
        //noinspection unchecked
        return (A)this;
    }

    public A getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        get(format(GET_ACCESS_TOKEN, appId, secret), handler);
        //noinspection unchecked
        return (A)this;
    }

    protected void get(String uri, Handler<AsyncResult<JsonObject>> handler) {
        httpClient.get(uri, responseHandler(handler))
                /*.setTimeout(1000)*/
                .exceptionHandler(e -> fail(handler, e))
                .end();
    }

    protected Handler<HttpClientResponse> responseHandler(Handler<AsyncResult<JsonObject>> handler) {
        return response -> {
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                response.bodyHandler(body -> {
                    JsonObject data = body.toJsonObject();
                    Integer errcode = data.getInteger("errcode");
                    if (errcode != null && errcode != 0) {
                        logger.error(response.request().uri() + " - " + data);
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
        httpClient.post(uri, responseHandler(handler))
                /*.setTimeout(1000)*/
                .exceptionHandler(e -> fail(handler, e))
                .end(data);
    }
}
