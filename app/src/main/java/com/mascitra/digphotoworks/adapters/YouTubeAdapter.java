package com.mascitra.digphotoworks.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.activities.PlayActivity;
import com.mascitra.digphotoworks.activities.Transaksi;
import com.mascitra.digphotoworks.models.youtubes.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blegoh on 02/11/17.
 */

public class YouTubeAdapter extends RecyclerView.Adapter<YouTubeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Item> itemList;
    private YouTubeAdapter.ItemListener itemListener;

    public YouTubeAdapter(final Context context) {
        this.context = context;
        this.itemList = new ArrayList<>();
        this.itemListener = new YouTubeAdapter.ItemListener() {
            @Override
            public void onPostClick(Item item) {
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putString("yt",item.getId().getVideoId());
                i.putExtras(b);
                i.setClass(context, PlayActivity.class);
                context.startActivity(i);
            }
        };
    }

    @Override
    public YouTubeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_vid,parent,false);

        YouTubeAdapter.ViewHolder viewHolder = new YouTubeAdapter.ViewHolder(view, this.itemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(YouTubeAdapter.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        TextView txtTitle = holder.txtTitle;
        ImageView imgPreview = holder.imgPreview;
        txtTitle.setText(item.getSnippet().getDescription());
        String url = item.getSnippet().getThumbnails().get_default().getUrl();

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .build());
        Glide.with(context).load(glideUrl).into(imgPreview);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgPreview;
        public TextView txtTitle;

        YouTubeAdapter.ItemListener ItemListener;

        public ViewHolder(View itemView, YouTubeAdapter.ItemListener ItemListener) {
            super(itemView);
            imgPreview = itemView.findViewById(R.id.imgPreview);
            txtTitle = itemView.findViewById(R.id.txtTitle);

            this.ItemListener = ItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.ItemListener.onPostClick(item);

            notifyDataSetChanged();
        }
    }

    public void addItems(Item item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return itemList.get(adapterPosition);
    }

    public interface ItemListener {
        void onPostClick(Item id);
    }
}
