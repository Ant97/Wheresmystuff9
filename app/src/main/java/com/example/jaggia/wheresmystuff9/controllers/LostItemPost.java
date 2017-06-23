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

import com.example.jaggia.wheresmystuff9.Model.*;

import com.example.jaggia.wheresmystuff9.Model.Model;
import com.example.jaggia.wheresmystuff9.R;

import java.util.Date;

public class LostItemPost extends AppCompatActivity {
    final Model mdl = Model.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        final EditText lostName = (EditText) findViewById(R.id.itemName);
        final EditText lostDescription = (EditText) findViewById(R.id.itemDescription);
        final EditText lostLocationLat = (EditText) findViewById(R.id.longitude);
        final EditText lostLocationLng = (EditText) findViewById(R.id.latitude);
        final Spinner lostCategory = (Spinner) findViewById(R.id.categorySpinner);
        final EditText lostReward = (EditText) findViewById(R.id.reward);
        final Spinner lostDateDay = (Spinner) findViewById(R.id.daySpinner);
        final Spinner lostDateMonth = (Spinner) findViewById(R.id.monthSpinner);
        final Spinner lostDateYear = (Spinner) findViewById(R.id.yearSpinner);

        Button post = (Button) findViewById(R.id.createButton);
        Button cancelPost = (Button) findViewById(R.id.cancelLost);


        ArrayAdapter<Item.ItemCategory> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Item.getItemCategoryValues());
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

                User user = Model.getCurrentUser();
                String name = lostName.getText().toString();
                String description = lostDescription.getText().toString();
                String latitude = lostLocationLat.getText().toString();
                String longitude = lostLocationLng.getText().toString();
                Item.ItemStatus status = Item.ItemStatus.UNRESOLVED;
                Item.ItemCategory category = (Item.ItemCategory) lostCategory.getSelectedItem();
                String reward = lostReward.getText().toString();
                Item.ItemType type = Item.ItemType.LOST;
                if(name.length() == 0 || latitude.length() == 0 || longitude.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LostItemPost.this);
                    builder.setMessage("Item was not create: Please fill in required information");
                    builder.setNegativeButton("Retry", null).create().show();
                } else {
                    Location location = new Location("itemLocation");
                    location.setLatitude(Double.parseDouble(latitude));
                    location.setLongitude(Double.parseDouble(longitude));

                    int dateDay = (int) lostDateDay.getSelectedItem();
                    int dateMonth = (int) lostDateMonth.getSelectedItem();
                    int dateYear = (int) lostDateYear.getSelectedItem();
                    Date date = new Date(dateYear, dateMonth, dateDay);

                    Item newItem = Model.createNewItem(user, name, description, date, location, reward, status, type, category);

                    if (Model.addItem(Model.getLostList(), newItem)) {
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

}
