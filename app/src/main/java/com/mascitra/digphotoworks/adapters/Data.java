package com.mascitra.digphotoworks.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.util.List;

/**
 * Created by SONY on 22/11/2017.
 */

public class Data {
    private String judul;
    private int ImageID;

    public Data() {

    }

    public Data(String judul, int imageID) {
        this.judul = judul;
        ImageID = imageID;
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }
}
