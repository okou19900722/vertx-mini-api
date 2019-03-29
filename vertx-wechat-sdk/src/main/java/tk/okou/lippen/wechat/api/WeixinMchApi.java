package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.lippen.wechat.api.impl.WeixinMchApiImpl;


/**
 * 微信支付Api，文档:https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_1
 */
@VertxGen
public interface WeixinMchApi extends BaseMiniApi {
    String UNIFIED_ORDER_URI = "/pay/unifiedorder";

    static WeixinMchApi create(Vertx vertx) {
        return new WeixinMchApiImpl(vertx, new WeixinMchOptions());
    }

    static WeixinMchApi create(Vertx vertx, WeixinMchOptions weixinMchOptions) {
        return new WeixinMchApiImpl(vertx, weixinMchOptions);
    }

    /**
     * 统一下单
     *应用场景：商户在小程序中先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易后调起支付。
     *
     *
     */
    @Fluent
    WeixinMchApi unifiedorder(UnifiedOrderParameter parameter, Handler<AsyncResult<JsonObject>> handler);

}
