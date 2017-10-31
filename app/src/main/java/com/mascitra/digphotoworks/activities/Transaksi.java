package com.mascitra.digphotoworks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.models.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Transaksi extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.txtPaketDetail)
    TextView tvpaket;

    @BindView(R.id.txtHargaItemDetail)
    TextView tvharga;

    @BindView(R.id.txt_jml_pp)
    TextView tvhargaAwal;

    @BindView(R.id.txtDeskripsiItem)
    TextView tvdetail;

    @BindView(R.id.txt_jml_tmbh_pp)
    TextView tvtambahan;

    @BindView(R.id.txt_hasil_final_pp)
    TextView tvHasilFinal;

    @BindView(R.id.txt_harga_final_bawah_pp)
    TextView tvHasilFinal2;

    @BindView(R.id.imgPemesananDetail)
    ImageView  img;

    @BindView(R.id.jmlPlus)
    TextView jmlText;

    @BindView(R.id.btnMin)
    ImageButton btnMin;

    @BindView(R.id.btnPlus)
    ImageButton btnPlus;

    @BindView(R.id.btnSubmit)
    Button btnPesan;

    Integer jmlAngka,v2,v3,Tambahan,hargaAwal,Total,Total2;

    String hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        Product product = bundle.getParcelable("product");

        jmlText.setText("0");

        tvpaket.setText(product.getName());
        tvharga.setText(product.getPrice()+"");
        tvhargaAwal.setText(product.getPrice()+"");
        tvHasilFinal.setText(product.getPrice()+"");
        tvHasilFinal2.setText(product.getPrice()+"");
        tvdetail.setText(product.getDetail());
        String url = AppsCore.BASE_IMAGE+product.getImage();

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .build());
        Glide.with(this).load(glideUrl).into(img);

        btnMin.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnPesan.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    void decimal(){
        double hrg_awl = Double.parseDouble(tvharga.getText().toString());
        double hrg_awl2 = Double.parseDouble(tvhargaAwal.getText().toString());
        double hrg_tmbahan = Double.parseDouble(tvtambahan.getText().toString());
        double total1 = Double.parseDouble(tvHasilFinal.getText().toString());


        String hrg_awls = String.format("%,.0f", hrg_awl).replaceAll(",", ".");
        String hrg_awls2 = String.format("%,.0f", hrg_awl2).replaceAll(",", ".");
        String hrg_tmbahans = String.format("%,.0f", hrg_tmbahan).replaceAll(",", ".");
        String total1s = String.format("%,.0f", total1).replaceAll(",", ".");


        tvharga.setText(hrg_awls);
        tvhargaAwal.setText(hrg_awls2);
        tvtambahan.setText(hrg_tmbahans);
        tvHasilFinal.setText(total1s);
        tvHasilFinal2.setText(total1s);
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

    public void convert (){
        jmlAngka = Integer.valueOf((jmlText.getText().toString()));
        v2 = Integer.valueOf(("1"));
        v3 = Integer.valueOf(("10000"));
        Tambahan = Integer.valueOf((tvtambahan.getText().toString()));
        hargaAwal = Integer.valueOf((tvhargaAwal.getText().toString()));
        Total = Integer.valueOf((tvHasilFinal.getText().toString()));
        Total2 = Integer.valueOf((tvHasilFinal2.getText().toString()));
    }

    public void FuncAngkaUp(){
        convert();
        hasil = String.valueOf(jmlAngka+v2);
        jmlText.setText(hasil);
    }

    public void FuncAngkaDown(){
        convert();
        hasil = String.valueOf(jmlAngka-v2);
        jmlText.setText(hasil);
    }

    public void PerhitunganTambahan(){
        convert();
        hasil = String.valueOf(jmlAngka*v3);
        System.out.println("penambahan : "+hasil);
        tvtambahan.setText(hasil);
    }

    public void TotalFinal(){
        convert();
        hasil = String.valueOf(hargaAwal+Tambahan);
        tvHasilFinal.setText(hasil);
        tvHasilFinal2.setText(hasil);
    }

    public void PenguranganFinal(){
        convert();
        hasil = String.valueOf(Total-v3);
        tvHasilFinal.setText(hasil);
        tvHasilFinal2.setText(hasil);
        if (Integer.parseInt(tvHasilFinal.getText().toString()) < Integer.parseInt(tvharga.getText().toString())){
            Toast.makeText(this, "Jangan dilanjutkan, karena melewati  " + tvharga.getText(), Toast.LENGTH_LONG).show();
            tvHasilFinal.setText(tvharga.getText());
            tvHasilFinal2.setText(tvharga.getText());
            jmlText.setText("0");
        }
    }

    public void PenguranganTambahan(){
        convert();
        hasil = String.valueOf(jmlAngka*v3);
        System.out.println("penambahan : "+hasil);
        tvtambahan.setText(hasil);

    }


    @Override
    public void onClick(View view) {
        if (view == btnMin){
            FuncAngkaDown();
            PenguranganFinal();
            PenguranganTambahan();


        }

        if (view == btnPlus){
            FuncAngkaUp();
            PerhitunganTambahan();
            TotalFinal();

        }

        if (view == btnPesan){



            Intent intent = new Intent(Transaksi.this, Pemesanan.class);

            Bundle b = new Bundle();
            b.putString("nm_paket", tvpaket.getText().toString());
            b.putString("jml_tambahan", jmlText.getText().toString());
            b.putString("hrg_awal", tvharga.getText().toString());
            b.putString("tambahan", tvtambahan.getText().toString());
            b.putString("total", tvHasilFinal.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
        }

    }
}
