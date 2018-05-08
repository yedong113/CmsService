package kmlc.CmsMessagesResponse;

public class CmsUploadResult {

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public CmsUploadResponse getData() {
        return data;
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

    private CmsUploadResponse data = new CmsUploadResponse();

    private String msg;
}
