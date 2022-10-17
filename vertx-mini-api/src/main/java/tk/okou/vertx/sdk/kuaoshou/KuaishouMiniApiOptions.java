package tk.okou.vertx.sdk.kuaoshou;

import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

public class KuaishouMiniApiOptions extends BaseMiniApiOptions {

    public KuaishouMiniApiOptions() {
        this.setDefaultHost("open.kuaishou.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
    public KuaishouMiniApiOptions(JsonObject json) {

    }
}
