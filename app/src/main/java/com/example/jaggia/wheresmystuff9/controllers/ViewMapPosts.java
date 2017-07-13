package com.example.jaggia.wheresmystuff9.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jaggia.wheresmystuff9.model.item_system.FoundItem;
import com.example.jaggia.wheresmystuff9.model.item_system.Item;
import com.example.jaggia.wheresmystuff9.model.Model;
import com.example.jaggia.wheresmystuff9.model.item_system.LostItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.jaggia.wheresmystuff9.R;



public class ViewMapPosts extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Model mFacade;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("app");
    public final String TAG = "MapsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFacade = Model.getInstance();

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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(20, 20)));

        databaseReference.child("FoundItem").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemFoundChildren = dataSnapshot.getChildren();
                Model.clearList(Model.getFoundList());
                for (DataSnapshot child: itemFoundChildren) {
                    FoundItem value = child.getValue(FoundItem.class);
                    Model.addItem(Model.getFoundList(), value);
                    LatLng loc = new LatLng(value.getLocation().getLatitude(),
                            value.getLocation().getLongitude());
                    mMap.addMarker(new MarkerOptions().position(loc).
                            title(value.getName()).snippet(value.getDescription()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, databaseError.getMessage());
            }
        });

        databaseReference.child("LostItem").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemLostChildren = dataSnapshot.getChildren();
                Model.clearList(Model.getLostList());
                for (DataSnapshot child: itemLostChildren) {
                    LostItem value = child.getValue(LostItem.class);
                    Model.addItem(Model.getLostList(), value);
                    LatLng loc = new LatLng(value.getLocation().getLatitude(),
                            value.getLocation().getLongitude());
                    mMap.addMarker(new MarkerOptions().position(loc).
                            title(value.getName()).snippet(value.getDescription()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, databaseError.getMessage());
            }
        });

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        CustomInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }

}

