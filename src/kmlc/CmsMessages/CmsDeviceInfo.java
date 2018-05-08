package kmlc.CmsMessages;



//情报板设备信息
public class CmsDeviceInfo {
    private String deviceCode;//设备编码
    private String name;//设备名称
    private String type;
    private String lng;//经纬度
    private String lat;
    private String location;//所属机构
    private String stakeNum;//桩号
    private String url;//访问地址
    private String state;
    private String site;


    private String describe;
    private Integer Width;
    private Integer Height;
    private Integer maxText;
    private Integer maxRecord;
    private String  manufacture;


    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStakeNum(String stakeNum) {
        this.stakeNum = stakeNum;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setWidth(Integer width) {
        Width = width;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    public void setMaxText(Integer maxText) {
        this.maxText = maxText;
    }

    public void setMaxRecord(Integer maxRecord) {
        this.maxRecord = maxRecord;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public void setDeviceCode(String deviceCode) {

        this.deviceCode = deviceCode;
    }

    private String direction;

    public String getDeviceCode() {
        return deviceCode;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getLocation() {
        return location;
    }

    public String getStakeNum() {
        return stakeNum;
    }

    public String getUrl() {
        return url;
    }

    public String getState() {
        return state;
    }

    public String getSite() {
        return site;
    }

    public String getDirection() {
        return direction;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getWidth() {
        return Width;
    }

    public Integer getHeight() {
        return Height;
    }

    public Integer getMaxText() {
        return maxText;
    }

    public Integer getMaxRecord() {
        return maxRecord;
    }

    public String getManufacture() {
        return manufacture;
    }

}
