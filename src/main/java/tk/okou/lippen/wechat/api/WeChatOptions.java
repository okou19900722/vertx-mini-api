package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpClientOptions;

import java.util.Objects;

@DataObject
public class WeChatOptions {
    private HttpClientOptions apiHttpClientOptions = new HttpClientOptions().setDefaultHost("api.weixin.qq.com");
    private HttpClientOptions apiHttpsClientOptions = new HttpClientOptions().setDefaultHost("api.weixin.qq.com").setSsl(true).setTrustAll(true).setDefaultPort(443);
    private HttpClientOptions fileHttpClientOptions = new HttpClientOptions().setDefaultHost("file.api.weixin.qq.com");
    private HttpClientOptions mpHttpClientOptions = new HttpClientOptions().setDefaultHost("mp.weixin.qq.com");
    private HttpClientOptions openHttpClientOptions = new HttpClientOptions().setDefaultHost("open.weixin.qq.com");
    private HttpClientOptions mchHttpClientOptions = new HttpClientOptions().setDefaultHost("api.mch.weixin.qq.com");

    public HttpClientOptions getApiHttpsClientOptions() {
        return apiHttpsClientOptions;
    }

    public WeChatOptions setApiHttpsClientOptions(HttpClientOptions apiHttpsClientOptions) {
        Objects.requireNonNull(apiHttpsClientOptions, "apiHttpsClientOptions cannot be null!");
        this.apiHttpsClientOptions = apiHttpsClientOptions;
        return this;
    }
}
