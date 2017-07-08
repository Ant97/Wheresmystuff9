package com.example.jaggia.wheresmystuff9.controllers;
import com.example.jaggia.wheresmystuff9.Model.*;
import com.example.jaggia.wheresmystuff9.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * This is controller for Login Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class LoginScreen extends AppCompatActivity {
    private final String TAG = "LoginScreen";

    private FirebaseAuth myAuth;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference databaseReferenceUsers = databaseReference.child("Users");

    EditText loginUsername;
    EditText loginEmail;
    EditText loginPW;
    Button login;
    Button cancelLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);

        loginUsername = (EditText) findViewById(R.id.userName);
        loginEmail = (EditText) findViewById(R.id.email);
        loginPW = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.ButtonLogin);
        cancelLogin = (Button) findViewById(R.id.ButtonCancel);
        databaseReferenceUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> usersChildren = dataSnapshot.getChildren();

                for (DataSnapshot  child: usersChildren){
                    User value = child.getValue(User.class);
                    Model.registerNewUser(value);
                    Log.v(TAG, "adding users from database to model");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> usersChildren = dataSnapshot.getChildren();

                for (DataSnapshot  child: usersChildren){
                    User value = child.getValue(User.class);
                    Model.registerNewUser(value);
                    Log.v(TAG, "adding users from database to model");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
                String username = loginUsername.getText().toString();
                String email = loginEmail.getText().toString();
                String pw = loginPW.getText().toString();
                myAuth.signInWithEmailAndPassword(email, pw)
                        .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = myAuth.getCurrentUser();
                                    Intent loginIntent =
                                            new Intent(LoginScreen.this, MainUserScreen.class);
                                    LoginScreen.this.startActivity(loginIntent);
                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    AlertDialog.Builder builder =
                                            new AlertDialog.Builder(LoginScreen.this);
                                    builder.setMessage("Login Failed: UserName or PW incorrect")
                                            .setNegativeButton("Retry", null)
                                            .create().show();
                                }
                            }
                        });
            }
        });
    }
}

