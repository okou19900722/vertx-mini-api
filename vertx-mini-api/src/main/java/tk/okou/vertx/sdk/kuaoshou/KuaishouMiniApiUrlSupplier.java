package tk.okou.vertx.sdk.kuaoshou;

import tk.okou.vertx.sdk.BaseMiniApiUrlSupplier;

import java.text.MessageFormat;

import static tk.okou.sdk.util.MessageFormatUtils.format;

public interface KuaishouMiniApiUrlSupplier extends BaseMiniApiUrlSupplier {
    String CODE_2_SESSION_URL = "/oauth2/mp/code2session";
    MessageFormat CODE_2_SESSION_FORMATTER = new MessageFormat("/oauth2/mp/code2session?app_id={0}&app_secret={1}&js_code={2}");
    MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/oauth2/access_token?grant_type={0}&app_id={1}&app_secret={2}&scope=smartapp_snsapi_base");

    @Override
    default String getUrlOfCode2session(String appId, String secret, String jsCode, String grantType) {
        return format(CODE_2_SESSION_FORMATTER, appId, secret, jsCode);
    }

    @Override
    default String getUrlOfGetAccessToken(String grantType, String appId, String secret) {
        return format(GET_ACCESS_TOKEN, grantType, appId, secret);
    }
}
