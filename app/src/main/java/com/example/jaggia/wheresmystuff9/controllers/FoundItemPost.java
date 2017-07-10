package com.example.jaggia.wheresmystuff9.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jaggia.wheresmystuff9.Model.Item;
import com.example.jaggia.wheresmystuff9.Model.ItemCategory;
import com.example.jaggia.wheresmystuff9.Model.ItemStatus;
import com.example.jaggia.wheresmystuff9.Model.ItemType;
import com.example.jaggia.wheresmystuff9.Model.Model;
import com.example.jaggia.wheresmystuff9.Model.MyLocation;
import com.example.jaggia.wheresmystuff9.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/**
 * Created by jaggia on 6/27/17.
 */

public class FoundItemPost extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item_post);//for view people
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        final EditText foundName = (EditText) findViewById(R.id.itemName);
        final EditText foundDescription = (EditText) findViewById(R.id.itemDescription);
        final EditText foundLocationLat = (EditText) findViewById(R.id.longitude);
        final EditText foundLocationLng = (EditText) findViewById(R.id.latitude);
        final Spinner foundCategory = (Spinner) findViewById(R.id.categorySpinner);
        //final EditText foundReward = (EditText) findViewById(R.id.reward);
        final Spinner foundDateDay = (Spinner) findViewById(R.id.daySpinner);
        final Spinner foundDateMonth = (Spinner) findViewById(R.id.monthSpinner);
        final Spinner foundDateYear = (Spinner) findViewById(R.id.yearSpinner);

        Button post = (Button) findViewById(R.id.createFound);
        Button cancelPost = (Button) findViewById(R.id.cancelFound);//for view people


        ArrayAdapter<ItemCategory> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ItemCategory.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foundCategory.setAdapter(adapter);

       // ArrayAdapter<Item.ItemCategory> adapter1 = new ArrayAdapter<Item.ItemCategory>()

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
                String latitude = foundLocationLat.getText().toString();
                String longitude = foundLocationLng.getText().toString();
                ItemStatus status = ItemStatus.UNRESOLVED;
                ItemCategory category = (ItemCategory) foundCategory.getSelectedItem();
                String reward = "";
                ItemType type = ItemType.LOST;

                if(name.length() == 0 || latitude.length() == 0 || longitude.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(FoundItemPost.this);
                    builder.setMessage("Item was not create: Please fill in required information");
                    builder.setNegativeButton("Retry", null).create().show();
                } else {
                    MyLocation location = new MyLocation("itemLocation");
                    location.setLatitude(Double.parseDouble(latitude));
                    location.setLongitude(Double.parseDouble(longitude));

                    int dateDay = (int) foundDateDay.getSelectedItem();
                    int dateMonth = (int) foundDateMonth.getSelectedItem();
                    int dateYear = (int) foundDateYear.getSelectedItem();
                    Date date = new Date(dateYear, dateMonth, dateDay);


                    if (Model.addItem(Model.getFoundList(), Model.createNewItem(Model.getCurrentUser(), name, description, date, location, "", status, type, category))) {
                        databaseReference.child("app").child("ItemFound").push().setValue(Model.createNewItem(Model.getCurrentUser(), name, description, date, location, reward, status, type, category));
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

}
