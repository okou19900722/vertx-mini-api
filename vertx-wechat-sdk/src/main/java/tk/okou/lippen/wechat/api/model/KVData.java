package tk.okou.lippen.wechat.api.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
public class KVData {
    private String key;
    private String value;

    public KVData(JsonObject data) {
        KVDataConverter.fromJson(data, this);
    }

    public KVData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof KVData)) {
            return false;
        }
        KVData other = (KVData) obj;
        return other.key.equals(key) && other.value.equals(value);
    }
}
