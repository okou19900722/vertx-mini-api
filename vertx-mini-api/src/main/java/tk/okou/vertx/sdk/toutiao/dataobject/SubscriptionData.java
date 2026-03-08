package tk.okou.vertx.sdk.toutiao.dataobject;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class SubscriptionData {
    private String key;
    private String value;

    public SubscriptionData() {

    }
    public SubscriptionData(JsonObject json) {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
