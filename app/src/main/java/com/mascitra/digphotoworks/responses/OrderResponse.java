package com.mascitra.digphotoworks.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mascitra.digphotoworks.models.Order;

/**
 * Created by blegoh on 06/11/17.
 */

public class OrderResponse {
    @SerializedName("data")
    @Expose
    public Order data;

    public Order getData() {
        return data;
    }

    public void setData(Order data) {
        this.data = data;
    }
}
