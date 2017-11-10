package com.mascitra.digphotoworks.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mascitra.digphotoworks.models.OrderW;

/**
 * Created by blegoh on 10/11/17.
 */

public class OrderWResponse {
    @SerializedName("data")
    @Expose
    public OrderW data;

    public OrderW getData() {
        return data;
    }

    public void setData(OrderW data) {
        this.data = data;
    }
}
