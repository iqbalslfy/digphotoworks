package com.mascitra.digphotoworks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapters.DetailAdapter;
import com.mascitra.digphotoworks.adapters.PromoAdapter;
import com.mascitra.digphotoworks.models.Detail;
import com.mascitra.digphotoworks.models.Product;
import com.mascitra.digphotoworks.models.Promo;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.BaseResponse;
import com.mascitra.digphotoworks.responses.PromoResponse;
import com.mascitra.digphotoworks.responses.PromoShowResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPromo extends AppCompatActivity {

    private Promo promo;

    @BindView(R.id.btnPesanPromo)
    Button btnPesan;

    @BindView(R.id.tv_nm_paket_detail_promo)
    TextView tvNama;

    @BindView(R.id.tvHarga_promo)
    TextView tvHarga;

    @BindView(R.id.tvDetail)
    TextView tvDetail;

    @BindView(R.id.tvPeriode)
    TextView tvPeriode;


    private RecyclerView recyclerView;
    private DetailAdapter detailAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        promo = bundle.getParcelable("promo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadPromo(promo.getId());

        recyclerView = (RecyclerView) findViewById(R.id.rc_detail);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        detailAdapter = new DetailAdapter(this, new ArrayList<Detail>(0));
        recyclerView.setAdapter(detailAdapter);
    }

    public void loadPromo(int id) {
        Call<BaseResponse<PromoShowResponse>> call;
        call = RetrofitApi.getInstance().getApiService("").promoShow(id);
        call.enqueue(new Callback<BaseResponse<PromoShowResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<PromoShowResponse>> call, Response<BaseResponse<PromoShowResponse>> response) {
                if(response.isSuccessful()) {
                    promo = response.body().getData().getData();
                    tvNama.setText(promo.getProduct().getName());
                    tvDetail.setText(promo.getProduct().getDetail());
                    tvPeriode.setText("Periode Promo "+promo.getStart()+" - "+promo.getEnd());
                    tvHarga.setText(promo.getProduct().getPrice()+"");
                    detailAdapter.updateDetails(promo.getDetails());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<PromoShowResponse>> call, Throwable t) {
                Toast.makeText(DetailPromo.this, AppsCore.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
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
