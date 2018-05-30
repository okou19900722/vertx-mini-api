package tk.okou.lippen.wechat.api.model;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KVDataTest {
    @Test
    public void testKVData(){
        KVData data = new KVData("key", "value");
        assertEquals(data.getKey(), "key");
        assertEquals(data.getValue(), "value");
    }
    @Test
    public void testKVDataJsonObject(){
        KVData data = new KVData("key", "value");
        JsonObject json = JsonObject.mapFrom(data);
        assertEquals(json, new JsonObject("{\"key\":\"key\",\"value\":\"value\"}"));
    }
    @Test
    public void testListKVDataJsonObject(){
        List<KVData> dataList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            KVData data = new KVData("key", "value");
            dataList.add(data);
        }
        JsonArray arr = new JsonArray(dataList);
        arr = new JsonArray(arr.encode());
        assertEquals(arr, new JsonArray("[{\"key\":\"key\",\"value\":\"value\"},{\"key\":\"key\",\"value\":\"value\"}]"));
    }
}
