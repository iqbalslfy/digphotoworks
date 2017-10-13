package com.mascitra.digphotoworks.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mascitra.digphotoworks.Click.ItemClickListener;
import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.activity.DetailPromo;
import com.mascitra.digphotoworks.activity.Transaksi;
import com.mascitra.digphotoworks.product.Product;
import com.mascitra.digphotoworks.product.Promo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 09/10/2017.
 */

class RecyclerViewHolderTwo  extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imgDeskripsi;
    public TextView paket;
    public TextView harga;
    private ItemClickListener itemClickListener;

    public RecyclerViewHolderTwo(View itemView) {
        super(itemView);

        imgDeskripsi = itemView.findViewById(R.id.imgPromo);
        paket = itemView.findViewById(R.id.txtNamaPromo);
        harga = itemView.findViewById(R.id.txtHargaPromo);

        itemView.setOnClickListener(this);
    }


    public void setItemClickListener(ItemClickListener itemClick){
        this.itemClickListener = itemClick;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }


}

public class DataAdapterTwo extends RecyclerView.Adapter<RecyclerViewHolderTwo>{
    private List<Promo> productList = new ArrayList<Promo>();
    private Context context;

    public DataAdapterTwo(List<Promo> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolderTwo onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_horizontal,parent,false);
        return new RecyclerViewHolderTwo(view);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderTwo holder, int position) {
        holder.imgDeskripsi.setImageResource(productList.get(position).getGambarID());
        holder.paket.setText(productList.get(position).getPaket());
        holder.harga.setText(productList.get(position).getHarga());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if (isLongClick){

                } else {
                    Intent intent = new Intent(context, DetailPromo.class);
                    Bundle b = new Bundle();

                    b.putString("paket", productList.get(pos).getPaket());
                    b.putString("harga", productList.get(pos).getHarga());
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
