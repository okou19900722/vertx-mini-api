package tk.okou.lippen.wechat.api;

public class Not200Exception extends RuntimeException {
    private final int status;

    public Not200Exception(int status) {
        this.status = status;
    }
}
