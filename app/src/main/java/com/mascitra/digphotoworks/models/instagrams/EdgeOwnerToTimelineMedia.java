package com.mascitra.digphotoworks.models.instagrams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by blegoh on 17/10/17.
 */

public class EdgeOwnerToTimelineMedia {
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("page_info")
    @Expose
    public PageInfo pageInfo;
    @SerializedName("edges")
    @Expose
    public List<Edge> edges = null;

}
