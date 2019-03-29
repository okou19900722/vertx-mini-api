package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
public class MiniProgramOptions extends BaseMiniOptions{
    private long timeout;

    public MiniProgramOptions(JsonObject options) {
        MiniProgramOptionsConverter.fromJson(options, this);
    }
    public MiniProgramOptions() {
        super();
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
