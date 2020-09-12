package tk.okou.vertx.sdk.tencent.wechat;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

@DataObject(generateConverter = true, publicConverter = false)
public class WechatMiniApiOptions extends BaseMiniApiOptions {
    public WechatMiniApiOptions() {
        this.setDefaultHost("api.weixin.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
    public WechatMiniApiOptions(JsonObject json) {
        super(json);
        WechatMiniApiOptionsConverter.fromJson(json, this);
    }
}
