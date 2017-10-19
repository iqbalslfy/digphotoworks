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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
