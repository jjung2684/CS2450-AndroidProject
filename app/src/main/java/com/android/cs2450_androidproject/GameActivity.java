package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity{
int numCards;
int score;
    Button try_again;
    MediaPlayer mediaPlayer;
    TextView score_box;
    LinearLayout main_Layout;
    String[] gameWords = {"Panda", "Lion", "Tiger", "Bear", "Eagle", "Snake", "Cheetah",
            "Jaguar", "Dolphin", "Wolf"};
    ArrayList<String> open_List;
    ArrayList<Button> bttn_List;
    ArrayList<Button> rmv_List;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        int cards= intent.getIntExtra("cards", numCards);
        Log.d("num", String.valueOf(cards));




        mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.jazz_music);
        Intent music = new Intent(GameActivity.this, BackgroundSoundService.class);
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


                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button c1 = (Button) findViewById(R.id.card1);
                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button c2 = (Button) findViewById(R.id.card2);
                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button c3 = (Button) findViewById(R.id.card3);
                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button c4 = (Button) findViewById(R.id.card4);


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
            case 109:
                break;
            default:
                break;

        }





    }

    private void checkMatch(Button button){


        String text = (String) button.getText();
        if(open_List.contains(text) && bttn_List.size() > 1){
            score+=2;
            score_box.setText("Score: "+ score);
            bttn_List.clear();
            open_List.clear();
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

    }

    private void noMatch(){
        if(!rmv_List.isEmpty()){
            for(int i = 0; i < rmv_List.size(); i++){
                rmv_List.get(i).setText("");
            }
            try_again.setVisibility(View.INVISIBLE);
        }


    }



}