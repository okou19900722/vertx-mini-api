package tk.okou.vertx.sdk.tencent.qq;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.BaseMiniApiOptions;

@DataObject(generateConverter = true, publicConverter = false)
public class QQMiniApiOptions extends BaseMiniApiOptions {
    public QQMiniApiOptions() {
        this.setDefaultHost("api.q.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }
    public QQMiniApiOptions(JsonObject json) {
        super(json);
        QQMiniApiOptionsConverter.fromJson(json, this);
    }
}
