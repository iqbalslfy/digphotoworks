package com.mascitra.digphotoworks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.adapters.DataAdapterPW;
import com.mascitra.digphotoworks.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Preweding extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataAdapterPW dataAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Product> productList = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preweding);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tampilProduct();

        recyclerView = (RecyclerView) findViewById(R.id.rc_preweding);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapterPW(productList, this);
        recyclerView.setAdapter(dataAdapter);

    }

    private void tampilProduct() {
        productList.add(new Product(R.drawable.paket4, "Paket 1", "350000", "yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te."));
        productList.add(new Product(R.drawable.paket2, "Paket 2", "150000", "Lorem ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te."));
        productList.add(new Product(R.drawable.paket3, "Paket 3", "250000", "amber ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te."));
        productList.add(new Product(R.drawable.paket4, "Paket 4", "350000", "iqbal ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te."));
        productList.add(new Product(R.drawable.paket5, "Paket 5", "150000", "amber ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te."));
        productList.add(new Product(R.drawable.paket4, "Paket 6", "300000", "iqbal ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te.yofan ipsum dolor sit amet, ne meis soleat probatus mei. Velit adipiscing ad qui. An qui regione integre intellegam. Eu quo enim agam adipiscing, enim utinam dissentiunt eam ex. Et sed apeirian forensibus, id mel semper minimum phaedrum. Ad sumo ancillae molestie vis, summo percipit tractatos vel te."));
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
