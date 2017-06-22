package com.example.jaggia.wheresmystuff9.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jaggia.wheresmystuff9.Model.*;
import com.example.jaggia.wheresmystuff9.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.jaggia.wheresmystuff9.R.id.list_view;

public class ViewPosts extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final Model mdl = Model.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        populateListView();
        registerClick();
    }

    private void populateListView() {
        ItemList posts = Model.getLostList();

        List<String> postNames = new ArrayList<>();
        int count = 0;
        for(Item i : posts.getItemList()){
            postNames.add(i.getName());
            System.out.println(postNames.get(count));
            count++;
        }
        List<String> post = new ArrayList<>();
        post.add("hi");
        post.add("hello");
        post.add("bye");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, postNames);
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(adapter);
    }

    private void registerClick() {
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {
                Intent viewPostIntent =
                        new Intent(ViewPosts.this, newPost.class);
                ViewPosts.this.startActivity(viewPostIntent);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_posts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.make_post) {
            Intent makePostIntent =
                    new Intent(ViewPosts.this, MakeAPost.class);
            ViewPosts.this.startActivity(makePostIntent);
        } else if (id == R.id.view_posts) {
            Intent viewPostIntent =
                    new Intent(ViewPosts.this, ViewPosts.class);
            ViewPosts.this.startActivity(viewPostIntent);
        } else if (id == R.id.logout) {
            Intent logoutIntent =
                    new Intent(ViewPosts.this, LoginScreen.class);
            ViewPosts.this.startActivity(logoutIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
