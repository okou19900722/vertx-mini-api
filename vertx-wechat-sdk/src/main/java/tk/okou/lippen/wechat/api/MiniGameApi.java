package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
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
public interface MiniGameApi extends BaseMiniApi {
    @GenIgnore
    MessageFormat CHECK_SESSION_KEY = new MessageFormat("/wxa/checksession?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    @GenIgnore
    MessageFormat SET_USER_STORAGE = new MessageFormat("/wxa/set_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    @GenIgnore
    MessageFormat REMOVE_USER_STORAGE = new MessageFormat("/wxa/remove_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    @GenIgnore
    MessageFormat QRCODE_A = new MessageFormat("/wxa/getwxacode?access_token={0}");
    @GenIgnore
    MessageFormat QRCODE_B = new MessageFormat("/wxa/getwxacodeunlimit?access_token={0}");
    @GenIgnore
    MessageFormat QRCODE_C = new MessageFormat("/cgi-bin/wxaapp/createwxaqrcode?access_token={0}");

    static MiniGameApi create(Vertx vertx) {
        return new MiniGameApiImpl(vertx, new MiniGameOptions());
    }

    static MiniGameApi create(Vertx vertx, MiniGameOptions miniGameOptions) {
        return new MiniGameApiImpl(vertx, miniGameOptions);
    }
    @Fluent
    MiniGameApi checkSessionKey(String accessToken, String sessionKey, String openId, SignatureMethod method);

    @Fluent
    default MiniGameApi code2accessToken(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2accessToken(appId, secret, jsCode, "authorization_code", handler);
    }

    @Fluent
    MiniGameApi code2accessToken(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    MiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    default MiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }


    @Fluent
    MiniGameApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

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
    MiniGameApi setUserStorage(String accessToken, String openId, String appId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    /**
     * like {@link #removeUserStorage(String, String, String, SignatureMethod, List, Handler)} 但是使用默认的签名方法{@link SignatureMethod#HMAC_SHA256}
     *
     * @see #removeUserStorage(String, String, String, SignatureMethod, List, Handler)
     */
    @Fluent
    MiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @param accessToken     接口调用凭证
     * @param openId          用户唯一标识符
     * @param sessionKey      会话密钥
     * @param signatureMethod 签名的方法
     * @param key             要删除的key列表
     * @param handler         回调
     */
    @GenIgnore
    MiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @param accessToken       接口调用凭证
     * @param path              跳转的页面路径
     * @param width             二维码的宽度
     * @param auto_color        自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
     * @param line_color_r      auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
     * @param line_color_g      auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
     * @param line_color_b      auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
     * @param is_hyaline        是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
     * @param successHandler    成功回调(buffer为图片数据)
     * @param failHandler       失败回调
     * */
    @Fluent
    MiniGameApi getwxacode(String accessToken, String path, Integer width, Boolean auto_color, Integer line_color_r, Integer line_color_g, Integer line_color_b, Boolean is_hyaline, Handler<AsyncResult<Buffer>> successHandler, Handler<AsyncResult<JsonObject>> failHandler);

    /**
     * @param accessToken       接口调用凭证
     * @param scene             小程序码入口参数(通过options获取)
     * @param page              跳转的页面路径(如果不填写这个字段，默认跳主页面)
     * @param width             二维码的宽度
     * @param auto_color        自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
     * @param line_color_r      auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
     * @param line_color_g      auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
     * @param line_color_b      auth_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"},十进制表示
     * @param is_hyaline        是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
     * @param successHandler    成功回调(buffer为图片数据)
     * @param failHandler       失败回调
     * */
    @Fluent
    MiniGameApi getwxacodeunlimit(String accessToken, String scene, String page, Integer width, Boolean auto_color, Integer line_color_r, Integer line_color_g, Integer line_color_b, Boolean is_hyaline, Handler<AsyncResult<Buffer>> successHandler, Handler<AsyncResult<JsonObject>> failHandler);

    /**
     * @param accessToken       接口调用凭证
     * @param path              跳转的页面路径
     * @param width             二维码的宽度
     * @param successHandler    成功回调(buffer为图片数据)
     * @param failHandler       失败回调
     * */
    @Fluent
    MiniGameApi createwxaqrcode(String accessToken, String path, String width, Handler<AsyncResult<Buffer>> successHandler, Handler<AsyncResult<JsonObject>> failHandler);

}
