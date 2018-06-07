package tk.okou.lippen.wechat.api.util;

import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class DigestUtilTest {
    @Test
    public void testHMacSha256() throws InvalidKeyException, NoSuchAlgorithmException {
        String sessionKey = "o0q0otL8aEzpcZL/FT9WsQ==";
        String data = "{\"foo\":\"bar\"}";
        String hash = DigestUtil.hmacSha256(data, sessionKey);
        assertEquals(hash, "654571f79995b2ce1e149e53c0a33dc39c0a74090db514261454e8dbe432aa0b");
    }
}
