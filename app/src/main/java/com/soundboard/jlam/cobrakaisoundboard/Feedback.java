package com.soundboard.jlam.cobrakaisoundboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.util.UUID;

public class Feedback extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    AdView adView_user, adView_developer;
    Analytics analytics = new Analytics();
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText editText_feedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
      MobileAds.initialize(this, "ca-app-pub-7448660774088972/5467742818");
        adView_user = (AdView) findViewById(R.id.adView_user);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView_user.loadAd(adRequest);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        editText_feedBack = (EditText) findViewById(R.id.editText_feedBack);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editText_feedBack.setOnEditorActionListener(new TextView.OnEditorActionListener()

        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) ||
                        (actionId == EditorInfo.IME_ACTION_DONE))
                {
                    editText_feedBack.clearFocus();
                    InputMethodManager iMgr = null;
                    iMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    iMgr.hideSoftInputFromWindow(editText_feedBack.getWindowToken(), 0);
                }
                return true;
            }
        });


        editText_feedBack.setOnKeyListener(new View.OnKeyListener()

        {
            public boolean onKey(View view, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText_feedBack.getWindowToken(), 0);
                    editText_feedBack.setFocusable(false);
                    editText_feedBack.setFocusableInTouchMode(true);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Feedback");

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback)
        {

        }
        else if (id == R.id.nav_home)
        {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void feedBackSubmit(View view)
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        if (editText_feedBack.getText().toString().equals("id"))
        {
            editText_feedBack.getText().clear();
            editText_feedBack.setText(android_id);
        }
        else
        {
            if (isConnected)
            {
                if (!editText_feedBack.getText().toString().equals(""))
                {
                    analytics.sendAnalytics(this, "Open App");
                    myRef.child(System.currentTimeMillis() / 1000L + UUID.randomUUID().toString()).setValue(editText_feedBack.getText().toString()
                            + "/// Phone Model: " + Build.MANUFACTURER.substring(0, 1).toUpperCase() + Build.MANUFACTURER.substring(1) + " - " + Build.MODEL
                            + "///Version Name: " + BuildConfig.VERSION_NAME + ", Version Code: " + BuildConfig.VERSION_CODE
                            + "/// Release#: " + Build.VERSION.RELEASE);
                    Toast.makeText(this, "Thank you for your feedback.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(this, "Please write a feedback.", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Please make sure you're connected to an internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            finish();
        }
    }

}
