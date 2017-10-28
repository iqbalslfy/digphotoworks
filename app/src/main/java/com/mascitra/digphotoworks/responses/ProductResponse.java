package com.mascitra.digphotoworks.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mascitra.digphotoworks.models.Product;

import java.util.List;

/**
 * Created by blegoh on 28/10/17.
 */

public class ProductResponse {

    @SerializedName("product")
    @Expose
    public List<Product> product = null;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
