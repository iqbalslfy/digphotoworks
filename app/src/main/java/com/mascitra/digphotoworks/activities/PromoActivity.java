package com.mascitra.digphotoworks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapters.DataAdapterTwo;
import com.mascitra.digphotoworks.product.Promo;

import java.util.ArrayList;
import java.util.List;

public class PromoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataAdapterTwo dataAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Promo> productList = new ArrayList<Promo>();

    private EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.rc_promo);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dataAdapter = new DataAdapterTwo(productList, this);

        recyclerView.setAdapter(dataAdapter);

        tampilProduct();

        text = (EditText)findViewById(R.id.txtCariPromo);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Pencarian.class));
            }
        });
    }

    private void tampilProduct() {
        productList.add(new Promo(R.drawable.paket4, "Paket 1", "350.000"));
        productList.add(new Promo(R.drawable.paket2, "Paket 2", "150.000"));
        productList.add(new Promo(R.drawable.paket3, "Paket 3", "250.000"));
        productList.add(new Promo(R.drawable.paket4, "Paket 4", "350.000"));
        productList.add(new Promo(R.drawable.paket5, "Paket 5", "150.000"));
        productList.add(new Promo(R.drawable.paket4, "Paket 6", "300.000"));
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
}
