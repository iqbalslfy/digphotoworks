package com.mascitra.digphotoworks;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mascitra.digphotoworks.activities.About;
import com.mascitra.digphotoworks.activities.Gallery;
import com.mascitra.digphotoworks.activities.MakeUp;
import com.mascitra.digphotoworks.activities.Paket;
import com.mascitra.digphotoworks.activities.PhotoStudio;
import com.mascitra.digphotoworks.activities.Preweding;
import com.mascitra.digphotoworks.activities.PromoActivity;
import com.mascitra.digphotoworks.activities.Video;
import com.mascitra.digphotoworks.activities.Weding;
import com.mascitra.digphotoworks.adapters.Data;
import com.mascitra.digphotoworks.adapters.RecyclerViewAdapter;
import com.mascitra.digphotoworks.adapters.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Home extends Fragment implements View.OnClickListener {

    ViewPager viewPager;
    LinearLayout SliderDots;
    private int dotcounts;
    private ImageView[] dots;
    private Timer timer;
    CardView photostudio,paket, gallery, about,promo,preweding,video,weding,make_up;
    TextView textView;


    RecyclerView recyclerView;
    List<Data> data_list = new ArrayList<Data>();
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    GridLayoutManager gridLayoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rc_home);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new RecyclerViewAdapter(getActivity(), data_list);
        recyclerView.setAdapter(adapter);


        viewPager = view.findViewById(R.id.viewPagerHome);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);

        SliderDots = view.findViewById(R.id.Dots);
        dotcounts = viewPagerAdapter.getCount();

        dots = new ImageView[dotcounts];

        for (int i = 0; i < dotcounts; i++){
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_enable));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8 ,0);
            SliderDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_unable));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i=0; i<dotcounts; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_unable));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_enable));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        photostudio = view.findViewById(R.id.card_photo_studio);
//        paket = view.findViewById(R.id.card_package);
//        gallery = view.findViewById(R.id.card_gallery);
//        about = view.findViewById(R.id.card_tentang);
//        promo = view.findViewById(R.id.cardPromo);
//        preweding = view.findViewById(R.id.card_preweding);
//        video = view.findViewById(R.id.card_video);
//        weding = view.findViewById(R.id.card_weding);
//        make_up = view.findViewById(R.id.card_makeup);
//        textView = view.findViewById(R.id.tv_card_makeup);
//
//        textView.setText("Make Up & Wardrobe");
//        paket.setOnClickListener(this);
//        photostudio.setOnClickListener(this);
//        gallery.setOnClickListener(this);
//        about.setOnClickListener(this);
//        promo.setOnClickListener(this);
//        preweding.setOnClickListener(this);
//        video.setOnClickListener(this);
//        weding.setOnClickListener(this);
//        make_up.setOnClickListener(this);




        initData();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTasks(), 1000, 2000);
        return view;
    }

    public class TimerTasks extends TimerTask {

    @Override
    public void run() {
       getActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {

               if (viewPager.getCurrentItem() == 0){
                   viewPager.setCurrentItem(1);
               } else if (viewPager.getCurrentItem() == 1){
                   viewPager.setCurrentItem(2);
               } else {
                   viewPager.setCurrentItem(0);
               }

               }
            });


        }
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
        timer.purge();
    }

private void initData(){
    data_list.add(new Data("Promo Saat Ini", R.drawable.baru));
    data_list.add(new Data("Photo Studio", R.drawable.camera));
    data_list.add(new Data("Make Up & Wardrobe", R.drawable.makeup));
    data_list.add(new Data("Package", R.drawable.prewed));
    data_list.add(new Data("Prewedding", R.drawable.clock));
    data_list.add(new Data("Wedding", R.drawable.weding));
    data_list.add(new Data("Gallery", R.drawable.gallery));
    data_list.add(new Data("Video", R.drawable.video));
    data_list.add(new Data("Tentang Kami", R.drawable.tentang));
}

    @Override
    public void onClick(View view) {
//        if (view == photostudio){
//            startActivity(new Intent(getActivity(), PhotoStudio.class));
//        }
//
//        if (view == paket){
//            startActivity(new Intent(getActivity(), Paket.class));
//        }
//
//        if (view == gallery){
//            startActivity(new Intent(getActivity(), Gallery.class));
//        }
//
//        if (view == about){
//            startActivity(new Intent(getActivity(), About.class));
//        }
//
//        if (view == promo){
//            startActivity(new Intent(getActivity(), PromoActivity.class));
//        }
//
//        if (view == preweding){
//            startActivity(new Intent(getActivity(), Preweding.class));
//        }
//
//        if (view == weding) {
//            startActivity(new Intent(getActivity(), Weding.class));
//        }
//
//        if (view == video) {
//            startActivity(new Intent(getActivity(), Video.class));
//        }
//
//        if (view == make_up) {
//            startActivity(new Intent(getActivity(), MakeUp.class));
//        }

    }
}
