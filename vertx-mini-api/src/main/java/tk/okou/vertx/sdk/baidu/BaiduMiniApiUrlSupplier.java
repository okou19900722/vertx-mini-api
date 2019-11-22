package tk.okou.vertx.sdk.baidu;

import tk.okou.vertx.sdk.BaseMiniApiUrlSupplier;

import java.text.MessageFormat;

import static tk.okou.sdk.util.MessageFormatUtils.format;

public interface BaiduMiniApiUrlSupplier extends BaseMiniApiUrlSupplier {
    MessageFormat CODE_2_SESSION_FORMATTER = new MessageFormat("/nalogin/getSessionKeyByCode?client_id={0}&sk={1}&code={2}");
    MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/oauth/2.0/token?grant_type={0}&client_id={1}&client_secret={2}&scope=smartapp_snsapi_base");

    @Override
    default String getUrlOfCode2session(String appId, String secret, String jsCode, String grantType) {
        return format(CODE_2_SESSION_FORMATTER, appId, secret, jsCode);
    }

    @Override
    default String getUrlOfGetAccessToken(String grantType, String clientId, String sk) {
        return format(GET_ACCESS_TOKEN, grantType, clientId, sk);
    }
}
