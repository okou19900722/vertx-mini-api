package tk.okou.lippen.wechat.api.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DigestUtil {

    public static String hmacSha256(String data, String sessionKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(sessionKey.getBytes(), "HmacSHA256");
        hmacSha256.init(secretKey);
        byte[] hash = Base64.getEncoder().encode(hmacSha256.doFinal(data.getBytes()));
        return new String(hash);
    }
}
