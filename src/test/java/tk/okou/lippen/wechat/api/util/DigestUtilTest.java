package tk.okou.lippen.wechat.api.util;

import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class DigestUtilTest {
    @Test
    public void testHMacSha256() throws InvalidKeyException, NoSuchAlgorithmException {
        String sessionKey = "dasdfasdfae";
        String data = "123456";
        String hash = DigestUtil.hmacSha256(data, sessionKey);
        assertEquals(hash, "dUrVv5xw576IrvliDy8PzGnPHBIWxjKVXpUxaPeRsP0=");
    }
}
