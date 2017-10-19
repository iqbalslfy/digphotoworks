package com.mascitra.digphotoworks.models.instagrams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by blegoh on 17/10/17.
 */

public class Instagram {
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("status")
    @Expose
    public String status;
}