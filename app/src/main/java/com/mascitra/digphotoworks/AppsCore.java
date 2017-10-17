package com.mascitra.digphotoworks;

import android.app.Application;

/**
 * Created by blegoh on 17/10/17.
 */

public class AppsCore extends Application{
    public static final String BASE_URL = "http://188.166.179.34:8080/";
    public static final String BASE_IMAGE = "http://188.166.179.34:8080/";
    public static final String TOKEN= "b7ytbe2DgHhpW4ss1wzR1NtMWpLROxSFHgoe64bl";
    //    public static final String GOOGLE_API_KEY = "AIzaSyCqk5uuqfG8Ska3ENzJHe5PNGTtNTXhvpk";
    public static final String ERROR_NETWORK = "Error. Please check your internet connection";
    public static final String ERROR = "An error has occurred, ";
    public static final String TOKEN_EXPIRED = "Sesi login anda telah habis, silahkan melakukan login ulang !";
    public static final String TAG_TOKEN_EXPIRED = "token_expired";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
