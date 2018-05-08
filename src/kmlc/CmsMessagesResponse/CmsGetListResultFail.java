package kmlc.CmsMessagesResponse;

public class CmsGetListResultFail {
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
    public CmsGetListResultFail(Integer status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }
    private Integer status;
    private String msg;
}
