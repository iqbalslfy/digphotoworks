package com.mascitra.digphotoworks.activity;

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

import com.mascitra.digphotoworks.R;

public class TransaksiTwo extends AppCompatActivity implements View.OnClickListener {
    TextView tvpaket,tvharga,tvhargaAwal,tvdetail,tvtambahan,tvHasilFinal,tvHasilFinal2;
    ImageView img;
    TextView jmlText;
    ImageButton btnMin,BtnPlus;
    Button btnPesan;
    Integer jmlAngka,v2,v3,Tambahan,hargaAwal,Total,Total2;

    String hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_two);

        tvpaket = (TextView) findViewById(R.id.txtPaketDetail);
        tvharga = (TextView) findViewById(R.id.txtHargaItemDetail);
        tvdetail = (TextView) findViewById(R.id.txtDeskripsiItem);
        tvhargaAwal = (TextView)findViewById(R.id.txt_jml_pp);
        tvtambahan = (TextView)findViewById(R.id.txt_jml_tmbh_pp);
        tvHasilFinal = (TextView)findViewById(R.id.txt_hasil_final_pp);
        tvHasilFinal2 = (TextView)findViewById(R.id.txt_harga_final_bawah_pp);
        img = (ImageView) findViewById(R.id.imgPemesananDetail);

        jmlText = (TextView)findViewById(R.id.jmlPlus);

        jmlText.setText("0");

        btnMin = (ImageButton)findViewById(R.id.btnMin);
        BtnPlus = (ImageButton)findViewById(R.id.btnPlus);
        btnPesan = (Button)findViewById(R.id.btnSubmit);

        btnMin.setOnClickListener(this);
        BtnPlus.setOnClickListener(this);
        btnPesan.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        tvpaket.setText(bundle.getCharSequence("paket"));
        tvharga.setText(bundle.getCharSequence("harga"));
        tvhargaAwal.setText(bundle.getCharSequence("harga"));
        tvHasilFinal.setText(bundle.getCharSequence("harga"));
        tvHasilFinal2.setText(bundle.getCharSequence("harga"));

    }

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

        if (view == BtnPlus){
            FuncAngkaUp();
            PerhitunganTambahan();
            TotalFinal();

        }

        if (view == btnPesan){

            Intent intent = new Intent(TransaksiTwo.this, PemesananVersiTwo.class);

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
