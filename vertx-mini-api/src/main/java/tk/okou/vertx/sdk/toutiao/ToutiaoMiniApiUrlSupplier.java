package tk.okou.vertx.sdk.toutiao;

import java.text.MessageFormat;

import static tk.okou.sdk.util.MessageFormatUtils.format;

public interface ToutiaoMiniApiUrlSupplier {
    MessageFormat CODE_2_SESSION_FORMATTER = new MessageFormat("/mgplatform/api/apps/jscode2session?appid={0}&secret={1}&code={2}&anonymous_code={3}");
    String GET_ACCESS_TOKE = "/mgplatform/api/apps/v2/token";
    MessageFormat SET_USER_STORAGE = new MessageFormat("/mgplatform/api/apps/set_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    MessageFormat REMOVE_USER_STORAGE = new MessageFormat("/mgplatform/api/apps/remove_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");

    default String getUrlOfCode2session(String appId, String secret, String jsCode, String anonymousCode) {
        return format(CODE_2_SESSION_FORMATTER, appId, secret, jsCode, anonymousCode);
    }

    default String getUrlOfGetAccessToken(String grantType, String appId, String secret) {
        return GET_ACCESS_TOKE;
    }

    default String getUrlOfSetUserStorage(String accessToken, String signature, String openId, String signatureMethod) {
        return format(SET_USER_STORAGE, accessToken, signature, openId, signatureMethod);
    }

    default String getUrlOfRemoveUserStorage(String accessToken, String signature, String openId, String signatureMethod) {
        return format(REMOVE_USER_STORAGE, accessToken, signature, openId, signatureMethod);
    }
}
