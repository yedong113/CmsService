package kmlc.CmsMessagesResponse;

public class CmsUploadResultFail {



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

    public CmsUploadResultFail(Integer status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    private Integer status;
    private String msg;

}
