package com.mascitra.digphotoworks.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.MainActivity;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.models.Product;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.BaseResponse;
import com.mascitra.digphotoworks.responses.OrderResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    Product product;

    int tambahan;

    int tahun,bulan,hari;
    int jam,menit;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID1 = 1;

    DecimalFormat myFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        ButterKnife.bind(this);
        DecimalFormatSymbols otherSymbols;
        otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        myFormatter = new DecimalFormat("###,###.###", otherSymbols);

        Bundle b = getIntent().getExtras();
        product = b.getParcelable("product");
        tambahan = b.getInt("tambahan");

        tvNmPaket.setText(product.getName());
        tvJmlTmbahan.setText(tambahan+"");
        tvHrgStandart.setText("Rp "+myFormatter.format(product.getPrice()));
        tvHrgTambahan.setText("Rp "+myFormatter.format(product.getPricePlus()));
        tvTotalBiaya.setText("Rp "+myFormatter.format((product.getPrice()+(product.getPricePlus()*tambahan))));


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
        String time = tahun+"-"+bulan+"-"+hari+" "+jam+":"+menit+":00";
        Call<BaseResponse<OrderResponse>> call;
        call = RetrofitApi.getInstance().getApiService("").order(edNama.getText().toString(),edTelp.getText().toString(),
                time, tambahan,(product.getPrice()+(product.getPricePlus()+tambahan)),product.getId());
        call.enqueue(new Callback<BaseResponse<OrderResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<OrderResponse>> call, Response<BaseResponse<OrderResponse>> response) {
                if(response.isSuccessful()) {
                    startActivity(new Intent(Pemesanan.this, MainActivity.class));
                    Toast.makeText(Pemesanan.this, "berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(Pemesanan.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {

                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<OrderResponse>> call, Throwable t) {
                Toast.makeText(Pemesanan.this, AppsCore.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
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
