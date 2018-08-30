package tk.okou.lippen.wechat.api;

import io.vertx.core.Vertx;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class WeixinMchApiTest {

    private Vertx vertx;
    private WeixinMchApi mchApi;


    @Before
    public void before() {
        vertx = Vertx.vertx();
        /*
         * create a wxapi
         */
        mchApi = WeixinMchApi.create(vertx);

    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        UnifiedOrderParameter unifiedOrderParameter = new UnifiedOrderParameter();
        unifiedOrderParameter.setAppid("wxdf17cc896ea7846a");
        unifiedOrderParameter.setMch_id("1509623791");
        unifiedOrderParameter.setBody("小游戏-XX商品");
        unifiedOrderParameter.setNonce_str(String.valueOf(System.currentTimeMillis()));
        unifiedOrderParameter.setNotify_url("127.0.0.1");
        unifiedOrderParameter.setOpenid("XXX");
        unifiedOrderParameter.setOut_trade_no("JKFDSLDKS552");
        unifiedOrderParameter.setSpbill_create_ip("127.0.0.1");
        unifiedOrderParameter.setTotal_fee(1);
        unifiedOrderParameter.setTrade_type("JSAPI");

        unifiedOrderParameter.setSign(unifiedOrderParameter.signMD5("tlYL6z0Bjq7QF9Adiql8OfdbaVGoBY0L"));
        mchApi.unifiedorder(unifiedOrderParameter, async->{
            if (async.succeeded()){
                System.out.println("async.succeeded");
                System.out.println(async.result());
            }else {
                System.out.println(async.cause());
            }
            c.countDown();
        });
        c.await();
    }


    @After
    public void after() {
        vertx.close();
    }
}
