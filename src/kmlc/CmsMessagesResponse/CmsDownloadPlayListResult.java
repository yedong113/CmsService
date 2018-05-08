package kmlc.CmsMessagesResponse;

public class CmsDownloadPlayListResult {
    private Integer status=0;

    public Integer getStatus() {
        return status;
    }

    public CmsDownloadPlayListResponse getData() {
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

    private CmsDownloadPlayListResponse data = new CmsDownloadPlayListResponse();

    private String msg="";
}
