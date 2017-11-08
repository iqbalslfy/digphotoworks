package com.mascitra.digphotoworks.activities;

import android.content.Intent;
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
import com.mascitra.digphotoworks.models.Detail;
import com.mascitra.digphotoworks.models.Promo;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.BaseResponse;
import com.mascitra.digphotoworks.responses.PromoShowResponse;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPromo extends AppCompatActivity {

    private Promo promo;

    @BindView(R.id.tv_nm_paket_detail_promo)
    TextView tvNama;

    @BindView(R.id.tvHarga_promo)
    TextView tvHarga;

    @BindView(R.id.tvDetail)
    TextView tvDetail;

    @BindView(R.id.tvPeriode)
    TextView tvPeriode;

    @BindView(R.id.rc_detail)
    RecyclerView recyclerView;

    private DetailAdapter detailAdapter;
    private RecyclerView.LayoutManager layoutManager;
    DecimalFormat myFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);
        ButterKnife.bind(this);
        DecimalFormatSymbols otherSymbols;
        otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        myFormatter = new DecimalFormat("###,###.###", otherSymbols);
        Bundle bundle = getIntent().getExtras();
        promo = bundle.getParcelable("promo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadPromo(promo.getId());

        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        detailAdapter = new DetailAdapter(this, new ArrayList<Detail>(0));
        recyclerView.setAdapter(detailAdapter);
    }

    private void loadPromo(int id) {
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
                    tvHarga.setText("Rp "+myFormatter.format(promo.getProduct().getPrice()));
                    detailAdapter.updateDetails(promo.getDetails());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<PromoShowResponse>> call, Throwable t) {
                Toast.makeText(DetailPromo.this, AppsCore.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnProduct)
    public void onClickButton()
    {
        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putParcelable("product", promo.getProduct());
        i.putExtras(b);
        i.setClass(DetailPromo.this, Transaksi.class);
        startActivity(i);
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
