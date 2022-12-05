package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
int numCards;
    MediaPlayer mediaPlayer;
    TextView score_box;
    LinearLayout main_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        int cards= intent.getIntExtra("cards", numCards);
        Log.d("num", String.valueOf(cards));


        mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.jazz_music);
        Intent music = new Intent(GameActivity.this, BackgroundSoundService.class);
        startService(music);
        main_Layout = (LinearLayout) findViewById(R.id.mainLayout);

        switch(cards){
            case 4:
                Log.d("reachd", "hello");
                LayoutInflater inflater = LayoutInflater.from(GameActivity.this);
                View inflatedLayout = inflater.inflate(R.layout.level_4_layout, null);
                setContentView(inflatedLayout);

        }



    }
}