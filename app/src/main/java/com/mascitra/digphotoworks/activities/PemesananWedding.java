package com.mascitra.digphotoworks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.MainActivity;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.models.Product;
import com.mascitra.digphotoworks.networks.RetrofitApi;
import com.mascitra.digphotoworks.responses.BaseResponse;
import com.mascitra.digphotoworks.responses.OrderResponse;
import com.mascitra.digphotoworks.responses.OrderWResponse;

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

public class PemesananWedding extends AppCompatActivity {

    @BindView(R.id.et_nama)
    EditText edNama;

    @BindView(R.id.et_no_telp)
    EditText edTelp;

    @BindView(R.id.tv_isi_paket_p)
    TextView tvNmPaket;

    @BindView(R.id.tv_isi_hrg_standart)
    TextView  tvHrgStandart;

    @BindView(R.id.tv_total_biaya)
    TextView  tvTotalBiaya;

    Product product;

    DecimalFormat myFormatter;

    @BindView(R.id.rg_option)
    RadioGroup radioGroup;

    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan_versi_two);
        ButterKnife.bind(this);
        DecimalFormatSymbols otherSymbols;
        otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        myFormatter = new DecimalFormat("###,###.###", otherSymbols);

        Bundle b = getIntent().getExtras();
        product = b.getParcelable("product");

        tvNmPaket.setText(product.getName());
        tvHrgStandart.setText("Rp "+myFormatter.format(product.getPrice()));
        tvTotalBiaya.setText("Rp "+myFormatter.format(product.getPrice()));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btnSubmit)
    public void submit() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        radioButton = (RadioButton) findViewById(selectedId);

        Call<BaseResponse<OrderWResponse>> call;
        call = RetrofitApi.getInstance().getApiService("").orderW(edNama.getText().toString(),edTelp.getText().toString(),
                radioButton.getText().toString(), (product.getPrice()+product.getPricePlus()),product.getId());
        call.enqueue(new Callback<BaseResponse<OrderWResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<OrderWResponse>> call, Response<BaseResponse<OrderWResponse>> response) {
                if(response.isSuccessful()) {
                    startActivity(new Intent(PemesananWedding.this, MainActivity.class));
                    Toast.makeText(PemesananWedding.this, "berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(PemesananWedding.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {

                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<OrderWResponse>> call, Throwable t) {
                Toast.makeText(PemesananWedding.this, AppsCore.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }
}