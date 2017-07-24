package com.example.jaggia.wheresmystuff9.controllers;
import com.example.jaggia.wheresmystuff9.model.*;
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
import java.util.Map;


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

    private EditText loginUsername;
    private EditText loginEmail;
    private EditText loginPW;
    private Button login;
    private Button cancelLogin;

    LinkedHashMap<String, Integer> LoginAttempts = new LinkedHashMap<String, Integer>();

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
                final String username = loginUsername.getText().toString();
                final String email = loginEmail.getText().toString();
                final String pw = loginPW.getText().toString();
                if(!LoginAttempts.containsKey(username)) {
                    LoginAttempts.put(username, 0);
                }
                if(Model.validateUser(username, pw)) {
                    myAuth.signInWithEmailAndPassword(email, pw)
                            .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "signInWithEmail:success");
//                                        FirebaseUser user = myAuth.getCurrentUser();
                                        Model.setCurrentUser(Model.findUserByUsername(username));
                                        Intent loginIntent =
                                                new Intent(LoginScreen.this, MainUserScreen.class);
                                        LoginScreen.this.startActivity(loginIntent);
                                    } else {
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        AlertDialog.Builder builder =
                                                new AlertDialog.Builder(LoginScreen.this);
                                        builder.setMessage("Login Failed: Email or PW incorrect. If you logged in with a Social Media app previously, please continue using said app login button.")
                                                .setNegativeButton("Retry", null)
                                                .create().show();
                                    }
                                }
                            });
                } else if(LoginAttempts.get(username) >= 2){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                    builder.setMessage("This account is now locked after multiple failed attempts. Please contact an admin to unlock it")
                            .setNegativeButton("Retry", null)
                            .create().show();
                } else {
                    LoginAttempts.put(username, LoginAttempts.get(username)+1);
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                    builder.setMessage("Login Failed: Username or password not correct")
                            .setNegativeButton("Retry", null)
                            .create().show();
                }
            }
        });
    }
}

