package tk.okou.vertx.sdk.toutiao;

public interface ToutiaoMiniGameApiUrlSupplier extends ToutiaoMiniApiUrlSupplier {

    @Override
    default String getUrlOfSetUserStorage(String accessToken, String signature, String openId, String signatureMethod) {
        return ToutiaoMiniApiUrlSupplier.super.getUrlOfSetUserStorage(accessToken, signature, openId, signatureMethod);
    }

    @Override
    default String getUrlOfRemoveUserStorage(String accessToken, String signature, String openId, String signatureMethod) {
        return ToutiaoMiniApiUrlSupplier.super.getUrlOfRemoveUserStorage(accessToken, signature, openId, signatureMethod);
    }
}
