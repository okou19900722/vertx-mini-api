package tk.okou.vertx.sdk.tencent.wechat;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import tk.okou.sdk.util.SignatureMethod;
import tk.okou.vertx.sdk.BaseMiniGameApi;
import tk.okou.vertx.sdk.model.KVData;
import tk.okou.vertx.sdk.tencent.wechat.impl.WechatMiniGameApiImpl;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@VertxGen
public interface WechatMiniGameApi extends BaseMiniGameApi, WechatMiniApi {
    @GenIgnore
    MessageFormat QRCODE_A = new MessageFormat("/wxa/getwxacode?access_token={0}");
    @GenIgnore
    MessageFormat QRCODE_B = new MessageFormat("/wxa/getwxacodeunlimit?access_token={0}");
    @GenIgnore
    MessageFormat QRCODE_C = new MessageFormat("/cgi-bin/wxaapp/createwxaqrcode?access_token={0}");

    @GenIgnore
    DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ofPattern("yyyyMMdd");
    @GenIgnore
    DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    static WechatMiniGameApi create(Vertx vertx, WechatMiniApiOptions options) {
        return new WechatMiniGameApiImpl(vertx, options);
    }

    static WechatMiniGameApi create(Vertx vertx) {
        return new WechatMiniGameApiImpl(vertx, new WechatMiniApiOptions());
    }

    @Fluent
    default WechatMiniGameApi code2session(String appId, String secret, String jsCode, Handler<AsyncResult<JsonObject>> handler) {
        return code2session(appId, secret, jsCode, "authorization_code", handler);
    }

    @Fluent
    WechatMiniGameApi code2session(String appId, String secret, String jsCode, String grantType, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    default WechatMiniGameApi getAccessToken(String appId, String secret, Handler<AsyncResult<JsonObject>> handler) {
        return getAccessToken("client_credential", appId, secret, handler);
    }

    @Fluent
    WechatMiniGameApi getAccessToken(String grantType, String appId, String secret, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi setUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<KVData> kvList, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, List<String> keys, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi removeUserStorage(String accessToken, String openId, String sessionKey, SignatureMethod signatureMethod, List<String> key, Handler<AsyncResult<JsonObject>> handler);

    @Fluent
    WechatMiniGameApi getWXACode(String accessToken, String path, Integer width, Boolean autoColor, Color lineColor, Boolean isHyaline, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler);

    @Fluent
    WechatMiniGameApi getWXACodeUnlimited(String accessToken, String scene, String page, Integer width, Boolean autoColor, Color lineColor, Boolean isHyaline, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler);

    @Fluent
    WechatMiniGameApi createWXAQRCode(String accessToken, String path, String width, Handler<Buffer> successConsumer, Handler<AsyncResult<JsonObject>> failHandler);

    @Fluent
    WechatMiniGameApi sendSubscriptionMessage(String accessToken, String toUserOpenId, String templateId, JsonObject data, @Nullable String page, @Nullable String miniprogramState, @Nullable String lang, Handler<AsyncResult<JsonObject>> handler);

    /**
     * 查询流量主每日广告金收入
     *
     * <a href="https://developers.weixin.qq.com/minigame/dev/guide/open-ability/ad/adq.html#_1%E3%80%81%E6%9F%A5%E8%AF%A2%E6%B5%81%E9%87%8F%E4%B8%BB%E6%AF%8F%E6%97%A5%E5%B9%BF%E5%91%8A%E9%87%91%E6%94%B6%E5%85%A5">文档</a>
     */
    @Fluent
    WechatMiniGameApi getGameGiftList(String accessToken, int page, int pageSize, String startDate, String endDate, Handler<AsyncResult<JsonObject>> handler);
    @GenIgnore
    default WechatMiniGameApi getGameGiftList(String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, Handler<AsyncResult<JsonObject>> handler) {
        return getGameGiftList(accessToken, page, pageSize, startDate.format(FORMATTER_1), endDate.format(FORMATTER_1), handler);
    }

    /**
     * 获取小游戏广告汇总数据（publisher_adpos_general）
     *
     * <a href="https://developers.weixin.qq.com/minigame/dev/guide/open-ability/ad/ad-data-interface.html#%E4%B8%80%E3%80%81%E8%8E%B7%E5%8F%96%E5%B0%8F%E6%B8%B8%E6%88%8F%E5%B9%BF%E5%91%8A%E6%B1%87%E6%80%BB%E6%95%B0%E6%8D%AE%EF%BC%88publisher-adpos-general%EF%BC%89">文档</a>
     */
    @Fluent
    WechatMiniGameApi getPublisherAdPosGeneral(String accessToken, int page, int pageSize, String startDate, String endDate, @Nullable String adSlot, Handler<AsyncResult<JsonObject>> handler);
    @GenIgnore
    default WechatMiniGameApi getPublisherAdPosGeneral(String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, @Nullable String adSlot, Handler<AsyncResult<JsonObject>> handler) {
        return getPublisherAdPosGeneral(accessToken, page, pageSize, startDate.format(FORMATTER_2), endDate.format(FORMATTER_2), adSlot, handler);
    }
    /**
     * 获取小游戏广告细分数据（publisher_adunit_general）
     *
     * <a href="https://developers.weixin.qq.com/minigame/dev/guide/open-ability/ad/ad-data-interface.html#%E4%BA%8C%E3%80%81%E8%8E%B7%E5%8F%96%E5%B0%8F%E6%B8%B8%E6%88%8F%E5%B9%BF%E5%91%8A%E7%BB%86%E5%88%86%E6%95%B0%E6%8D%AE%EF%BC%88publisher-adunit-general%EF%BC%89">文档</a>
     */
    @Fluent
    WechatMiniGameApi getPublisherAdUnitGeneral(String accessToken, int page, int pageSize, String startDate, String endDate, @Nullable String adSlot, @Nullable String adUnitId, Handler<AsyncResult<JsonObject>> handler);
    @GenIgnore
    default WechatMiniGameApi getPublisherAdUnitGeneral(String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, @Nullable String adSlot, @Nullable String adUnitId, Handler<AsyncResult<JsonObject>> handler) {
        return getPublisherAdUnitGeneral(accessToken, page, pageSize, startDate.format(FORMATTER_2), endDate.format(FORMATTER_2), adSlot, adUnitId, handler);
    }
    /**
     * 获取小游戏广告位清单（get_adunit_list）
     *
     * <a href="https://developers.weixin.qq.com/minigame/dev/guide/open-ability/ad/ad-data-interface.html#%E4%B8%89%E3%80%81%E8%8E%B7%E5%8F%96%E5%B0%8F%E6%B8%B8%E6%88%8F%E5%B9%BF%E5%91%8A%E4%BD%8D%E6%B8%85%E5%8D%95%EF%BC%88get-adunit-list%EF%BC%89">文档</a>
     */
    @Fluent
    WechatMiniGameApi getAdUnitList(String accessToken, int page, int pageSize, @Nullable String adSlot, @Nullable String adUnitId, Handler<AsyncResult<JsonObject>> handler);
    /**
     * 获取小游戏结算收入数据及结算主体信息（publisher_settlement）
     *
     * <a href="https://developers.weixin.qq.com/minigame/dev/guide/open-ability/ad/ad-data-interface.html#%E5%9B%9B%E3%80%81%E8%8E%B7%E5%8F%96%E5%B0%8F%E6%B8%B8%E6%88%8F%E7%BB%93%E7%AE%97%E6%94%B6%E5%85%A5%E6%95%B0%E6%8D%AE%E5%8F%8A%E7%BB%93%E7%AE%97%E4%B8%BB%E4%BD%93%E4%BF%A1%E6%81%AF%EF%BC%88publisher-settlement%EF%BC%89">文档</a>
     */
    @Fluent
    WechatMiniGameApi getPublisherSettlement(String accessToken, int page, int pageSize, String startDate, String endDate, Handler<AsyncResult<JsonObject>> handler);
    @GenIgnore
    default WechatMiniGameApi getPublisherSettlement(String accessToken, int page, int pageSize, LocalDate startDate, LocalDate endDate, Handler<AsyncResult<JsonObject>> handler) {
        return getPublisherSettlement(accessToken, page, pageSize, startDate.format(FORMATTER_2), endDate.format(FORMATTER_2), handler);
    }

    /**
     * 活跃日期口径（monetize_daily_data）
     *
     * <a href="https://developers.weixin.qq.com/minigame/analysis/ctb/basic.html#%E4%BA%8C%E3%80%81%E6%B4%BB%E8%B7%83%E6%97%A5%E6%9C%9F%E5%8F%A3%E5%BE%84%EF%BC%88monetize-daily-data%EF%BC%89">文档</a>
     */
    @Fluent
    WechatMiniGameApi getMonetizeDailyData(String appId, String accessToken, String startDate, String endDate, Handler<AsyncResult<JsonObject>> handler);
    @GenIgnore
    default WechatMiniGameApi getMonetizeDailyData(String appId, String accessToken, LocalDate startDate, LocalDate endDate, Handler<AsyncResult<JsonObject>> handler) {
        return getMonetizeDailyData(appId, accessToken, startDate.format(FORMATTER_1), endDate.format(FORMATTER_1), handler);
    }
    /**
     * 注册日期口径（monetize_trace_data）
     *
     * <a href="https://developers.weixin.qq.com/minigame/analysis/ctb/basic.html#%E4%B8%89%E3%80%81%E6%B3%A8%E5%86%8C%E6%97%A5%E6%9C%9F%E5%8F%A3%E5%BE%84%EF%BC%88monetize-trace-data%EF%BC%89">文档</a>
     */
    @Fluent
    WechatMiniGameApi getMonetizeTraceData(String appId, String accessToken, String startDate, String endDate, Handler<AsyncResult<JsonObject>> handler);
    @GenIgnore
    default WechatMiniGameApi getMonetizeTraceData(String appId, String accessToken, LocalDate startDate, LocalDate endDate, Handler<AsyncResult<JsonObject>> handler) {
        return getMonetizeTraceData(appId, accessToken, startDate.format(FORMATTER_1), endDate.format(FORMATTER_1), handler);
    }

}
