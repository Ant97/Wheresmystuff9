package com.example.jaggia.wheresmystuff9.controllers;
import com.example.jaggia.wheresmystuff9.R;
//import com.example.jaggia.wheresmystuff9.model.Model;
//import com.example.jaggia.wheresmystuff9.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This is controller for Login Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final EditText loginName = (EditText) findViewById(R.id.loginName);
        final EditText loginPW = (EditText) findViewById(R.id.loginPW);

        final Button login = (Button) findViewById(R.id.login);
        final Button cancelLogin = (Button) findViewById(R.id.cancel1);
        final TextView checkLogin = (TextView) findViewById(R.id.checkLogin);

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
                    checkLogin.setText("Username or PW incorrect");
                }
            }
        });
    }
}

