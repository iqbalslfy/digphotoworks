package com.mascitra.digphotoworks.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mascitra.digphotoworks.models.Promo;

/**
 * Created by blegoh on 07/11/17.
 */

public class PromoShowResponse {
    @SerializedName("data")
    @Expose
    public Promo data;

    public Promo getData() {
        return data;
    }

    public void setData(Promo data) {
        this.data = data;
    }
}
