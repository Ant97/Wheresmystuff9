package com.example.jaggia.wheresmystuff9.controllers;
import com.example.jaggia.wheresmystuff9.Model.*;
import com.example.jaggia.wheresmystuff9.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * This is controller for Login Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginName = (EditText) findViewById(R.id.userName);
        final EditText loginPW = (EditText) findViewById(R.id.password);

        final Button login = (Button) findViewById(R.id.ButtonLogin);
        final Button cancelLogin = (Button) findViewById(R.id.ButtonCancel);

        cancelLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel1Intent =
                        new Intent(LoginScreen.this, WelcomeScreen.class);
                LoginScreen.this.startActivity(cancel1Intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = loginName.getText().toString();
                final String pw = loginPW.getText().toString();

                User user = new User(name, pw);

                if (Model.validateUser(user)) {
                    Intent loginIntent =
                            new Intent(LoginScreen.this, mainApp.class);
                    LoginScreen.this.startActivity(loginIntent);
                } else {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(LoginScreen.this);
                    builder.setMessage("Login Failed: UserName or PW incorrect")
                            .setNegativeButton("Retry", null)
                            .create().show();
                }
            }
        });
    }
}

