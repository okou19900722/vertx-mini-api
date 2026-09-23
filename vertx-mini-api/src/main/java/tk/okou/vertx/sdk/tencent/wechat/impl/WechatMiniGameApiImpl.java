package tk.okou.vertx.sdk.tencent.wechat.impl;

import io.netty.handler.codec.http.QueryStringEncoder;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
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

    @Override
    public WechatMiniGameApi getGameGiftList(String accessToken, int page, int pageSize, String startDate, String endDate, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createAdDataQueryEncoder("general_action", accessToken, page, pageSize);
        encoder.addParam("cmd", "get_game_gift_list");
        encoder.addParam("begin_date", startDate);
        encoder.addParam("end_date", endDate);
        String url = encoder.toString();
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public WechatMiniGameApi getPublisherAdPosGeneral(String accessToken, int page, int pageSize, String startDate, String endDate, String adSlot, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createAdDataQueryEncoder("publisher_adpos_general", accessToken, page, pageSize, startDate, endDate);
        if (adSlot != null) {
            encoder.addParam("ad_slot", adSlot);
        }
        String url = encoder.toString();
        System.out.println("url: " + url);
        getWithJsonResponse(url, handler);
        return this;
    }
    @Override
    public WechatMiniGameApi getPublisherAdUnitGeneral(String accessToken, int page, int pageSize, String startDate, String endDate, String adSlot, String adUnitId, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createAdDataQueryEncoder("publisher_adunit_general", accessToken, page, pageSize, startDate, endDate);
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
    public WechatMiniGameApi getPublisherSettlement(String accessToken, int page, int pageSize, String startDate, String endDate, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createAdDataQueryEncoder("publisher_settlement", accessToken, page, pageSize, startDate, endDate);
        String url = encoder.toString();
        System.out.println("url: " + url);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public WechatMiniGameApi getMonetizeDailyData(String appId, String accessToken, String startDate, String endDate, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createMonetizeQueryEncoder("monetize_daily_data", appId, accessToken, startDate, endDate);
        String url = encoder.toString();
        System.out.println("url: " + url);
        getWithJsonResponse(url, handler);
        return this;
    }

    @Override
    public WechatMiniGameApi getMonetizeTraceData(String appId, String accessToken, String startDate, String endDate, Handler<AsyncResult<JsonObject>> handler) {
        QueryStringEncoder encoder = createMonetizeQueryEncoder("monetize_trace_data", appId, accessToken, startDate, endDate);
        String url = encoder.toString();
        System.out.println("url: " + url);
        getWithJsonResponse(url, handler);
        return this;
    }

    private QueryStringEncoder createAdDataQueryEncoder(String action, String accessToken) {
        QueryStringEncoder encoder = new QueryStringEncoder("/publisher/stat");
        encoder.addParam("action", action);
        encoder.addParam("access_token", accessToken);
        return encoder;
    }
    private QueryStringEncoder createMonetizeQueryEncoder(String action, String appId, String accessToken, String startDate, String endDate) {
        QueryStringEncoder encoder = createAdDataQueryEncoder(action, accessToken);
        encoder.addParam("appid", appId);
        JsonArray filters = new JsonArray();
        JsonObject filter = new JsonObject();
        filter.put("begin_ds", startDate);
        filter.put("end_ds", endDate);
        filters.add(filter);
        encoder.addParam("filters", filters.encode());
        return encoder;
    }
    private QueryStringEncoder createAdDataQueryEncoder(String action, String accessToken, int page, int pageSize) {
        QueryStringEncoder encoder = createAdDataQueryEncoder(action, accessToken);
        encoder.addParam("page", String.valueOf(page));
        encoder.addParam("page_size", String.valueOf(pageSize));
        return encoder;
    }
    private QueryStringEncoder createAdDataQueryEncoder(String action, String accessToken, int page, int pageSize, String startDate, String endDate) {
        QueryStringEncoder encoder = createAdDataQueryEncoder(action, accessToken, page, pageSize);
        encoder.addParam("start_date", startDate);
        encoder.addParam("end_date", endDate);
        return encoder;
    }
}
