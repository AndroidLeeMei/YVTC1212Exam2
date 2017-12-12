package com.example.auser.yvtc1212exam2;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private DB mDBHelper;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setAdapter();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Cursor c = mDBHelper.getAll();
        Log.d("AAA",c.getColumnCount()+"");
        Log.d("AAA",c.getCount()+"");
        if (!(c==null)) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
//                alrLatLang = new ArrayList<LatLng>(c.getString(1),c.getString(2))
                LatLng latLng = new LatLng(Double.parseDouble(c.getString(2)),Double.parseDouble(c.getString(1)));
//                Log.d("DATA0", "~~~~~~~~~~~~~~~~~~~~~~~");
//                Log.d("DATA0", c.getString(0));
//                Log.d("DATA1", c.getString(1));
//                Log.d("DATA2", c.getString(2));

                mMap.addMarker(new MarkerOptions().position(latLng).title(c.getString(0)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
                c.moveToNext();
            }
        }
    }


    private void setAdapter() {
        mDBHelper = new DB(this).open();
        fillData();
    }

    void fillData() {
        Cursor c = mDBHelper.getAll();
        startManagingCursor(c);

        if (!(c==null)) {
            c.moveToFirst();
            while (!c.isAfterLast()) {

                c.moveToNext();
            }
        }

    }


}
