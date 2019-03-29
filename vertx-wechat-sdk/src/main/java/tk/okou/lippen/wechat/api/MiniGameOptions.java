package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

@DataObject(generateConverter = true, publicConverter = false)
public class MiniGameOptions extends BaseMiniOptions{
    private long timeout;

    public MiniGameOptions(JsonObject options) {
        MiniGameOptionsConverter.fromJson(options, this);
    }
    public MiniGameOptions() {
        super();
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
