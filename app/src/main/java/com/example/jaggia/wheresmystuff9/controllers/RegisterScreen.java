package com.example.jaggia.wheresmystuff9.controllers;
import com.example.jaggia.wheresmystuff9.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jaggia.wheresmystuff9.Model.Model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


/**
 * This is controller for Register Screen
 * @author AnT. & Annette
 * @version 1.0
 */

public class RegisterScreen extends AppCompatActivity {
    private final String TAG = "RegisterScreen";

    private FirebaseAuth myAuth;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    EditText registerEmail;
    EditText registerName;
    EditText registerUsername;
    EditText registerPW;
    EditText registerPW2;
    Spinner registerUserType;
    Button register;
    Button cancelRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmail = (EditText) findViewById(R.id.emailRegistering);
        registerName =
                (EditText) findViewById(R.id.nameRegistering);
        registerUsername =
                (EditText) findViewById(R.id.userNameRegistering);
        registerPW =
                (EditText) findViewById(R.id.passwordRegister);
        registerPW2 =
                (EditText) findViewById(R.id.password);
        registerUserType = (Spinner) findViewById(R.id.typeSpinner);

        register = (Button) findViewById(R.id.ButtonRegisterReg);
        cancelRegister = (Button) findViewById(R.id.ButtonCancelReg);

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

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = myAuth.getCurrentUser();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = registerName.getText().toString();
                final String email = registerEmail.getText().toString();
                final String username = registerUsername.getText().toString();
                final String pw = registerPW.getText().toString();
                final String pw2 = registerPW2.getText().toString();
                final boolean userType = false;

                if (Model.validatePassword(pw) && Model.validatePasswordMatch(pw, pw2) && (null == Model.findUser(username)) ) {
                    Log.v(TAG, "It is attempting to make a new user");
                    myAuth.createUserWithEmailAndPassword(email, pw)
                            .addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Model.registerNewUser(Model.createNewUser(name, username, pw, userType, email));
                                        firebaseDatabase.getReference().child("app").child("Users").push().setValue(Model.createNewUser(name, username, pw, userType, email));
                                        AlertDialog.Builder builder =
                                                new AlertDialog.Builder(RegisterScreen.this);
                                        builder.setMessage("Registration Successful")
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
                                    } else {
                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        Log.d("LoginActivity", "Failed Registration", e);
                                        AlertDialog.Builder builder =
                                                new AlertDialog.Builder(RegisterScreen.this);
                                        builder.setMessage("Model Successful but database failed")
                                                .setNegativeButton("Darn", null)
                                                .create().show();
                                    }
                                }
                            });
                } else if (!(null == Model.findUser(username))){
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegisterScreen.this);
                    builder.setMessage("Register failed because the username has been taken")
                            .setNegativeButton("Retry", null)
                            .create().show();
                } else if(!Model.validatePassword(pw)) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RegisterScreen.this);
                    builder.setMessage("Register failed because the password is illegal. Must be at least 7 characters")
                            .setNegativeButton("Retry", null)
                            .create().show();
                } else if(!Model.validatePasswordMatch(pw, pw2)){
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
