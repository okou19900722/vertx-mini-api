package tk.okou.sdk.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    public static String hmacSha256(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec sk = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        hmacSha256.init(sk);
        return toHex(hmacSha256.doFinal(data.getBytes()));
    }
    private static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            ret.append(HEX_DIGITS[(b >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[b & 0x0f]);
        }
        return ret.toString();
    }
}
