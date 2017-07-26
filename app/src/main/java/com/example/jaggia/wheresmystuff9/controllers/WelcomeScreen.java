package com.example.jaggia.wheresmystuff9.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.jaggia.wheresmystuff9.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * This is controller for Welcome Screen
 * @author AnT. & Annette
 * @version 1.0
 */
public class WelcomeScreen extends AppCompatActivity {
    private GoogleApiClient myGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private CallbackManager callbackManager;
    private FirebaseAuth myAuth;
    // --Commented out by Inspection (7/16/17, 7:37 PM):private final String TAG = "Welcome Screen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();

        myAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("496422704020-5b6puqdsslqmkrb9tk63rvjbdfq8k02j.apps.googleusercontent.com").requestEmail().build();
        myGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener(){
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(WelcomeScreen.this);
                        builder.setMessage("Failed to connect with GoogleApiClient")
                                .setNegativeButton("Retry", null)
                                .create().show();
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(myGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
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
                handleFacebookAccessToken(loginResult.getAccessToken());
                Intent loginIntent =
                        new Intent(WelcomeScreen.this, MainUserScreen.class);
                WelcomeScreen.this.startActivity(loginIntent);
            }

            @Override
            public void onCancel() {
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
    protected void onRestart(){
        super.onRestart();
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(WelcomeScreen.this);
                builder.setMessage("Failed to sign in with Google plus account")
                        .setNegativeButton("Retry", null)
                        .create().show();
            }
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        myAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent loginIntent =
                                    new Intent(WelcomeScreen.this, MainUserScreen.class);
                            WelcomeScreen.this.startActivity(loginIntent);
                        } else {
                            AlertDialog.Builder builder =
                                    new AlertDialog.Builder(WelcomeScreen.this);
                            builder.setMessage("Failed to sign in with Google plus account")
                                    .setNegativeButton("Retry", null)
                                    .create().show();
                        }
                    }
                });
    }
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        myAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = myAuth.getCurrentUser();
                        }
                    }
                });
    }
}

