package com.mascitra.digphotoworks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.mascitra.digphotoworks.R;

import java.util.ArrayList;

/**
 * Created by blegoh on 17/10/17.
 */

public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private final LayoutInflater mInflater;

    private ArrayList<String> ig = new ArrayList<>();
    private ArrayList<String> caption = new ArrayList<>();

    public ImageAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return this.ig.size();
    }

    public String getItem(int position) {
        return ig.get(position);
    }

    public String getCaption(int position) {
        return caption.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView picture;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_item, parent, false);
            convertView.setTag(R.id.picture, convertView .findViewById(R.id.picture));
        }
        picture = (ImageView) convertView.getTag(R.id.picture);

        String url = this.ig.get(position);

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .build());
        Glide.with(mContext).load(glideUrl).into(picture);
        return convertView;
    }

    public void addImage(String url){
        this.ig.add(url);
    }
    public void addCaption(String caption){
        this.caption.add(caption);
    }

}
