package com.mascitra.digphotoworks.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mascitra.digphotoworks.R;

import java.util.Calendar;

public class Pemesanan extends AppCompatActivity implements View.OnClickListener {
    EditText ed_nama,ed_telp,ed_tgl, ed_jam;
    TextView tv_nm_paket, tv_jml_tmbahan, tv_hrg_standart,tv_hrg_tambahan,tv_total_biaya;
    Button btnSubmit;
    ImageButton ibjam,ibtgl;
    String nama_paket, jml_tambahan,harga_awal,hrg_tambahan,total;
    int tahun,bulan,hari;
    int jam,menit;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        ibtgl = (ImageButton)findViewById(R.id.ib_tgl);
        ibjam = (ImageButton)findViewById(R.id.ib_jam);
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
        ibjam.setOnClickListener(this);
        ibtgl.setOnClickListener(this);

        final Calendar cal = Calendar.getInstance();
        tahun = cal.get(Calendar.YEAR);
        bulan = cal.get(Calendar.MONTH);
        hari = cal.get(Calendar.DAY_OF_MONTH);

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
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID){
            return new DatePickerDialog(this, dpickerlistener, tahun,bulan,hari);

        } else if (id == DIALOG_ID1){
            return new TimePickerDialog(this, kTimePickerDialog, jam, menit, false);
        }
            return null;
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerDialog = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            jam = hour;
            menit = minute;
            ed_jam.setText(jam+":"+menit);
        }
    };

    private DatePickerDialog.OnDateSetListener dpickerlistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            tahun = year;
            bulan = month + 1;
            hari = day;

            ed_tgl.setText(tahun+"/"+bulan+"/"+hari);
        }
    };

    @Override
    public void onClick(View view) {
        if (view == btnSubmit){
            Toast.makeText(Pemesanan.this, "Rincian : " + "\n"
                    +"Nama : " + ed_nama.getText() + "\n"
                    + "No Telp : " + ed_telp.getText() + "\n"
                    + "Tanggal : " + ed_tgl.getText() + "\n"
                    + "Jam : " + ed_jam.getText(), Toast.LENGTH_SHORT).show();
        }

        if (view == ibtgl){
            showDialog(DIALOG_ID);
        }

        if (view == ibjam){
            showDialog(DIALOG_ID1);
        }

    }
}
