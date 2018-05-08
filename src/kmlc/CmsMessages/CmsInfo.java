package kmlc.CmsMessages;



public class CmsInfo {
    private String Id;//设备编码
    private String Name;//设备名称
    private String Url;//访问地址
    private String Location;//所属机构
    private float Lng;//经纬度
    private float Lat;
    private String StakeId;//桩号
    private Integer Width;
    private Integer Height;

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getUrl() {
        return Url;
    }

    public String getLocation() {
        return Location;
    }

    public float getLng() {
        return Lng;
    }

    public float getLat() {
        return Lat;
    }

    public String getStakeId() {
        return StakeId;
    }

    public Integer getWidth() {
        return Width;
    }

    public Integer getHeight() {
        return Height;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setLng(float lng) {
        Lng = lng;
    }

    public void setLat(float lat) {
        Lat = lat;
    }

    public void setStakeId(String stakeId) {
        StakeId = stakeId;
    }

    public void setWidth(Integer width) {
        Width = width;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    @Override
    public String toString() {
        return "CmsInfo{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Url='" + Url + '\'' +
                ", Location='" + Location + '\'' +
                ", Lng=" + Lng +
                ", Lat=" + Lat +
                ", StakeId='" + StakeId + '\'' +
                ", Width=" + Width +
                ", Height=" + Height +
                '}';
    }
}


