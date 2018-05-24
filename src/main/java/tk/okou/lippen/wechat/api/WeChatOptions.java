package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.http.HttpClientOptions;

@DataObject
public class WeChatOptions {
    private final HttpClientOptions apiHttpClientOptions = new HttpClientOptions().setDefaultHost("api.weixin.qq.com");
    private final HttpClientOptions apiHttpsClientOptions = new HttpClientOptions().setDefaultHost("api.weixin.qq.com").setSsl(true).setDefaultPort(443);
    private final HttpClientOptions fileHttpClientOptions = new HttpClientOptions().setDefaultHost("file.api.weixin.qq.com");
    private final HttpClientOptions mpHttpClientOptions = new HttpClientOptions().setDefaultHost("mp.weixin.qq.com");
    private final HttpClientOptions openHttpClientOptions = new HttpClientOptions().setDefaultHost("open.weixin.qq.com");
    private final HttpClientOptions mchHttpClientOptions = new HttpClientOptions().setDefaultHost("api.mch.weixin.qq.com");

    public int getApiHttpsPort(){
        return apiHttpsClientOptions.getDefaultPort();
    }

}
