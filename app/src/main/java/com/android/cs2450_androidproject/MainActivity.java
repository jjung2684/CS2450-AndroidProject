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

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;

    Button firstChoiceButton;

    ArrayList<Button> buttonList;

    MediaPlayer mediaPlayer;

    String[] gameWords = {"Panda", "Lion", "Tiger", "Bear", "Eagle", "Snake", "Cheetah", "Jaguar", "Dolphin", "Wolf"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.jazz_music);

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

    }

    /*
    If all tiles are blank, the user has not chosen any tiles yet.
    Each time a tile is chosen it will be checked to see if it's the first tile chosen.
    If it's the first tile, all remaining tiles can be compared to see if there's a match.
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
    Users turn over two tiles and if a match is found, the tiles remain displayed.
    All other tiles are reset to blank.
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

    public void onButton1Click(View view) {
        button1.setText(gameWords[0]);
        checkForMatch(button1);
    }

    public void onButton2Click(View view) {
        button2.setText(gameWords[1]);
        checkForMatch(button2);
    }

    public void onButton3Click(View view) {
        button3.setText(gameWords[2]);
        checkForMatch(button3);
    }

    public void onButton4Click(View view) {
        button4.setText(gameWords[0]);
        checkForMatch(button4);
    }

    public void onButton5Click(View view) {
        button5.setText(gameWords[1]);
        checkForMatch(button5);
    }

    public void onButton6Click(View view) {
        button6.setText(gameWords[2]);
        checkForMatch(button6);
    }

    public void onButton7Click(View view) {
        button7.setText(gameWords[3]);
        checkForMatch(button7);
    }

    public void onButton8Click(View view) {
        button8.setText(gameWords[5]);
        checkForMatch(button8);
    }

    public void onButton9Click(View view) {
        button9.setText(gameWords[3]);
        checkForMatch(button9);
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