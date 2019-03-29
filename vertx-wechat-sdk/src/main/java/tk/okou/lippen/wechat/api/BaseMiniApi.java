package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

public interface BaseMiniApi {
    default void fail(Handler<AsyncResult<JsonObject>> handler, Throwable throwable) {
        handler.handle(Future.failedFuture(throwable));
    }

    default void fail(Handler<AsyncResult<JsonObject>> handler, String message) {
        handler.handle(Future.failedFuture(message));
    }

    default <T> void success(Handler<AsyncResult<T>> handler, T result) {
        handler.handle(Future.succeededFuture(result));
    }

    /**
     * @see #code2accessToken(String, String, String, String, Handler) grantType默认使用authorization_code
     */
    @Fluent
    default BaseMiniApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2accessToken(appId, secret, jsCode, "authorization_code", handler);
    }

    /**
     * 登录凭证校验，开发者服务器使用 临时登录凭证code 获取 session_key 和 openid 等。
     * 文档:https://developers.weixin.qq.com/minigame/dev/document/open-api/login/code2accessToken.html
     *
     * @param appId   小程序 appId
     * @param secret  小程序 appSecret
     * @param jsCode  登录时获取的 cod
     * @param handler 登录凭证校验成功之后的回调
     * @return 返回自己，方便更fluent的调用
     */
    @Fluent
    BaseMiniApi code2accessToken(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    /**
     * 获取小程序 access_token. access_token 介绍详见 https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
     * 文档https://developers.weixin.qq.com/minigame/dev/document/open-api/access-token/getAccessToken.html
     *
     * @param grantType 填写 client_credential
     * @param appId     小程序 appId
     * @param secret    小程序 appSecret
     * @param handler   回调
     * @return 返回自己，方便更fluent的调用
     */
    @Fluent
    BaseMiniApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @see #getAccessToken(String, String, String, Handler) grantType 默认使用 client_credential
     */
    @Fluent
    default BaseMiniApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }
}
