package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
public class MiniGameOptions extends HttpClientOptions {
    private Long timeout = null;

    public MiniGameOptions(JsonObject options) {
        MiniGameOptionsConverter.fromJson(options, this);
    }

    public MiniGameOptions() {
        this.setDefaultHost("api.weixin.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }


    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
