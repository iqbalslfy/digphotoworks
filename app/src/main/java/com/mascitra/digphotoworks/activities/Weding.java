package com.mascitra.digphotoworks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapters.ProductAdapter;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.BaseResponse;
import com.mascitra.digphotoworks.responses.ProductResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Weding extends AppCompatActivity {

    @BindView(R.id.txtCari)
    EditText search;

    @BindView(R.id.rc_product)
    RecyclerView recyclerView;

    private ProductAdapter productAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, new ArrayList<com.mascitra.digphotoworks.models.Product>(0));
        recyclerView.setAdapter(productAdapter);

        loadProduct();
    }

    @OnTextChanged(R.id.txtCari)
    void search(){
        Log.d("search",search.getText().toString());
        productAdapter.search(search.getText().toString());
    }

    public void loadProduct() {
        Call<BaseResponse<ProductResponse>> call;
        call = RetrofitApi.getInstance().getApiService("").wedding();
        call.enqueue(new Callback<BaseResponse<ProductResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<ProductResponse>> call, Response<BaseResponse<ProductResponse>> response) {
                if(response.isSuccessful()) {
                    List<com.mascitra.digphotoworks.models.Product> products = response.body().getData().getProduct();
                    productAdapter.updateProducts(products);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ProductResponse>> call, Throwable t) {
                Toast.makeText(Weding.this, AppsCore.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
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
