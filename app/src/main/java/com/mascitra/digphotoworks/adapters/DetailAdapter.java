package com.mascitra.digphotoworks.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.models.Detail;
import com.mascitra.digphotoworks.models.Product;

import java.util.List;

/**
 * Created by yusuf on 07/11/17.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{
    private Context context;
    private List<Detail> detailList;
    private DetailItemListener detailItemListener;

    public DetailAdapter(final Context context, List<Detail> detailList) {
        this.context = context;
        this.detailList = detailList;
        this.detailItemListener = new DetailItemListener() {
            @Override
            public void onPostClick(Detail id) {

            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.row_layout,parent,false);

        DetailAdapter.ViewHolder viewHolder = new DetailAdapter.ViewHolder(view, this.detailItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Detail item = detailList.get(position);
        TextView name = holder.name;
        name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;

        DetailItemListener detailItemListener;

        public ViewHolder(View itemView, DetailItemListener DetailItemListener) {
            super(itemView);
            name = itemView.findViewById(R.id.txtNamaItem);

            this.detailItemListener = detailItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Detail item = getItem(getAdapterPosition());
            this.detailItemListener.onPostClick(item);

            notifyDataSetChanged();
        }
    }

    private Detail getItem(int adapterPosition) {
        return detailList.get(adapterPosition);
    }

    public interface DetailItemListener {
        void onPostClick(Detail id);
    }
}
