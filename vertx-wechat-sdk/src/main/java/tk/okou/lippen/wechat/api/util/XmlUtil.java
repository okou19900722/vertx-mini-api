package tk.okou.lippen.wechat.api.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class XmlUtil {
    private static XmlMapper mapper = new XmlMapper();

    public static JsonObject xml2Json(String xml){
        JsonObject result = null;
        try {
            Map map = mapper.readValue(xml, Map.class);
            result = new JsonObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static <T> String writeObject2Xml(T obj){
        String result = null;
        try {
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, obj);
            result = sw.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
