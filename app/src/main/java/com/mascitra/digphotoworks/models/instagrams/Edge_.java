package com.mascitra.digphotoworks.models.instagrams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by blegoh on 17/10/17.
 */

public class Edge_ {
    @SerializedName("node")
    @Expose
    public Node_ node;

    public Node_ getNode() {
        return node;
    }

    public void setNode(Node_ node) {
        this.node = node;
    }
}
