package tk.okou.vertx.sdk.ext

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi
import java.time.LocalDate


/**
 * Suspending version of method [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi.getGameGiftList]
 *
 * @param accessToken
 * @param page
 * @param pageSize
 * @param startDate
 * @param endDate
 * @return [JsonObject]
 *
 * NOTE: This function has been automatically generated from [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi] using Vert.x codegen.
 */
suspend fun WechatMiniGameApi.getGameGiftListAwait(accessToken: String, page: Int, pageSize: Int, startDate: LocalDate, endDate: LocalDate): JsonObject {
    return awaitResult {
        this.getGameGiftList(accessToken, page, pageSize, startDate, endDate, it)
    }
}

/**
 * Suspending version of method [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi.getPublisherAdPosGeneral]
 *
 * @param accessToken
 * @param page
 * @param pageSize
 * @param startDate
 * @param endDate
 * @param adSlot
 * @return [JsonObject]
 *
 * NOTE: This function has been automatically generated from [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi] using Vert.x codegen.
 */
suspend fun WechatMiniGameApi.getPublisherAdPosGeneralAwait(accessToken: String, page: Int, pageSize: Int, startDate: LocalDate, endDate: LocalDate, adSlot: String?): JsonObject {
    return awaitResult {
        this.getPublisherAdPosGeneral(accessToken, page, pageSize, startDate, endDate, adSlot, it)
    }
}

/**
 * Suspending version of method [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi.getPublisherAdUnitGeneral]
 *
 * @param accessToken
 * @param page
 * @param pageSize
 * @param startDate
 * @param endDate
 * @param adSlot
 * @param adUnitId
 * @return [JsonObject]
 *
 * NOTE: This function has been automatically generated from [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi] using Vert.x codegen.
 */
suspend fun WechatMiniGameApi.getPublisherAdUnitGeneralAwait(accessToken: String, page: Int, pageSize: Int, startDate: LocalDate, endDate: LocalDate, adSlot: String?, adUnitId: String?): JsonObject {
    return awaitResult {
        this.getPublisherAdUnitGeneral(accessToken, page, pageSize, startDate, endDate, adSlot, adUnitId, it)
    }
}

/**
 * Suspending version of method [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi.getPublisherSettlement]
 *
 * @param accessToken
 * @param page
 * @param pageSize
 * @param startDate
 * @param endDate
 * @return [JsonObject]
 *
 * NOTE: This function has been automatically generated from [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi] using Vert.x codegen.
 */
suspend fun WechatMiniGameApi.getPublisherSettlementAwait(accessToken: String, page: Int, pageSize: Int, startDate: LocalDate, endDate: LocalDate): JsonObject {
    return awaitResult {
        this.getPublisherSettlement(accessToken, page, pageSize, startDate, endDate, it)
    }
}


/**
 * Suspending version of method [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi.getMonetizeDailyData]
 *
 * @param appId
 * @param accessToken
 * @param startDate
 * @param endDate
 * @return [JsonObject]
 *
 * NOTE: This function has been automatically generated from [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi] using Vert.x codegen.
 */
suspend fun WechatMiniGameApi.getMonetizeDailyDataAwait(appId: String, accessToken: String, startDate: LocalDate, endDate: LocalDate): JsonObject {
    return awaitResult {
        this.getMonetizeDailyData(appId, accessToken, startDate, endDate, it)
    }
}

/**
 * Suspending version of method [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi.getMonetizeTraceData]
 *
 * @param appId
 * @param accessToken
 * @param startDate
 * @param endDate
 * @return [JsonObject]
 *
 * NOTE: This function has been automatically generated from [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi] using Vert.x codegen.
 */
suspend fun WechatMiniGameApi.getMonetizeTraceDataAwait(appId: String, accessToken: String, startDate: LocalDate, endDate: LocalDate): JsonObject {
    return awaitResult {
        this.getMonetizeTraceData(appId, accessToken, startDate, endDate, it)
    }
}

