package tk.okou.lippen.wechat.api.model;

public class KVData {
    private final String key;
    private final String value;

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
