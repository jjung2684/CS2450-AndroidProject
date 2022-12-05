package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LevelsActivity extends AppCompatActivity {



    ImageButton back_bttn;
    ImageButton level_4;
    ImageButton level_6;
    ImageButton level_8;
    ImageButton level_10;
    ImageButton level_12;
    ImageButton level_14;
    ImageButton level_16;
    ImageButton level_18;
    ImageButton level_20;
    ImageButton level_22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        back_bttn = (ImageButton) findViewById(R.id.back_bttn);
        back_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.back_bttn) {
                    Intent myIntent = new Intent(LevelsActivity.this, MenuActivity.class);
                    startActivity(myIntent);
                    Log.d("create", "clicked");
                }            }
        });

        level_4 = (ImageButton) findViewById(R.id.level_4);
        level_4.setOnClickListener(View -> startGameLevel(4));

        level_6 = (ImageButton) findViewById(R.id.level_6);
        level_6.setOnClickListener(View -> startGameLevel(6));

        level_8 = (ImageButton) findViewById(R.id.level_8);
        level_8.setOnClickListener(View -> startGameLevel(8));

        level_10 = (ImageButton) findViewById(R.id.level_10);
        level_10.setOnClickListener(View -> startGameLevel(10));

        level_12 = (ImageButton) findViewById(R.id.level_12);
        level_12.setOnClickListener(View -> startGameLevel(12));

        level_14 = (ImageButton) findViewById(R.id.level_14);
        level_14.setOnClickListener(View -> startGameLevel(14));

        level_16 = (ImageButton) findViewById(R.id.level_16);
        level_16.setOnClickListener(View -> startGameLevel(16));

        level_18 = (ImageButton) findViewById(R.id.level_18);
        level_18.setOnClickListener(View -> startGameLevel(18));

        level_20 = (ImageButton) findViewById(R.id.level_20);
        level_20.setOnClickListener(View -> startGameLevel(20));




    }

    private void startGameLevel(int level){
        Intent myIntent = new Intent(LevelsActivity.this, GameActivity.class);
        myIntent.putExtra("cards", level);
        startActivity(myIntent);
    }
}