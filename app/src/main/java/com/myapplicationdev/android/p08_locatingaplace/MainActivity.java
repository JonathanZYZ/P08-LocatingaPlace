package com.myapplicationdev.android.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback, AdapterView.OnItemSelectedListener {

    Button btn1, btn2, btn3;
    private GoogleMap map;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Select Location");
        categories.add("North");
        categories.add("Central");
        categories.add("East");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    LatLng poi_North = new LatLng(1.4333256,103.7742969);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                            15));
                }
                Toast toast = Toast.makeText(getApplicationContext(), "North - HQ", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    LatLng poi_Central = new LatLng(1.314097,103.8701533);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                            15));
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Central", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    LatLng poi_East = new LatLng(1.3488823,103.9352603);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                            15));
                }
                Toast toast = Toast.makeText(getApplicationContext(), "East", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast toast = Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT);
        toast.show();
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        UiSettings ui = map.getUiSettings();

        ui.setCompassEnabled(true);

        ui.setZoomControlsEnabled(true);

        LatLng poi_Sing= new LatLng(1.3311577,103.8325641);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Sing,
                11));

        LatLng poi_North = new LatLng(1.4333256,103.7742969);
        Marker north = map.addMarker(new
                MarkerOptions()
                .position(poi_North)
                .title("North - HQ")
                .snippet("Block 333, Admiralty Ave 3, 765654 \n" +
                        "Operating hours: 10am-5pm\n" +
                        "Tel:65433456\n")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        LatLng poi_Central = new LatLng(1.314097,103.8701533);
        Marker central = map.addMarker(new
                MarkerOptions()
                .position(poi_Central)
                .title("Central")
                .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                        "Operating hours: 11am-8pm\n" +
                        "Tel:67788652\n")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng poi_East = new LatLng(1.3488823,103.9352603);
        Marker east = map.addMarker(new
                MarkerOptions()
                .position(poi_East)
                .title("East")
                .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                        "Operating hours: 9am-5pm\n" +
                        "Tel:66776677\n")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            Log.e("GMap - Permission", "GPS access has not been granted");
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        map.setOnMarkerClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if (position == 1){
            if (map != null) {
                LatLng poi_North = new LatLng(1.4333256,103.7742969);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                        15));
            }
            Toast toast = Toast.makeText(getApplicationContext(), "North - HQ", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (position == 2){
            if (map != null) {
                LatLng poi_Central = new LatLng(1.314097,103.8701533);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                        15));
            }
            Toast toast = Toast.makeText(getApplicationContext(), "Central", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (position == 3){
            if (map != null) {
                LatLng poi_East = new LatLng(1.3488823,103.9352603);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                        15));
            }
            Toast toast = Toast.makeText(getApplicationContext(), "East", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}