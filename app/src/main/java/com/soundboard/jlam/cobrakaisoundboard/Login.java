package com.soundboard.jlam.cobrakaisoundboard;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Login extends AppCompatActivity
{
    boolean doubleBackToExitPressedOnce = false;
    MediaPlayer cobra;
    AdView adView_user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        cobra = MediaPlayer.create(Login.this, R.raw.cobra);

       // MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
         MobileAds.initialize(this, "ca-app-pub-7448660774088972/5467742818");
        adView_user = (AdView) findViewById(R.id.adView_user);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView_user.loadAd(adRequest);


        Button login = (Button) findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                cobra.start();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
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
