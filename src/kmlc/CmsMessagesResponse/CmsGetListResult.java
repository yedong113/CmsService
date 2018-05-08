package kmlc.CmsMessagesResponse;


import kmlc.CmsMessages.CmsDeviceInfo;

import java.util.ArrayList;
import java.util.List;

//获取情报板设备信息返回结构
public class CmsGetListResult {
    private Integer status;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public List<CmsDeviceInfo> getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<CmsDeviceInfo> data = new ArrayList<CmsDeviceInfo>();
    private String msg;
}
