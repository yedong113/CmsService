package kmlc.CmsMessagesResponse;

public class CmsDownloadPlayListResultFail {
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public CmsDownloadPlayListResultFail(Integer status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    private Integer status;
    private String msg;

}
