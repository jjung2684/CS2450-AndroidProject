package com.android.cs2450_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

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


    }
}