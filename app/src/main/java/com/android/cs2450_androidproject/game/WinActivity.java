package com.android.cs2450_androidproject.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cs2450_androidproject.MenuActivity;
import com.android.cs2450_androidproject.R;

import java.util.ArrayList;

/**
 * Author:
 * Spencer Barrett
 */

public class WinActivity extends AppCompatActivity {
     // Variable declaration
    protected final ScoreJSONSerializer mSerializer= new ScoreJSONSerializer(this, "scores.json");
    private static ScoreMaker mScoreMaker;
    Score score;
    int userScore;
    int numberOfCards;
    EditText playerNameField;
    TextView highScoreView;
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        mScoreMaker = new ScoreMaker(this);

        /**
         * Receive data from previous activity (GameActivity)
         * ...receives score -> @variable - usrScore
         */
        Intent intent = getIntent();
        userScore = intent.getIntExtra("score", userScore);
        numberOfCards = intent.getIntExtra("numberOfCards", numberOfCards);

        playerNameField = findViewById(R.id.editTextTextPersonName);

        highScoreView = findViewById(R.id.hsMssg);
        highScoreView.append(" " + userScore);

        homeButton = (Button) findViewById(R.id.homeBttn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goHome();
                saveScoreAndGoHome(playerNameField.getText().toString(), userScore, numberOfCards);
            }
        });
    }

    /**
     * Helper method to change activity from WinActivity (this), to MenuActivity (home).
     */
    private void goHome() {
        Intent myIntent = new Intent(WinActivity.this, MenuActivity.class);
        startActivity(myIntent);
    }

    /**
     * Takes the user back to the home menu and saves the score with the player name
     * @param playerName The name of the player (Currently saves up to 10 chars)
     * @param playerScore The score from the game
     * @param numberOfCards The number of cards played with from the game
     */
    private void saveScoreAndGoHome(String playerName, int playerScore, int numberOfCards) {
        this.score = new Score(validateString(playerName), playerScore, numberOfCards);
        mScoreMaker.addScore(this.score);
        mScoreMaker.saveScores();
        this.goHome();
    }

    /**
     * Validate the name and fix it if too long
     * @param nameToValidate
     * @return
     */
    private String validateString(String nameToValidate) {
        String fixedName = null;
        if (nameToValidate.length() > 10) {
            for (int i = 0; i < 10; i++) {
                fixedName = fixedName + nameToValidate.charAt(i);
            }
            Toast.makeText(this, "Name was saved as " + fixedName, Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "Card game with " + level + " not available", Toast.LENGTH_SHORT).show();
        } else {
            fixedName = nameToValidate;
        }
        return fixedName;
    }

    /**
     * Inner class that handles saving scores and loading them
     */
    class ScoreMaker {
        private static final String TAG = "GAME-SAVE";
        private static final String FILENAME = "scores.json";
        private ArrayList<Score> mScores;
        private Context mAppContext;

        /**
         * Makes a new instance of the ScoreMaker and loads existing scores
         *
         * @param ctx
         */
        private ScoreMaker(Context ctx) {
            mAppContext = ctx;
            try {
                mScores = mSerializer.loadScores();
            } catch (Exception e) {
                mScores = new ArrayList<Score>();
                Log.e(TAG, "Error loading scores from file");
            }
        }

        /**
         * Adds a score to the array
         *
         * @param score the Score object with the user data
         */
        public void addScore(Score score) {
            mScores.add(score);
        }

        /**
         * Clears the array and makes a new empty one
         */
        public void clearScoreArray() {
            mScores = new ArrayList<Score>();
        }

        /**
         * Saves the scores currently in the array to the file
         *
         * @return true of successful, false otherwise
         */
        public boolean saveScores() {
            Log.d(TAG, "Saving in process. Do not turn off the power...");
            try {
                mSerializer.saveScores(mScores);
                Log.d(TAG, "Scores saved to file.");
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Error saving scores: ", e);
                return false;
            }
        }
    }
}