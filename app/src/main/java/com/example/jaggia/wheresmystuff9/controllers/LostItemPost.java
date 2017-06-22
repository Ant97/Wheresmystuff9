package com.example.jaggia.wheresmystuff9.controllers;

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
import com.example.jaggia.wheresmystuff9.R;

public class LostItemPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText lostName = (EditText) findViewById(R.id.lostName);
        final EditText lostDescription = (EditText) findViewById(R.id.lostDescription);
        final EditText lostLocationLat = (EditText) findViewById(R.id.lostLocationLat);
        final EditText lostLocationLng = (EditText) findViewById(R.id.lostLocationLng);
        final Spinner lostCategory = (Spinner) findViewById(R.id.lostCategory);
        final EditText lostReward = (EditText) findViewById(R.id.lostReward);
        final Spinner lostDateDay = (Spinner) findViewById(R.id.lostDateDay);
        final Spinner lostDateMonth = (Spinner) findViewById(R.id.lostDateMonth);
        final Spinner lostDateYear = (Spinner) findViewById(R.id.lostDateYear);

        Button cancelPost = (Button) findViewById(R.id.ButtonCancelPost);
        Button post = (Button) findViewById(R.id.ButtonPost);


        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostCategory.setAdapter(adapter);

        String days[] = new String[31];
        for(int i = 0; i<days.length; i++){
            days[i] = Integer.toString(i+1);
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateDay.setAdapter(adapter2);

        String months[] = {"January", "Feburary", "March", "April","May","June","July","August","Septemeber", "October","November","December"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateMonth.setAdapter(adapter3);

        String years[] = new String[100];
        for(int i = 0; i<years.length; i++){
            years[i] = Integer.toString(i + 1990);
        }

        ArrayAdapter<String> adapter4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lostDateYear.setAdapter(adapter4);

        cancelPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent =
                        new Intent(LostItemPost.this, MainUserScreen.class);
                LostItemPost.this.startActivity(cancelIntent);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = lostName.getText().toString();
                String description = lostDescription.getText().toString();
                Location location = new Location("itemLocation");
                location.setLatitude(Double.parseDouble(lostLocationLat.getText().toString()));
                location.setLongitude(Double.parseDouble(lostLocationLng.getText().toString()));
                ItemStatus status = lostStatus.getSelectedItem().toString();
                String category = lostCategory.getSelectedItem().toString();
                String reward = lostReward.getText().toString();
                ItemType type = "lost";
                String dateDay = lostDateDay.getSelectedItem().toString();
                String dateMonth = lostDateMonth.getSelectedItem().toString();
                String dateYear = lostDateYear.getSelectedItem().toString();

                Item newItem = new Item(name, description,location,status,category,reward,type, date);

                if(Model.lostItems.addItem(newItem)){
                    //the item was created and added to the lostItems
                    AlertDialog.Builder builder = new AlertDialog.Builder(LostItemPost.this);
                    builder.setMessage("Item was created successfully!").setNegativeButton("OK", null).create().show();
                    Intent postIntent = new Intent(LostItemPost.this, MainUserScreen.class);
                    LostItemPost.this.startActivity(postIntent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LostItemPost.this);
                    builder.setMessage("Item was not created").setNegativeButton("Retry", null).create().show();

                }
            }

        });
    }

}
