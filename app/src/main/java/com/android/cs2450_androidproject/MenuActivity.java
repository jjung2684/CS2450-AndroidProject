package com.android.cs2450_androidproject;

import static com.android.cs2450_androidproject.R.id.start_activity_bttn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button start_bttn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        start_bttn = (Button) findViewById(start_activity_bttn);
        start_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.start_activity_bttn) {
                    Intent myIntent = new Intent(MenuActivity.this, LevelsActivity.class);
                    startActivity(myIntent);
                    Log.d("create", "clicked");
                }
            }
        });

    }


}