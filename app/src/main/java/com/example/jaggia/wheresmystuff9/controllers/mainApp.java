package com.example.jaggia.wheresmystuff9.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jaggia.wheresmystuff9.R;


public class mainApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);

        final Button logoutbtn = (Button) findViewById(R.id.ButtonLogout);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent =
                        new Intent(mainApp.this, LoginScreen.class);
                mainApp.this.startActivity(loginIntent);
            }
        });
    }
}
