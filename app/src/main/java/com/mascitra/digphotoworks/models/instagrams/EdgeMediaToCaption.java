package com.mascitra.digphotoworks.models.instagrams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by blegoh on 17/10/17.
 */

public class EdgeMediaToCaption {
    @SerializedName("edges")
    @Expose
    public List<Edge_> edges = null;

    public List<Edge_> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge_> edges) {
        this.edges = edges;
    }
}
