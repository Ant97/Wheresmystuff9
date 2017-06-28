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

import com.example.jaggia.wheresmystuff9.Model.Model;
import com.example.jaggia.wheresmystuff9.Model.Item;

import com.example.jaggia.wheresmystuff9.R;

import java.util.Calendar;
import java.util.Date;

public class FoundItemPost extends AppCompatActivity {
    final Model mdl = Model.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        final EditText foundName = (EditText) findViewById(R.id.itemName1);
        final EditText foundDescription = (EditText) findViewById(R.id.itemDescription1);
        final EditText foundLocationLat = (EditText) findViewById(R.id.longitude1);
        final EditText foundLocationLng = (EditText) findViewById(R.id.latitude1);
        final Spinner foundCategory = (Spinner) findViewById(R.id.categorySpinner1);
        final Spinner foundDateDay = (Spinner) findViewById(R.id.daySpinner1);
        final Spinner foundDateMonth = (Spinner) findViewById(R.id.monthSpinner1);
        final Spinner foundDateYear = (Spinner) findViewById(R.id.yearSpinner1);

        Button createFound = (Button) findViewById(R.id.createFound);
        Button cancelFound = (Button) findViewById(R.id.cancelFound);


        ArrayAdapter<Item.ItemCategory> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Item.getItemCategoryValues());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundCategory.setAdapter(adapter);

        Integer days[] = new Integer[31];
        for(int i = 0; i<days.length; i++){
            days[i] = (i+1);
        }

        ArrayAdapter<Integer> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateDay.setAdapter(adapter2);

        //String months[] = {"January", "Feburary", "March", "April","May","June","July","August","Septemeber", "October","November","December"};
        Integer months[] = new Integer[12];
        for(int i = 0; i<months.length; i++){
            months[i] = (i+1);
        }

        ArrayAdapter<Integer> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateMonth.setAdapter(adapter3);

        Integer years[] = new Integer[100];
        for(int i = 0; i<years.length; i++){
            years[i] = (i + 1990);
        }

        ArrayAdapter<Integer> adapter4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundDateYear.setAdapter(adapter4);

        cancelFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent =
                        new Intent(FoundItemPost.this, MakeAPost.class);
                FoundItemPost.this.startActivity(cancelIntent);
            }
        });

        createFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = foundName.getText().toString();
                String description = foundDescription.getText().toString();
                String latitude = foundLocationLat.getText().toString();
                String longitude = foundLocationLng.getText().toString();
                Item.ItemStatus status = Item.ItemStatus.UNRESOLVED;
                Item.ItemCategory category = (Item.ItemCategory) foundCategory.getSelectedItem();
                Item.ItemType type = Item.ItemType.FOUND;
                if(name.length() == 0 || latitude.length() == 0 || longitude.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(FoundItemPost.this);
                    builder.setMessage("Item was not create: Please fill in required information");
                    builder.setNegativeButton("Retry", null).create().show();
                } else {
                    Location location = new Location("itemLocation");
                    location.setLatitude(Double.parseDouble(latitude));
                    location.setLongitude(Double.parseDouble(longitude));

                    int dateDay = (int) foundDateDay.getSelectedItem();
                    int dateMonth = (int) foundDateMonth.getSelectedItem();
                    int dateYear = (int) foundDateYear.getSelectedItem();
                    Date date = new Date(dateYear, dateMonth, dateDay);


                    if (Model.addItem(Model.getLostList(), Model.createNewItem(Model.getCurrentUser(), name, description, date, location, null, status, type, category))) {
                        //the item was created and added to the lostItems
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

}
