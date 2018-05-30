package tk.okou.lippen.wechat.api.util;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MockRouter {
    public static void route(Router router) {
        router.get("/sns/jscode2session").handler(MockRouter::jscode2session);
        router.get("/wxa/set_user_storage").handler(MockRouter::set_user_storage);
        router.get("/cgi-bin/token").handler(MockRouter::getAccessToken);
    }

    private static void getAccessToken(RoutingContext context) {
        MultiMap params = context.request().params();
        String grantType = params.get("grant_type");
        String appId = params.get("appid");
        String secret = params.get("secret");
        assertNotNull(appId);
        assertEquals(grantType, "client_credential");
        assertNotNull(secret);

        JsonObject result = new JsonObject();
        switch (appId) {
            case "ok":
                result.put("access_token", "10_7HdWQJMyJvrCF4F_18TOGg1VWrrYfl5RxbPilavE78VJCqP1QSBmNVLO6usdNsGH7B6nugGUP1FdJVpbz_pBbkcsa4Qhx59cxqNxDBZ-SzYPjr7VIemiim6UAC74GE1lN4T_xwSaYH0kCpoOMZBgABAZMO");
                result.put("expires_in", 7200);
                break;
            default:
                result.put("errcode", 40125);
                result.put("errmsg", "invalid appsecret, view more at http:\\/\\/t.cn\\/RAEkdVq hint: [otsnpA06732790]");
        }
        context.response().headers().add("Content-Type", "text/plain;charset=UTF-8");
        context.response().end(result.toBuffer());
    }

    private static void set_user_storage(RoutingContext context) {
        MultiMap params = context.request().params();
        String accessToken = params.get("access_token");
        String openId = params.get("openid");
        String appId = params.get("appid");
        String signature = params.get("signature");
        String sigMethod = params.get("sig_method");
        String kvList = params.get("kv_list");
        assertNotNull(accessToken);
        assertNotNull(openId);
        assertNotNull(appId);
        assertNotNull(signature);
        assertNotNull(sigMethod);
        assertNotNull(kvList);
    }

    private static void jscode2session(RoutingContext context) {
        MultiMap params = context.request().params();
        String appId = params.get("appid");
        String secret = params.get("secret");
        String jsCode = params.get("js_code");
        String grantType = params.get("grant_type");
        assertNotNull(appId);
        assertNotNull(secret);
        assertNotNull(jsCode);
        assertEquals(grantType, "authorization_code");
        JsonObject result = new JsonObject();
        switch (jsCode) {
            case "ok":
                result
                        .put("openid", "openid")
                        .put("session_key", "session_key")
                        .put("uinionid", "uinionid")
                        .put("errcode", 0)
                        .put("errMsg", "success");
                break;
            case "error":
                result
                        .put("errcode", 40029)
                        .put("errMsg", "code 无效");
                break;
            default:
                result
                        .put("errcode", -1)
                        .put("errMsg", "系统繁忙，此时请开发者稍候再试");
        }
        context.response().headers().add("Content-Type", "text/plain;charset=UTF-8");
        context.response().end(result.toBuffer());
    }
}
