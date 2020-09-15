package com.example.mapsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, View.OnClickListener {
Button flyTo;
EditText lat;
EditText lon;

GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flyTo = findViewById(R.id.flyTo);
        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setOnMarkerClickListener(this);


        LatLng aromaKava = new LatLng(50.4875146,30.4884806);
//
//        map.addMarker(new MarkerOptions()
//        .position(aromaKava));
//        map.addMarker(new MarkerOptions().position(aromaKava).title("This is aroma kava").snippet("Wisit it please"));
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(aromaKava, 15));

        flyTo.setOnClickListener(this);
    }
    @Override
    public boolean onMarkerClick(Marker marker){
        marker.remove();

        Toast.makeText(this, marker.getPosition().latitude + " " +
                marker.getPosition().longitude, Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onClick(View view) {
        double lat = Double.parseDouble(this.lat.getText().toString());
        double lon = Double.parseDouble(this.lon.getText().toString());
        LatLng position = new LatLng(lat, lon);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(8)
                .bearing(30)
                .tilt(45)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        map.addMarker(new MarkerOptions()
        .position(position));
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

    }
}