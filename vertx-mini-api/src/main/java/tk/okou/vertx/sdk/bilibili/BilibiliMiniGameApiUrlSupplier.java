package tk.okou.vertx.sdk.bilibili;

import java.text.MessageFormat;

import static tk.okou.sdk.util.MessageFormatUtils.format;

public interface BilibiliMiniGameApiUrlSupplier {
    MessageFormat CODE_2_SESSION_FORMATTER = new MessageFormat("/api/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}");
    MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/api/cgi-bin/token?grant_type={0}&appid={1}&secret={2}");

    default String getUrlOfCode2session(String appId, String secret, String jsCode, String grantType) {
        return format(CODE_2_SESSION_FORMATTER, appId, secret, jsCode, grantType);
    }

    default String getUrlOfGetAccessToken(String grantType, String appId, String secret) {
        return format(GET_ACCESS_TOKEN, grantType, appId, secret);
    }
}
