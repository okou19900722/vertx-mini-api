package tk.okou.vertx.sdk.baidu;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

@DataObject(generateConverter = true, publicConverter = false)
public class BaiduMiniApiOptions extends BaseMiniApiOptions {

    public BaiduMiniApiOptions() {
        this.setDefaultHost("openapi.baidu.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
    public BaiduMiniApiOptions(JsonObject json) {
        super(json);
        BaiduMiniApiOptionsConverter.fromJson(json, this);
    }
}
