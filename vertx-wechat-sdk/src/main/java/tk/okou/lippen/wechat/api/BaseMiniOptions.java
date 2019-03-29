package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpClientOptions;

@DataObject
public abstract class BaseMiniOptions {
    private HttpClientOptions apiHttpsClientOptions;

    public BaseMiniOptions() {
        this.apiHttpsClientOptions = new HttpClientOptions().setDefaultHost("api.weixin.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    }

    public HttpClientOptions getApiHttpsClientOptions() {
        return apiHttpsClientOptions;
    }

    public void setApiHttpsClientOptions(HttpClientOptions apiHttpsClientOptions) {
        this.apiHttpsClientOptions = apiHttpsClientOptions;
    }
}
