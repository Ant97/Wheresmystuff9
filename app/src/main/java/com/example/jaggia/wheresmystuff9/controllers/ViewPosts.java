package com.example.jaggia.wheresmystuff9.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jaggia.wheresmystuff9.model.item_system.FoundItem;
import com.example.jaggia.wheresmystuff9.model.item_system.LostItem;
import com.example.jaggia.wheresmystuff9.model.Model;
import com.example.jaggia.wheresmystuff9.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

//import static com.example.jaggia.wheresmystuff9.R.id.list_view;

public class ViewPosts extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final String TAG = "ViewPosts";
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("app");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseReference.child("LostItem").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemLostChildren = dataSnapshot.getChildren();
                Model.clearList(Model.getLostList());
                for (DataSnapshot  child: itemLostChildren){
                    LostItem value = child.getValue(LostItem.class);
                    Model.addItem(Model.getLostList(), value);
                    populateListView();
                    registerClick();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, databaseError.getMessage());
            }
        });
        databaseReference.child("FoundItem").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> itemFoundChildren = dataSnapshot.getChildren();
                Model.clearList(Model.getFoundList());
                for (DataSnapshot  child: itemFoundChildren){
                    FoundItem value = child.getValue(FoundItem.class);
                    Model.addItem(Model.getFoundList(), value);
                    populateListView();
                    registerClick();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, databaseError.getMessage());
            }
        });
    }

    private void populateListView() {


        List<String> postNames = Model.listItems(Model.getLostList());
        List<String> postNamesFound = Model.listItems(Model.getFoundList());
        postNames.addAll(postNamesFound);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postNames);
        ListView list = (ListView) findViewById(R.id.postlist);
        list.setAdapter(adapter);


        EditText searchBar = (EditText) findViewById(R.id.search_bar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void registerClick() {
        ListView list = (ListView) findViewById(R.id.postlist);
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
        } else if (id == R.id.map_posts) {
            Intent logoutIntent =
                    new Intent(ViewPosts.this, ViewMapPosts.class);
            ViewPosts.this.startActivity(logoutIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
