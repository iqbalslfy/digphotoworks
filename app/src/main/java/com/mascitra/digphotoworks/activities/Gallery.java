package com.mascitra.digphotoworks.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapters.ImageAdapter;
import com.mascitra.digphotoworks.models.instagrams.Edge;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.InstagramResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gallery extends AppCompatActivity {
    ImageAdapter imageAdapter;
    GridView gridview;
    String endCursor;
    Boolean hasNextPage = false;
    ProgressDialog loading;
    ArrayList<String> cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cek = new ArrayList<>();
        loading = new ProgressDialog(this);
        imageAdapter = new ImageAdapter(this);
        ButterKnife.bind(this);
        loading.setTitle("Loading");
        loading.setMessage("Wait while loading...");
        loading.setCancelable(false); // disable dismiss by tapping outside of the dialog
        loading.show();
        loadInsatgram(false);

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putString("image",imageAdapter.getItem(position));
                i.putExtras(b);
                i.setClass(Gallery.this, LoadImage.class);
                startActivity(i);
            }
        });
        gridview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount >= totalItemCount){
                    if (hasNextPage){
                        if (!(cek.size()>0 && cek.get(cek.size()-1).equals(endCursor))){
                            loading.setTitle("Loading");
                            loading.setMessage("Wait while loading...");
                            loading.setCancelable(false);
                            loading.show();
                            loadInsatgram(true);
                        }
                    }
                }
            }
        });
    }

    public void loadInsatgram(boolean next)
    {
        Call<InstagramResponse> call;
        String variables = "";
        if (next){
            cek.add(endCursor);
            variables = "{\"id\":\"4016255810\",\"first\":12,\"after\":\""+endCursor+"\"}";
        }else{
            variables = "{\"id\":\"4016255810\",\"first\":12}";
        }
        call = RetrofitApi.getInstance(true).getApiService("").instagram(variables);
        call.enqueue(new Callback<InstagramResponse>() {
            @Override
            public void onResponse(Call<InstagramResponse> call, Response<InstagramResponse> response) {

                if(response.isSuccessful()) {
                    endCursor = response.body().getData().getUser().getEdgeOwnerToTimelineMedia().getPageInfo().getEndCursor();
                    hasNextPage = response.body().getData().getUser().getEdgeOwnerToTimelineMedia().getPageInfo().getHasNextPage();
                    List<Edge> edges = response.body().getData().getUser().getEdgeOwnerToTimelineMedia().getEdges();
                    for (int i = 0; i < edges.size();i++){
                        String thumbnail_src = edges.get(i).getNode().getThumbnailSrc();
                        imageAdapter.addImage(thumbnail_src);
                    }
                    Log.d("isine",imageAdapter.getCount()+"");
                    gridview.invalidateViews();
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InstagramResponse> call, Throwable t) {
                loading.dismiss();
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
