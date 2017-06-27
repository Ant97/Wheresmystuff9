package com.example.jaggia.wheresmystuff9.controllers;


import com.example.jaggia.wheresmystuff9.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jaggia.wheresmystuff9.Model.Model;
/**
 * This is controller for Register Screen
 * @author AnT. & Annette
 * @version 1.0
 */

public class RegisterScreen extends AppCompatActivity {

    Model mdl = Model.getInstance();
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
        final EditText registerPW2 =
                (EditText) findViewById(R.id.password);
        final Spinner registerUserType = (Spinner) findViewById(R.id.typeSpinner);

        Button register = (Button) findViewById(R.id.ButtonRegisterReg);
        Button cancelRegister = (Button) findViewById(R.id.ButtonCancelReg);

        String userTypes[] = {"User", "Admin"};

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        registerUserType.setAdapter(adapter);

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
                String pw1 = registerPW2.getText().toString();
                boolean userType = false;
                String success = "Registration Successful";
                if(registerUserType.getSelectedItem().toString().equals("Admin")){
                    userType = true;
                    success = "Registration as Admin Successful";
                }

                if (Model.validatePassword(pw, pw1) && Model.registerNewUser(Model.createNewUser(name, username, pw, userType))) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegisterScreen.this);
                    builder.setMessage(success)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent registerIntent =
                                            new Intent(RegisterScreen.this, LoginScreen.class);
                                    RegisterScreen.this.startActivity(registerIntent);
                                }
                            })
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    Intent registerIntent =
                                            new Intent(RegisterScreen.this, LoginScreen.class);
                                    RegisterScreen.this.startActivity(registerIntent);
                                }
                            }).create().show();
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
