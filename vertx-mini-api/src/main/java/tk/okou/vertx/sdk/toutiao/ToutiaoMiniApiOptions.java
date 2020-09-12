package tk.okou.vertx.sdk.toutiao;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

@DataObject(generateConverter = true, publicConverter = false)
public class ToutiaoMiniApiOptions extends BaseMiniApiOptions {
    public ToutiaoMiniApiOptions() {
        this.setDefaultHost("developer.toutiao.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
    public ToutiaoMiniApiOptions(JsonObject json) {
        super(json);
        ToutiaoMiniApiOptionsConverter.fromJson(json, this);
    }
}
