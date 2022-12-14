package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.cs2450_androidproject.game.Score;
import com.android.cs2450_androidproject.game.ScoreJSONSerializer;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Purpose of this activity is to display highscores from games played
 * @author Spencer Barrett
 */
public class ScoresActivity extends AppCompatActivity {
    protected final ScoreJSONSerializer mSerializer= new ScoreJSONSerializer(this, "scores.json");
    TextView lvl4, lvl6, lvl8, lvl10, lvl12, lvl14, lvl16, lvl18, lvl20;
    ArrayList<Score> scores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        scores = new ArrayList<>();

        lvl4 = findViewById(R.id.textView4);
        lvl6 = findViewById(R.id.textView6);
        lvl8 = findViewById(R.id.textView8);
        lvl10 = findViewById(R.id.textView10);
        lvl12 = findViewById(R.id.textView12);
        lvl14 = findViewById(R.id.textView14);
        lvl16 = findViewById(R.id.textView16);
        lvl18 = findViewById(R.id.textView18);
        lvl20 = findViewById(R.id.textView20);

        try {
            setViewText(lvl4, 4);
            setViewText(lvl6, 6);
            setViewText(lvl8, 8);
            setViewText(lvl10, 10);
            setViewText(lvl12, 12);
            setViewText(lvl14, 14);
            setViewText(lvl16, 16);
            setViewText(lvl18, 18);
            setViewText(lvl20, 20);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setViewText(TextView tv, Integer num) throws JSONException, IOException {
        if(mSerializer != null){
           scores = mSerializer.loadScores();
        }
        Integer tmp = 0;
        Score highScore = null;
        for(int i = 0; i < scores.size(); i++){
            Score score = scores.get(i);
            if(score.getCardNumber().equals(num.toString())){
                if(Integer.parseInt(score.getScore()) > tmp){

                    tmp = Integer.parseInt(score.getScore());
                    highScore = score;
                }

            }
            if(highScore != null) {
                tv.setText("Level: " + highScore.getCardNumber() + " " + highScore.getPlayerName() + " " + score.getScore());
            }else{
                tv.setText("No score data...");
            }
        }

    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                if(NavUtils.getParentActivityName(this) != null)
                    NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}