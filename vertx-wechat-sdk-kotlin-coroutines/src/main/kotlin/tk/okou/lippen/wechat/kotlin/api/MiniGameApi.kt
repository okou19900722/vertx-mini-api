package tk.okou.lippen.wechat.kotlin.api

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.lippen.wechat.api.MiniGameApi
import tk.okou.lippen.wechat.api.model.KVData

/**
 *
 * @param appId 
 * @param secret 
 * @param jsCode 
 * @param handler 
 * @return *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.code2accessTokenAwait(appId : String, secret : String, jsCode : String) : JsonObject {
  return awaitResult{
    this.code2accessToken(appId, secret, jsCode, it)
  }
}

/**
 * 登录凭证校验，开发者服务器使用 临时登录凭证code 获取 session_key 和 openid 等。
 * 文档:https://developers.weixin.qq.com/minigame/dev/document/open-api/login/code2accessToken.html
 *
 * @param appId 小程序 appId
 * @param secret 小程序 appSecret
 * @param jsCode 登录时获取的 cod
 * @param grantType 
 * @param handler 登录凭证校验成功之后的回调
 * @return返回自己，方便更fluent的调用 *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.code2accessTokenAwait(appId : String, secret : String, jsCode : String, grantType : String) : JsonObject {
  return awaitResult{
    this.code2accessToken(appId, secret, jsCode, grantType, it)
  }
}

/**
 * 获取小程序 access_token. access_token 介绍详见 https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
 * 文档https://developers.weixin.qq.com/minigame/dev/document/open-api/access-token/getAccessToken.html
 *
 * @param grantType 填写 client_credential
 * @param appId 小程序 appId
 * @param secret 小程序 appSecret
 * @param handler 回调
 * @return返回自己，方便更fluent的调用 *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.getAccessTokenAwait(grantType : String, appId : String, secret : String) : JsonObject {
  return awaitResult{
    this.getAccessToken(grantType, appId, secret, it)
  }
}

/**
 *
 * @param appId 
 * @param secret 
 * @param handler 
 * @return *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.getAccessTokenAwait(appId : String, secret : String) : JsonObject {
  return awaitResult{
    this.getAccessToken(appId, secret, it)
  }
}

/**
 * like [tk.okou.lippen.wechat.api.MiniGameApi] 但是使用默认的签名方法
 *
 * @param accessToken 
 * @param openId 
 * @param appId 
 * @param sessionKey 
 * @param kvList 
 * @param handler 
 * @return *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.setUserStorageAwait(accessToken : String, openId : String, appId : String, sessionKey : String, kvList : List<KVData>) : JsonObject {
  return awaitResult{
    this.setUserStorage(accessToken, openId, appId, sessionKey, kvList, it)
  }
}

/**
 * like [tk.okou.lippen.wechat.api.MiniGameApi] 但是使用默认的签名方法
 *
 * @param accessToken 
 * @param openId 
 * @param sessionKey 
 * @param keys 
 * @param handler 
 * @return *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.removeUserStorageAwait(accessToken : String, openId : String, sessionKey : String, keys : List<String>) : JsonObject {
  return awaitResult{
    this.removeUserStorage(accessToken, openId, sessionKey, keys, it)
  }
}

