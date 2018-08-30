package tk.okou.lippen.wechat.api;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.SelfSignedCertificate;
import io.vertx.ext.web.Router;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.okou.lippen.wechat.api.util.MockRouter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertTrue;

public class MiniGameApiQrcodeTest {
    private Vertx vertx;
    private MiniGameApi wxApi;


    @Before
    public void before() {
        vertx = Vertx.vertx();
        /*
         * create a wxapi
         */
        wxApi = MiniGameApi.create(vertx);

    }

    public void writeTofile(String absolutePath, Buffer buffer){
        File file = new File(absolutePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(absolutePath);
            out.write(buffer.getBytes());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String accessToken = "11_3Zxkn5dHYVPXVmZMAtW-FGqWJbMAbgRkHc4WHkJ-9_d5i12-ee0-gJmz4V8SgNKZxcaXnS3T64KK7FnzZRuYm-AoJa0DJwXf_bF0JZbo7tx2et6qPLWtZGlFAUfCt_1_Yq03kyFu8E18EVBeAFIgACAFKN";

    @Test
    public void genQrcodeA() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        wxApi.getwxacode(accessToken, "pages/pay/pay", null, null, null, null, null, null, successAsync->{
            if (successAsync.succeeded()){
                writeTofile("D:\\qrcode\\hehe_a.jpg", successAsync.result());
            }else {
                System.out.println(successAsync.cause());
            }
            c.countDown();
        }, failAsync->{
            if (failAsync.succeeded()){
                System.out.println(failAsync.result());
            }else {
                System.out.println(failAsync.cause());
            }
            c.countDown();
        });
        c.await();
    }


    @Test
    public void genQrcodeB() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        wxApi.getwxacodeunlimit(accessToken, "custom_data", "pages/pay/pay", null, null, null, null, null, null, successAsync->{
            if (successAsync.succeeded()){
                writeTofile("D:\\qrcode\\hehe_b.jpg", successAsync.result());
            }else {
                System.out.println(successAsync.cause());
            }
            c.countDown();
        }, failAsync->{
            if (failAsync.succeeded()){
                System.out.println(failAsync.result());
            }else {
                System.out.println(failAsync.cause());
            }
            c.countDown();
        });
        c.await();
    }

    @Test
    public void genQrcodeC() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
        wxApi.createwxaqrcode(accessToken, "pages/pay/pay", null, successAsync->{
            if (successAsync.succeeded()){
                writeTofile("D:\\qrcode\\hehe_c.jpg", successAsync.result());
            }else {
                System.out.println(successAsync.cause());
            }
            c.countDown();
        }, failAsync->{
            if (failAsync.succeeded()){
                System.out.println(failAsync.result());
            }else {
                System.out.println(failAsync.cause());
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
