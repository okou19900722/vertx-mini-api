package tk.okou.lippen.wechat.api;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.vertx.core.json.JsonObject;
import org.junit.Test;
import tk.okou.lippen.wechat.api.util.XmlUtil;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class XmlTest {

    @Test
    public void test(){
        String xml = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml> ";
        JsonObject json = XmlUtil.xml2Json(xml);
        System.out.println(json);

        UnifiedOrderParameter parameter = new UnifiedOrderParameter();
        parameter.toPostXml();
        System.out.println(XmlUtil.writeObject2Xml(parameter));

        XmlMapper mapper = new XmlMapper();
        StringWriter sw = new StringWriter();
        Map<String, String> map = new HashMap();
        map.put("abc", "123");
        map.put("你好", "hello");
        map.put("555", "cry");
        try {
            mapper.writeValue(sw, map);
            System.out.println(sw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(XmlUtil.writeObject2Xml(map));
    }
}
