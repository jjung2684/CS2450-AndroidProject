package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;

    Button firstChoiceButton;

    ArrayList<Button> buttonList;

    MediaPlayer mediaPlayer;

    String[] gameWords = {"Panda", "Lion", "Tiger", "Bear", "Eagle", "Snake", "Cheetah",
            "Jaguar", "Dolphin", "Wolf"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init buttons and set listeners
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);

        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);

        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);

        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);

        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(this);

        button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(this);

        button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(this);


        // add buttons to button list
        buttonList = new ArrayList<>();
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);
        buttonList.add(button10);
        buttonList.add(button11);
        buttonList.add(button12);


        // media player
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.jazz_music);
    }

    /*
    1. If all tiles are blank, the user has not chosen any tiles yet
    2. Each time a tile is chosen it will be checked to see if it's the first tile chosen
    3. If it's the first tile, all remaining tiles can be compared to see if there's a match
     */
    public boolean checkFirstChoice(Button button) {
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i) != button & !buttonList.get(i).getText().equals("")) {
                System.out.println("Not first choice: ");
                return false;
            }
        }

        return true;
    }

    /*
    1. Users turn over two tiles and if a match is found, the tiles remain displayed
    2. All other tiles are reset to blank
     */
    public void displayWinningTiles(Button button1, Button button2) {
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i) != button1 & buttonList.get(i) != button2) {
                buttonList.get(i).setText("");
            }
        }
    }

    /*
    Resets all tiles to the blank
     */
    public void resetButtons() {
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setText("");
        }
    }

    public void PlayBackgroundSound(View view) {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
    }

    /*
    1. Checks if the button clicked is the first choice
    2. If not, it checks to see if the button clicked matches the first choice
    3. If there's a match it removes the 2 buttons from the button list and calls the method to
    display only the matching tiles

    Note: A "Try again" button needs to be implemented to reset the tiles if there's no match
     */
    private void checkForMatch(Button button) {
        if (checkFirstChoice(button)) {
            firstChoiceButton = button;
            System.out.println("First choice: " + firstChoiceButton.getText());
        } else if (button.getText().equals(firstChoiceButton.getText())) {
            buttonList.remove(button);
            buttonList.remove(firstChoiceButton);
            displayWinningTiles(button, firstChoiceButton);
            System.out.println("Matching pair!");
        }
    }

    // on click method for buttons
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1) {
            button1.setText(gameWords[0]);
            checkForMatch(button1);
        } else if (view.getId() == R.id.button2) {
            button2.setText(gameWords[1]);
            checkForMatch(button2);
        } else if (view.getId() == R.id.button3) {
            button3.setText(gameWords[2]);
            checkForMatch(button3);
        } else if (view.getId() == R.id.button4) {
            button4.setText(gameWords[0]);
            checkForMatch(button4);
        } else if (view.getId() == R.id.button5) {
            button5.setText(gameWords[1]);
            checkForMatch(button5);
        } else if (view.getId() == R.id.button6) {
            button6.setText(gameWords[2]);
            checkForMatch(button6);
        } else if (view.getId() == R.id.button7) {
            button7.setText(gameWords[3]);
            checkForMatch(button7);
        } else if (view.getId() == R.id.button8) {
            button8.setText(gameWords[5]);
            checkForMatch(button8);
        } else if (view.getId() == R.id.button9) {
            button9.setText(gameWords[3]);
            checkForMatch(button9);
        }else if (view.getId() == R.id.button10) {
            button10.setText(gameWords[6]);
            checkForMatch(button10);
        }else if (view.getId() == R.id.button11) {
            button11.setText(gameWords[5]);
            checkForMatch(button11);
        }else if (view.getId() == R.id.button12) {
            button12.setText(gameWords[6]);
            checkForMatch(button12);
        }

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

        Intent startServiceIntent = new Intent(MainActivity.this, BackgroundSoundService.class);
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