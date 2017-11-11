package com.mascitra.digphotoworks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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

    @BindView(R.id.txt_text_jumlah_tambahan_org)
    TextView txtJumlahTambahanOrg;

    @BindView(R.id.txt_hrg_tmbh_pp)
    TextView txtHrgTmbh;

    @BindView(R.id.btnMin)
    ImageButton btnMin;

    @BindView(R.id.btnPlus)
    ImageButton btnPlus;

    @BindView(R.id.btnSubmit)
    Button btnPesan;

    @BindView(R.id.card_p_one)
    CardView cardViewFoto;

    int jmlAngka;
    int hargaTambah;
    int tambahan = 0;

    String hasil;

    Product product;
    DecimalFormat myFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.bind(this);

        DecimalFormatSymbols otherSymbols;
        otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        myFormatter = new DecimalFormat("###,###.###", otherSymbols);
        Bundle bundle = getIntent().getExtras();

        product = bundle.getParcelable("product");

        jmlPlus.setText(tambahan+"");

        tvpaket.setText(product.getName());
        tvharga.setText("Rp "+myFormatter.format(product.getPrice()));
        tvhargaAwal.setText("Rp "+myFormatter.format(product.getPrice()) );
        tvHasilFinal.setText("Rp "+myFormatter.format(product.getPrice() ));
        tvHasilFinal2.setText("Rp "+myFormatter.format(product.getPrice() ));
        hargaTambah = product.getPricePlus();
        tvdetail.setText(product.getDetail());
        String url = AppsCore.BASE_IMAGE + product.getImage();

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .build());
        Glide.with(this).load(glideUrl).into(img);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (product.getCategoryId() != 1){
            hidden();
        }

    }

    private void hidden()
    {
        btnMin.setVisibility(View.GONE);
        btnPlus.setVisibility(View.GONE);
        jmlPlus.setVisibility(View.GONE);
        txtJumlahTambahanOrg.setVisibility(View.GONE);
        tvTambahan.setVisibility(View.GONE);
        txtHrgTmbh.setVisibility(View.GONE);
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
        tvTambahan.setText("Rp "+myFormatter.format((hargaTambah * tambahan) ));
        tvHasilFinal.setText("Rp "+myFormatter.format((product.getPrice() + (hargaTambah * tambahan)) ));
        tvHasilFinal2.setText("Rp "+myFormatter.format((product.getPrice() + (hargaTambah * tambahan)) ));
    }

    @OnClick(R.id.btnSubmit)
    public void submit() {
        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putParcelable("product", product);
        b.putInt("tambahan",tambahan);
        i.putExtras(b);
        if (product.getCategoryId() == 3 || product.getCategoryId() == 4){
            i.setClass(this, PemesananWedding.class);
        }else{
            i.setClass(this, Pemesanan.class);
        }
        startActivity(i);
    }

    @OnClick(R.id.card_p_one)
    public void view(){
        startActivity(new Intent(getApplicationContext(), LoadImage.class));
    }


}
