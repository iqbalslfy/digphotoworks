package com.mascitra.digphotoworks.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapter.ImageAdapter;
import com.mascitra.digphotoworks.models.instagrams.Edge;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.InstagramResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gallery extends AppCompatActivity {
    private WebView webView = null;
    private ProgressDialog progressBar;
    ImageAdapter imageAdapter;
    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageAdapter = new ImageAdapter(this);

        loadInsatgram();

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(Gallery.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void loadInsatgram()
    {
        Call<InstagramResponse> call;
        call = RetrofitApi.getInstance(true).getApiService("").instagram();
        call.enqueue(new Callback<InstagramResponse>() {
            @Override
            public void onResponse(Call<InstagramResponse> call, Response<InstagramResponse> response) {
                if(response.isSuccessful()) {
                    List<Edge> edges = response.body().getData().getUser().getEdgeOwnerToTimelineMedia().getEdges();
                    for (int i = 0; i < edges.size();i++){
                        String thumbnail_src = edges.get(i).getNode().getThumbnailSrc();
                        imageAdapter.addImage(thumbnail_src);
                    }
                    Log.d("isine",imageAdapter.getCount()+"");
                    gridview.invalidateViews();
                }
            }

            @Override
            public void onFailure(Call<InstagramResponse> call, Throwable t) {
                Toast.makeText(Gallery.this, AppsCore.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
