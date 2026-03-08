package tk.okou.vertx.sdk.bilibili;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

@DataObject(generateConverter = true, publicConverter = false)
public class BilibiliMiniApiOptions extends BaseMiniApiOptions {
    public BilibiliMiniApiOptions() {
        this.setDefaultHost("miniapp.bilibili.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
    public BilibiliMiniApiOptions(JsonObject json) {
//        this.setDefaultHost("api.q.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
}
