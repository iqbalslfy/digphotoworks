package com.mascitra.digphotoworks.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mascitra.digphotoworks.R;

public class PemesananVersiTwo extends AppCompatActivity implements View.OnClickListener {
    EditText ed_nama,ed_telp,ed_tgl, ed_jam;
    TextView tv_nm_paket, tv_jml_tmbahan, tv_hrg_standart,tv_hrg_tambahan,tv_total_biaya;
    Button btnSubmit;
    String nama_paket, jml_tambahan,harga_awal,hrg_tambahan,total;
    private RadioButton radioButton;
    private RadioGroup rgrub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan_versi_two);
        ed_nama = (EditText) findViewById(R.id.et_nama);
        ed_telp = (EditText) findViewById(R.id.et_no_telp);
        ed_tgl = (EditText) findViewById(R.id.et_tgl_pesan);
        ed_jam = (EditText) findViewById(R.id.et_jam_pesan);
        tv_nm_paket = (TextView) findViewById(R.id.tv_isi_paket_p);
        tv_jml_tmbahan = (TextView) findViewById(R.id.tv_isi_jml_p);
        tv_hrg_standart = (TextView) findViewById(R.id.tv_isi_hrg_standart);
        tv_hrg_tambahan = (TextView) findViewById(R.id.tv_isi_hrg_tambahan);
        tv_total_biaya = (TextView) findViewById(R.id.tv_total_biaya);
        btnSubmit = (Button) findViewById(R.id.btnSubmit_p);




        Bundle b = getIntent().getExtras();
        nama_paket  = b.getString("nm_paket");
        jml_tambahan = b.getString("jml_tambahan");
        harga_awal = b.getString("hrg_awal");
        hrg_tambahan = b.getString("tambahan");
        total = b.getString("total");

        tv_nm_paket.setText(nama_paket);
        tv_jml_tmbahan.setText(jml_tambahan);
        tv_hrg_standart.setText(harga_awal);
        tv_hrg_tambahan.setText(hrg_tambahan);
        tv_total_biaya.setText(total);

        btnSubmit.setOnClickListener(this);
        double total = Double.parseDouble(tv_total_biaya.getText().toString());
        double hrg_awl = Double.parseDouble(tv_hrg_standart.getText().toString());
        double hrg_tmbahan = Double.parseDouble(tv_hrg_tambahan.getText().toString());

        String totalDecimal = String.format("%,.0f", total).replaceAll(",", ".");
        String hrg_awl_Decimal = String.format("%,.0f", hrg_awl).replaceAll(",", ".");
        String hrg_awl_Tambahan = String.format("%,.0f", hrg_tmbahan).replaceAll(",", ".");

        tv_total_biaya.setText(totalDecimal);
        tv_hrg_standart.setText(hrg_awl_Decimal);
        tv_hrg_tambahan.setText(hrg_awl_Tambahan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void onClick(View view) {
        rgrub = (RadioGroup) findViewById(R.id.rg_option);
        int selectedId = rgrub.getCheckedRadioButtonId();

        radioButton = (RadioButton)findViewById(selectedId);
        if (view == btnSubmit){
System.out.println("memilih :" + radioButton.getText());
            Toast.makeText(PemesananVersiTwo.this, "Rincian Pemesanan : " + "\n"
                    +"Nama : " + ed_nama.getText() + "\n"
                    + "No Telp : " + ed_telp.getText() + "\n"
                    + "Pilihan Waktu : " + radioButton.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
