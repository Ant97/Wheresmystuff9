package com.example.jaggia.wheresmystuff9.controllers;
import com.example.jaggia.wheresmystuff9.R;
//import com.example.jaggia.wheresmystuff9.model.Model;
//import com.example.jaggia.wheresmystuff9.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * This is controller for Welcome Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        final TextView title = (TextView) findViewById(R.id.title);
        final Button registerbtn = (Button) findViewById(R.id.registerbtn);
        final Button loginbtn = (Button) findViewById(R.id.loginbtn);

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

