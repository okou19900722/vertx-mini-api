package tk.okou.lippen.wechat.kotlin.api

import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.lippen.wechat.api.MiniGameApi
import tk.okou.lippen.wechat.api.model.KVData

/**
 *
 * @param appId 
 * @param secret 
 * @param jsCode 
 * @return  *
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
 * @return 返回自己，方便更fluent的调用 *
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
 * @return 返回自己，方便更fluent的调用 *
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
 * @return  *
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
 * @return  *
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
 * @return  *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.removeUserStorageAwait(accessToken : String, openId : String, sessionKey : String, keys : List<String>) : JsonObject {
  return awaitResult{
    this.removeUserStorage(accessToken, openId, sessionKey, keys, it)
  }
}

/**
 *
 * @param accessToken 接口调用凭证
 * @param path 跳转的页面路径
 * @param width 二维码的宽度
 * @param auto_color 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
 * @param line_color_r auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
 * @param line_color_g auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
 * @param line_color_b auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
 * @param is_hyaline 是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
 * @param successHandler 成功回调(buffer为图片数据)
 * @return  *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.getwxacodeAwait(accessToken : String, path : String, width : Int, auto_color : Boolean, line_color_r : Int, line_color_g : Int, line_color_b : Int, is_hyaline : Boolean, successHandler : Handler<AsyncResult<Buffer>>) : JsonObject {
  return awaitResult{
    this.getwxacode(accessToken, path, width, auto_color, line_color_r, line_color_g, line_color_b, is_hyaline, successHandler, it)
  }
}

/**
 *
 * @param accessToken 接口调用凭证
 * @param scene 小程序码入口参数(通过options获取)
 * @param page 跳转的页面路径(如果不填写这个字段，默认跳主页面)
 * @param width 二维码的宽度
 * @param auto_color 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
 * @param line_color_r auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
 * @param line_color_g auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
 * @param line_color_b auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
 * @param is_hyaline 是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
 * @param successHandler 成功回调(buffer为图片数据)
 * @return  *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.getwxacodeunlimitAwait(accessToken : String, scene : String, page : String, width : Int, auto_color : Boolean, line_color_r : Int, line_color_g : Int, line_color_b : Int, is_hyaline : Boolean, successHandler : Handler<AsyncResult<Buffer>>) : JsonObject {
  return awaitResult{
    this.getwxacodeunlimit(accessToken, scene, page, width, auto_color, line_color_r, line_color_g, line_color_b, is_hyaline, successHandler, it)
  }
}

/**
 *
 * @param accessToken 接口调用凭证
 * @param path 跳转的页面路径
 * @param width 二维码的宽度
 * @param successHandler 成功回调(buffer为图片数据)
 * @return  *
 * <p/>
 * NOTE: This function has been automatically generated from the [tk.okou.lippen.wechat.api.MiniGameApi original] using Vert.x codegen.
 */
suspend fun MiniGameApi.createwxaqrcodeAwait(accessToken : String, path : String, width : String, successHandler : Handler<AsyncResult<Buffer>>) : JsonObject {
  return awaitResult{
    this.createwxaqrcode(accessToken, path, width, successHandler, it)
  }
}

