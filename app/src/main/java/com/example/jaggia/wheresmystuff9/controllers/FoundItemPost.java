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

import com.example.jaggia.wheresmystuff9.model.item_system.FoundItem;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemCategory;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemStatus;
import com.example.jaggia.wheresmystuff9.model.Model;
import com.example.jaggia.wheresmystuff9.model.item_system.MyLocation;
import com.example.jaggia.wheresmystuff9.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Date;

public class FoundItemPost extends AppCompatActivity {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private final int REQUEST_CODE_PLACEPICKER = 1;
    // --Commented out by Inspection (7/17/17, 12:36 PM):private static final String TAG = "FoundItemPost";

    private EditText foundName;
    private EditText foundDescription;
    private Spinner foundCategory;
    private Spinner foundDateDay;
    private Spinner foundDateMonth;
    private Spinner foundDateYear;

    private static LatLng latLng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_post);//for view people
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        foundName = (EditText) findViewById(R.id.itemName);
        foundDescription = (EditText) findViewById(R.id.itemDescription);
        foundCategory = (Spinner) findViewById(R.id.categorySpinner);
        foundDateDay = (Spinner) findViewById(R.id.daySpinner);
        foundDateMonth = (Spinner) findViewById(R.id.monthSpinner);
        foundDateYear = (Spinner) findViewById(R.id.yearSpinner);

        Button post = (Button) findViewById(R.id.createFound);
        Button cancelPost = (Button) findViewById(R.id.cancelFound);
        Button map = (Button) findViewById(R.id.mapButton);

        ArrayAdapter<ItemCategory> itemCategoryArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, ItemCategory.values());
        itemCategoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundCategory.setAdapter(itemCategoryArrayAdapter);

        Integer days[] = new Integer[31];
        for(int i = 0; i<days.length; i++){
            days[i] = (i+1);
        }

        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateDay.setAdapter(dayAdapter);

        //String months[] = {"January", "Feburary", "March", "April","May","June","July","August","Septemeber", "October","November","December"};
        Integer months[] = new Integer[12];
        for(int i = 0; i<months.length; i++) {
            months[i] = (i+1);
        }

        ArrayAdapter<Integer> monthAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateMonth.setAdapter(monthAdapter);

        Integer years[] = new Integer[100];
        for(int i = 0; i<years.length; i++){
            years[i] = (i + 1990);
        }

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateYear.setAdapter(yearAdapter);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlacePickerActivity();
            }
        });

        cancelPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent =
                        new Intent(FoundItemPost.this, MakeAPost.class);
                FoundItemPost.this.startActivity(cancelIntent);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = database.getReference();

                String name = foundName.getText().toString();
                String description = foundDescription.getText().toString();
                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;
                ItemStatus status = ItemStatus.UNRESOLVED;
                ItemCategory category = (ItemCategory) foundCategory.getSelectedItem();
//                ItemType type = ItemType.FOUND;

                if (name.length() == 0 || latitude.toString().length() <= 0
                        || longitude.toString().length() <= 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(FoundItemPost.this);
                    builder.setMessage("Item was not created: Please fill in required information");
                    builder.setNegativeButton("Retry", null).create().show();
                } else {
                    MyLocation location = new MyLocation("itemLocation");
                    location.setLatitude((latitude));
                    location.setLongitude((longitude));

                    int dateDay = (int) foundDateDay.getSelectedItem();
                    int dateMonth = ((int) foundDateMonth.getSelectedItem()) - 1;
                    int dateYear = (int) foundDateYear.getSelectedItem();
                    Date date = new Date(dateYear, dateMonth, dateDay);

                    FoundItem itemToAdd = (new FoundItem.Builder(name, location).User(Model.getCurrentUsername())
                            .Description(description).Date(date)
                            .ItemStatus(status).ItemCategory(category)).Build();

                    if (Model.addItem(Model.getFoundList(), itemToAdd)) {
                       databaseReference.child("app").child("FoundItem").push().setValue(itemToAdd);
                        //the item was created and added to the foundItems
                        AlertDialog.Builder builder = new AlertDialog.Builder(FoundItemPost.this);
                        builder.setMessage("Item was created successfully!");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent postIntent = new Intent(FoundItemPost.this, ViewPosts.class);
                                FoundItemPost.this.startActivity(postIntent);
                            }
                        });
                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Intent postIntent = new Intent(FoundItemPost.this, ViewPosts.class);
                                FoundItemPost.this.startActivity(postIntent);
                            }
                        }).create().show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FoundItemPost.this);
                        builder.setMessage("Item was not created").setNegativeButton("Retry", null).create().show();

                    }
                }
            }

        });

    }
    private void startPlacePickerActivity() {
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
