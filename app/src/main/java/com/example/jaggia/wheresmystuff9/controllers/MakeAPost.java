package com.example.jaggia.wheresmystuff9.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


import com.example.jaggia.wheresmystuff9.R;

public class MakeAPost extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_apost);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Button foundPost = (Button) findViewById(R.id.ButtonFound);
        Button lostPost = (Button) findViewById(R.id.ButtonLost);
        Button donatePost = (Button) findViewById(R.id.ButtonDonate);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    //Chane LostItemPost.class for found and donate once that iteration is reached
        foundPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foundPostIntent = new Intent(MakeAPost.this, FoundItemPost.class);
                MakeAPost.this.startActivity(foundPostIntent);
            }
        });
        lostPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lostPostIntent = new Intent(MakeAPost.this, LostItemPost.class);
                MakeAPost.this.startActivity(lostPostIntent);
            }
        });
        donatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donatePostIntent = new Intent(MakeAPost.this, LostItemPost.class);
                MakeAPost.this.startActivity(donatePostIntent);
            }
        });

    //Chane LostItemPost.class for found and donate once that iteration is reached
        foundPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foundPostIntent = new Intent(MakeAPost.this, FoundItemPost.class);
                MakeAPost.this.startActivity(foundPostIntent);
            }
        });
        lostPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lostPostIntent = new Intent(MakeAPost.this, LostItemPost.class);
                MakeAPost.this.startActivity(lostPostIntent);
            }
        });
        donatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donatePostIntent = new Intent(MakeAPost.this, LostItemPost.class);
                MakeAPost.this.startActivity(donatePostIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.make_post) {
            Intent makePostIntent =
                    new Intent(MakeAPost.this, MakeAPost.class);
            MakeAPost.this.startActivity(makePostIntent);
        } else if (id == R.id.view_posts) {
            Intent viewPostIntent =
                    new Intent(MakeAPost.this, ViewPosts.class);
            MakeAPost.this.startActivity(viewPostIntent);
        } else if (id == R.id.logout) {
            Intent logoutIntent =
                    new Intent(MakeAPost.this, LoginScreen.class);
            MakeAPost.this.startActivity(logoutIntent);
        } else if (id == R.id.map_posts) {
            Intent logoutIntent =
                    new Intent(MakeAPost.this, ViewMapPosts.class);
            MakeAPost.this.startActivity(logoutIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
