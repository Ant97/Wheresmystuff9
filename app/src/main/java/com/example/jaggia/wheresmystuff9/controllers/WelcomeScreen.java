package com.example.jaggia.wheresmystuff9.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.jaggia.wheresmystuff9.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * This is controller for Welcome Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class WelcomeScreen extends AppCompatActivity {
    CallbackManager callbackManager;
    // --Commented out by Inspection (7/16/17, 7:37 PM):private final String TAG = "Welcome Screen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        callbackManager = CallbackManager.Factory.create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

//        final TextView title = (TextView) findViewById(R.id.welcome);
        LoginButton facebookbutton = (LoginButton) findViewById(R.id.fblogin_button);
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

        facebookbutton.setReadPermissions("email");
        facebookbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent loginIntent =
                        new Intent(WelcomeScreen.this, MainUserScreen.class);
                WelcomeScreen.this.startActivity(loginIntent);
            }

            @Override
            public void onCancel() {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(WelcomeScreen.this);
                builder.setMessage("Facebook Login Canceled")
                        .setNegativeButton("Retry", null)
                        .create().show();
            }

            @Override
            public void onError(FacebookException error) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(WelcomeScreen.this);
                builder.setMessage(error.getMessage())
                        .setNegativeButton("Retry", null)
                        .create().show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

