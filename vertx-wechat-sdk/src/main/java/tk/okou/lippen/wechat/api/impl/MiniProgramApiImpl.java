package tk.okou.lippen.wechat.api.impl;

import io.vertx.core.Vertx;
import tk.okou.lippen.wechat.api.*;

public class MiniProgramApiImpl extends AbstractMiniApi<MiniProgramApi> implements MiniProgramApi {
    public MiniProgramApiImpl(Vertx vertx, MiniProgramOptions miniGameOptions) {
        super(vertx, miniGameOptions);
    }
}
