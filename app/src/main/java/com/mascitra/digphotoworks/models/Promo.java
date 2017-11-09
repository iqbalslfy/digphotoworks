package com.mascitra.digphotoworks.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by blegoh on 06/11/17.
 */

public class Promo implements Parcelable{
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("product_id")
    @Expose
    public Integer productId;
    @SerializedName("start")
    @Expose
    public String start;
    @SerializedName("end")
    @Expose
    public String end;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("product")
    @Expose
    public Product product;
    @SerializedName("details")
    @Expose
    public List<Detail> details = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    // Parcelling part
    public Promo(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);
        this.id = Integer.parseInt(data[0]);
        this.productId = Integer.parseInt(data[1]);
        this.start = data[2];
        this.end = data[3];

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Promo createFromParcel(Parcel in) {
            return new Promo(in);
        }

        public Promo[] newArray(int size) {
            return new Promo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                this.id+"",
                this.productId+"",
                this.start,
                this.end,
        });
    }


}
