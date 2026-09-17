package tk.okou.vertx.sdk.my;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

@DataObject(generateConverter = true, publicConverter = false)
public class MyMiniGameApiOptions extends BaseMiniApiOptions {
    public MyMiniGameApiOptions() {
        this.setDefaultHost("openapi.alipay.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
    public MyMiniGameApiOptions(JsonObject json) {
//        this.setDefaultHost("api.q.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
}
