package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cs2450_androidproject.game.WinActivity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Author:
 * Spencer Barrett
 */

public class GameActivity extends AppCompatActivity {

    /**
     * Variable declaration
     */
    private int numCards;
    private int score;
    private int numMatches;
    private Toast matchTst;
    private Toast noMatchTst;
    LinearLayout ll;
    Button try_again;
    MediaPlayer mediaPlayer;
    TextView score_box;
    String[] gameWords = {"Panda", "Lion", "Tiger", "Bear", "Eagle", "Snake", "Cheetah",
            "Jaguar", "Dolphin", "Wolf"};
    ArrayList<String> openList;
    ArrayList<Integer> clickedList;
    ArrayList<CardButton> cbActive;
    ArrayList<CardButton> toRemove;
    ArrayList<CardButton> cbList;
    ArrayList<Button> bttn_List;
    ArrayList<CardButton> rmv_List;
    Map<ImageButton, Integer> mp;
    Button quit;
    Intent music;
    CardButton cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8;
    Button c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,
            c11, c12, c13, c14, c15, c16, c17, c18, c19, c20;
float scale;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /**
         * Receive data from previous activity
         */
        Intent intent = getIntent();
        int cards = intent.getIntExtra("cards", numCards);
        numCards = cards;

        /**
         * Create and start media player for background music
         */
        mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.jazz_music);
        music = new Intent(GameActivity.this, BackgroundSoundService.class);
        startService(music);

        // TODO: Remove
        openList = new ArrayList<>();
        bttn_List = new ArrayList<>();
        rmv_List = new ArrayList<>();


        cbActive = new ArrayList<>();
        clickedList = new ArrayList<>();
        toRemove = new ArrayList<>();
        cbList = new ArrayList<>();

        matchTst = Toast.makeText(this, "Congrats, you've made a match!", Toast.LENGTH_SHORT);
        noMatchTst = Toast.makeText(this, "Not a match, try again!", Toast.LENGTH_SHORT);



        /**
         * Level begin switch-case..
         * Inflates View with layout based on data passed from
         * previous activity (LevelsActivity)
         * @param cards -- the number of cards to display
         */
        switch (cards) {
            case 4:
                scale = getApplicationContext().getResources().getDisplayMetrics().density;

                LayoutInflater inflater = LayoutInflater.from(GameActivity.this);
                View inflatedLayout = inflater.inflate(R.layout.level_4_layout, null);
                setContentView(inflatedLayout);
                ll = (LinearLayout) findViewById(R.id.l4portrait);

                score_box = findViewById(R.id.score_box2);
                score_box.setText("Score: 0");
                try_again = findViewById(R.id.try_again);
                quit = findViewById(R.id.quit_bttn);

                cb1 = findViewById(R.id.i_card1);
                cb1.setCardNum(8);
                cb1.setCardDrawable(R.drawable.card_8);
                cbList.add(cb1);

                cb2 = findViewById(R.id.i_card2);
                cb2.setCardNum(3);
                cb2.setCardDrawable(R.drawable.card_3);
                cbList.add(cb2);

                cb3 = findViewById(R.id.i_card3);
                cb3.setCardNum(3);
                cb3.setCardDrawable(R.drawable.card_3);
                cbList.add(cb3);

                cb4 = findViewById(R.id.i_card4);
                cb4.setCardNum(8);
                cb4.setCardDrawable(R.drawable.card_8);
                cbList.add(cb4);

                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());

                setListeners();

                break;


            case 6:

                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_2 = LayoutInflater.from(GameActivity.this);
                View inflatedLayout_2 = inflater_2.inflate(R.layout.level_6_layout, null);
                setContentView(inflatedLayout_2);
                score_box = findViewById(R.id.score_box3);
                score_box.setText("Score: 0");
                try_again = findViewById(R.id.try_again2);
                quit = findViewById(R.id.quit_bttn2);

                cb1 = findViewById(R.id.i_card1);
                cb1.setCardNum(3);
                cb1.setCardDrawable(R.drawable.card_3);
                cbList.add(cb1);

                cb2 = findViewById(R.id.i_card2);
                cb2.setCardNum(9);
                cb2.setCardDrawable(R.drawable.card_9);
                cbList.add(cb2);

                cb3 = findViewById(R.id.i_card3);
                cb3.setCardNum(3);
                cb3.setCardDrawable(R.drawable.card_3);
                cbList.add(cb3);

                cb4 = findViewById(R.id.i_card4);
                cb4.setCardNum(8);
                cb4.setCardDrawable(R.drawable.card_8);
                cbList.add(cb4);

                cb5 = findViewById(R.id.i_card5);
                cb5.setCardNum(8);
                cb5.setCardDrawable(R.drawable.card_8);
                cbList.add(cb5);

                cb6 = findViewById(R.id.i_card6);
                cb6.setCardNum(9);
                cb6.setCardDrawable(R.drawable.card_9);
                cbList.add(cb6);

                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());

               setListeners();

                break;

            case 8:
                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_3 = LayoutInflater.from(GameActivity.this);
                View inflatedLayout_3 = inflater_3.inflate(R.layout.level_8_layout, null);
                setContentView(inflatedLayout_3);

                score_box = findViewById(R.id.score_lvl8);
                score_box.setText("Score: 0");
                try_again = findViewById(R.id.try_again6);
                quit = findViewById(R.id.quit_lvl8);

                cb1 = findViewById(R.id.i_card1);
                cb1.setCardNum(3);
                cb1.setCardDrawable(R.drawable.card_3);
                cbList.add(cb1);

                cb2 = findViewById(R.id.i_card2);
                cb2.setCardNum(9);
                cb2.setCardDrawable(R.drawable.card_9);
                cbList.add(cb2);

                cb3 = findViewById(R.id.i_card3);
                cb3.setCardNum(9);
                cb3.setCardDrawable(R.drawable.card_9);
                cbList.add(cb3);

                cb4 = findViewById(R.id.i_card4);
                cb4.setCardNum(12);
                cb4.setCardDrawable(R.drawable.card_king);
                cbList.add(cb4);

                cb5 = findViewById(R.id.i_card5);
                cb5.setCardNum(11);
                cb5.setCardDrawable(R.drawable.card_queen);
                cbList.add(cb5);

                cb6 = findViewById(R.id.i_card6);
                cb6.setCardNum(3);
                cb6.setCardDrawable(R.drawable.card_3);
                cbList.add(cb6);

                cb7 = findViewById(R.id.i_card7);
                cb7.setCardNum(11);
                cb7.setCardDrawable(R.drawable.card_queen);
                cbList.add(cb7);

                cb8 = findViewById(R.id.i_card8);
                cb8.setCardNum(12);
                cb8.setCardDrawable(R.drawable.card_king);
                cbList.add(cb8);

                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());

                setListeners();
                break;


            case 20:

                LayoutInflater inflater_4 = LayoutInflater.from(GameActivity.this);
                View inflatedLayout_4 = inflater_4.inflate(R.layout.level_20_layout, null);
                setContentView(inflatedLayout_4);

                score_box = findViewById(R.id.score_box5);
                score_box.setText("Score: 0");
                try_again = findViewById(R.id.try_again4);
                quit = findViewById(R.id.quit_bttn4);

                c1 = findViewById(R.id.card1);
                c2 = findViewById(R.id.card2);
                c3 = findViewById(R.id.card3);
                c4 = findViewById(R.id.card4);
                c5 = findViewById(R.id.card5);
                c6 = findViewById(R.id.card6);
                c7 = findViewById(R.id.card7);
                c8 = findViewById(R.id.card8);
                c9 = findViewById(R.id.card9);
                c10 = findViewById(R.id.card10);
                c11 = findViewById(R.id.card11);
                c12 = findViewById(R.id.card12);
                c13 = findViewById(R.id.card13);
                c14 = findViewById(R.id.card14);
                c15 = findViewById(R.id.card15);
                c16 = findViewById(R.id.card16);
                c17 = findViewById(R.id.card17);
                c18 = findViewById(R.id.card18);
                c19 = findViewById(R.id.card19);
                c20 = findViewById(R.id.card20);

                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());

                c1.setOnClickListener(View -> checkMatchHelper(c1, gameWords[0]));
                c2.setOnClickListener(View -> checkMatchHelper(c2, gameWords[9]));
                c3.setOnClickListener(View -> checkMatchHelper(c3, gameWords[8]));
                c4.setOnClickListener(View -> checkMatchHelper(c4, gameWords[6]));
                c5.setOnClickListener(View -> checkMatchHelper(c5, gameWords[3]));
                c6.setOnClickListener(View -> checkMatchHelper(c6, gameWords[6]));
                c7.setOnClickListener(View -> checkMatchHelper(c7, gameWords[0]));
                c8.setOnClickListener(View -> checkMatchHelper(c8, gameWords[8]));
                c9.setOnClickListener(View -> checkMatchHelper(c9, gameWords[2]));
                c10.setOnClickListener(View -> checkMatchHelper(c10, gameWords[1]));
                c11.setOnClickListener(View -> checkMatchHelper(c11, gameWords[5]));
                c12.setOnClickListener(View -> checkMatchHelper(c12, gameWords[7]));
                c13.setOnClickListener(View -> checkMatchHelper(c13, gameWords[9]));
                c14.setOnClickListener(View -> checkMatchHelper(c14, gameWords[1]));
                c15.setOnClickListener(View -> checkMatchHelper(c15, gameWords[4]));
                c16.setOnClickListener(View -> checkMatchHelper(c16, gameWords[2]));
                c17.setOnClickListener(View -> checkMatchHelper(c17, gameWords[7]));
                c18.setOnClickListener(View -> checkMatchHelper(c18, gameWords[5]));
                c19.setOnClickListener(View -> checkMatchHelper(c19, gameWords[4]));
                c20.setOnClickListener(View -> checkMatchHelper(c20, gameWords[3]));

                break;
            default:
                break;
        }
    }

    private void checkMatch(CardButton cardButton) {
        int cardNum = cardButton.getCardNum();

        if (clickedList.contains(cardNum) && cbActive.size() > 1) {
            score += 2;
            score_box.setText("Score: " + score);
            for (int i = 0; i < cbActive.size(); i++) { // Disable matched buttons
                CardButton tmp = cbActive.get(i);
               tmp.setClickable(false);
               tmp.setMatched(true);

            }
            clickedList.clear();
            cbActive.clear();
            numMatches++;
            matchTst.show();
        } else if (cbActive.size() > 1) {      // No Match

            for (int i = 0; i < cbActive.size(); i++) {
                toRemove.add(cbActive.get(i));
            }

            if (score != 0) {
                score -= 1;
                score_box.setText("Score: " + score);

            }
            cbActive.clear();
            clickedList.clear();

            try_again.setVisibility(View.VISIBLE);
            for(int i =0; i < cbList.size(); i++){
                CardButton tmp = cbList.get(i);
                if(!tmp.getIsMatched())
                    tmp.setClickable(false);

            }
            noMatchTst.show();

        } else {                            // First of two cards picked
            clickedList.add(cardNum);
        }
        if (numMatches == (numCards / 2)) { // Check for win
            winCondition();
        }
    }

    private void checkMatch(Button button) {
        String text = (String) button.getText();

        /**
         * open_List holds the two words to be checked
         * bttn_List holds the current buttons to be checked
         *
         * checks if first clicked card has the second card's word
         * AND ensures more than one button is clicked
         *
         */
        if (openList.contains(text) && bttn_List.size() > 1) { //Match Found
            score += 2;
            score_box.setText("Score: " + score);
            for (int i = 0; i < bttn_List.size(); i++) { // Disable matched buttons
                bttn_List.get(i).setClickable(false);
            }

            // Empty Lists & increment number of matches tracker
            bttn_List.clear();
            openList.clear();
            numMatches++;
        } else if (bttn_List.size() > 1) {      // No Match

            for (int i = 0; i < bttn_List.size(); i++) {
//                rmv_List.add(bttn_List.get(i));
            }

            if (score != 0) {
                score -= 1;
                score_box.setText("Score: " + score);

            }
            bttn_List.clear();
            openList.clear();
            try_again.setVisibility(View.VISIBLE);


        } else {                            // First of two cards picked
            openList.add(text);
        }
        if (numMatches == (numCards / 2)) { // Check for win
            winCondition();
        }

    }

    private void winCondition() {

        Intent myIntent = new Intent(GameActivity.this, WinActivity.class);
        myIntent.putExtra("score", score);
        myIntent.putExtra("numberOfCards", numCards);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopService(music);
                startActivity(myIntent);
            }
        }, 2000);

    }

    private void noMatch() {
        if (!toRemove.isEmpty()) {
            for (int i = 0; i < toRemove.size(); i++) {
                flipCard(toRemove.get(i));
            }
            for (int i =0; i < cbList.size(); i++){
                CardButton tmp = cbList.get(i);
                if(!tmp.getIsMatched()){
                    tmp.setClickable(true);
                }
            }
            toRemove.clear();
            try_again.setVisibility(View.INVISIBLE);
        }
    }

    private void quitGame() {

        for (int i = 0; i < cbList.size(); i++) {
            if (!cbList.get(i).isFlipped()) {
                flipCard(cbList.get(i));
            }
        }

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(GameActivity.this, MenuActivity.class);
                stopService(music);
                startActivity(myIntent);
            }
        }, 8000);

    }

    private void setListeners(){
        for(int i = 0; i < cbList.size(); i++){
            int finalI = i;
            cbList.get(i).setCameraDistance((8000 * scale));
            cbList.get(i).setOnClickListener(View -> checkMatchHelper(cbList.get(finalI)));
        }
    }

    private void flipCard(CardButton cb) {

        if (!cb.isFlipped()) {

            final View v1 = cb;
            v1.animate().withLayer().rotationY(90).setDuration(150).withEndAction(() -> {
                forwardFlip(cb, v1, cb.getCardDrawable());
            });
            cb.setFlipped(true);
            cb.setClickable(false);

        } else {
            final View v1 = cb;
            v1.animate().withLayer().rotationY(-90).setDuration(150).withEndAction(() -> {
                reverseFlip(cb, v1, cb.getCardDrawable());
            });
            cb.setFlipped(false);
            cb.setClickable(true);
        }
    }

    private void forwardFlip(CardButton ib, View v1, int d) {
        ib.setImageResource(d);
        v1.setRotationY(-90);
        v1.animate().withLayer().rotationY(0).setDuration(150).start();
    }

    private void reverseFlip(CardButton ib, View v1, int d) {
        ib.setImageResource(R.drawable.card_back);
        v1.setRotationY(90);
        v1.animate().withLayer().rotationY(0).setDuration(150).start();
    }

    private void checkMatchHelper(CardButton cardButton) {
        flipCard(cardButton);
        cbActive.add(cardButton);
        checkMatch(cardButton);

    }

    private void checkMatchHelper(Button button, String word) {
        button.setText(word);
        bttn_List.add(button);
        checkMatch(button);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.playButton) {
            playMusic();
            return true;
        }

        if (id == R.id.pauseButton) {
            disableMusic();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void playMusic() {

        Intent startServiceIntent = new Intent(GameActivity.this, BackgroundSoundService.class);
        startService(startServiceIntent);
        Toast.makeText(this, "Playing Background Music", Toast.LENGTH_SHORT).show();

    }

    private void disableMusic() {

        stopService(new Intent(getApplicationContext(), BackgroundSoundService.class));
        Toast.makeText(this, "Stopping Background Music", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopService(new Intent(getApplicationContext(), BackgroundSoundService.class));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position", mediaPlayer.getCurrentPosition());
        mediaPlayer.pause();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int pos = savedInstanceState.getInt("position");
        mediaPlayer.seekTo(pos);
        super.onRestoreInstanceState(savedInstanceState);
    }


}