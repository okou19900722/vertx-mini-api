package tk.okou.vertx.sdk.tencent.wechat;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
public class Color {
    private final Integer red;
    private final Integer green;
    private final Integer blue;

    public Color(JsonObject json) {
        this.red = json.getInteger("red");
        this.green = json.getInteger("green");
        this.blue = json.getInteger("blue");
    }

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Integer getRed() {
        return red;
    }

    public Integer getGreen() {
        return green;
    }

    public Integer getBlue() {
        return blue;
    }
}
