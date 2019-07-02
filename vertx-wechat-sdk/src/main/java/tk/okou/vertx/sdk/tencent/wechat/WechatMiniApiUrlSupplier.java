package tk.okou.vertx.sdk.tencent.wechat;

import tk.okou.vertx.sdk.tencent.BaseMiniApiUrlSupplier;
import tk.okou.sdk.util.MessageFormatUtils;

import java.text.MessageFormat;

public interface WechatMiniApiUrlSupplier extends BaseMiniApiUrlSupplier {
    MessageFormat CODE_2_ACCESS_TOKEN_FORMATTER = new MessageFormat("/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}");
    MessageFormat GET_ACCESS_TOKEN = new MessageFormat("/cgi-bin/token?grant_type={0}&appid={1}&secret={2}");


    default String getUrlOfCode2accessToken(String appId, String secret, String jsCode, String grantType) {
        return MessageFormatUtils.format(CODE_2_ACCESS_TOKEN_FORMATTER, appId, secret, jsCode, grantType);
    }

    default String getUrlOfGetAccessToken(String grantType, String appId, String secret) {
        return MessageFormatUtils.format(GET_ACCESS_TOKEN, grantType, appId, secret);
    }
}
