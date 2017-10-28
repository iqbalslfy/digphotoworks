package com.mascitra.digphotoworks.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapters.ImageAdapter;
import com.mascitra.digphotoworks.models.instagrams.Edge;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.InstagramResponse;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gallery extends AppCompatActivity {
    private WebView webView = null;
    private ProgressDialog progressBar;
    ImageAdapter imageAdapter;
    GridView gridview;
    String endCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageAdapter = new ImageAdapter(this);
        ButterKnife.bind(this);
        loadInsatgram(false);

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

    @OnClick(R.id.button)
    void nextPost()
    {
        loadInsatgram(true);
    }

    public void loadInsatgram(boolean next)
    {
        Call<InstagramResponse> call;
        String variables = "";
        if (next){
            variables = "{\"id\":\"29614503\",\"first\":12,\"after\":\""+endCursor+"\"}";
        }else{
            variables = "{\"id\":\"29614503\",\"first\":12}";
        }
        call = RetrofitApi.getInstance(true).getApiService("").instagram(variables);
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
                    endCursor = response.body().getData().getUser().getEdgeOwnerToTimelineMedia().getPageInfo().getEndCursor();
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
