package com.example.jaggia.wheresmystuff9.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jaggia.wheresmystuff9.Model.ItemCategory;
import com.example.jaggia.wheresmystuff9.Model.ItemStatus;
import com.example.jaggia.wheresmystuff9.Model.ItemType;
import com.example.jaggia.wheresmystuff9.Model.Model;
import com.example.jaggia.wheresmystuff9.Model.Item;

import com.example.jaggia.wheresmystuff9.Model.MyLocation;
import com.example.jaggia.wheresmystuff9.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;


import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class LostItemPost extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final int REQUEST_CODE_PLACEPICKER = 1;
    private static LatLng latLng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        final EditText lostName = (EditText) findViewById(R.id.itemName);
        final EditText lostDescription = (EditText) findViewById(R.id.itemDescription);
        //final EditText lostLocationLat = (EditText) findViewById(R.id.longitude);
        //final EditText lostLocationLng = (EditText) findViewById(R.id.latitude);
        final Spinner lostCategory = (Spinner) findViewById(R.id.categorySpinner);
        final EditText lostReward = (EditText) findViewById(R.id.reward);
        final Spinner lostDateDay = (Spinner) findViewById(R.id.daySpinner);
        final Spinner lostDateMonth = (Spinner) findViewById(R.id.monthSpinner);
        final Spinner lostDateYear = (Spinner) findViewById(R.id.yearSpinner);
        final TextView addressTextBox = (TextView) findViewById(R.id.show_selected_location);

        Button post = (Button) findViewById(R.id.createButton);
        Button cancelPost = (Button) findViewById(R.id.cancelLost);
        Button map = (Button) findViewById(R.id.mapButton);


        ArrayAdapter<ItemCategory> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostCategory.setAdapter(adapter);

        Integer days[] = new Integer[31];
        for(int i = 0; i<days.length; i++){
            days[i] = (i+1);
        }

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateDay.setAdapter(adapter2);

        //String months[] = {"January", "Feburary", "March", "April","May","June","July","August","Septemeber", "October","November","December"};
        Integer months[] = new Integer[12];
        for(int i = 0; i<months.length; i++){
            months[i] = (i+1);
        }

        ArrayAdapter<Integer> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateMonth.setAdapter(adapter3);

        Integer years[] = new Integer[100];
        for(int i = 0; i<years.length; i++){
            years[i] = (i + 1990);
        }

        ArrayAdapter<Integer> adapter4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateYear.setAdapter(adapter4);


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlacePicker();
            }
        });

        cancelPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent =
                        new Intent(LostItemPost.this, MakeAPost.class);
                LostItemPost.this.startActivity(cancelIntent);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = database.getReference();
                String name = lostName.getText().toString();
                String description = lostDescription.getText().toString();
                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;
                ItemStatus status = ItemStatus.UNRESOLVED;
                ItemCategory category = (ItemCategory) lostCategory.getSelectedItem();
                String reward = lostReward.getText().toString();
                ItemType type = ItemType.LOST;
                if (name.length() == 0 || latitude.toString().length() <= 0
                        || longitude.toString().length() <= 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LostItemPost.this);
                    builder.setMessage("Item was not create: Please fill in required information");
                    builder.setNegativeButton("Retry", null).create().show();
                } else {
                    MyLocation location = new MyLocation("itemLocation");
                    location.setLatitude((latitude));
                    location.setLongitude((longitude));

                    int dateDay = (int) lostDateDay.getSelectedItem();
                    int dateMonth = (int) lostDateMonth.getSelectedItem();
                    int dateYear = (int) lostDateYear.getSelectedItem();
                    Date date = new Date(dateYear, dateMonth, dateDay);


                    if (Model.addItem(Model.getLostList(), Model.createNewItem(Model.getCurrentUser(), name, description, date, location, reward, status, type, category))) {
                        databaseReference.child("app").child("ItemLost").push().setValue(Model.createNewItem(Model.getCurrentUser(), name, description, date, location, reward, status, type, category));
                        //the item was created and added to the lostItems
                        AlertDialog.Builder builder = new AlertDialog.Builder(LostItemPost.this);
                        builder.setMessage("Item was created successfully!");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent postIntent = new Intent(LostItemPost.this, ViewPosts.class);
                                LostItemPost.this.startActivity(postIntent);
                            }
                        });
                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Intent postIntent = new Intent(LostItemPost.this, ViewPosts.class);
                                LostItemPost.this.startActivity(postIntent);
                            }
                        }).create().show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LostItemPost.this);
                        builder.setMessage("Item was not created").setNegativeButton("Retry", null).create().show();

                    }
                }
            }

        });

    }

    private void startPlacePicker() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        // this would only work if you have your Google Places API working

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            displaySelectedPlaceFromPlacePicker(data);
        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, this);

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();
        latLng = placeSelected.getLatLng();
        TextView enterCurrentLocation = (TextView) findViewById(R.id.show_selected_location);
        enterCurrentLocation.setText(name + ", " + address);
    }

}
