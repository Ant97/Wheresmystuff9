package com.example.jaggia.wheresmystuff9.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jaggia.wheresmystuff9.Model.Item;
import com.example.jaggia.wheresmystuff9.Model.Model;
import com.example.jaggia.wheresmystuff9.Model.User;
import com.example.jaggia.wheresmystuff9.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * This is controller for Welcome Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class WelcomeScreen extends AppCompatActivity {

    private final String TAG = "Welcome Screen";
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReferenceUsers = firebaseDatabase.getReference().child("Users");
    DatabaseReference databaseReferenceItemFound = firebaseDatabase.getReference().child("ItemFound");
    DatabaseReference databaseReferenceItemLost = firebaseDatabase.getReference().child("ItemLost");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        final TextView title = (TextView) findViewById(R.id.welcome);
        final Button registerbtn = (Button) findViewById(R.id.register);
        final Button loginbtn = (Button) findViewById(R.id.login);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent =
                        new Intent(WelcomeScreen.this, LoginScreen.class);
                WelcomeScreen.this.startActivity(loginIntent);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent =
                        new Intent(WelcomeScreen.this, RegisterScreen.class);
                WelcomeScreen.this.startActivity(registerIntent);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        databaseReferenceUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> usersChildren = dataSnapshot.getChildren();

                for (DataSnapshot  child: usersChildren){
                    User value = child.getValue(User.class);
                    Model.registerNewUser(value);
                    Log.v(TAG, "adding users from database to model");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> usersChildren = dataSnapshot.getChildren();

                for (DataSnapshot  child: usersChildren){
                    User value = child.getValue(User.class);
                    Model.registerNewUser(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReferenceItemFound.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemChildren = dataSnapshot.getChildren();

                for(DataSnapshot child: itemChildren){
                    Item value = child.getValue(Item.class);
                    Model.addItem(Model.getFoundList(), value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReferenceItemLost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemChildren = dataSnapshot.getChildren();

                for(DataSnapshot child: itemChildren){
                    Item value = child.getValue(Item.class);
                    Model.addItem(Model.getLostList(), value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

