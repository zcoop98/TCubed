package com.example.zachl.tcubed;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static com.example.zachl.tcubed.R.id.board;

public class MainActivity extends AppCompatActivity {

    private BoardView boardView;
    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        boardView = findViewById(board);
        gameEngine = new GameEngine();
        boardView.setGameBoard(gameEngine);
        boardView.setMainActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_game) {
            newGame();
        }

        return super.onOptionsItemSelected(item);
    }

    public void gameEnded(char c) {
        String msg = (c == 'T') ? "Game Ended. Tie" : "GameEnded. " + c + " win";

        new AlertDialog.Builder(this).setTitle("T CUBED").
                setMessage(msg).
                setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        newGame();
                    }
                }).show();
    }

    private void newGame() {
        gameEngine.newGame();
        boardView.invalidate();
    }

    // Intent for Play Button
    public void playMenu(View view) {
        Intent play_menu = new Intent(this, PlayMenu.class);
        startActivity(play_menu);
    }

    // Intent for Customize Button
    public void themesMenu(View view) {
        Intent themes_menu = new Intent (this, ThemesMenu.class);
        startActivity(themes_menu);
    }

    // Intent for Directions Button
    public void directionsMenu(View view) {
        Intent directions_menu = new Intent(this, DirectionsMenu.class);
        startActivity(directions_menu);
    }

    // Intent for Credits Button
    public void credits(View view) {
        Intent credits_page = new Intent(this, Credits.class);
        startActivity(credits_page);
    }

    // Intent for main menu settings
    public void mainSettings(View view) {
        Intent settings_fragment = new Intent(this, SettingsFragment.class);
        startActivity(settings_fragment);
    }
}