package com.mascitra.digphotoworks;

import android.app.Application;

/**
 * Created by blegoh on 17/10/17.
 */

public class AppsCore extends Application{
    public static final String BASE_URL = "http://127.0.0.1:8000/";
    public static final String BASE_IMAGE = "http://127.0.0.1:8000/";
    public static final String INSTAGRAM = "https://www.instagram.com/graphql/query/";
    public static final String ERROR_NETWORK = "Error. Please check your internet connection";
    public static final String ERROR = "An error has occurred, ";
    public static final String TOKEN_EXPIRED = "Sesi login anda telah habis, silahkan melakukan login ulang !";
    public static final String TAG_TOKEN_EXPIRED = "token_expired";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
