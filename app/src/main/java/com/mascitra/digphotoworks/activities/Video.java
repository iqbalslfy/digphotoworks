package com.mascitra.digphotoworks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapters.YouTubeAdapter;
import com.mascitra.digphotoworks.models.youtubes.Id;
import com.mascitra.digphotoworks.models.youtubes.Item;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.YouTubeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Video extends AppCompatActivity {

    private RecyclerView recyclerView;
    private YouTubeAdapter youtubeAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rc_yt);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        youtubeAdapter = new YouTubeAdapter(this);
        recyclerView.setAdapter(youtubeAdapter);

        loadVideo();

    }

    public void loadVideo() {
        Call<YouTubeResponse> call;
        call = RetrofitApi.getInstance(false).getApiService().youtube();
        call.enqueue(new Callback<YouTubeResponse>() {
            @Override
            public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {
                if(response.isSuccessful()) {
                    List<Item> items = response.body().getItems();
                    for (int i =0; i < items.size();i++){
                        Id id = items.get(i).getId();
                        if (id.getVideoId() != null)
                            youtubeAdapter.addItems(items.get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<YouTubeResponse> call, Throwable t) {
                Toast.makeText(Video.this, AppsCore.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
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
