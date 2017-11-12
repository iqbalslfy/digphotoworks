package com.mascitra.digphotoworks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.mascitra.digphotoworks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadImage extends AppCompatActivity {

    @BindView(R.id.imgLoad)
    ImageView imgView;

    @BindView(R.id.txtImage)
    TextView txtCaption;

    String image;
    String caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();

        image = bundle.getString("image");
        caption = bundle.getString("caption");

        GlideUrl glideUrl = new GlideUrl(image, new LazyHeaders.Builder()
                .build());
        Glide.with(this).load(glideUrl).into(imgView);

        txtCaption.setText(caption);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
