package kmlc.CmsMessages;


/**
 * 播放内容属性
 */
public class CmsContextInfo {
    public void setDelay(Integer delay) {
        Delay = delay;
    }

    public void setInStyle(Integer inStyle) {
        InStyle = inStyle;
    }

    public void setOutStyle(Integer outStyle) {
        OutStyle = outStyle;
    }

    @Override
    public String toString() {
        return "CmsContextInfo{" +
                "Delay=" + Delay +
                ", InStyle=" + InStyle +
                ", OutStyle=" + OutStyle +
                ", Speed=" + Speed +
                '}';
    }

    public void setSpeed(Integer speed) {
        Speed = speed;
    }

    private Integer Delay;//停留时间

    public Integer getDelay() {
        return Delay;
    }

    public Integer getInStyle() {
        return InStyle;
    }

    public Integer getOutStyle() {
        return OutStyle;
    }

    public Integer getSpeed() {
        return Speed;
    }

    private Integer InStyle;//入屏方式
    private Integer OutStyle;//出屏方式
    private Integer Speed;//入屏速度

}
