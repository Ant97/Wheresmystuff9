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
 * This is controller for Register Screen
 * @author AnT. & Annette
 * @version 1.0
 */

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText registerName =
                (EditText) findViewById(R.id.nameRegistering);
        final EditText registerUsername =
                (EditText) findViewById(R.id.userNameRegistering);
        final EditText registerPW =
                (EditText) findViewById(R.id.passwordRegister);
        final EditText registerPW1 =
                (EditText) findViewById(R.id.password);

        final Button register = (Button) findViewById(R.id.ButtonRegisterReg);
        final Button cancelRegister = (Button) findViewById(R.id.ButtonCancelReg);

        cancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel2Intent =
                        new Intent(RegisterScreen.this, WelcomeScreen.class);
                RegisterScreen.this.startActivity(cancel2Intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = registerName.getText().toString();
                final String username = registerUsername.getText().toString();
                final String pw = registerPW.getText().toString();
                final String pw1 = registerPW1.getText().toString();

                User newUser = new User(name, pw);

                if (Model.registerNewUser(newUser) &&
                        Model.validatePassword(pw, pw1)) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegisterScreen.this);
                    builder.setMessage("Register Successful")
                            .setNegativeButton("OK", null)
                            .create().show();
                    Intent registerIntent =
                            new Intent(RegisterScreen.this, LoginScreen.class);
                    RegisterScreen.this.startActivity(registerIntent);
                } else {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegisterScreen.this);
                    builder.setMessage("Register Failed")
                            .setNegativeButton("Retry", null)
                            .create().show();
                }
            }
        });
    }
}
