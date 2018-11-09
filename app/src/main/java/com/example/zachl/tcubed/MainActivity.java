package com.example.zachl.tcubed;

import android.content.DialogInterface;
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
        boardView = (BoardView) findViewById(board);
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
    public void playmenu(View view) {
        // Do something in response to button
    }

    // Intent for Customize Button
    public void themesMenu(View view) {
        // Do something in response to button
    }

    // Intent for Directions Button
    public void directionsMenu(View view) {
        // Do something in response to button
    }

    // Intent for Credits Button
    public void credits(View view) {
        // Do something in response to button
    }

    // Intent for main menu settings
    public void mainSettings(View view) {
        // Do something in response to button
    }
}