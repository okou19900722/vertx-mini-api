package tk.okou.vertx.sdk.toutiao;

public interface ToutiaoMiniGameApiUrlSupplier extends ToutiaoMiniApiUrlSupplier {

//    MessageFormat REMOVE_USER_STORAGE = new MessageFormat("/mgplatform/api/apps/subscribe_notification/developer/v1/notify");
    String REMOVE_USER_STORAGE = "/mgplatform/api/apps/subscribe_notification/developer/v1/notify";
    @Override
    default String getUrlOfSetUserStorage(String accessToken, String signature, String openId, String signatureMethod) {
        return ToutiaoMiniApiUrlSupplier.super.getUrlOfSetUserStorage(accessToken, signature, openId, signatureMethod);
    }

    @Override
    default String getUrlOfRemoveUserStorage(String accessToken, String signature, String openId, String signatureMethod) {
        return ToutiaoMiniApiUrlSupplier.super.getUrlOfRemoveUserStorage(accessToken, signature, openId, signatureMethod);
    }
    default String getUrlOfSendSubscriptionMessage() {
        return REMOVE_USER_STORAGE;
    }
}
