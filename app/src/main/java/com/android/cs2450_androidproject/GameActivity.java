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
    Button try_again, reset_button;
    TextView score_box;
    ArrayList<Integer> clickedList;
    ArrayList<CardButton> cbActive;
    ArrayList<CardButton> toRemove;
    ArrayList<CardButton> cbList;
    ArrayList<CardButton> reList;
    Button quit;
    Intent music;
    CardButton cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12, cb13, cb14, cb15, cb16, cb17, cb18, cb19, cb20;

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

                score_box = findViewById(R.id.score_box4);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again4);
                quit = findViewById(R.id.quit_bttn4);
                reset_button = findViewById(R.id.reset_lvl4);

                cb1 = findViewById(R.id.i_card1);
                cb2 = findViewById(R.id.i_card2);
                cb3 = findViewById(R.id.i_card3);
                cb4 = findViewById(R.id.i_card4);

                if (!reloaded) {
                    cb1.setCardNum(8);
                    cb1.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb1);
                    cb2.setCardNum(3);
                    cb2.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb2);
                    cb3.setCardNum(3);
                    cb3.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb3);
                    cb4.setCardNum(8);
                    cb4.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb4);
                    setListeners();
                } else {
                    reinitializeCard(cb1, cbList.get(0));
                    reinitializeCard(cb2, cbList.get(1));
                    reinitializeCard(cb3, cbList.get(2));
                    reinitializeCard(cb4, cbList.get(3));
                    cbList.clear();
                    cbList.addAll(reList);
                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }

                }

                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());
                reset_button.setOnClickListener(View -> resetGame());

                break;


            case 6:

                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_2 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_2 = inflater_2.inflate(R.layout.level_6_layout, null);
                setContentView(inflatedLayout_2);
                score_box = findViewById(R.id.score_boxlvl6);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_againlvl6);
                quit = findViewById(R.id.quit_lvl6);
                reset_button = findViewById(R.id.reset_lvl6);

                cb1 = findViewById(R.id.i_card1);
                cb2 = findViewById(R.id.i_card2);
                cb3 = findViewById(R.id.i_card3);
                cb4 = findViewById(R.id.i_card4);
                cb5 = findViewById(R.id.i_card5);
                cb6 = findViewById(R.id.i_card6);

                if (!reloaded) {
                    cb1.setCardNum(3);
                    cb1.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb1);

                    cb2.setCardNum(9);
                    cb2.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb2);

                    cb3.setCardNum(3);
                    cb3.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb3);

                    cb4.setCardNum(8);
                    cb4.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb4);

                    cb5.setCardNum(8);
                    cb5.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb5);

                    cb6.setCardNum(9);
                    cb6.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb6);
                } else {
                    reinitializeCard(cb1, cbList.get(0));
                    reinitializeCard(cb2, cbList.get(1));
                    reinitializeCard(cb3, cbList.get(2));
                    reinitializeCard(cb4, cbList.get(3));
                    reinitializeCard(cb5, cbList.get(4));
                    reinitializeCard(cb6, cbList.get(5));

                    cbList.clear();
                    cbList.addAll(reList);
                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }
                }

                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());
                reset_button.setOnClickListener(View -> resetGame());


                setListeners();

                break;

            case 8:
                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_3 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_3 = inflater_3.inflate(R.layout.level_8_layout, null);
                setContentView(inflatedLayout_3);

                score_box = findViewById(R.id.score_lvl8);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again8);
                quit = findViewById(R.id.quit_lvl8);
                reset_button = findViewById(R.id.reset_lvl8);


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

                    cb4.setCardNum(13);
                    cb4.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb4);

                    cb5.setCardNum(12);
                    cb5.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb5);

                    cb6.setCardNum(3);
                    cb6.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb6);

                    cb7.setCardNum(12);
                    cb7.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb7);

                    cb8.setCardNum(13);
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
                reset_button.setOnClickListener(View -> resetGame());

                break;

            case 10:

                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_4 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_4 = inflater_4.inflate(R.layout.level_10_layout, null);
                setContentView(inflatedLayout_4);

                score_box = findViewById(R.id.score_lvl10);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again10);
                quit = findViewById(R.id.quit_lvl10);
                reset_button = findViewById(R.id.reset_lvl10);

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
                    cb1.setCardNum(12);
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

                    cb5.setCardNum(12);
                    cb5.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb5);

                    cb6.setCardNum(3);
                    cb6.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb6);

                    cb7.setCardNum(9);
                    cb7.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb7);

                    cb8.setCardNum(13);
                    cb8.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb8);

                    cb9.setCardNum(1);
                    cb9.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb9);

                    cb10.setCardNum(13);
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
                reset_button.setOnClickListener(View -> resetGame());


                break;

            case 12:
                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_5 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_5 = inflater_5.inflate(R.layout.level_12_layout, null);
                setContentView(inflatedLayout_5);

                score_box = findViewById(R.id.score_lvl12);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again12);
                quit = findViewById(R.id.quit_lvl12);
                reset_button = findViewById(R.id.reset_lvl12);

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
                cb11 = findViewById(R.id.i_card10);
                cb12 = findViewById(R.id.i_card10);


                if (!reloaded) {
                    cb1.setCardNum(2);
                    cb1.setCardDrawable(R.drawable.card_2);
                    cbList.add(cb1);

                    cb2.setCardNum(11);
                    cb2.setCardDrawable(R.drawable.card_j);
                    cbList.add(cb2);

                    cb3.setCardNum(7);
                    cb3.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb3);

                    cb4.setCardNum(1);
                    cb4.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb4);

                    cb5.setCardNum(12);
                    cb5.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb5);

                    cb6.setCardNum(13);
                    cb6.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb6);

                    cb7.setCardNum(7);
                    cb7.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb7);

                    cb8.setCardNum(11);
                    cb8.setCardDrawable(R.drawable.card_j);
                    cbList.add(cb8);

                    cb9.setCardNum(1);
                    cb9.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb9);

                    cb10.setCardNum(13);
                    cb10.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb10);

                    cb11.setCardNum(2);
                    cb11.setCardDrawable(R.drawable.card_2);
                    cbList.add(cb11);

                    cb12.setCardNum(12);
                    cb12.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb12);

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
                    reinitializeCard(cb11, cbList.get(10));
                    reinitializeCard(cb12, cbList.get(11));
                    cbList.clear();
                    cbList.addAll(reList);

                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }

                }

                try_again.setOnClickListener(View -> noMatch());
                quit.setOnClickListener(View -> quitGame());
                reset_button.setOnClickListener(View -> resetGame());


                break;

            case 14:
                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_6 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_6 = inflater_6.inflate(R.layout.level_14_layout, null);
                setContentView(inflatedLayout_6);

                score_box = findViewById(R.id.score_lvl14);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again14);
                quit = findViewById(R.id.quit_lvl14);
                reset_button = findViewById(R.id.reset_lvl14);

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
                cb11 = findViewById(R.id.i_card11);
                cb12 = findViewById(R.id.i_card12);
                cb13 = findViewById(R.id.i_card13);
                cb14 = findViewById(R.id.i_card14);

                if (!reloaded) {
                    cb1.setCardNum(10);
                    cb1.setCardDrawable(R.drawable.card_10);
                    cbList.add(cb1);

                    cb2.setCardNum(3);
                    cb2.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb2);

                    cb3.setCardNum(12);
                    cb3.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb3);

                    cb4.setCardNum(10);
                    cb4.setCardDrawable(R.drawable.card_10);
                    cbList.add(cb4);

                    cb5.setCardNum(7);
                    cb5.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb5);

                    cb6.setCardNum(13);
                    cb6.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb6);

                    cb7.setCardNum(12);
                    cb7.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb7);

                    cb8.setCardNum(3);
                    cb8.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb8);

                    cb9.setCardNum(9);
                    cb9.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb9);

                    cb10.setCardNum(2);
                    cb10.setCardDrawable(R.drawable.card_2);
                    cbList.add(cb10);

                    cb11.setCardNum(7);
                    cb11.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb11);

                    cb12.setCardNum(9);
                    cb12.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb12);

                    cb13.setCardNum(13);
                    cb13.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb13);

                    cb14.setCardNum(2);
                    cb14.setCardDrawable(R.drawable.card_2);
                    cbList.add(cb14);

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
                    reinitializeCard(cb11, cbList.get(10));
                    reinitializeCard(cb12, cbList.get(11));
                    reinitializeCard(cb13, cbList.get(12));
                    reinitializeCard(cb14, cbList.get(13));
                    cbList.clear();
                    cbList.addAll(reList);

                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }

                }

                try_again.setOnClickListener(View -> noMatch());
                quit.setOnClickListener(View -> quitGame());
                reset_button.setOnClickListener(View -> resetGame());


                break;

            case 16:
                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_7 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_7 = inflater_7.inflate(R.layout.level_16_layout, null);
                setContentView(inflatedLayout_7);

                score_box = findViewById(R.id.score_lvl16);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again16);
                quit = findViewById(R.id.quit_lvl16);
                reset_button = findViewById(R.id.reset_lvl16);

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
                cb11 = findViewById(R.id.i_card11);
                cb12 = findViewById(R.id.i_card12);
                cb13 = findViewById(R.id.i_card13);
                cb14 = findViewById(R.id.i_card14);
                cb15 = findViewById(R.id.i_card15);
                cb16 = findViewById(R.id.i_card16);

                if (!reloaded) {
                    cb1.setCardNum(10);
                    cb1.setCardDrawable(R.drawable.card_10);
                    cbList.add(cb1);

                    cb2.setCardNum(3);
                    cb2.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb2);

                    cb3.setCardNum(8);
                    cb3.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb3);

                    cb4.setCardNum(7);
                    cb4.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb4);

                    cb5.setCardNum(3);
                    cb5.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb5);

                    cb6.setCardNum(12);
                    cb6.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb6);

                    cb7.setCardNum(7);
                    cb7.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb7);

                    cb8.setCardNum(8);
                    cb8.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb8);

                    cb9.setCardNum(13);
                    cb9.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb9);

                    cb10.setCardNum(11);
                    cb10.setCardDrawable(R.drawable.card_j);
                    cbList.add(cb10);

                    cb11.setCardNum(10);
                    cb11.setCardDrawable(R.drawable.card_10);
                    cbList.add(cb11);

                    cb12.setCardNum(1);
                    cb12.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb12);

                    cb13.setCardNum(12);
                    cb13.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb13);

                    cb14.setCardNum(11);
                    cb14.setCardDrawable(R.drawable.card_j);
                    cbList.add(cb14);

                    cb15.setCardNum(13);
                    cb15.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb15);

                    cb16.setCardNum(1);
                    cb16.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb16);

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
                    reinitializeCard(cb11, cbList.get(10));
                    reinitializeCard(cb12, cbList.get(11));
                    reinitializeCard(cb13, cbList.get(12));
                    reinitializeCard(cb14, cbList.get(13));
                    reinitializeCard(cb15, cbList.get(14));
                    reinitializeCard(cb16, cbList.get(15));
                    cbList.clear();
                    cbList.addAll(reList);

                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }

                }

                try_again.setOnClickListener(View -> noMatch());
                quit.setOnClickListener(View -> quitGame());
                reset_button.setOnClickListener(View -> resetGame());


                break;

            case 18:
                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_8 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_8 = inflater_8.inflate(R.layout.level_18_layout, null);
                setContentView(inflatedLayout_8);

                score_box = findViewById(R.id.score_lvl18);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again18);
                quit = findViewById(R.id.quit_lvl18);
                reset_button = findViewById(R.id.reset_lvl18);

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
                cb11 = findViewById(R.id.i_card11);
                cb12 = findViewById(R.id.i_card12);
                cb13 = findViewById(R.id.i_card13);
                cb14 = findViewById(R.id.i_card14);
                cb15 = findViewById(R.id.i_card15);
                cb16 = findViewById(R.id.i_card16);
                cb17 = findViewById(R.id.i_card17);
                cb18 = findViewById(R.id.i_card18);

                if (!reloaded) {
                    cb1.setCardNum(13);
                    cb1.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb1);

                    cb2.setCardNum(12);
                    cb2.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb2);

                    cb3.setCardNum(10);
                    cb3.setCardDrawable(R.drawable.card_10);
                    cbList.add(cb3);

                    cb4.setCardNum(2);
                    cb4.setCardDrawable(R.drawable.card_2);
                    cbList.add(cb4);

                    cb5.setCardNum(8);
                    cb5.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb5);

                    cb6.setCardNum(11);
                    cb6.setCardDrawable(R.drawable.card_j);
                    cbList.add(cb6);

                    cb7.setCardNum(1);
                    cb7.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb7);

                    cb8.setCardNum(2);
                    cb8.setCardDrawable(R.drawable.card_2);
                    cbList.add(cb8);

                    cb9.setCardNum(1);
                    cb9.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb9);

                    cb10.setCardNum(7);
                    cb10.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb10);

                    cb11.setCardNum(3);
                    cb11.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb11);

                    cb12.setCardNum(11);
                    cb12.setCardDrawable(R.drawable.card_j);
                    cbList.add(cb12);

                    cb13.setCardNum(3);
                    cb13.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb13);

                    cb14.setCardNum(8);
                    cb14.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb14);

                    cb15.setCardNum(10);
                    cb15.setCardDrawable(R.drawable.card_10);
                    cbList.add(cb15);

                    cb16.setCardNum(13);
                    cb16.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb16);

                    cb17.setCardNum(7);
                    cb17.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb17);

                    cb18.setCardNum(12);
                    cb18.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb18);

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
                    reinitializeCard(cb11, cbList.get(10));
                    reinitializeCard(cb12, cbList.get(11));
                    reinitializeCard(cb13, cbList.get(12));
                    reinitializeCard(cb14, cbList.get(13));
                    reinitializeCard(cb15, cbList.get(14));
                    reinitializeCard(cb16, cbList.get(15));
                    reinitializeCard(cb17, cbList.get(16));
                    reinitializeCard(cb18, cbList.get(17));
                    cbList.clear();
                    cbList.addAll(reList);

                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }

                }

                try_again.setOnClickListener(View -> noMatch());
                quit.setOnClickListener(View -> quitGame());
                reset_button.setOnClickListener(View -> resetGame());


                break;

            //TODO: Refactor for CardButton class implementation
            case 20:

                scale = getApplicationContext().getResources().getDisplayMetrics().density;
                LayoutInflater inflater_9 = LayoutInflater.from(GameActivity.this);
                @SuppressLint("InflateParams") View inflatedLayout_9 = inflater_9.inflate(R.layout.level_20_layout, null);
                setContentView(inflatedLayout_9);

                score_box = findViewById(R.id.score_lvl20);
                score_box.setText(R.string.init_score);
                try_again = findViewById(R.id.try_again20);
                quit = findViewById(R.id.quit_lvl20);
                reset_button = findViewById(R.id.reset_lvl20);

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
                cb11 = findViewById(R.id.i_card11);
                cb12 = findViewById(R.id.i_card12);
                cb13 = findViewById(R.id.i_card13);
                cb14 = findViewById(R.id.i_card14);
                cb15 = findViewById(R.id.i_card15);
                cb16 = findViewById(R.id.i_card16);
                cb17 = findViewById(R.id.i_card17);
                cb18 = findViewById(R.id.i_card18);
                cb19 = findViewById(R.id.i_card19);
                cb20 = findViewById(R.id.i_card20);

                if (!reloaded) {
                    cb1.setCardNum(9);
                    cb1.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb1);

                    cb2.setCardNum(1);
                    cb2.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb2);

                    cb3.setCardNum(8);
                    cb3.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb3);

                    cb4.setCardNum(12);
                    cb4.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb4);

                    cb5.setCardNum(9);
                    cb5.setCardDrawable(R.drawable.card_9);
                    cbList.add(cb5);

                    cb6.setCardNum(10);
                    cb6.setCardDrawable(R.drawable.card_10);
                    cbList.add(cb6);

                    cb7.setCardNum(7);
                    cb7.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb7);

                    cb8.setCardNum(1);
                    cb8.setCardDrawable(R.drawable.card_a);
                    cbList.add(cb8);

                    cb9.setCardNum(8);
                    cb9.setCardDrawable(R.drawable.card_8);
                    cbList.add(cb9);

                    cb10.setCardNum(2);
                    cb10.setCardDrawable(R.drawable.card_2);
                    cbList.add(cb10);

                    cb11.setCardNum(7);
                    cb11.setCardDrawable(R.drawable.card_7);
                    cbList.add(cb11);

                    cb12.setCardNum(10);
                    cb12.setCardDrawable(R.drawable.card_10);
                    cbList.add(cb12);

                    cb13.setCardNum(13);
                    cb13.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb13);

                    cb14.setCardNum(11);
                    cb14.setCardDrawable(R.drawable.card_j);
                    cbList.add(cb14);

                    cb15.setCardNum(2);
                    cb15.setCardDrawable(R.drawable.card_2);
                    cbList.add(cb15);

                    cb16.setCardNum(3);
                    cb16.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb16);

                    cb17.setCardNum(13);
                    cb17.setCardDrawable(R.drawable.card_king);
                    cbList.add(cb17);

                    cb18.setCardNum(12);
                    cb18.setCardDrawable(R.drawable.card_queen);
                    cbList.add(cb18);

                    cb19.setCardNum(11);
                    cb19.setCardDrawable(R.drawable.card_j);
                    cbList.add(cb19);

                    cb20.setCardNum(3);
                    cb20.setCardDrawable(R.drawable.card_3);
                    cbList.add(cb20);

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
                    reinitializeCard(cb11, cbList.get(10));
                    reinitializeCard(cb12, cbList.get(11));
                    reinitializeCard(cb13, cbList.get(12));
                    reinitializeCard(cb14, cbList.get(13));
                    reinitializeCard(cb15, cbList.get(14));
                    reinitializeCard(cb16, cbList.get(15));
                    reinitializeCard(cb17, cbList.get(16));
                    reinitializeCard(cb18, cbList.get(17));
                    reinitializeCard(cb19, cbList.get(18));
                    reinitializeCard(cb20, cbList.get(19));
                    cbList.clear();
                    cbList.addAll(reList);

                    if (tryVis) {
                        try_again.setVisibility(View.VISIBLE);
                    }

                }

                try_again.setOnClickListener(View -> noMatch());
                quit.setOnClickListener(View -> quitGame());
                reset_button.setOnClickListener(View -> resetGame());


                break;
            default:
                break;
        }

    }

    /**
     * Method to determine a card match...while checking disable non-active buttons
     *
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
        }, 6000);


    }

    public void resetGame() {
        endGame = true;
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
    }


    /**
     * On a configuration change...ie.rotation copy over values to "new" cards
     *
     * @param cb  - card to be reinitialized
     * @param tmp - card to copy from
     */
    public void reinitializeCard(CardButton cb, CardButton tmp) {
        cb.setFlipped(tmp.isFlipped());
        cb.setCardNum(tmp.getCardNum());
        cb.setMatched(tmp.getIsMatched());
        cb.setCardDrawable(tmp.getCardDrawable());
        if (!tmp.isEnabled()) {
            cb.setEnabled(false);
        }
        if (cb.isFlipped()) {

            cb.setImageResource(tmp.getCardDrawable());

            if (!cb.getIsMatched()) {
                toRemove.add(cb);
            }
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
     *
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
     *
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
     *
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
     *
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
     *
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