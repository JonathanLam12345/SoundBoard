package com.soundboard.jlam.cobrakaisoundboard;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import android.Manifest;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    AdView adView_developer;//, adView_user;
    Button button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_10, button_11, button_12, button_13, button_14, button_15, button_16, button_17, button_18;
    MediaPlayer one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen;
    Analytics analytics = new Analytics();
    FirebaseDatabase database;
    DatabaseReference myRef;
    boolean doubleBackToExitPressedOnce = false;
    public static final int PERMISSION_READ_STATE = 123;
    private static Context mContext;

    public void stopPlayingPlease()
    {
        try
        {
            if (one.isPlaying())
            {
                one.stop();
                one.release();
                one = null;
                one = MediaPlayer.create(MainActivity.this, R.raw.one);
            }
            else if (two.isPlaying())
            {
                two.stop();
                two.release();
                two = null;
                two = MediaPlayer.create(MainActivity.this, R.raw.two);
            }
            else if (three.isPlaying())
            {
                three.stop();
                three.release();
                three = null;
                three = MediaPlayer.create(MainActivity.this, R.raw.three);
            }
            else if (four.isPlaying())
            {
                four.stop();
                four.release();
                four = null;
                four = MediaPlayer.create(MainActivity.this, R.raw.four);
            }
            else if (five.isPlaying())
            {
                five.stop();
                five.release();
                five = null;
                five = MediaPlayer.create(MainActivity.this, R.raw.five);
            }
            else if (six.isPlaying())
            {
                six.stop();
                six.release();
                six = null;
                six = MediaPlayer.create(MainActivity.this, R.raw.six);
            }
            else if (seven.isPlaying())
            {
                seven.stop();
                seven.release();
                seven = null;
                seven = MediaPlayer.create(MainActivity.this, R.raw.seven);
            }
            else if (eight.isPlaying())
            {
                eight.stop();
                eight.release();
                eight = null;
                eight = MediaPlayer.create(MainActivity.this, R.raw.eight);
            }
            else if (nine.isPlaying())
            {
                nine.stop();
                nine.release();
                nine = null;
                nine = MediaPlayer.create(MainActivity.this, R.raw.nine);
            }
            else if (ten.isPlaying())
            {
                ten.stop();
                ten.release();
                ten = null;
                ten = MediaPlayer.create(MainActivity.this, R.raw.ten);
            }
            else if (eleven.isPlaying())
            {
                eleven.stop();
                eleven.release();
                eleven = null;
                eleven = MediaPlayer.create(MainActivity.this, R.raw.eleven);
            }
            else if (twelve.isPlaying())
            {
                twelve.stop();
                twelve.release();
                twelve = null;
                twelve = MediaPlayer.create(MainActivity.this, R.raw.twelve);
            }
            else if (thirteen.isPlaying())
            {
                thirteen.stop();
                thirteen.release();
                thirteen = null;
                thirteen = MediaPlayer.create(MainActivity.this, R.raw.thirteen);
            }
            else if (fourteen.isPlaying())
            {
                fourteen.stop();
                fourteen.release();
                fourteen = null;
                fourteen = MediaPlayer.create(MainActivity.this, R.raw.fourteen);
            }
            else if (fifteen.isPlaying())
            {
                fifteen.stop();
                fifteen.release();
                fifteen = null;
                fifteen = MediaPlayer.create(MainActivity.this, R.raw.fifteen);
            }
            else if (sixteen.isPlaying())
            {
                sixteen.stop();
                sixteen.release();
                sixteen = null;
                sixteen = MediaPlayer.create(MainActivity.this, R.raw.sixteen);
            }
            else if (seventeen.isPlaying())
            {
                seventeen.stop();
                seventeen.release();
                seventeen = null;
                seventeen = MediaPlayer.create(MainActivity.this, R.raw.seventeen);
            }
            else if (eighteen.isPlaying())
            {
                eighteen.stop();
                eighteen.release();
                eighteen = null;
                eighteen = MediaPlayer.create(MainActivity.this, R.raw.eighteen);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();

        TelephonyManager imei = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
        }

        /////////////////////////////////////////////////////////////////////
        //https://www.youtube.com/watch?v=w7muIkMYE_A
        // (me) For developer: "ca-app-pub-3940256099942544/6300978111"
        //($$$) For User: "ca-app-pub-7448660774088972/5467742818"

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        adView_developer = (AdView) findViewById(R.id.adView_developer);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView_developer.loadAd(adRequest);

        //////////////////////////////////////////////////////////////////////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Home Page");


        one = MediaPlayer.create(MainActivity.this, R.raw.one);
        two = MediaPlayer.create(MainActivity.this, R.raw.two);
        three = MediaPlayer.create(MainActivity.this, R.raw.three);
        four = MediaPlayer.create(MainActivity.this, R.raw.four);
        five = MediaPlayer.create(MainActivity.this, R.raw.five);
        six = MediaPlayer.create(MainActivity.this, R.raw.six);
        seven = MediaPlayer.create(MainActivity.this, R.raw.seven);
        eight = MediaPlayer.create(MainActivity.this, R.raw.eight);
        nine = MediaPlayer.create(MainActivity.this, R.raw.nine);
        ten = MediaPlayer.create(MainActivity.this, R.raw.ten);
        eleven = MediaPlayer.create(MainActivity.this, R.raw.eleven);
        twelve = MediaPlayer.create(MainActivity.this, R.raw.twelve);
        thirteen = MediaPlayer.create(MainActivity.this, R.raw.thirteen);
        fourteen = MediaPlayer.create(MainActivity.this, R.raw.fourteen);
        fifteen = MediaPlayer.create(MainActivity.this, R.raw.fifteen);
        sixteen = MediaPlayer.create(MainActivity.this, R.raw.sixteen);
        seventeen = MediaPlayer.create(MainActivity.this, R.raw.seventeen);
        eighteen = MediaPlayer.create(MainActivity.this, R.raw.eighteen);

        button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                one.start();


                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "1");
                }
            }
        });

        button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                two.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "2");
                }
            }
        });

        button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                three.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "3");
                }
            }
        });

        button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                four.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "4");
                }
            }
        });

        button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                five.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "5");
                }
            }
        });

        button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                six.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "6");
                }
            }
        });

        button_7 = findViewById(R.id.button_7);
        button_7.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                seven.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "7");
                }
            }
        });

        button_8 = findViewById(R.id.button_8);
        button_8.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                eight.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "8");
                }
            }
        });

        button_9 = findViewById(R.id.button_9);
        button_9.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                nine.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "9");
                }
            }
        });

        button_10 = findViewById(R.id.button_10);
        button_10.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                ten.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "10");
                }
            }
        });

        button_11 = findViewById(R.id.button_11);
        button_11.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                eleven.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "11");
                }
            }
        });
        button_12 = findViewById(R.id.button_12);
        button_12.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                twelve.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "12");
                }
            }
        });

        button_13 = findViewById(R.id.button_13);
        button_13.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                thirteen.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "13");
                }
            }
        });

        button_14 = findViewById(R.id.button_14);
        button_14.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                fourteen.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "14");
                }
            }
        });

        button_15 = findViewById(R.id.button_15);
        button_15.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                fifteen.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "15");
                }
            }
        });

        button_16 = findViewById(R.id.button_16);
        button_16.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                sixteen.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "16");
                }
            }
        });
        button_17 = findViewById(R.id.button_17);
        button_17.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                seventeen.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "17");
                }
            }
        });

        button_18 = findViewById(R.id.button_18);
        button_18.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopPlayingPlease();
                eighteen.start();

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected && (!Analytics.isDeveloper))
                {
                    analytics.sendAnalytics(mContext, "17");
                }
            }
        });

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected && (!Analytics.isDeveloper))
        {
            analytics.sendAnalytics(this, "Open App");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback)
        {
            Intent i = new Intent(getApplicationContext(), Feedback.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        else if (id == R.id.nav_home)
        {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
            if (doubleBackToExitPressedOnce)
            {
                super.onBackPressed();
                finish();
                System.exit(0);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Click back again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable()
            {

                @Override
                public void run()
                {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
