package com.mascitra.digphotoworks.networks;

import com.mascitra.digphotoworks.responses.BaseResponse;
import com.mascitra.digphotoworks.responses.InstagramResponse;
import com.mascitra.digphotoworks.responses.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
