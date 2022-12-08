package com.android.cs2450_androidproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cs2450_androidproject.data.CardGamesJSONSerializer;
import com.android.cs2450_androidproject.high_scores.Score;

<<<<<<< Updated upstream
/**
 * Author:
 * Spencer Barrett
 */

public class WinActivity extends AppCompatActivity {
    /**
     * Variable declaration
     */
    int usrScore;
    TextView hs;
    Button hb;
=======
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WinActivity extends AppCompatActivity {
    private static ScoreMaker mScoreMaker;
    WinActivity linkage = this;
    private CardGamesJSONSerializer mSerializer = new CardGamesJSONSerializer(linkage, "scores.json"); // Declared here to avoid rewriting the save file
    Score finalUserScore;
    int userScore;
    int numberOfCards;
    EditText nameField;
    TextView highScorePromptView;
    Button returnHomeButton;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScoreMaker = new ScoreMaker(this);
        setContentView(R.layout.activity_win);

<<<<<<< Updated upstream
        /**
         * Receive data from previous activity (GameActivity)
         * ...receives score -> @variable - usrScore
         */
        Intent intent = getIntent();
        usrScore = intent.getIntExtra("score", usrScore);

        hs = (TextView) findViewById(R.id.hsMssg);
        hs.append(" " + String.valueOf(usrScore));

        hb = (Button) findViewById(R.id.homeBttn);
        hb.setOnClickListener(View -> goHome());

=======
        Intent intent = getIntent();
        userScore = intent.getIntExtra("score", userScore);
        numberOfCards = intent.getIntExtra("numberOfCards", numberOfCards);

        // Find buttons
        nameField = (EditText) findViewById(R.id.editTextTextPersonName);
        highScorePromptView = (TextView) findViewById(R.id.hsMssg);
        returnHomeButton = (Button) findViewById(R.id.homeBttn);

        // Set score at end of message
        highScorePromptView.append(" " + String.valueOf(userScore));

        returnHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome(nameField.getText().toString(), userScore, numberOfCards);
            }
        });
    }

    /**
     * Takes the user back to the home menu
     */
    private void goHome(){
        Intent myIntent = new Intent(WinActivity.this, MenuActivity.class);
        startActivity(myIntent);
    }

    /**
     * Takes the user back to the home menu and saves the score with the player name
     * @param playerName The name of the player (Currently saves up to 10 chars)
     * @param playerScore The score from the game
     * @param numberOfCards The number of cards played with from the game
     */
    private void goHome(String playerName, int playerScore, int numberOfCards) {
        finalUserScore = new Score(validateString(playerName), userScore, numberOfCards);
        mScoreMaker.addScore(finalUserScore); // Only adds it to the array, DOES NOT SAVE IT!
        mScoreMaker.saveScores();
        goHome();
    }

    /**
     * Validate the name and fix it if too long
     * @param nameToValidate
     * @return
     */
    private String validateString(String nameToValidate) {
        String fixedName = "";
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
         * @param score the Score object with the user data
         */
        public void addScore(Score score) {
            mScores.add(score);
        }
>>>>>>> Stashed changes

        /**
         * Clears the array and makes a new empty one
         */
        public void clearScoreArray() {
            mScores = new ArrayList<Score>();
        }

<<<<<<< Updated upstream
    /**
     * Helper method to change activity from WinActivity (this), to MenuActivity (home).
     */
    private void goHome() {
        Intent myIntent = new Intent(WinActivity.this, MenuActivity.class);
        startActivity(myIntent);
=======
        /**
         * Saves the scores currently in the array to the file
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
>>>>>>> Stashed changes
    }
}