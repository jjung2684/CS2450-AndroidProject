package com.android.cs2450_androidproject;

import static com.android.cs2450_androidproject.R.id.start_activity_bttn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;



/**
 * The purpose of this class is to serve as a menu screen for the
 * application. When start_bttn is clicked, the activity changes
 * from MenuActivity (this) -> LevelsActivity.
 * @author Spencer Barrett
 */
public class MenuActivity extends AppCompatActivity {
    // Declare variables
    Button start_button, scores_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        start_button = (Button) findViewById(start_activity_bttn);
        scores_button = (Button) findViewById(R.id.scores_button);

        start_button.setOnClickListener(View -> startGame());
        scores_button.setOnClickListener(View -> goToScores());

    }

    /**
     * Helper method to change activities on start_button click.
     */
    private void startGame(){
        Intent myIntent = new Intent(MenuActivity.this, LevelsActivity.class);
        startActivity(myIntent);
    }
    private void goToScores(){
        Intent myIntent = new Intent(MenuActivity.this, ScoresActivity.class);
        startActivity(myIntent);
    }


}