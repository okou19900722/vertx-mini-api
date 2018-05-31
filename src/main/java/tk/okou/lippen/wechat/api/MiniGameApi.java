package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.lippen.wechat.api.impl.MiniGameApiImpl;

import java.text.MessageFormat;

/**
 * 小游戏Api，文档:https://developers.weixin.qq.com/minigame/dev/index.html
 */
@SuppressWarnings("UnusedReturnValue")
@VertxGen
public interface MiniGameApi extends BaseApi {
    String DEFAULT_GRANT_TYPE = "authorization_code";
    MessageFormat CODE_2_ACCESS_TOKEN_FORMATTER = new MessageFormat("/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code");
    MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}");
    MessageFormat SET_USER_STORAGE = new MessageFormat("/wxa/set_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    MessageFormat REMOVE_USER_STORAGE = new MessageFormat("/wxa/remove_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");

    static MiniGameApi create(Vertx vertx) {
        return new MiniGameApiImpl(vertx, new WeChatOptions());
    }

    static MiniGameApi create(Vertx vertx, WeChatOptions weChatOptions) {
        return new MiniGameApiImpl(vertx, weChatOptions);
    }

    /**
     * @see #code2accessToken(String, String, String, String, Handler) grantType默认使用authorization_code
     */
    @Fluent
    MiniGameApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler);

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
    MiniGameApi code2accessToken(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);
}
