package tk.okou.vertx.sdk;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
public abstract class BaseMiniApiOptions extends HttpClientOptions {
    private Long timeout;

    protected BaseMiniApiOptions() {

    }
    protected BaseMiniApiOptions(JsonObject json) {
        BaseMiniApiOptionsConverter.fromJson(json, this);
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
