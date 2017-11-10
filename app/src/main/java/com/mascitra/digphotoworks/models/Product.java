package com.mascitra.digphotoworks.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by blegoh on 28/10/17.
 */

public class Product implements Parcelable {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("detail")
    @Expose
    public String detail;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("price_plus")
    @Expose
    public Integer pricePlus;
    @SerializedName("people")
    @Expose
    public Integer people;
    @SerializedName("category_id")
    @Expose
    public Integer categoryId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPricePlus() {
        return pricePlus;
    }

    public void setPricePlus(Integer pricePlus) {
        this.pricePlus = pricePlus;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    // Parcelling part
    public Product(Parcel in) {
        String[] data = new String[8];

        in.readStringArray(data);
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.image = data[2];
        this.detail = data[3];
        this.price = Integer.parseInt(data[4]);
        this.pricePlus = Integer.parseInt(data[5]);
        this.people = Integer.parseInt(data[6]);
        this.categoryId = Integer.parseInt(data[7]);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
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
                this.name,
                this.image,
                this.detail,
                this.price + "",
                this.pricePlus + "",
                this.people + "",
                this.categoryId +""
        });
    }
}
