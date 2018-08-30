package tk.okou.lippen.wechat.kotlin.api

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.lippen.wechat.api.UnifiedOrderParameter
import tk.okou.lippen.wechat.api.WeixinMchApi

/**
 * 统一下单
 *应用场景：商户在小程序中先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易后调起支付。
 *
 *
 * @param parameter 
 * @param handler 
 * @return *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.WeixinMchApi original] using Vert.x codegen.
 */
suspend fun WeixinMchApi.unifiedorderAwait(parameter : UnifiedOrderParameter) : JsonObject {
  return awaitResult{
    this.unifiedorder(parameter, it)
  }
}

