package tk.okou.sdk.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public enum SignatureMethod {
    HMAC_SHA256("hmac_sha256") {
        @Override
        public String signature(String data, String sessionKey) throws InvalidKeyException, NoSuchAlgorithmException {
            return DigestUtil.hmacSha256(data, sessionKey);
        }
    };
    public final String signatureMethod;

    SignatureMethod(String signatureMethod) {
        this.signatureMethod = signatureMethod;
    }

    public String getSignatureMethod() {
        return signatureMethod;
    }

    public abstract String signature(String data, String sessionKey) throws InvalidKeyException, NoSuchAlgorithmException;
}
