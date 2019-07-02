package tk.okou.vertx.sdk.tencent;

public interface BaseMiniGameApiUrlSupplier extends BaseMiniApiUrlSupplier {
    String getUrlOfSetUserStorage(String accessToken, String signature, String openId, String signatureMethod);

    String getUrlOfRemoveUserStorage(String accessToken, String signature, String openId, String signatureMethod);
}
