package com.example.jlam.cobrakaisoundboard;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer hi, bye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hi = MediaPlayer.create(MainActivity.this,R.raw.hi);
        bye = MediaPlayer.create(MainActivity.this,R.raw.bye);
    }

    public void helloClicked(View view)
    {
        hi.start();
    }
    public void byeClicked(View view)
    {
        bye.start();
    }
}
