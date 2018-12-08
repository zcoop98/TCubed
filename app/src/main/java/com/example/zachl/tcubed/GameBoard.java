package com.example.zachl.tcubed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

public class GameBoard extends AppCompatActivity {
    public static final String XNAME = "com.zachl.tcubed.XNAME";
    public static final String ONAME = "com.zachl.tcubed.ONAME";
    public static final String PVPSWITCH = "com.zachl.tcubed.PVPSWITCH";
    public static final String XOSWITCH = "com.zachl.tcubed.XOSWITCH";
    private static final String TAG = "GameBoard";
    private GameEngine game_engine;
    private BoardView board_view;
    private TextView player1Score;
    private TextView player2Score;
    private int xWins;
    private int oWins;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
    }

    protected void onStart()
    {

        Log.d(TAG, "onStart() Firing");
        super.onStart();

        Log.d(TAG, "Constructing BoardView");
        board_view = findViewById(R.id.board);

        Log.d(TAG, "Constructing GameEngine");
        game_engine = new GameEngine();

        Log.d(TAG,"Setting GameEngine param in BoardView");
        board_view.setGameEngine(game_engine);

        Log.d(TAG, "Setting GameBoard param in BoardView");
        board_view.setGameBoard(this);

        Log.d(TAG, "Receiving intents from PlayMenu:");
        Intent intent = getIntent();
        String player1Name = intent.getStringExtra(XNAME);
        if (player1Name.equals(""))
            player1Name = "X Player";
        String player2Name = intent.getStringExtra(ONAME);
        if (player2Name.equals(""))
            player2Name = "O Player";
        boolean playerVCPU = intent.getBooleanExtra(PVPSWITCH, false);
        boolean xGoesFirst = intent.getBooleanExtra(XOSWITCH, false);

        Log.d(TAG, "*P1Name: " + player1Name + "\n*P2Name: " + player2Name + "\n*playerVCPU = " + playerVCPU + "\n*xGoesFirst = " + xGoesFirst);

        TextView textView = findViewById(R.id.xPlayerNameView);
        textView.setText(player1Name);
        TextView textView2 = findViewById(R.id.oPlayerNameView);
        textView2.setText(player2Name);

        Log.d(TAG, "Setting Score to zero");

        xWins = 0;
        oWins = 0;

        Log.d(TAG, "Setting player scores");

        player1Score = findViewById(R.id.xPlayerScoreView);
        player1Score.setText(Integer.toString(xWins));
        player2Score = findViewById(R.id.oPlayerScoreView);
        player2Score.setText(Integer.toString(oWins));

        Log.d(TAG, "Sending user settings to gameEngine");

        game_engine.setPlayerVCPU(playerVCPU);
        game_engine.setXGoesFirst(xGoesFirst);
        game_engine.newGame();

        Log.d(TAG, "End onStart()");
    }   //Here's where all hell breaks loose

    public void incrementXScore()
    {
        xWins++;
        player1Score.setText(Integer.toString(xWins));
    }

    public void incrementOScore()
    {
        oWins++;
        player2Score.setText(Integer.toString(oWins));
    }
}