package tk.okou.vertx.sdk.tencent.wechat.impl;

import io.netty.handler.codec.http.QueryStringEncoder;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import tk.okou.vertx.sdk.tencent.wechat.AbstractWechatMiniGameApi;
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniApiOptions;
import tk.okou.vertx.sdk.model.KVData;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniGameApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WechatMiniGameApiImpl extends AbstractWechatMiniGameApi implements WechatMiniGameApi {
    public WechatMiniGameApiImpl(Vertx vertx, WechatMiniApiOptions options) {
        super(vertx, options);
    }

    @Fluent
    public WechatMiniGameApiImpl code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, handler);
        return this;
    }

    @Fluent
    public WechatMiniGameApiImpl code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler) {
        super.code2session(appId, secret, jsCode, grantType, handler);
        return this;
    }

    @Fluent
    public WechatMiniGameApiImpl getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(appId, secret, handler);
        return this;
    }

    @Fluent
    public WechatMiniGameApiImpl getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        super.getAccessToken(grantType, appId, secret, handler);
        return this;
    }

    @Override
    public WechatMiniGameApiImpl setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList, handler);
        return this;
    }

    @Override
    public WechatMiniGameApiImpl setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler) {
        super.setUserStorage(accessToken, openId, sessionKey, kvList, handler);
        return this;
    }

    @Override
    public WechatMiniGameApiImpl removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key, handler);
        return this;
    }

    @Override
    public WechatMiniGameApiImpl removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler) {
        super.removeUserStorage(accessToken, openId, sessionKey, keys, handler);
        return this;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public WechatMiniGameApi getGameGiftList(String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, Handler<AsyncResult<JsonObject>> handler) {
        String url = String.format("/publisher/stat?action=general_action&cmd=get_game_gift_list&access_token=%s&begin_date=%s&end_date=%s&page=%d&page_size=%d",
                accessToken,
                startDate.format(formatter),
                endDate.format(formatter),
                page, pageSize);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public WechatMiniGameApi getPublisherAdPosGeneral(String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, String adSlot, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createAdDataQueryEncoder("publisher_adpos_general", accessToken, page, pageSize, startDate, endDate, formatter2);
        if (adSlot != null) {
            encoder.addParam("ad_slot", adSlot);
        }
        String url = encoder.toString();
        System.out.println("url: " + url);
        getWithJsonResponse(url, handler);
        return this;
    }
    @Override
    public WechatMiniGameApi getPublisherAdUnitGeneral(String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, String adSlot, String adUnitId, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createAdDataQueryEncoder("publisher_adunit_general", accessToken, page, pageSize, startDate, endDate, formatter2);
        if (adSlot != null) {
            encoder.addParam("ad_slot", adSlot);
        }
        if (adUnitId != null) {
            encoder.addParam("ad_unit_id", adUnitId);
        }
        String url = encoder.toString();
        System.out.println("url: " + url);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public WechatMiniGameApi getAdUnitList(String accessToken, int page, int pageSize, @Nullable String adSlot, @Nullable String adUnitId, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createAdDataQueryEncoder("get_adunit_list", accessToken, page, pageSize);
        if (adSlot != null) {
            encoder.addParam("ad_slot", adSlot);
        }
        if (adUnitId != null) {
            encoder.addParam("ad_unit_id", adUnitId);
        }
        String url = encoder.toString();
        System.out.println("url: " + url);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public WechatMiniGameApi getPublisherSettlement(String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createAdDataQueryEncoder("publisher_settlement", accessToken, page, pageSize, startDate, endDate, formatter2);
        String url = encoder.toString();
        System.out.println("url: " + url);
        getWithJsonResponse(url, handler);
        return this;
    }

    private QueryStringEncoder createAdDataQueryEncoder(String action, String accessToken, int page, int pageSize) {
        QueryStringEncoder encoder = new QueryStringEncoder("/publisher/stat");
        encoder.addParam("action", action);
        encoder.addParam("access_token", accessToken);
        encoder.addParam("page", String.valueOf(page));
        encoder.addParam("page_size", String.valueOf(pageSize));
        return encoder;
    }
    private QueryStringEncoder createAdDataQueryEncoder(String action, String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, DateTimeFormatter formatter) {
        QueryStringEncoder encoder = createAdDataQueryEncoder(action, accessToken, page, pageSize);
        encoder.addParam("start_date", formatter2.format(startDate));
        encoder.addParam("end_date", formatter2.format(endDate));
        return encoder;
    }
}
