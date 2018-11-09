package com.example.zachl.tcubed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);
    }

    // Intent for play button
    public void play (View view) {
       Intent game_engine = new Intent(this, GameEngine.class);
       // Take in input from all buttons - right now it just calls the next screen
       startActivity(game_engine);
    }
}
