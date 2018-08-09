package com.example.jlam.cobrakaisoundboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.util.UUID;

public class Feedback extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Analytics analytics = new Analytics();
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText editText_feedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        editText_feedBack = (EditText) findViewById(R.id.editText_feedBack);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Feedback");

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback) {
            Intent i = new Intent(getApplicationContext(), Feedback.class);
            startActivity(i);
        } else if (id == R.id.nav_home) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void feedBackSubmit(View view) {

        if (!editText_feedBack.getText().toString().equals("")) {
            analytics.sendAnalytics(this, "Open App");
            myRef.child(System.currentTimeMillis() / 1000L + UUID.randomUUID().toString()).setValue(editText_feedBack.getText().toString());
            Toast.makeText(this, "Thank you for your feedback.", Toast.LENGTH_SHORT).show();
             Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);


        } else {
            Toast.makeText(this, "Please write a feedback.", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }
}
