package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

@DataObject
public class MiniGameOptions {
    private HttpClientOptions apiHttpsClientOptions;

    public MiniGameOptions(JsonObject options) {

    }
    public MiniGameOptions() {
        this.apiHttpsClientOptions = new HttpClientOptions().setDefaultHost("api.weixin.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }

    public HttpClientOptions getApiHttpsClientOptions() {
        return apiHttpsClientOptions;
    }

    public MiniGameOptions setApiHttpsClientOptions(HttpClientOptions apiHttpsClientOptions) {
        Objects.requireNonNull(apiHttpsClientOptions, "apiHttpsClientOptions cannot be null!");
        this.apiHttpsClientOptions = apiHttpsClientOptions;
        return this;
    }

    public MiniGameOptions setMaxPoolSize(int maxPoolSize) {
        this.apiHttpsClientOptions.setMaxPoolSize(maxPoolSize);
        return this;
    }
}
