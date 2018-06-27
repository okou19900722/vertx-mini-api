package tk.okou.lippen.wechat.api.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public abstract class SignatureMethod {
    public final String signatureMethod;

    public SignatureMethod(String signatureMethod) {
        this.signatureMethod = signatureMethod;
    }

    public String getSignatureMethod() {
        return signatureMethod;
    }

    public abstract String signature(String data, String sessionKey) throws InvalidKeyException, NoSuchAlgorithmException;

    public static final SignatureMethod HMAC_SHA256 = new SignatureMethod("hmac_sha256") {
        @Override
        public String signature(String data, String sessionKey) throws InvalidKeyException, NoSuchAlgorithmException {
            return DigestUtil.hmacSha256(data, sessionKey);
        }
    };
}
