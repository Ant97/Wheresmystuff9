package com.example.jaggia.wheresmystuff9.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jaggia.wheresmystuff9.model.item_system.FoundItem;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemCategory;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemStatus;
import com.example.jaggia.wheresmystuff9.model.item_system.ItemType;
import com.example.jaggia.wheresmystuff9.model.Model;
import com.example.jaggia.wheresmystuff9.model.item_system.MyLocation;
import com.example.jaggia.wheresmystuff9.model.item_system.Item;
import com.example.jaggia.wheresmystuff9.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Date;

/**
 * Created by jaggia on 6/27/17.
 */

public class FoundItemPost extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private final int REQUEST_CODE_PLACEPICKER = 1;
    private static final String TAG = "FoundItemPost";

    EditText foundName;
    EditText foundDescription;
    Spinner foundCategory;
    Spinner foundDateDay;
    Spinner foundDateMonth;
    Spinner foundDateYear;

    Button post;
    Button cancelPost;
    Button map;
    private static LatLng latLng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_post);//for view people
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        foundName = (EditText) findViewById(R.id.itemName);
        foundDescription = (EditText) findViewById(R.id.itemDescription);
        foundCategory = (Spinner) findViewById(R.id.categorySpinner);
        foundDateDay = (Spinner) findViewById(R.id.daySpinner);
        foundDateMonth = (Spinner) findViewById(R.id.monthSpinner);
        foundDateYear = (Spinner) findViewById(R.id.yearSpinner);

        post = (Button) findViewById(R.id.createFound);
        cancelPost = (Button) findViewById(R.id.cancelFound);//for view people
        map = (Button) findViewById(R.id.mapButton);

        ArrayAdapter<ItemCategory> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, ItemCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundCategory.setAdapter(adapter);

        Integer days[] = new Integer[31];
        for(int i = 0; i<days.length; i++){
            days[i] = (i+1);
        }

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateDay.setAdapter(adapter2);

        //String months[] = {"January", "Feburary", "March", "April","May","June","July","August","Septemeber", "October","November","December"};
        Integer months[] = new Integer[12];
        for(int i = 0; i<months.length; i++){
            months[i] = (i+1);
        }

        ArrayAdapter<Integer> adapter3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateMonth.setAdapter(adapter3);

        Integer years[] = new Integer[100];
        for(int i = 0; i<years.length; i++){
            years[i] = (i + 1990);
        }

        ArrayAdapter<Integer> adapter4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateYear.setAdapter(adapter4);

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
                ItemType type = ItemType.FOUND;

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
