package com.mascitra.digphotoworks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mascitra.digphotoworks.R;

public class DetailPromo extends AppCompatActivity implements View.OnClickListener {

    TextView tvnama,tvharga;
   private Button btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);

        tvnama = (TextView) findViewById(R.id.tv_nm_paket_detail_promo);
        tvharga = (TextView)findViewById(R.id.tvHarga_promo);

        btnPesan = (Button)findViewById(R.id.btnPesanPromo);

        Bundle bundle = getIntent().getExtras();
        tvnama.setText(bundle.getCharSequence("paket"));
        tvharga.setText(bundle.getCharSequence("harga"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnPesan.setOnClickListener(this);

//        double harga = Double.parseDouble(tvharga.getText().toString());
//        String hrg = String.format("%,.0f", harga).replaceAll(",", ".");
//        tvharga.setText(hrg);

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
        if (view == btnPesan){
            Intent intent = new Intent(DetailPromo.this, TransaksiTwo.class);
            Bundle b = new Bundle();

            b.putString("paket", tvnama.getText().toString());
            b.putString("harga", tvharga.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
        }
    }
}
