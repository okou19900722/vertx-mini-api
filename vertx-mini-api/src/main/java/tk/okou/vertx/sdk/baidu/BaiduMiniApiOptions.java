package tk.okou.vertx.sdk.baidu;

import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

public class BaiduMiniApiOptions extends BaseMiniApiOptions {

    public BaiduMiniApiOptions() {
        this.setDefaultHost("openapi.baidu.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
    public BaiduMiniApiOptions(JsonObject json) {

    }
}
