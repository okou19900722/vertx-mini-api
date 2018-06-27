package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import tk.okou.lippen.wechat.api.impl.MiniGameApiImpl;
import tk.okou.lippen.wechat.api.model.KVData;
import tk.okou.lippen.wechat.api.util.SignatureMethod;

import java.text.MessageFormat;
import java.util.List;

/**
 * 小游戏Api，文档:https://developers.weixin.qq.com/minigame/dev/index.html
 */
@VertxGen
public interface MiniGameApi extends BaseApi {
    MessageFormat CODE_2_ACCESS_TOKEN_FORMATTER = new MessageFormat("/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}");
    MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}");
    MessageFormat SET_USER_STORAGE = new MessageFormat("/wxa/set_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    MessageFormat REMOVE_USER_STORAGE = new MessageFormat("/wxa/remove_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");

    static MiniGameApi create(Vertx vertx) {
        return new MiniGameApiImpl(vertx, new MiniGameOptions());
    }

    static MiniGameApi create(Vertx vertx, MiniGameOptions miniGameOptions) {
        return new MiniGameApiImpl(vertx, miniGameOptions);
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
    MiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @see #getAccessToken(String, String, String, Handler) grantType 默认使用 client_credential
     */
    @Fluent
    MiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @see #setUserStorage(String, String, String, String, SignatureMethod, JsonArray, Handler)
     */
    @GenIgnore
    MiniGameApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, String kvList, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @see #setUserStorage(String, String, String, String, SignatureMethod, JsonArray, Handler)
     */
    @GenIgnore
    MiniGameApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @param accessToken     接口调用凭证
     * @param openId          用户唯一标识符
     * @param appId           小程序 appId
     * @param sessionKey      会话密钥
     * @param signatureMethod 签名的方法
     * @param kvList          数据
     * @param handler         回调
     * @return 返回自己，方便更fluent的调用
     */
    @GenIgnore
    MiniGameApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, JsonArray kvList, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @see #removeUserStorage(String, String, String, String, SignatureMethod, JsonArray, Handler)
     */
    @GenIgnore
    MiniGameApi removeUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, String key, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @see #removeUserStorage(String, String, String, String, SignatureMethod, JsonArray, Handler)
     */
    @GenIgnore
    MiniGameApi removeUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @param accessToken     接口调用凭证
     * @param openId          用户唯一标识符
     * @param appId           小程序 appId
     * @param sessionKey      会话密钥
     * @param signatureMethod 签名的方法
     * @param key             要删除的key列表
     * @param handler         回调
     * @return 返回自己，方便更fluent的调用
     */
    @GenIgnore
    MiniGameApi removeUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, JsonArray key, Handler<AsyncResult<JsonObject>> handler);
}
