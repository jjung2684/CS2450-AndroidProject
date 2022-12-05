package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity{
int numCards;
int score;
int numMatches;
    Button try_again;
    MediaPlayer mediaPlayer;
    TextView score_box;
    LinearLayout main_Layout;
    String[] gameWords = {"Panda", "Lion", "Tiger", "Bear", "Eagle", "Snake", "Cheetah",
            "Jaguar", "Dolphin", "Wolf"};
    ArrayList<String> open_List;
    ArrayList<Button> bttn_List;
    ArrayList<Button> rmv_List;
    Button quit;
    Intent music;
    Button c1, c2, c3, c4, c5, c6, c7,c8,c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        int cards= intent.getIntExtra("cards", numCards);
        Log.d("num", String.valueOf(cards));
        numCards = cards;




        mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.jazz_music);
        music = new Intent(GameActivity.this, BackgroundSoundService.class);
        startService(music);
        main_Layout = (LinearLayout) findViewById(R.id.mainLayout);
        open_List= new ArrayList<>();
        bttn_List = new ArrayList<>();
        rmv_List = new ArrayList<>();


        switch(cards){
            case 4:
                        Log.d("reachd", "hello");
                LayoutInflater inflater = LayoutInflater.from(GameActivity.this);
                View inflatedLayout = inflater.inflate(R.layout.level_4_layout, null);
                setContentView(inflatedLayout);
                score_box = (TextView) findViewById(R.id.score_box2);
                score_box.setText("Score: 0");
                try_again = (Button) findViewById(R.id.try_again);
                quit = (Button) findViewById(R.id.quit_bttn);


                 c1 = (Button) findViewById(R.id.card1);
                 c2 = (Button) findViewById(R.id.card2);
                 c3 = (Button) findViewById(R.id.card3);
                 c4 = (Button) findViewById(R.id.card4);

                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());



                c1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c1.setText(gameWords[0]);

                        bttn_List.add(c1);

                        checkMatch(c1);

                    }
                });

                c2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c2.setText(gameWords[3]);
                        bttn_List.add(c2);
                        checkMatch(c2);

                    }
                });

                c3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c3.setText(gameWords[0]);
                        bttn_List.add(c3);
                        checkMatch(c3);
                    }
                });

                c4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c4.setText(gameWords[3]);

                        bttn_List.add(c4);

                        checkMatch(c4);
                    }
                });
                break;




            case 6:
                Log.d("reachd", "me");
                LayoutInflater inflater_2 = LayoutInflater.from(GameActivity.this);
                View inflatedLayout_2 = inflater_2.inflate(R.layout.level_6_layout, null);
                setContentView(inflatedLayout_2);
                score_box = (TextView) findViewById(R.id.score_box3);
                score_box.setText("Score: 0");
                try_again = (Button) findViewById(R.id.try_again2);
                quit = (Button) findViewById(R.id.quit_bttn2);


                c1 = (Button) findViewById(R.id.card1);
                c2 = (Button) findViewById(R.id.card2);
                c3 = (Button) findViewById(R.id.card3);
                c4 = (Button) findViewById(R.id.card4);
                c5 = (Button) findViewById(R.id.card5);
                c6 = (Button) findViewById(R.id.card6);


                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());



                c1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c1.setText(gameWords[0]);

                        bttn_List.add(c1);

                        checkMatch(c1);

                    }
                });

                c2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c2.setText(gameWords[6]);
                        bttn_List.add(c2);
                        checkMatch(c2);

                    }
                });

                c3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c3.setText(gameWords[0]);
                        bttn_List.add(c3);
                        checkMatch(c3);
                    }
                });

                c4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c4.setText(gameWords[3]);

                        bttn_List.add(c4);

                        checkMatch(c4);
                    }
                });

                c5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c5.setText(gameWords[3]);

                        bttn_List.add(c5);

                        checkMatch(c5);
                    }
                });


                c6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c6.setText(gameWords[6]);

                        bttn_List.add(c6);

                        checkMatch(c6);
                    }
                });

                break;
            case 20:
                Log.d("reachd", "m3");
                LayoutInflater inflater_3 = LayoutInflater.from(GameActivity.this);
                View inflatedLayout_3 = inflater_3.inflate(R.layout.level_20_layout, null);
                setContentView(inflatedLayout_3);
                score_box = (TextView) findViewById(R.id.score_box5);
                score_box.setText("Score: 0");
                try_again = (Button) findViewById(R.id.try_again4);
                quit = (Button) findViewById(R.id.quit_bttn4);


                c1 = (Button) findViewById(R.id.card1);
                c2 = (Button) findViewById(R.id.card2);
                c3 = (Button) findViewById(R.id.card3);
                c4 = (Button) findViewById(R.id.card4);
                c5 = (Button) findViewById(R.id.card5);
                c6 = (Button) findViewById(R.id.card6);
                c7 = (Button) findViewById(R.id.card7);
                c8 = (Button) findViewById(R.id.card8);
                c9 = (Button) findViewById(R.id.card9);
                c10 = (Button) findViewById(R.id.card10);
                c11 = (Button) findViewById(R.id.card11);
                c12 = (Button) findViewById(R.id.card12);
                c13 = (Button) findViewById(R.id.card13);
                c14 = (Button) findViewById(R.id.card14);
                c15 = (Button) findViewById(R.id.card15);
                c16 = (Button) findViewById(R.id.card16);
                c17 = (Button) findViewById(R.id.card17);
                c18 = (Button) findViewById(R.id.card18);
                c19 = (Button) findViewById(R.id.card19);
                c20 = (Button) findViewById(R.id.card20);


                quit.setOnClickListener(View -> quitGame());
                try_again.setOnClickListener(View -> noMatch());

                c1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c1.setText(gameWords[0]);

                        bttn_List.add(c1);

                        checkMatch(c1);

                    }
                });

                c2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c2.setText(gameWords[9]);
                        bttn_List.add(c2);
                        checkMatch(c2);

                    }
                });

                c3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c3.setText(gameWords[8]);
                        bttn_List.add(c3);
                        checkMatch(c3);
                    }
                });

                c4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c4.setText(gameWords[6]);

                        bttn_List.add(c4);

                        checkMatch(c4);
                    }
                });

                c5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c5.setText(gameWords[3]);

                        bttn_List.add(c5);

                        checkMatch(c5);
                    }
                });


                c6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c6.setText(gameWords[6]);

                        bttn_List.add(c6);

                        checkMatch(c6);
                    }
                });

                c7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c7.setText(gameWords[0]);

                        bttn_List.add(c7);

                        checkMatch(c7);

                    }
                });

                c8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c8.setText(gameWords[8]);
                        bttn_List.add(c8);
                        checkMatch(c8);

                    }
                });

                c9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c9.setText(gameWords[2]);
                        bttn_List.add(c9);
                        checkMatch(c9);
                    }
                });

                c10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c10.setText(gameWords[1]);

                        bttn_List.add(c10);

                        checkMatch(c10);
                    }
                });

                c11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c11.setText(gameWords[5]);

                        bttn_List.add(c11);

                        checkMatch(c11);
                    }
                });


                c12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c12.setText(gameWords[7]);

                        bttn_List.add(c12);

                        checkMatch(c12);
                    }
                });

                c13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c13.setText(gameWords[9]);

                        bttn_List.add(c13);

                        checkMatch(c13);

                    }
                });

                c14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c14.setText(gameWords[1]);
                        bttn_List.add(c14);
                        checkMatch(c14);

                    }
                });

                c15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c15.setText(gameWords[4]);
                        bttn_List.add(c15);
                        checkMatch(c15);
                    }
                });

                c16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c16.setText(gameWords[2]);

                        bttn_List.add(c16);

                        checkMatch(c16);
                    }
                });

                c17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c17.setText(gameWords[7]);

                        bttn_List.add(c17);

                        checkMatch(c17);
                    }
                });


                c18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c18.setText(gameWords[5]);

                        bttn_List.add(c18);

                        checkMatch(c18);
                    }
                });

                c19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c19.setText(gameWords[4]);

                        bttn_List.add(c19);

                        checkMatch(c19);
                    }
                });


                c20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        c20.setText(gameWords[3]);

                        bttn_List.add(c20);

                        checkMatch(c20);
                    }
                });

                break;
            default:
                break;

        }





    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.level_4_layout);
        }
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.level_4_layout);
        }
    }

    private void checkMatch(Button button){


        String text = (String) button.getText();
        if(open_List.contains(text) && bttn_List.size() > 1){
            score+=2;
            score_box.setText("Score: "+ score);
            for(int i = 0; i < bttn_List.size(); i++){
             bttn_List.get(i).setClickable(false);
            }
            bttn_List.clear();
            open_List.clear();
            numMatches++;
        }else if (bttn_List.size() > 1){

            for(int i = 0; i < bttn_List.size(); i++){
                rmv_List.add(bttn_List.get(i));
            }

            if(score != 0) {
                score -= 1;
                score_box.setText("Score: " + score);

            }
            bttn_List.clear();
            open_List.clear();
            try_again.setVisibility(View.VISIBLE);

        }else{
            open_List.add(text);
        }
        if(numMatches == (numCards/2)){
            winCondition();
        }

    }

    private void winCondition(){
        Intent myIntent = new Intent(GameActivity.this, WinActivity.class);
        myIntent.putExtra("score", score);
        stopService(music);
        startActivity(myIntent);

    }

    private void noMatch(){
        if(!rmv_List.isEmpty()){
            for(int i = 0; i < rmv_List.size(); i++){
                rmv_List.get(i).setText("");
            }
            rmv_List.clear();
            try_again.setVisibility(View.INVISIBLE);
        }


    }

    private void quitGame(){
        switch(numCards){
            case 4:
                c1.setText(gameWords[0]);
                c2.setText(gameWords[3]);
                c3.setText(gameWords[0]);
                c4.setText(gameWords[3]);
                break;

            case 6:
                c1.setText(gameWords[0]);
                c2.setText(gameWords[6]);
                c3.setText(gameWords[0]);
                c4.setText(gameWords[3]);
                c5.setText(gameWords[3]);
                c6.setText(gameWords[6]);

                break;
            case 20:
                c1.setText(gameWords[0]);
                c2.setText(gameWords[9]);
                c3.setText(gameWords[8]);
                c4.setText(gameWords[6]);
                c5.setText(gameWords[3]);
                c6.setText(gameWords[6]);
                c7.setText(gameWords[0]);
                c8.setText(gameWords[8]);
                c9.setText(gameWords[2]);
                c10.setText(gameWords[1]);
                c11.setText(gameWords[5]);
                c12.setText(gameWords[7]);
                c13.setText(gameWords[9]);
                c14.setText(gameWords[1]);
                c15.setText(gameWords[4]);
                c16.setText(gameWords[2]);
                c17.setText(gameWords[7]);
                c18.setText(gameWords[5]);
                c19.setText(gameWords[4]);
                c20.setText(gameWords[3]);
                break;
            default:
                break;
        }


        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(GameActivity.this, MenuActivity.class);
                stopService(music);
                startActivity(myIntent);
            }
        },8000);

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
        Toast.makeText(this, "Disabling Background Music", Toast.LENGTH_SHORT).show();


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