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
import com.mascitra.digphotoworks.activities.Transaksi;
import com.mascitra.digphotoworks.models.Product;

import java.util.List;

/**
 * Created by blegoh on 28/10/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private Context context;
    private List<Product> productList;
    private ProductItemListener productListener;

    public ProductAdapter(final Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.productListener = new ProductItemListener() {
            @Override
            public void onPostClick(Product product) {
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putParcelable("product", product);
                i.putExtras(b);
                i.setClass(context, Transaksi.class);
                context.startActivity(i);
            }
        };
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.row_layout,parent,false);

        ProductAdapter.ViewHolder viewHolder = new ProductAdapter.ViewHolder(view, this.productListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product item = productList.get(position);
        TextView paket = holder.paket;
        TextView harga = holder.harga;
        TextView deskripsi = holder.deskripsi;
        ImageView imgDeskripsi = holder.imgDeskripsi;
        paket.setText(item.getName());
        harga.setText(item.getPrice()+"");
        deskripsi.setText(item.getDetail());
        String url = AppsCore.BASE_IMAGE+item.getImage();

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .build());
        Glide.with(context).load(glideUrl).into(imgDeskripsi);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgDeskripsi;
        public TextView paket;
        public TextView harga;
        public TextView deskripsi;
        public Button btnPesan;

        ProductItemListener productItemListener;

        public ViewHolder(View itemView, ProductItemListener productItemListener) {
            super(itemView);
            imgDeskripsi = itemView.findViewById(R.id.imgProduk);
            paket = itemView.findViewById(R.id.txtNamaItem);
            harga = itemView.findViewById(R.id.txtHargaItem);
            deskripsi = itemView.findViewById(R.id.txtDeskripsiItem);
            btnPesan = itemView.findViewById(R.id.btnPesan);

            this.productItemListener = productItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Product item = getItem(getAdapterPosition());
            this.productItemListener.onPostClick(item);

            notifyDataSetChanged();
        }
    }

    public void updateProducts(List<Product> items) {
        productList = items;
        notifyDataSetChanged();
    }

    private Product getItem(int adapterPosition) {
        return productList.get(adapterPosition);
    }

    public interface ProductItemListener {
        void onPostClick(Product id);
    }
}
