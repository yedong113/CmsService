package kmlc.CmsMessagesResponse;



public class CmsServiceResultFail {
    public Integer getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public CmsServiceResultFail(){super();}

    @Override
    public String toString() {
        return "CmsServiceResultFail{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }

    public CmsServiceResultFail(Integer status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }
    private Integer status;
    private String msg;
}
