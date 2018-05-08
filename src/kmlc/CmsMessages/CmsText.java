package kmlc.CmsMessages;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 播放文本属性
 */
public class CmsText {
    @JsonProperty
    public void setFont(Integer font) {
        Font = font;
    }
    @JsonProperty
    public void setHFont(Integer HFont) {
        this.HFont = HFont;
    }
    @JsonProperty
    public void setColor(Integer color) {
        Color = color;
    }
    @JsonProperty
    public void setText(String text) {
        Text = text;
    }
    @JsonProperty
    public void setX(Integer x) {
        X = x;
    }
    @JsonProperty
    public void setY(Integer y) {
        Y = y;
    }


    @JsonIgnore
    public Integer getFont() {
        return Font;
    }

    @JsonIgnore
    public Integer getHFont() {
        return HFont;
    }

    @JsonIgnore
    public Integer getColor() {
        return Color;
    }

    @JsonIgnore
    public String getText() {
        return Text;
    }

    @JsonIgnore
    public Integer getX() {
        return X;
    }

    @JsonIgnore
    public Integer getY() {
        return Y;
    }

    @JsonProperty
    private Integer	Font;//	字体（1：宋， 2：楷，3：黑，4：仿宋，5：隶体）
    @JsonProperty
    private Integer HFont;   //	文字大小（1：16*16，2：24*24，3：32*32，4：48*48，5：96*96）
    @JsonProperty
    private Integer Color;   //	文字前景颜色（1：红，2：绿，3：蓝，4：黄，5：紫，6：青，7：白，8：黑）
    @JsonProperty
    private String	Text;    //	文本
    @JsonProperty
    private Integer	X;       //	显示X坐标
    @JsonProperty
    private Integer Y;       //	显示Y坐标
}
