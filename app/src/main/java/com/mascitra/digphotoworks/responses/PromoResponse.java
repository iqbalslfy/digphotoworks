package com.mascitra.digphotoworks.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mascitra.digphotoworks.models.Promo;

import java.util.List;

/**
 * Created by blegoh on 06/11/17.
 */

public class PromoResponse {
    @SerializedName("promo")
    @Expose
    public List<Promo> promo = null;

    public List<Promo> getPromo() {
        return promo;
    }

    public void setPromo(List<Promo> promo) {
        this.promo = promo;
    }
}
