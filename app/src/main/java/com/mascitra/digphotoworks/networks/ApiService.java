package com.mascitra.digphotoworks.networks;

import com.mascitra.digphotoworks.responses.BaseResponse;
import com.mascitra.digphotoworks.responses.InstagramResponse;
import com.mascitra.digphotoworks.responses.OrderResponse;
import com.mascitra.digphotoworks.responses.OrderWResponse;
import com.mascitra.digphotoworks.responses.ProductResponse;
import com.mascitra.digphotoworks.responses.PromoResponse;
import com.mascitra.digphotoworks.responses.PromoShowResponse;
import com.mascitra.digphotoworks.responses.YouTubeResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by blegoh on 19/10/17.
 */

public interface ApiService {

    @GET("?query_id=17888483320059182")
    Call<InstagramResponse> instagram(@Query("variables") String variables);

    @GET("/api/photoStudio")
    Call<BaseResponse<ProductResponse>> photoStudio();

    @GET("/api/makeUp")
    Call<BaseResponse<ProductResponse>> makeUp();

    @GET("/api/wedding")
    Call<BaseResponse<ProductResponse>> wedding();

    @GET("/api/preWedding")
    Call<BaseResponse<ProductResponse>> preWedding();

    @GET("/api/product")
    Call<BaseResponse<ProductResponse>> product();

    @GET("/api/promo")
    Call<BaseResponse<PromoResponse>> promo();

    @GET("/api/promo/{id}")
    Call<BaseResponse<PromoShowResponse>> promoShow(@Path("id") int id);

    @POST("/api/order")
    @FormUrlEncoded
    Call<BaseResponse<OrderResponse>> order(
            @Field("name") String email,
            @Field("phone") String phone,
            @Field("tgl_waktu_pesan") String time,
            @Field("tambahan") int tambahan,
            @Field("total") int total,
            @Field("product_id") int productId);

    @POST("/api/orderw")
    @FormUrlEncoded
    Call<BaseResponse<OrderWResponse>> orderW(
            @Field("name") String email,
            @Field("phone") String phone,
            @Field("waktu") String waktu,
            @Field("date") String date,
            @Field("total") int total,
            @Field("product_id") int productId);

    @GET("search?key=AIzaSyDjw8VSvL3kB7HfcXSxb7KiPqr51O4OqIM&channelId=UCaCI_aYqyamCllaB0mvFKCQ&part=snippet,id&order=date&maxResults=20")
    Call<YouTubeResponse> youtube();

}
