package com.mascitra.digphotoworks.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mascitra.digphotoworks.models.instagrams.Data;

/**
 * Created by blegoh on 17/10/17.
 */

public class InstagramResponse {
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("status")
    @Expose
    public String status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
