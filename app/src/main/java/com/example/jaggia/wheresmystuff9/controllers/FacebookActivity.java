package com.example.jaggia.wheresmystuff9.controllers;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jaggia.wheresmystuff9.R;
import com.facebook.CallbackManager;

public class FacebookActivity extends FragmentActivity {
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_facebook);
    }
}
