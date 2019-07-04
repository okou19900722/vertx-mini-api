package tk.okou.vertx.sdk.tencent.qq;

import tk.okou.vertx.sdk.BaseMiniApiUrlSupplier;
import tk.okou.sdk.util.MessageFormatUtils;

import java.text.MessageFormat;

public interface QQMiniApiUrlSupplier extends BaseMiniApiUrlSupplier {
    MessageFormat CODE_2_SESSION_FORMATTER = new MessageFormat("/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}");
    MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/api/getToken?grant_type={0}&appid={1}&secret={2}");

    default String getUrlOfCode2session(String appId, String secret, String jsCode, String grantType) {
        return MessageFormatUtils.format(CODE_2_SESSION_FORMATTER, appId, secret, jsCode, grantType);
    }

    default String getUrlOfGetAccessToken(String grantType, String appId, String secret) {
        return MessageFormatUtils.format(GET_ACCESS_TOKEN, grantType, appId, secret);
    }
}
