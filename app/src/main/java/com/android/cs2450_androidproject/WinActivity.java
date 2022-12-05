package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {
int usrScore;
TextView hs;
Button hb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Intent intent = getIntent();
        usrScore = intent.getIntExtra("score", usrScore);
        hs = (TextView) findViewById(R.id.hsMssg);
        hs.append(" " + String.valueOf(usrScore));

        hb = (Button) findViewById(R.id.homeBttn);
        hb.setOnClickListener(View -> goHome());


    }

    private void goHome(){
        Intent myIntent = new Intent(WinActivity.this, MenuActivity.class);
        startActivity(myIntent);
    }
}