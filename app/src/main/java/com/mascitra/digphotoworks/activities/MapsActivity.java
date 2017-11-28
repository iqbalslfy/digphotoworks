package com.mascitra.digphotoworks.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mascitra.digphotoworks.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private FloatingActionButton fab_zoom_in, fab_zoom_out;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fab_zoom_in = findViewById(R.id.fab_zoom_in);
        fab_zoom_out = findViewById(R.id.fab_zoom_out);

        fab_zoom_in.setOnClickListener(this);
        fab_zoom_out.setOnClickListener(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lokasi = new LatLng(-8.164830, 113.695434);
        float zoom = 17.0f;
        mMap.addMarker(new MarkerOptions().position(lokasi).title("Mascitra.com"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, zoom));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(lokasi));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    public void onClick(View v) {
        if (v == fab_zoom_in){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }

        if (v == fab_zoom_out){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }
}
