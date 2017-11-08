package com.mascitra.digphotoworks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.models.Product;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Transaksi extends AppCompatActivity {

    @BindView(R.id.txtPaketDetail)
    TextView tvpaket;

    @BindView(R.id.txtHargaItemDetail)
    TextView tvharga;

    @BindView(R.id.txt_jml_pp)
    TextView tvhargaAwal;

    @BindView(R.id.txtDeskripsiItem)
    TextView tvdetail;

    @BindView(R.id.txt_jml_tmbh_pp)
    TextView tvTambahan;

    @BindView(R.id.txt_hasil_final_pp)
    TextView tvHasilFinal;

    @BindView(R.id.txt_harga_final_bawah_pp)
    TextView tvHasilFinal2;

    @BindView(R.id.imgPemesananDetail)
    ImageView img;

    @BindView(R.id.jmlPlus)
    TextView jmlPlus;

    @BindView(R.id.btnMin)
    ImageButton btnMin;

    @BindView(R.id.btnPlus)
    ImageButton btnPlus;

    @BindView(R.id.btnSubmit)
    Button btnPesan;

    int jmlAngka;
    int hargaTambah;
    int tambahan = 0;

    String hasil;

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        product = bundle.getParcelable("product");

        jmlPlus.setText(tambahan+"");

        tvpaket.setText(product.getName());
        tvharga.setText(product.getPrice() + "");
        tvhargaAwal.setText(product.getPrice() + "");
        tvHasilFinal.setText(product.getPrice() + "");
        tvHasilFinal2.setText(product.getPrice() + "");
        hargaTambah = product.getPricePlus();
        tvdetail.setText(product.getDetail());
        String url = AppsCore.BASE_IMAGE + product.getImage();

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .build());
        Glide.with(this).load(glideUrl).into(img);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @OnClick(R.id.btnPlus)
    public void increase() {
        tambahan++;
        updateView();
    }

    @OnClick(R.id.btnMin)
    public void decrease() {
        if (tambahan > 0) {
            tambahan--;
            updateView();
        }
    }

    public void updateView() {
        jmlPlus.setText(tambahan+"");
        tvTambahan.setText((hargaTambah * tambahan) + "");
        tvHasilFinal.setText((product.getPrice() + (hargaTambah * tambahan)) + "");
        tvHasilFinal2.setText((product.getPrice() + (hargaTambah * tambahan)) + "");
    }

    @OnClick(R.id.btnSubmit)
    public void submit() {
        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putParcelable("product", product);
        b.putInt("tambahan",tambahan);
        i.putExtras(b);
        i.setClass(this, Pemesanan.class);
        startActivity(i);
    }

}
