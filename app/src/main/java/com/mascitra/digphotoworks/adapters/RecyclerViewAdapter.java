package com.mascitra.digphotoworks.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mascitra.digphotoworks.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SONY on 22/11/2017.
 */

class MenuHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView textView;

    public MenuHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageMenu);
        textView = itemView.findViewById(R.id.namaJudul);
    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<MenuHolder>{
    private List<Data> listData = new ArrayList<Data>();

    public RecyclerViewAdapter(List<Data> listData) {
        this.listData = listData;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.card_item_menu,parent,false);
        return new MenuHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        holder.imageView.setImageResource(listData.get(position).getImageID());
        holder.textView.setText(listData.get(position).getJudul());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
