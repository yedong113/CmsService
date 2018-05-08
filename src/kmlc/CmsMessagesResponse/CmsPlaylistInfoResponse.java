package kmlc.CmsMessagesResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kmlc.CmsMessages.CmsPlaylistInfo;

import java.util.ArrayList;
import java.util.List;

public class CmsPlaylistInfoResponse {

    @JsonIgnore
    public List<CmsPlaylistInfo> getInfos() {
        return Infos;
    }

    @JsonProperty
    List<CmsPlaylistInfo> Infos = new ArrayList<CmsPlaylistInfo>();

    @JsonIgnore
    public Integer getPlayNo() {
        return PlayNo;
    }
    @JsonIgnore
    public void setPlayNo(Integer playNo) {
        PlayNo = playNo;
    }

    @JsonProperty
    private Integer PlayNo;
}
