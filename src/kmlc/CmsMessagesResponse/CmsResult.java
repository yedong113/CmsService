package kmlc.CmsMessagesResponse;

public class CmsResult {
    public String getResponse() {
        return response;
    }
    private int    serviceCode;//
    private String response;

    public CmsResult(int serviceCode, String response) {
        this.serviceCode = serviceCode;
        this.response = response;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getServiceCode() {
        return this.serviceCode;
    }
}
