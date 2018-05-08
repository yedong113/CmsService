package kmlc.CmsMessages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CmsPlaylistInfo {
    public void setDelay(Integer delay) {
        Delay = delay;
    }

    public void setInStyle(Integer inStyle) {
        InStyle = inStyle;
    }

    public void setOutStyle(Integer outStyle) {
        OutStyle = outStyle;
    }

    public void setSpeed(Integer speed) {
        Speed = speed;
    }

    @JsonIgnore
    public Integer getDelay() {
        return Delay;
    }

    @JsonIgnore
    public Integer getInStyle() {
        return InStyle;
    }

    @JsonIgnore
    public Integer getOutStyle() {
        return OutStyle;
    }

    @JsonIgnore
    public Integer getSpeed() {
        return Speed;
    }

    @JsonIgnore
    public CmsPlaylistInfo(){
        Texts = new ArrayList<CmsText>();
    }
    @JsonProperty
    private Integer Delay;//停留时间
    @JsonProperty
    private Integer InStyle;//入屏方式
    @JsonProperty
    private Integer OutStyle;//出屏方式
    @JsonProperty
    private Integer Speed;//入屏速度


    @JsonIgnore
    public List<CmsText> getTexts() {
        return Texts;
    }

    @JsonProperty
    private List<CmsText> Texts;
}
