package kmlc.CmsMessages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadPlayListRequest {

    @JsonProperty
    private String id;

    public CmsPlayList getPlayList() {
        return playList;
    }

    CmsPlayList playList = new CmsPlayList();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
