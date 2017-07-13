package com.example.jaggia.wheresmystuff9.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jaggia.wheresmystuff9.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * This is controller for Welcome Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class WelcomeScreen extends AppCompatActivity {

    private final String TAG = "Welcome Screen";
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
}

