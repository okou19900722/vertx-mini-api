package tk.okou.vertx.sdk.tencent.qq;

import tk.okou.vertx.sdk.BaseMiniGameApiUrlSupplier;
import tk.okou.sdk.util.MessageFormatUtils;

import java.text.MessageFormat;

public interface QQMiniGameApiUrlSupplier extends QQMiniApiUrlSupplier, BaseMiniGameApiUrlSupplier {
    MessageFormat SET_USER_STORAGE = new MessageFormat("/api/openDataContext/set_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");
    MessageFormat REMOVE_USER_STORAGE = new MessageFormat("/api/openDataContext/remove_user_storage?access_token={0}&signature={1}&openid={2}&sig_method={3}");

    @Override
    default String getUrlOfSetUserStorage(String accessToken, String signature, String openId, String signatureMethod) {
        return MessageFormatUtils.format(SET_USER_STORAGE, accessToken, signature, openId, signatureMethod);
    }

    @Override
    default String getUrlOfRemoveUserStorage(String accessToken, String signature, String openId, String signatureMethod) {
        return MessageFormatUtils.format(REMOVE_USER_STORAGE, accessToken, signature, openId, signatureMethod);
    }
}
