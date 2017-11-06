package com.mascitra.digphotoworks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.models.Promo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPromo extends AppCompatActivity {

    private Promo promo;

    @BindView(R.id.btnPesanPromo)
    Button btnPesan;

    @BindView(R.id.tv_nm_paket_detail_promo)
    TextView tvNama;

    @BindView(R.id.tvHarga_promo)
    TextView tvHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        promo = bundle.getParcelable("promo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNama.setText(promo.getName());
        tvHarga.setText(promo.getPrice()+"");
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
