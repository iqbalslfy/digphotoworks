package com.mascitra.digphotoworks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mascitra.digphotoworks.R;
import com.mascitra.digphotoworks.activities.About;
import com.mascitra.digphotoworks.activities.Gallery;
import com.mascitra.digphotoworks.activities.MakeUp;
import com.mascitra.digphotoworks.activities.Paket;
import com.mascitra.digphotoworks.activities.PhotoStudio;
import com.mascitra.digphotoworks.activities.Preweding;
import com.mascitra.digphotoworks.activities.PromoActivity;
import com.mascitra.digphotoworks.activities.Video;
import com.mascitra.digphotoworks.activities.Weding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SONY on 22/11/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MenuHolder>{
    private Context context;
    private List<Data> listData = new ArrayList<Data>();

    public RecyclerViewAdapter(Context context, List<Data> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.card_item_menu,parent,false);
        return new MenuHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MenuHolder holder, final int position) {
        holder.imageView.setImageResource(listData.get(position).getImageID());
        holder.textView.setText(listData.get(position).getJudul());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    context.startActivity(new Intent(context, PromoActivity.class));
                }

                if (position == 1){
                    context.startActivity(new Intent(context, PhotoStudio.class));
                }

                if (position == 2){
                    context.startActivity(new Intent(context, MakeUp.class));
                }

                if (position == 3){
                    context.startActivity(new Intent(context, Paket.class));
                }

                if (position == 4){
                    context.startActivity(new Intent(context, Preweding.class));
                }

                if (position == 5){
                    context.startActivity(new Intent(context, Weding.class));
                }

                if (position == 6){
                    context.startActivity(new Intent(context, Gallery.class));
                }

                if (position == 7){
                    context.startActivity(new Intent(context, Video.class));
                }

                if (position == 8){
                    context.startActivity(new Intent(context, About.class));
                }
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    context.startActivity(new Intent(context, PromoActivity.class));
                }

                if (position == 1){
                    context.startActivity(new Intent(context, PhotoStudio.class));
                }

                if (position == 2){
                    context.startActivity(new Intent(context, MakeUp.class));
                }

                if (position == 3){
                    context.startActivity(new Intent(context, Paket.class));
                }

                if (position == 4){
                    context.startActivity(new Intent(context, Preweding.class));
                }

                if (position == 5){
                    context.startActivity(new Intent(context, Weding.class));
                }

                if (position == 6){
                    context.startActivity(new Intent(context, Gallery.class));
                }

                if (position == 7){
                    context.startActivity(new Intent(context, Video.class));
                }

                if (position == 8){
                    context.startActivity(new Intent(context, About.class));
                }
            }
        });
    }

    class MenuHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public LinearLayout layout;

        public MenuHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageMenu);
            textView = itemView.findViewById(R.id.namaJudul);
            layout = itemView.findViewById(R.id.layout_item);

        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
