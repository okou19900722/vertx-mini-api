/*
 * Copyright 2019 Red Hat, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * The Apache License v2.0 is available at
 * http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package tk.okou.vertx.sdk.kotlin.tencent.wechat

import io.vertx.core.buffer.Buffer
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.sdk.util.SignatureMethod
import tk.okou.vertx.sdk.model.KVData
import tk.okou.vertx.sdk.tencent.wechat.Color
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi

suspend fun WechatMiniGameApi.code2sessionAwait(appId: String, secret: String, jsCode: String): JsonObject {
  return awaitResult {
    this.code2session(appId, secret, jsCode, it)
  }
}

suspend fun WechatMiniGameApi.code2sessionAwait(appId: String, secret: String, jsCode: String, grantType: String): JsonObject {
  return awaitResult {
    this.code2session(appId, secret, jsCode, grantType, it)
  }
}

suspend fun WechatMiniGameApi.getAccessTokenAwait(appId: String, secret: String): JsonObject {
  return awaitResult {
    this.getAccessToken(appId, secret, it)
  }
}

suspend fun WechatMiniGameApi.getAccessTokenAwait(grantType: String, appId: String, secret: String): JsonObject {
  return awaitResult {
    this.getAccessToken(grantType, appId, secret, it)
  }
}

suspend fun WechatMiniGameApi.setUserStorageAwait(accessToken: String, openId: String, sessionKey: String, kvList: List<KVData>): JsonObject {
  return awaitResult {
    this.setUserStorage(accessToken, openId, sessionKey, kvList, it)
  }
}

suspend fun WechatMiniGameApi.setUserStorageAwait(accessToken: String, openId: String, sessionKey: String, signatureMethod: SignatureMethod, kvList: List<KVData>): JsonObject {
  return awaitResult {
    this.setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList, it)
  }
}

suspend fun WechatMiniGameApi.removeUserStorageAwait(accessToken: String, openId: String, sessionKey: String, keys: List<String>): JsonObject {
  return awaitResult {
    this.removeUserStorage(accessToken, openId, sessionKey, keys, it)
  }
}

suspend fun WechatMiniGameApi.removeUserStorageAwait(accessToken: String, openId: String, sessionKey: String, signatureMethod: SignatureMethod, key: List<String>): JsonObject {
  return awaitResult {
    this.removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key, it)
  }
}

suspend fun WechatMiniGameApi.getWXACodeAwait(accessToken: String, path: String, width: Int, autoColor: Boolean, lineColor: Color, isHyaline: Boolean, successConsumer: (Buffer) -> Unit): JsonObject {
  return awaitResult {
    this.getWXACode(accessToken, path, width, autoColor, lineColor, isHyaline, successConsumer, it::handle)
  }
}

suspend fun WechatMiniGameApi.getWXACodeUnlimitedAwait(accessToken: String, scene: String, page: String, width: Int, autoColor: Boolean, lineColor: Color, isHyaline: Boolean, successConsumer: (Buffer) -> Unit): JsonObject {
  return awaitResult {
    this.getWXACodeUnlimited(accessToken, scene, page, width, autoColor, lineColor, isHyaline, successConsumer, it::handle)
  }
}

suspend fun WechatMiniGameApi.createWXAQRCodeAwait(accessToken: String, path: String, width: String, successConsumer: (Buffer) -> Unit): JsonObject {
  return awaitResult {
    this.createWXAQRCode(accessToken, path, width, successConsumer, it::handle)
  }
}

suspend fun WechatMiniGameApi.sendSubscriptionMessageAwait(accessToken: String, toUserOpenId: String, templateId: String, data: JsonObject, page: String?, miniprogramState: String?, lang: String?): JsonObject {
  return awaitResult {
    this.sendSubscriptionMessage(accessToken, toUserOpenId, templateId, data, page, miniprogramState, lang, it)
  }
}

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
suspend fun WechatMiniGameApi.getGameGiftListAwait(accessToken: String, page: Int, pageSize: Int, startDate: String, endDate: String): JsonObject {
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
suspend fun WechatMiniGameApi.getPublisherAdPosGeneralAwait(accessToken: String, page: Int, pageSize: Int, startDate: String, endDate: String, adSlot: String?): JsonObject {
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
suspend fun WechatMiniGameApi.getPublisherAdUnitGeneralAwait(accessToken: String, page: Int, pageSize: Int, startDate: String, endDate: String, adSlot: String?, adUnitId: String?): JsonObject {
  return awaitResult {
    this.getPublisherAdUnitGeneral(accessToken, page, pageSize, startDate, endDate, adSlot, adUnitId, it)
  }
}

/**
 * Suspending version of method [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi.getAdUnitList]
 *
 * @param accessToken 
 * @param page 
 * @param pageSize 
 * @param adSlot 
 * @param adUnitId 
 * @return [JsonObject]
 *
 * NOTE: This function has been automatically generated from [tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi] using Vert.x codegen.
 */
suspend fun WechatMiniGameApi.getAdUnitListAwait(accessToken: String, page: Int, pageSize: Int, adSlot: String?, adUnitId: String?): JsonObject {
  return awaitResult {
    this.getAdUnitList(accessToken, page, pageSize, adSlot, adUnitId, it)
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
suspend fun WechatMiniGameApi.getPublisherSettlementAwait(accessToken: String, page: Int, pageSize: Int, startDate: String, endDate: String): JsonObject {
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
suspend fun WechatMiniGameApi.getMonetizeDailyDataAwait(appId: String, accessToken: String, startDate: String, endDate: String): JsonObject {
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
suspend fun WechatMiniGameApi.getMonetizeTraceDataAwait(appId: String, accessToken: String, startDate: String, endDate: String): JsonObject {
  return awaitResult {
    this.getMonetizeTraceData(appId, accessToken, startDate, endDate, it)
  }
}

