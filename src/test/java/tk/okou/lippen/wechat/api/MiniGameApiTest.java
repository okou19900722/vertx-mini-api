package tk.okou.lippen.wechat.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.SelfSignedCertificate;
import io.vertx.ext.web.Router;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.okou.lippen.wechat.api.util.MockRouter;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class MiniGameApiTest {
    private Vertx vertx;
    private MiniGameApi wxApi;

    @Before
    public void before() {
        int port = 9099;
        String host = "localhost";
        vertx = Vertx.vertx();
        /*
         * create a wxapi
         */
        WeChatOptions weChatOptions = new WeChatOptions();
        weChatOptions.getApiHttpsClientOptions().setDefaultHost(host).setDefaultPort(port);
        wxApi = MiniGameApi.create(vertx, weChatOptions);

        /*
         * create mock server
         */
        SelfSignedCertificate certificate = SelfSignedCertificate.create(host);
        HttpServerOptions options = new HttpServerOptions()
                .setHost(host)
                .setPort(port)
                .setSsl(true)
                .setTrustOptions(certificate.trustOptions())
                .setKeyCertOptions(certificate.keyCertOptions());
        HttpServer server = vertx.createHttpServer(options);
        Router router = Router.router(vertx);
        MockRouter.route(router);

        server.requestHandler(router);
        server.listen();
    }

    @Test
    public void testCode2accessTokenOk() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        code2accessTokenTest(c, "ok", asyncResult -> {
            assertTrue(asyncResult.succeeded());
            JsonObject result = asyncResult.result();
            assertNotNull(result);
            assertEquals("openid", result.getString("openid"));
            assertEquals("session_key", result.getString("session_key"));
            assertEquals("uinionid", result.getString("uinionid"));
            assertEquals("success", result.getString("errMsg"));
            assertEquals(0, (int) result.getInteger("errcode"));
            c.countDown();
        });
    }

    @Test
    public void testCode2accessTokenError() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        code2accessTokenTest(c, "error", asyncResult -> {
            assertTrue(asyncResult.succeeded());
            JsonObject result = asyncResult.result();
            assertNotNull(result);
            assertEquals(40029, (int) result.getInteger("errcode"));
            assertEquals("code 无效", result.getString("errMsg"));
            c.countDown();
        });
    }
    @Test
    public void testCode2accessTokenFail() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        code2accessTokenTest(c, "fail", asyncResult -> {
            assertTrue(asyncResult.succeeded());
            JsonObject result = asyncResult.result();
            assertNotNull(result);
            assertEquals(-1, (int) result.getInteger("errcode"));
            assertEquals("系统繁忙，此时请开发者稍候再试", result.getString("errMsg"));
            c.countDown();
        });
    }

    private void code2accessTokenTest(CountDownLatch c, String jsCode, Handler<AsyncResult<JsonObject>> handler) throws InterruptedException {
        String appId = "wx1d138f3b46298880";
        String secret = "449667fafccc0e59bd9f0475a7b46388";
        String accessToken = "10_7HdWQJMyJvrCF4F_18TOGg1VWrrYfl5RxbPilavE78VJCqP1QSBmNVLO6usdNsGH7B6nugGUP1FdJVpbz_pBbkcsa4Qhx59cxqNxDBZ-SzYPjr7VIemiim6UAC74GE1lN4T_xwSaYH0kCpoOMZBgABAZMO";
        wxApi.code2accessToken(appId, secret, jsCode, handler);
        c.await();
    }

    @After
    public void after() {
        vertx.close();
    }
}
