package tk.okou.lippen.wechat.api;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import tk.okou.lippen.wechat.api.util.MD5Utils;
import tk.okou.lippen.wechat.api.util.XmlUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@DataObject(generateConverter = true, publicConverter = false)
public class UnifiedOrderParameter {

    /** 小程序ID */
    private String appid;

    /** 商户号 */
    private String mch_id;

    /** 设备号 */
    private String device_info;

    /** 随机字符串 */
    private String nonce_str;

    /** 签名 */
    private String sign;

    /** 签名类型 */
    private String sign_type;

    /** 商品描述 */
    private String body;

    /** 商品详情 */
    private String detail;

    /** 附加数据 */
    private String attach;

    /** 商户订单号 */
    private String out_trade_no;

    /** 标价币种 */
    private String fee_type;

    /** 标价金额 */
    private Integer total_fee;

    /** 终端IP */
    private String spbill_create_ip;

    /** 交易起始时间 */
    private String time_start;

    /** 交易结束时间 */
    private String time_expire;

    /** 订单优惠标记 */
    private String goods_tag;

    /** 通知地址 */
    private String notify_url;

    /** 交易类型 */
    private String trade_type;

    /** 商品ID */
    private String product_id;

    /** 指定支付方式 */
    private String limit_pay;

    /** 用户标识 */
    private String openid;

    /**
     * json转化构造函数
     * @param jsonObject json对象
     * */
    public UnifiedOrderParameter(JsonObject jsonObject) {
        UnifiedOrderParameterConverter.fromJson(jsonObject, this);
    }

    /**
     * 无参构造
     * */
    public UnifiedOrderParameter(){

    }

    /**
     * MD5签名
     *
     * */
    public String signMD5(String apiKey){
        Field fields[] = this.getClass().getDeclaredFields();
        Field.setAccessible(fields,   true);
        Map<String, Object> map = new HashMap();
        try {
            for (Field filed:fields) {
                map.put(filed.getName(), filed.get(this));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Object[] arr = map.keySet().toArray();
        Arrays.sort(arr);

        String stringA = "";
        for (Object o:arr) {
            String name = o.toString();
            Object obj = map.get(name);
            if (obj != null && obj != "sign"){
                stringA += name + "=" + obj + "&";
            }
        }
        stringA += "key=" + apiKey;

        return MD5Utils.encryptMD5(stringA).toUpperCase();
    }

    /**
     * 转换成post参数
     * */
    public String toPostXml(){
        return XmlUtil.writeObject2Xml(this);
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
