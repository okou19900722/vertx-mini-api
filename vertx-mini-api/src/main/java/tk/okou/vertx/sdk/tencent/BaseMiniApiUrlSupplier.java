package tk.okou.vertx.sdk.tencent;

public interface BaseMiniApiUrlSupplier {

    String getUrlOfCode2accessToken(String appId, String secret, String jsCode, String grantType);

    String getUrlOfGetAccessToken(String grantType, String appId, String secret);
}
