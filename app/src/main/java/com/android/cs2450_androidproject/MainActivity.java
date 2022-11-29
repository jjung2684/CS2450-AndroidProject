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

public class MainActivity extends AppCompatActivity {

    Button button2x2;
    Button button3x2;
    Button button4x2;
    Button button5x2;
    Button button6x2;
    Button button7x2;
    Button button4x4;
    Button button6x3;
    Button button4x5;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2x2 = (Button) findViewById(R.id.button2x2);
        button3x2 = (Button) findViewById(R.id.button3x2);
        button4x2 = (Button) findViewById(R.id.button4x2);
        button5x2 = (Button) findViewById(R.id.button5x2);
        button6x2 = (Button) findViewById(R.id.button6x2);
        button7x2 = (Button) findViewById(R.id.button7x2);
        button4x4 = (Button) findViewById(R.id.button4x4);
        button6x3 = (Button) findViewById(R.id.button6x3);
        button4x5 = (Button) findViewById(R.id.button4x5);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.jazz_music);

    }
    public void PlayBackgroundSound(View view) {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
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
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt("position", mediaPlayer.getCurrentPosition());
        mediaPlayer.pause();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        int pos = savedInstanceState.getInt("position");
        mediaPlayer.seekTo(pos);
        super.onRestoreInstanceState(savedInstanceState);
    }





}