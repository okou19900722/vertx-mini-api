package tk.okou.lippen.wechat.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.SelfSignedCertificate;
import io.vertx.ext.web.Router;
import org.junit.*;
import tk.okou.lippen.wechat.api.util.MockRouter;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class MiniGameApiTest {
    private static Vertx vertx;
    private static MiniGameApi wxApi;

    @BeforeClass
    public static void before() throws InterruptedException {
        int port = 9099;
        String host = "localhost";
        vertx = Vertx.vertx();
        /*
         * create a wxapi
         */
        MiniGameOptions miniGameOptions = new MiniGameOptions();
        miniGameOptions.getApiHttpsClientOptions().setDefaultHost(host).setDefaultPort(port);
        wxApi = MiniGameApi.create(vertx, miniGameOptions);

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
        CountDownLatch c = new CountDownLatch(1);
        server.listen(r -> {
            if (r.succeeded()) {
                System.out.println("[http server listen success]");
            } else {
                r.cause().printStackTrace();
            }
            c.countDown();
        });
        c.await();
    }
    @AfterClass
    public static void after() {
        vertx.close();
    }

    @Test
    public void testCode2accessTokenOk() throws InterruptedException {
        Future<JsonObject> f = Future.future();
        code2accessTokenTest("ok", f);
        f.setHandler(asyncResult -> {
            if (asyncResult.succeeded()) {
                assertTrue(asyncResult.succeeded());
                JsonObject result = asyncResult.result();
                assertNotNull(result);
                assertEquals("openid", result.getString("openid"));
                assertEquals("session_key", result.getString("session_key"));
                assertEquals("uinionid", result.getString("uinionid"));
                assertEquals("success", result.getString("errmsg"));
                assertEquals(0, (int) result.getInteger("errcode"));
            } else {
                asyncResult.cause().printStackTrace();
            }
        });
    }

    @Test
    public void testCode2accessTokenError() throws InterruptedException {
        Future<JsonObject> f = Future.future();
        code2accessTokenTest("error", f);
        f.setHandler(asyncResult -> {
            assertTrue(asyncResult.succeeded());
            JsonObject result = asyncResult.result();
            assertNotNull(result);
            assertEquals(40029, (int) result.getInteger("errcode"));
            assertEquals("code 无效", result.getString("errmsg"));
        });
    }

    @Test
    public void testCode2accessTokenFail() throws InterruptedException {
        Future<JsonObject> f = Future.future();
        code2accessTokenTest("fail", f);
        f.setHandler(asyncResult -> {
            if (asyncResult.failed()) {
                asyncResult.cause().printStackTrace();
            }
            assertTrue(asyncResult.succeeded());
            JsonObject result = asyncResult.result();
            assertNotNull(result);
            assertEquals(-1, (int) result.getInteger("errcode"));
            assertEquals("系统繁忙，此时请开发者稍候再试", result.getString("errmsg"));
        });
    }

    private void code2accessTokenTest(String jsCode, Handler<AsyncResult<JsonObject>> handler) throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        String appId = "wx1d138f3b46298880";
        String secret = "449667fafccc0e59bd9f0475a7b46388";
        String accessToken = "10_7HdWQJMyJvrCF4F_18TOGg1VWrrYfl5RxbPilavE78VJCqP1QSBmNVLO6usdNsGH7B6nugGUP1FdJVpbz_pBbkcsa4Qhx59cxqNxDBZ-SzYPjr7VIemiim6UAC74GE1lN4T_xwSaYH0kCpoOMZBgABAZMO";
        wxApi.code2accessToken(appId, secret, jsCode, r -> {
            try {
                handler.handle(r);
            } catch (Throwable t) {
                handler.handle(Future.failedFuture(t));
            } finally {
                c.countDown();
            }
        });
        c.await();
    }
}
