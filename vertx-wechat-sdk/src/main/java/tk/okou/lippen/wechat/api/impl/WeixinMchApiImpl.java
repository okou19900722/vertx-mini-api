package tk.okou.lippen.wechat.api.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import tk.okou.lippen.wechat.api.Not200Exception;
import tk.okou.lippen.wechat.api.UnifiedOrderParameter;
import tk.okou.lippen.wechat.api.WeixinMchApi;
import tk.okou.lippen.wechat.api.WeixinMchOptions;
import tk.okou.lippen.wechat.api.util.XmlUtil;

public class WeixinMchApiImpl implements WeixinMchApi {
    private static final Logger logger = LoggerFactory.getLogger(WeixinMchApiImpl.class);
    private final HttpClient httpClient;

    public WeixinMchApiImpl(Vertx vertx, WeixinMchOptions weixinMchOptions) {
        HttpClientOptions httpClientOptions = weixinMchOptions.getApiHttpsClientOptions();
        this.httpClient = vertx.createHttpClient(httpClientOptions);
    }

    @Override
    public WeixinMchApi unifiedorder(UnifiedOrderParameter parameter, Handler<AsyncResult<JsonObject>> handler) {
        post(UNIFIED_ORDER_URI, parameter.toPostXml(), handler);
        return this;
    }

    private void post(String uri, String data, Handler<AsyncResult<JsonObject>> handler) {
        Handler<HttpClientResponse> responseHandler = response -> {
            response.exceptionHandler(e -> {
                logger.info("response handler fail");
                fail(handler, "response exception statusCode="+response.statusCode());
            });
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                response.bodyHandler(body -> {
                    //把xml转成json
                    JsonObject json = XmlUtil.xml2Json(body.toString());
                    if (json == null){
                        logger.info("xml解析出错===>");
                        fail(handler, "xml解析出错");
                    }else {
                        succes(handler, json);
                    }
                });

            } else {
                fail(handler, new Not200Exception(statusCode));
            }
        };

        httpClient.post(uri, responseHandler)
                /*.setTimeout(1000)*/
                .exceptionHandler(e -> logger.info(e.getStackTrace()))
                .end(data);
    }
}
