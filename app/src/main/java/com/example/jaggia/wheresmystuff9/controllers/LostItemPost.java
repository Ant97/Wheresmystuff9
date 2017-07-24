package com.example.jaggia.wheresmystuff9.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jaggia.wheresmystuff9.model.item_system.ItemCategory;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemStatus;
import com.example.jaggia.wheresmystuff9.model.Model;

import com.example.jaggia.wheresmystuff9.model.item_system.LostItem;
import com.example.jaggia.wheresmystuff9.model.item_system.MyLocation;
import com.example.jaggia.wheresmystuff9.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;


import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class LostItemPost extends AppCompatActivity {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final int REQUEST_CODE_PLACEPICKER = 1;
    private boolean locationSelected = false;
    public static final String TAG = "LostItemPost";
    private static LatLng latLng = null;

    private EditText lostName;
    private EditText lostDescription;
    private Spinner lostCategory;
    private EditText lostReward;
    private Spinner lostDateDay;
    private Spinner lostDateMonth;
    private Spinner lostDateYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item_post);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        lostName = (EditText) findViewById(R.id.itemName);
        lostDescription = (EditText) findViewById(R.id.itemDescription);
        lostCategory = (Spinner) findViewById(R.id.categorySpinner);
        lostReward = (EditText) findViewById(R.id.reward);
        lostDateDay = (Spinner) findViewById(R.id.daySpinner);
        lostDateMonth = (Spinner) findViewById(R.id.monthSpinner);
        lostDateYear = (Spinner) findViewById(R.id.yearSpinner);

        Button post = (Button) findViewById(R.id.createButton);
        Button cancelPost = (Button) findViewById(R.id.cancelLost);
        Button map = (Button) findViewById(R.id.mapButton);


        ArrayAdapter<ItemCategory> itemCategoryArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, ItemCategory.values());
        itemCategoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostCategory.setAdapter(itemCategoryArrayAdapter);

        Integer days[] = new Integer[31];
        for(int i = 0; i<days.length; i++){
            days[i] = (i+1);
        }

        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateDay.setAdapter(dayAdapter);

        //String months[] = {"January", "Feburary", "March", "April","May","June","July","August","Septemeber", "October","November","December"};
        Integer months[] = new Integer[12];
        for(int i = 0; i<months.length; i++){
            months[i] = (i+1);
        }

        ArrayAdapter<Integer> monthAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateMonth.setAdapter(monthAdapter);

        Integer years[] = new Integer[100];
        for(int i = 0; i<years.length; i++){
            years[i] = (i + 1990);
        }

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateYear.setAdapter(yearAdapter);


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
                if(locationSelected) {
                    DatabaseReference databaseReference = database.getReference();
                    String name = lostName.getText().toString();
                    String description = lostDescription.getText().toString();
                    Double latitude = latLng.latitude;
                    Double longitude = latLng.longitude;
                    ItemStatus status = ItemStatus.UNRESOLVED;
                    ItemCategory category = (ItemCategory) lostCategory.getSelectedItem();
                    String reward = lostReward.getText().toString();
                    //                ItemType type = ItemType.LOST;
                    if (name.length() == 0 || latitude.toString().length() <= 0
                            || longitude.toString().length() <= 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LostItemPost.this);
                        builder.setMessage("Item was not created: Please fill in required information");
                        builder.setNegativeButton("Retry", null).create().show();
                    } else {
                        MyLocation location = new MyLocation("itemLocation");
                        location.setLatitude((latitude));
                        location.setLongitude((longitude));

                        int dateDay = (int) lostDateDay.getSelectedItem();
                        int dateMonth = ((int) lostDateMonth.getSelectedItem()) - 1;
                        int dateYear = (int) lostDateYear.getSelectedItem();
                        Date date = new Date(dateYear, dateMonth, dateDay);

                        LostItem itemToAdd = new LostItem.Builder(name, location).User(Model.getCurrentUsername())
                                .Reward(reward).Description(description)
                                .Date(date).ItemStatus(status)
                                .ItemCategory(category).Build();
                        if (Model.addItem(Model.getLostList(), itemToAdd)) {
                            databaseReference.child("app").child("LostItem").push().setValue(itemToAdd);
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
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LostItemPost.this);
                    builder.setMessage("Please click map and select a location").setNegativeButton("Retry", null).create().show();
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
        Place placeSelected = PlacePicker.getPlace(this, data);
        locationSelected = true;
        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();
        latLng = placeSelected.getLatLng();
        TextView enterCurrentLocation = (TextView) findViewById(R.id.show_selected_location);
        enterCurrentLocation.setText(name + ", " + address);
    }

}
