package com.android.cs2450_androidproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.cs2450_androidproject.game.WinActivity;
import java.util.ArrayList;

/**
 * Author:
 * Spencer Barrett
 */

public class GameActivity extends AppCompatActivity {

    /**
     * Variable declaration
     */
    private boolean mBound = false;
    private boolean tryVis = false;
    private boolean reloaded;
    private boolean endGame = false;
    private int numCards;
    private int score;
    private int numMatches;
    private float scale;
    private Toast matchTst;
    private Toast noMatchTst;
    BackgroundSoundService mService;
    Button try_again;
    TextView score_box;
    ArrayList<Integer> clickedList;
    ArrayList<CardButton> cbActive;
    ArrayList<CardButton> toRemove;
    ArrayList<CardButton> cbList;
    ArrayList<CardButton> reList;
    Button quit;
    Intent music;
    CardButton cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10;
    View currView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*
          Receive data from previous activity
         */
        Intent intent = getIntent();
        int cards = intent.getIntExtra("cards", numCards);
        numCards = cards;

        toRemove = new ArrayList<>();

        /*
          Uses deprecated method (getLastCustomNonConfigurationInstance())...checks for previous instance
          if there is a previous instance copy old values....otherwise...first instance -> initialize values
         */
        GameActivity gm = (GameActivity) getLastCustomNonConfigurationInstance();
        if (gm != null) {
            cbList = gm.cbList;
            music = gm.music;
            mService = gm.mService;
            bindService(music, connection, Context.BIND_AUTO_CREATE);
            clickedList = gm.clickedList;
            score = gm.score;
            numMatches = gm.numMatches;
            tryVis = gm.tryVis;
            reList = new ArrayList<>();

        } else {
            cbActive = new ArrayList<>();
            clickedList = new ArrayList<>();
            cbList = new ArrayList<>();
            music = new Intent(GameActivity.this, BackgroundSoundService.class);
            startService(music);
            bindService(music, connection, Context.BIND_AUTO_CREATE);
        }


        matchTst = Toast.makeText(this, "Congrats, you've made a match!", Toast.LENGTH_SHORT);
        noMatchTst = Toast.makeText(this, "Not a match, try again!", Toast.LENGTH_SHORT);
        if (savedInstanceState != null) {
            reloaded = savedInstanceState.getBoolean("reloaded");
            //noinspection unchecked
            cbActive = (ArrayList<CardButton>) savedInstanceState.get("active");
        }

        initializeUI(cards);




    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BackgroundSoundService.LocalBinder binder = (BackgroundSoundService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return this;
    }

    private void initializeUI(int cards) {

        /*
          Level begin switch-case..
          Inflates View with layout based on data passed from
          previous activity (LevelsActivity)
          @param cards -- the number of cards to display
         */
        switch (cards) {
            case 4:
                scale = getApplicationContext().getResources().getDisplayMetrics().density;

                LayoutInflater inflater = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout = inflater.inflate(R.layout.level_4_layout, null);
                setContentView(inflatedLayout);

                score_box = findViewById(R.id.score_box2);
                score_box.setText(R.string.init_score);
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
                currView = inflatedLayout;
                setListeners();

                break;


            case 6:

                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_2 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_2 = inflater_2.inflate(R.layout.level_6_layout, null);
                setContentView(inflatedLayout_2);
                score_box = findViewById(R.id.score_box3);
                score_box.setText(R.string.init_score);
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
                @SuppressLint("InflateParams") View inflatedLayout_3 = inflater_3.inflate(R.layout.level_8_layout, null);
                setContentView(inflatedLayout_3);

                score_box = findViewById(R.id.score_lvl8);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again6);
                quit = findViewById(R.id.quit_lvl8);


                cb1 = findViewById(R.id.i_card1);
                cb2 = findViewById(R.id.i_card2);
                cb3 = findViewById(R.id.i_card3);
                cb4 = findViewById(R.id.i_card4);
                cb5 = findViewById(R.id.i_card5);
                cb6 = findViewById(R.id.i_card6);
                cb7 = findViewById(R.id.i_card7);
                cb8 = findViewById(R.id.i_card8);

                if (!reloaded) {
                    cb1.setCardNum(3);
                    cb1.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb1);

                    cb2.setCardNum(9);
                    cb2.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb2);

                    cb3.setCardNum(9);
                    cb3.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb3);

                    cb4.setCardNum(12);
                    cb4.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb4);

                    cb5.setCardNum(11);
                    cb5.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb5);

                    cb6.setCardNum(3);
                    cb6.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb6);

                    cb7.setCardNum(11);
                    cb7.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb7);

                    cb8.setCardNum(12);
                    cb8.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb8);
                    setListeners();

                } else {
                    reinitializeCard(cb1, cbList.get(0));
                    reinitializeCard(cb2, cbList.get(1));
                    reinitializeCard(cb3, cbList.get(2));
                    reinitializeCard(cb4, cbList.get(3));
                    reinitializeCard(cb5, cbList.get(4));
                    reinitializeCard(cb6, cbList.get(5));
                    reinitializeCard(cb7, cbList.get(6));
                    reinitializeCard(cb8, cbList.get(7));
                    cbList.clear();
                    cbList.addAll(reList);
                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }
                }

                try_again.setOnClickListener(View -> noMatch());
                quit.setOnClickListener(View -> quitGame());

                break;

            case 10:

                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_4 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_4 = inflater_4.inflate(R.layout.level_10_layout, null);
                setContentView(inflatedLayout_4);

                score_box = findViewById(R.id.score_lvl10);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again6);
                quit = findViewById(R.id.quit_lvl10);

                cb1 = findViewById(R.id.i_card1);
                cb2 = findViewById(R.id.i_card2);
                cb3 = findViewById(R.id.i_card3);
                cb4 = findViewById(R.id.i_card4);
                cb5 = findViewById(R.id.i_card5);
                cb6 = findViewById(R.id.i_card6);
                cb7 = findViewById(R.id.i_card7);
                cb8 = findViewById(R.id.i_card8);
                cb9 = findViewById(R.id.i_card9);
                cb10 = findViewById(R.id.i_card10);

                if (!reloaded) {
                    cb1.setCardNum(11);
                    cb1.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb1);

                    cb2.setCardNum(3);
                    cb2.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb2);

                    cb3.setCardNum(9);
                    cb3.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb3);

                    cb4.setCardNum(1);
                    cb4.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb4);

                    cb5.setCardNum(11);
                    cb5.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb5);

                    cb6.setCardNum(3);
                    cb6.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb6);

                    cb7.setCardNum(9);
                    cb7.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb7);

                    cb8.setCardNum(12);
                    cb8.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb8);

                    cb9.setCardNum(1);
                    cb9.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb9);

                    cb10.setCardNum(12);
                    cb10.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb10);

                    setListeners();

                } else {
                    reinitializeCard(cb1, cbList.get(0));
                    reinitializeCard(cb2, cbList.get(1));
                    reinitializeCard(cb3, cbList.get(2));
                    reinitializeCard(cb4, cbList.get(3));
                    reinitializeCard(cb5, cbList.get(4));
                    reinitializeCard(cb6, cbList.get(5));
                    reinitializeCard(cb7, cbList.get(6));
                    reinitializeCard(cb8, cbList.get(7));
                    reinitializeCard(cb9, cbList.get(8));
                    reinitializeCard(cb10, cbList.get(9));
                    cbList.clear();
                    cbList.addAll(reList);

                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }

                }

                try_again.setOnClickListener(View -> noMatch());
                quit.setOnClickListener(View -> quitGame());


                break;

            //TODO: Refactor for CardButton class implementation
            case 20:


                score_box = findViewById(R.id.score_box5);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again4);
                quit = findViewById(R.id.quit_bttn4);

//                c1 = findViewById(R.id.card1);
//                c2 = findViewById(R.id.card2);
//                c3 = findViewById(R.id.card3);
//                c4 = findViewById(R.id.card4);
//                c5 = findViewById(R.id.card5);
//                c6 = findViewById(R.id.card6);
//                c7 = findViewById(R.id.card7);
//                c8 = findViewById(R.id.card8);
//                c9 = findViewById(R.id.card9);
//                c10 = findViewById(R.id.card10);
//                c11 = findViewById(R.id.card11);
//                c12 = findViewById(R.id.card12);
//                c13 = findViewById(R.id.card13);
//                c14 = findViewById(R.id.card14);
//                c15 = findViewById(R.id.card15);
//                c16 = findViewById(R.id.card16);
//                c17 = findViewById(R.id.card17);
//                c18 = findViewById(R.id.card18);
//                c19 = findViewById(R.id.card19);
//                c20 = findViewById(R.id.card20);

                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());


                break;
            default:
                break;
        }

    }

    /**
     * Method to determine a card match...while checking disable non-active buttons
     * @param cardButton - card button to check
     */
    private void checkMatch(CardButton cardButton) {
        int cardNum = cardButton.getCardNum();

        if (clickedList.contains(cardNum) && cbActive.size() > 1) {
            score += 2;
            score_box.setText(getString(R.string.score_box, score));
            for (int i = 0; i < cbActive.size(); i++) { // Disable matched buttons
                CardButton tmp = cbActive.get(i);
                tmp.setEnabled(false);
                tmp.setMatched(true);

            }
            clickedList.clear();
            cbActive.clear();
            numMatches++;
            matchTst.show();
        } else if (cbActive.size() > 1) {      // No Match

            toRemove.addAll(cbActive);

            if (score != 0) {
                score -= 1;
                score_box.setText(getString(R.string.score_box, score));

            }
            cbActive.clear();
            clickedList.clear();

            try_again.setVisibility(View.VISIBLE);
            tryVis = true;
            for (int i = 0; i < cbList.size(); i++) {
                CardButton tmp = cbList.get(i);
                if (!tmp.getIsMatched())
                    tmp.setEnabled(false);

            }
            noMatchTst.show();

        } else {                            // First of two cards picked
            clickedList.add(cardNum);
        }
        if (numMatches == (numCards / 2)) { // Check for win
            winCondition();
        }
    }

    /**
     * Method to handle when all cards are matched...GOTO -> WinActivity
     */
    private void winCondition() {

        Intent myIntent = new Intent(GameActivity.this, WinActivity.class);
        myIntent.putExtra("score", score);
        myIntent.putExtra("numberOfCards", numCards);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                endGame = true;
                startActivity(myIntent);
            }
        }, 2000);

    }

    /**
     * Helper function to handle if there is no match..resets cards clickable status
     */
    private void noMatch() {
        if (!toRemove.isEmpty()) {
            for (int i = 0; i < toRemove.size(); i++) {
                flipCard(toRemove.get(i));
            }
            for (int i = 0; i < cbList.size(); i++) {
                CardButton tmp = cbList.get(i);
                if (!tmp.getIsMatched()) {
                    tmp.setEnabled(true);
                }
            }
            toRemove.clear();
            try_again.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Method to handle when quit button is pressed..GOTO -> MenuActivity
     */
    private void quitGame() {
        endGame = true;
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

                startActivity(myIntent);
            }
        }, 8000);


    }


    /**
     * On a configuration change...ie.rotation copy over values to "new" cards
     * @param cb - card to be reinitialized
     * @param tmp - card to copy from
     */
    public void reinitializeCard(CardButton cb, CardButton tmp) {
        cb.setFlipped(tmp.isFlipped());
        cb.setCardNum(tmp.getCardNum());
        cb.setMatched(tmp.getIsMatched());
        cb.setCardDrawable(tmp.getCardDrawable());
        if(!tmp.isEnabled()){
            cb.setEnabled(false);
        }
        if (!cb.isFlipped()) {

        } else {
            cb.setImageResource(tmp.getCardDrawable());

            if (!cb.getIsMatched())
                toRemove.add(cb);

        }
        if (tmp.getIsMatched()) {
            cb.setClickable(false);
        } else {
            cb.setCameraDistance(8000 * scale);
            cb.setOnClickListener(View -> checkMatchHelper(cb));
        }
        reList.add(cb);


    }

    /**
     * Helper method to set onClickListeners for each card **reduces code bloat
     */
    private void setListeners() {
        for (int i = 0; i < cbList.size(); i++) {
            int finalI = i;
            cbList.get(i).setCameraDistance((8000 * scale));
            cbList.get(i).setOnClickListener(View -> checkMatchHelper(cbList.get(finalI)));
        }
    }

    /**
     * Method used to animate card flips..checks card for @boolean isFlipped
     * @param cb - card to flip
     */
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
                reverseFlip(cb, v1);
            });
            cb.setFlipped(false);
            cb.setClickable(true);
        }
    }
    /**
     * helper function to flip a card
     * @param ib - card to be flipped
     * @param v1 - view (i.e. card) TODO: likely redundant
     */
    private void forwardFlip(CardButton ib, View v1, int d) {
        ib.setImageResource(d);
        v1.setRotationY(-90);
        v1.animate().withLayer().rotationY(0).setDuration(150).start();
    }

    /**
     * helper function to reverse a flipped card
     * @param ib - card to be flipped
     * @param v1 - view (i.e. card) TODO: likely redundant
     */
    private void reverseFlip(CardButton ib, View v1) {
        ib.setImageResource(R.drawable.card_back);
        v1.setRotationY(90);
        v1.animate().withLayer().rotationY(0).setDuration(150).start();
    }

    /**
     * helper function for checking if a card has a active match
     * @param cardButton - card to check
     */
    private void checkMatchHelper(CardButton cardButton) {
        flipCard(cardButton);
        cbActive.add(cardButton);
        checkMatch(cardButton);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * Handle menu items
     * @param item - item id that is called
     * @return selectedOption with item id
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.playButton:
                playMusic();
                return true;
            case R.id.pauseButton:
                disableMusic();
                return true;
            case android.R.id.home:
                if (NavUtils.getParentActivityName(this) != null)
                    NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void playMusic() {

        Intent startServiceIntent = new Intent(GameActivity.this, BackgroundSoundService.class);
        bindService(music, connection, Context.BIND_AUTO_CREATE);
        startService(startServiceIntent);
        Toast.makeText(this, "Playing Background Music", Toast.LENGTH_SHORT).show();

    }

    /**
     * Used to stop music (Menu Item)
     */
    private void disableMusic() {
        if (mService != null) {
            unbindService(connection);
            mService = null;
        }

        stopService(new Intent(getApplicationContext(), BackgroundSoundService.class));
        Toast.makeText(this, "Stopping Background Music", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        if (mService != null) {
            unbindService(connection);
            mService = null;
        }

        if (isFinishing() || endGame) {
            stopService(new Intent(getApplicationContext(), BackgroundSoundService.class));
        }

        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("cards", numCards);
        outState.putSerializable("buttons", cbList);
        outState.putSerializable("active", cbActive);
        outState.putBoolean("reloaded", true);
        outState.putInt("y", 1);
        super.onSaveInstanceState(outState);
    }

}