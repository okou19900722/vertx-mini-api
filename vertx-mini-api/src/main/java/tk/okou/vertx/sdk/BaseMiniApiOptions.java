package tk.okou.vertx.sdk;

import io.vertx.core.http.HttpClientOptions;

public class BaseMiniApiOptions extends HttpClientOptions {
    private Long timeout;

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
