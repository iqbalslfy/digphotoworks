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
import com.mascitra.digphotoworks.models.Product;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Pemesanan extends AppCompatActivity  {

    @BindView(R.id.et_nama)
    EditText edNama;

    @BindView(R.id.et_no_telp)
    EditText edTelp;

    @BindView(R.id.et_tgl_pesan)
    EditText edTgl;

    @BindView(R.id.et_jam_pesan)
    EditText edJam;

    @BindView(R.id.tv_isi_paket_p)
    TextView tvNmPaket;

    @BindView(R.id.tv_isi_jml_p)
    TextView  tvJmlTmbahan;

    @BindView(R.id.tv_isi_hrg_standart)
    TextView  tvHrgStandart;

    @BindView(R.id.tv_isi_hrg_tambahan)
    TextView  tvHrgTambahan;

    @BindView(R.id.tv_total_biaya)
    TextView  tvTotalBiaya;

    Button btnSubmit;

    Product product;

    int tambahan;

    int tahun,bulan,hari;
    int jam,menit;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        ButterKnife.bind(this);

        btnSubmit = (Button) findViewById(R.id.btnSubmit_p);

        Bundle b = getIntent().getExtras();
        product = b.getParcelable("product");
        tambahan = b.getInt("tambahan");

        tvNmPaket.setText(product.getName());
        tvJmlTmbahan.setText(tambahan+"");
        tvHrgStandart.setText(product.getPrice()+"");
        tvHrgTambahan.setText(product.getPricePlus()+"");
        tvTotalBiaya.setText((product.getPrice()+(product.getPricePlus()+tambahan))+"");


        final Calendar cal = Calendar.getInstance();
        tahun = cal.get(Calendar.YEAR);
        bulan = cal.get(Calendar.MONTH);
        hari = cal.get(Calendar.DAY_OF_MONTH);

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
            edJam.setText(jam+":"+menit);
        }
    };

    private DatePickerDialog.OnDateSetListener dpickerlistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            tahun = year;
            bulan = month + 1;
            hari = day;

            edTgl.setText(tahun+"/"+bulan+"/"+hari);
        }
    };

    @OnClick(R.id.btnSubmit)
    public void submit() {

    }

    @OnClick(R.id.ib_tgl)
    public void tgl(){
        showDialog(DIALOG_ID);
    }

    @OnClick(R.id.ib_jam)
    public void jam(){
        showDialog(DIALOG_ID1);
    }

}
