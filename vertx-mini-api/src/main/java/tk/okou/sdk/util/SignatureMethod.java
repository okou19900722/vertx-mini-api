package tk.okou.sdk.util;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public enum SignatureMethod {
    HMAC_SHA256("hmac_sha256") {
        @Override
        public String signature(String data, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
            return DigestUtil.hmacSha256(data, secretKey);
        }
    },
    SHA256_WITH_RSA("SHA256withRSA") {
        @Override
        public String signature(String data, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
            PrivateKey privateKey = PrivateKeyLoader.loadPrivateKey(secretKey);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return Base64.getEncoder().encodeToString(signed);
        }
    },
    ;
    public final String signatureMethod;

    SignatureMethod(String signatureMethod) {
        this.signatureMethod = signatureMethod;
    }

    public String getSignatureMethod() {
        return signatureMethod;
    }

    public abstract String signature(String data, String secretKey) throws Exception;
}
