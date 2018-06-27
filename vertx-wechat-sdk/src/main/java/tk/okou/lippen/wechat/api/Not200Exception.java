package tk.okou.lippen.wechat.api;

public class Not200Exception extends RuntimeException {
    private final int status;

    public Not200Exception(int status) {
        super("http status:" + status);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
