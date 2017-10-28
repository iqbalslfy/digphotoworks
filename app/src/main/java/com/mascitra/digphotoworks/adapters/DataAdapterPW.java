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

import com.mascitra.digphotoworks.Click.ItemClickListener;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.activities.TransaksiTwo;
import com.mascitra.digphotoworks.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 11/10/2017.
 */

class RecyclerViewHolderPW extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    public ImageView imgDeskripsi;
    public TextView paket;
    public TextView harga;
    public TextView deskripsi;
    public Button btnPesan;

    private ItemClickListener itemClickListener;


    public RecyclerViewHolderPW(View itemView) {
        super(itemView);
        imgDeskripsi = itemView.findViewById(R.id.imgProduk);
        paket = itemView.findViewById(R.id.txtNamaItem);
        harga = itemView.findViewById(R.id.txtHargaItem);
        deskripsi = itemView.findViewById(R.id.txtDeskripsiItem);
        btnPesan = itemView.findViewById(R.id.btnPesan);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        btnPesan.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClick){
        this.itemClickListener = itemClick;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), true);
        return true;
    }

}

public class DataAdapterPW extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<Product> productList = new ArrayList<Product>();
    private Context context;

    public DataAdapterPW(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.row_layout,parent,false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.imgDeskripsi.setImageResource(productList.get(position).getGambarID());
        holder.paket.setText(productList.get(position).getPaket());
        holder.harga.setText(productList.get(position).getHarga());
        holder.deskripsi.setText(productList.get(position).getDeskripsi());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {

                if (isLongClick){

                } else {

                    Intent intent = new Intent(context, TransaksiTwo.class);
                    Bundle b = new Bundle();

                    b.putString("paket", productList.get(pos).getPaket());
                    b.putString("harga",productList.get(pos).getHarga());
                    b.putString("deskripsi", productList.get(pos).getDeskripsi());

                    intent.putExtras(b);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
