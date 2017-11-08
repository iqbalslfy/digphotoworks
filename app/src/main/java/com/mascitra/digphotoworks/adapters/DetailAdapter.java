package com.mascitra.digphotoworks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.models.Detail;

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

        View view = inflater.inflate(R.layout.simple_list_item,parent,false);

        DetailAdapter.ViewHolder viewHolder = new DetailAdapter.ViewHolder(view, this.detailItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Detail item = detailList.get(position);
        TextView name = holder.name;
        name.setText((position+1)+". "+item.getName());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;

        DetailItemListener detailItemListener;

        public ViewHolder(View itemView, DetailItemListener detailItemListener) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);

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

    public void updateDetails(List<Detail> items) {
        detailList = items;
        notifyDataSetChanged();
    }

    private Detail getItem(int adapterPosition) {
        return detailList.get(adapterPosition);
    }

    public interface DetailItemListener {
        void onPostClick(Detail id);
    }
}
