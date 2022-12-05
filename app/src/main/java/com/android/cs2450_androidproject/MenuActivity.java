package com.android.cs2450_androidproject;

import static com.android.cs2450_androidproject.R.id.start_activity_bttn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
/**
 * Author:
 * Spencer Barrett
 */



/**
 * The purpose of this class is to serve as a menu screen for the
 * application. When start_bttn is clicked, the activity changes
 * from MenuActivity (this) -> LevelsActivity.
 */
public class MenuActivity extends AppCompatActivity {
    // Declare variables
    Button start_bttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        start_bttn = (Button) findViewById(start_activity_bttn);
        start_bttn.setOnClickListener(View -> startGame());

    }

    /**
     * Helper method to change activities on start_bttn click.
     */
    private void startGame(){
        Intent myIntent = new Intent(MenuActivity.this, LevelsActivity.class);
        startActivity(myIntent);
    }


}