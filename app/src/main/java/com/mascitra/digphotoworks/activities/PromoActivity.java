package com.mascitra.digphotoworks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapters.PromoAdapter;
import com.mascitra.digphotoworks.models.Promo;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.BaseResponse;
import com.mascitra.digphotoworks.responses.PromoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PromoAdapter promoAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.rc_promo);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        promoAdapter = new PromoAdapter(this, new ArrayList<Promo>(0));
        recyclerView.setAdapter(promoAdapter);

        loadPromo();
        /*text = (EditText)findViewById(R.id.txtCariPromo);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Pencarian.class));
            }
        });*/
    }

    public void loadPromo() {
        Call<BaseResponse<PromoResponse>> call;
        call = RetrofitApi.getInstance().getApiService("").promo();
        call.enqueue(new Callback<BaseResponse<PromoResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<PromoResponse>> call, Response<BaseResponse<PromoResponse>> response) {
                if(response.isSuccessful()) {
                    List<Promo> promos = response.body().getData().getPromo();
                    promoAdapter.updatePromos(promos);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<PromoResponse>> call, Throwable t) {
                Toast.makeText(PromoActivity.this, AppsCore.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
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
