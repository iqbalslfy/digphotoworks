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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blegoh on 28/10/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private Context context;
    private List<Product> productList;
    private ArrayList<Product> productView;
    private String search;
    private ProductItemListener productListener;
    DecimalFormat myFormatter;

    public ProductAdapter(final Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.productView = new ArrayList<>(0);
        this.search = "";
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
        DecimalFormatSymbols otherSymbols;
        otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        myFormatter = new DecimalFormat("###,###.###", otherSymbols);
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
        Product item = productView.get(position);
        TextView paket = holder.paket;
        TextView harga = holder.harga;
        TextView deskripsi = holder.deskripsi;
        ImageView imgDeskripsi = holder.imgDeskripsi;
        paket.setText(item.getName());
        harga.setText("Rp "+myFormatter.format(item.getPrice()));
        deskripsi.setText(item.getDetail());
        String url = AppsCore.BASE_IMAGE+item.getImage();

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .build());

        Glide.with(context).load(glideUrl).into(imgDeskripsi);
    }

    @Override
    public int getItemCount() {
        return productView.size();
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

    private void updateView()
    {
        this.productView = new ArrayList<>();
        for (int i=0;i<this.productList.size();i++){
            if (this.search.equals("")){
                this.productView.add(this.productList.get(i));
            }else{
                if (this.productList.get(i).getName().toLowerCase().matches("(.*)"+search.toLowerCase()+"(.*)")){
                    this.productView.add(this.productList.get(i));
                }
            }
        }
    }

    public void updateProducts(List<Product> items) {
        productList = items;
        this.updateView();
        notifyDataSetChanged();
    }

    public void search(String search){
        this.search = search;
        this.updateView();
        notifyDataSetChanged();
    }

    private Product getItem(int adapterPosition) {
        return productView.get(adapterPosition);
    }

    public interface ProductItemListener {
        void onPostClick(Product id);
    }
}
