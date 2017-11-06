package com.mascitra.digphotoworks.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.mascitra.digphotoworks.AppsCore;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.activities.DetailPromo;
import com.mascitra.digphotoworks.activities.Transaksi;
import com.mascitra.digphotoworks.models.Promo;

import java.util.List;

/**
 * Created by blegoh on 06/11/17.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder>{
    private Context context;
    private List<Promo> promoList;
    private PromoAdapter.PromoItemListener promoListener;

    public PromoAdapter(final Context context, List<Promo> promoList) {
        this.context = context;
        this.promoList = promoList;
        this.promoListener = new PromoAdapter.PromoItemListener() {
            @Override
            public void onPostClick(Promo promo) {
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putParcelable("promo", promo);
                i.putExtras(b);
                i.setClass(context, DetailPromo.class);
                context.startActivity(i);
            }
        };
    }

    @Override
    public PromoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.row_horizontal,parent,false);

        PromoAdapter.ViewHolder viewHolder = new PromoAdapter.ViewHolder(view, this.promoListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PromoAdapter.ViewHolder holder, int position) {
        Promo item = promoList.get(position);
        TextView paket = holder.paket;
        TextView harga = holder.harga;
        ImageView imgDeskripsi = holder.imgDeskripsi;
        paket.setText(item.getName());
        harga.setText(item.getPrice()+"");
        String url = AppsCore.BASE_IMAGE+item.getImage();

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .build());
        Glide.with(context).load(glideUrl).into(imgDeskripsi);
    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgDeskripsi;
        public TextView paket;
        public TextView harga;

        PromoAdapter.PromoItemListener promoItemListener;

        public ViewHolder(View itemView, PromoAdapter.PromoItemListener promoItemListener) {
            super(itemView);
            imgDeskripsi = itemView.findViewById(R.id.imgPromo);
            paket = itemView.findViewById(R.id.txtNamaPromo);
            harga = itemView.findViewById(R.id.txtHargaPromo);

            this.promoItemListener = promoItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Promo item = getItem(getAdapterPosition());
            this.promoItemListener.onPostClick(item);

            notifyDataSetChanged();
        }
    }

    public void updatePromos(List<Promo> items) {
        promoList = items;
        notifyDataSetChanged();
    }

    private Promo getItem(int adapterPosition) {
        return promoList.get(adapterPosition);
    }

    public interface PromoItemListener {
        void onPostClick(Promo promo);
    }
}
