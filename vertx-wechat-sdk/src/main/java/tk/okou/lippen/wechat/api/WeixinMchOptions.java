package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

@DataObject(
        generateConverter = true,
        publicConverter = false
)
public class WeixinMchOptions {
    private HttpClientOptions apiHttpsClientOptions;
    private long timeout;

    public WeixinMchOptions(JsonObject options) {
        MiniProgramOptionsConverter.fromJson(options, this);
    }
    public WeixinMchOptions() {
        this.apiHttpsClientOptions = new HttpClientOptions().setDefaultHost("api.mch.weixin.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }

    public HttpClientOptions getApiHttpsClientOptions() {
        return apiHttpsClientOptions;
    }

    public WeixinMchOptions setApiHttpsClientOptions(HttpClientOptions apiHttpsClientOptions) {
        Objects.requireNonNull(apiHttpsClientOptions, "apiHttpsClientOptions cannot be null!");
        this.apiHttpsClientOptions = apiHttpsClientOptions;
        return this;
    }

    public WeixinMchOptions setMaxPoolSize(int maxPoolSize) {
        this.apiHttpsClientOptions.setMaxPoolSize(maxPoolSize);
        return this;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
