package com.example.ayush1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.ayush1.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    MarkerOptions marker;
    LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
        Intent i = getIntent();
        String name = i.getSerializableExtra("hosp").toString();
        try {
            double latitude = Double.parseDouble(i.getSerializableExtra("lat").toString());
            double longitude = Double.parseDouble(i.getSerializableExtra("long").toString());

            location = new LatLng(latitude, longitude);
            System.out.println(name);
            System.out.println(latitude + " " + longitude);
            marker = new MarkerOptions().title(name)
                    .position(location).snippet("Open 24 hours");
            mMap.addMarker(marker);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17));

        }
        catch (Exception e){
            System.out.println(e);
        }
//        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("Hospital");
//        mref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Intent i = getIntent();
//                String name = i.getSerializableExtra("hosp").toString();
//                Hospitals hosp = snapshot.child(name).getValue(Hospitals.class);
//
//                double latitude = hosp.getLat();
//                double longitude = hosp.getLongit();
//
//                location = new LatLng(latitude,longitude);
//                System.out.println(name);
//                System.out.println(latitude + " " + longitude);
//                marker = new MarkerOptions().title(name)
//                        .position(location).snippet("Open 24 hours");
//                mMap.addMarker(marker);
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,10));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MapsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}