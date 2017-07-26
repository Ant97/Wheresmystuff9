package com.example.jaggia.wheresmystuff9.controllers;
import com.example.jaggia.wheresmystuff9.model.*;
import com.example.jaggia.wheresmystuff9.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jaggia.wheresmystuff9.model.error_coding.InvalidEmailException;
import com.example.jaggia.wheresmystuff9.model.user_system.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedHashMap;


/**
 * This is controller for Login Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class LoginScreen extends AppCompatActivity {
    private final String TAG = "LoginScreen";

    private FirebaseAuth myAuth;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    private EditText loginEmail;
    private EditText loginPW;
    private Button login;
    private Button cancelLogin;
    private Button resetPassword;

    private static LinkedHashMap<String, Integer> LoginAttempts = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);

        loginEmail = (EditText) findViewById(R.id.email);
        loginPW = (EditText) findViewById(R.id.password);


        login = (Button) findViewById(R.id.ButtonLogin);
        cancelLogin = (Button) findViewById(R.id.ButtonCancel);
        resetPassword = (Button) findViewById(R.id.ResetPassword);

        databaseReference.child("app").child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> usersChildren = dataSnapshot.getChildren();
                for (DataSnapshot  child: usersChildren){
                    User value = child.getValue(User.class);
                    Model.registerNewUser(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, databaseError.getMessage());
            }
        });
    }
    protected void onStart(){
        super.onStart();

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
                final String email = loginEmail.getText().toString();
                final String pw = loginPW.getText().toString();
                if(!LoginAttempts.containsKey(email)) {
                    LoginAttempts.put(email, 0);
                }
                if(pw.length()>0) {
                    if (LoginAttempts.get(email) >= 3) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                        builder.setMessage("This account is currently locked. Please contact an admin to unlock it")
                                .setNegativeButton("Retry", null)
                                .create().show();
                    } else {
                        myAuth.signInWithEmailAndPassword(email, pw)
                                .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "signInWithEmail:success");
                                            Model.setCurrentUser(Model.findUserByEmail(email));
                                            Intent loginIntent =
                                                    new Intent(LoginScreen.this, MainUserScreen.class);
                                            LoginScreen.this.startActivity(loginIntent);
                                        } else if (LoginAttempts.get(email) >= 2) {
                                            LoginAttempts.put(email, LoginAttempts.get(email) + 1);
                                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                                            builder.setMessage("This account is now locked after multiple failed attempts. Please contact an admin to unlock it")
                                                    .setNegativeButton("Retry", null)
                                                    .create().show();
                                        } else {
                                            LoginAttempts.put(email, LoginAttempts.get(email) + 1);
                                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                                            builder.setMessage("Login Failed: Email or password not correct")
                                                    .setNegativeButton("Retry", null)
                                                    .create().show();
                                        }
                                    }
                                });
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                    builder.setMessage("Please fill in all information")
                            .setNegativeButton("Retry", null)
                            .create().show();
                }
            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(LoginScreen.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                builder.setMessage("Please enter email");
                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String emailAddress = input.getText().toString();
                        try {
                            Model.validateEmailFormat(emailAddress);
                            myAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(LoginScreen.this);
                                    builder2.setMessage("An email has been sent to reset your password")
                                            .setNegativeButton("Retry", null)
                                            .create().show();
                                }
                            });
                        }catch (InvalidEmailException e){
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(LoginScreen.this);
                            builder2.setMessage("The email is not an valid email address")
                                    .setNegativeButton("Retry", null)
                                    .create().show();
                        }
                    }
                });
                builder.setView(input);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }
}

