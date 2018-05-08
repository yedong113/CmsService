package kmlc.CmsMessages;



public class CmsServiceRequest {

    public CmsServiceRequest(int serviceCode, String sericeContext) {
        this.serviceCode = serviceCode;
        this.sericeContext = sericeContext;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setSericeContext(String sericeContext) {
        this.sericeContext = sericeContext;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public String getSericeContext() {
        return sericeContext;
    }

    private int serviceCode;
    private String sericeContext;

}
