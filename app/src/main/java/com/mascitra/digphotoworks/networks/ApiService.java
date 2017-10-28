package com.mascitra.digphotoworks.networks;

import com.mascitra.digphotoworks.responses.InstagramResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by blegoh on 19/10/17.
 */

public interface ApiService {

    @GET("?query_id=17888483320059182")
    Call<InstagramResponse> instagram(@Query("variables") String variables);

}
