package kmlc.CmsMessages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CmsPlayList {


    @JsonIgnore
    public List<CmsPlaylistInfo> getInfos() {
        return Infos;
    }

    @JsonProperty
    List<CmsPlaylistInfo> Infos = new ArrayList<CmsPlaylistInfo>();

}
