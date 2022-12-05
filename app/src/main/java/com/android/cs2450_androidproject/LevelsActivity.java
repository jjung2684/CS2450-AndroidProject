package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LevelsActivity extends AppCompatActivity {
    ImageButton back_bttn;

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

    }
}