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
import android.widget.Spinner;

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
        final Spinner registerUserType = (Spinner) findViewById(R.id.userType);

        Button register = (Button) findViewById(R.id.ButtonRegisterReg);
        Button cancelRegister = (Button) findViewById(R.id.ButtonCancelReg);

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
                String name = registerName.getText().toString();
                String username = registerUsername.getText().toString();
                String pw = registerPW.getText().toString();
                String pw1 = registerPW1.getText().toString();
                String userType = registerUserType.getSelectedItem().toString();

                User newUser = new User(name, username, pw, userType);

                if (Model.validatePassword(pw, pw1) && Model.registerNewUser(newUser)) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegisterScreen.this);
                    builder.setMessage("Register Successful")
                            .setNegativeButton("OK", null)
                            .create().show();
                    Intent registerIntent =
                            new Intent(RegisterScreen.this, LoginScreen.class);
                    RegisterScreen.this.startActivity(registerIntent);
                } else if(Model.validatePassword(pw, pw1)){
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegisterScreen.this);
                    builder.setMessage("Register failed because username has been taken")
                            .setNegativeButton("Retry", null)
                            .create().show();
                } else {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegisterScreen.this);
                    builder.setMessage("Register failed because the passwords do not match")
                            .setNegativeButton("Retry", null)
                            .create().show();
                }
            }
        });
    }
}
