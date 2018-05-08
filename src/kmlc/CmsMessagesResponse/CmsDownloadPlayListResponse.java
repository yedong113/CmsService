package kmlc.CmsMessagesResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kmlc.CmsMessages.CmsPlayList;

public class CmsDownloadPlayListResponse {
    @JsonIgnore
    public String getCmsCtrlId() {
        return CmsCtrlId;
    }

    public void setCmsCtrlId(String cmsCtrlId) {
        CmsCtrlId = cmsCtrlId;
    }

    @JsonProperty
    private String CmsCtrlId;



    @JsonProperty
    private String Id;



    public void setId(String Id) {
        this.Id = Id;
    }

    public void setPlayNo(Integer playNo) {
        PlayNo = playNo;
    }

    @JsonIgnore
    public String getId() {
        return Id;
    }

    @JsonIgnore
    public Integer getPlayNo() {
        return PlayNo;
    }

    @JsonIgnore
    public CmsPlayList getPlayList() {
        return Playlist;
    }

    @JsonProperty
    CmsPlayList Playlist = new CmsPlayList();

    @JsonProperty
    private Integer PlayNo;
}
