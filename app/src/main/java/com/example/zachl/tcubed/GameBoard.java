package com.example.zachl.tcubed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

public class GameBoard extends AppCompatActivity {
    public static final String XNAME = "com.zachl.tcubed.XNAME";
    public static final String ONAME = "com.zachl.tcubed.ONAME";
    public static final String PVPSWITCH = "com.zachl.tcubed.PVPSWITCH";
    public static final String XOSWITCH = "com.zachl.tcubed.XOSWITCH";
    private static final String TAG = "GameBoard";
    private static final String sharedPrefFile = "com.example.zachl.tcubed.sharedPrefs";
    private SharedPreferences mPreferences;
    private static final String BACKGROUND_KEY = "com.example.zachl.tcubed.backgroundKey";
    private GameEngine game_engine;
    private BoardView board_view;
    private TextView player1Score;
    private TextView player2Score;
    private String playerXName;
    private String playerOName;
    private int xWins;
    private int oWins;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        mPreferences = getApplicationContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
    }

    protected void onStart()
    {

        Log.d(TAG, "onStart() Firing");
        super.onStart();

        int colorCode = mPreferences.getInt(BACKGROUND_KEY, 0);

        Log.d(TAG, "Retrieved color code: " + colorCode);

        int r = (byte) colorCode & (byte) 0xFF0000;
        int g = (byte) colorCode & (byte) 0x00FF00;
        int b = (byte) colorCode & (byte) 0x0000FF;

        Log.d(TAG, "Extracted RGB vals- R: " + r + " G: " + g + " B: " + b);

        //findViewById(R.id.boardLayout).setBackgroundColor(Color.rgb(r, g, b));

        Log.d(TAG, "Set background color pref\nConstructing BoardView");
        board_view = findViewById(R.id.board);

        Log.d(TAG, "Constructing GameEngine");
        game_engine = new GameEngine();

        Log.d(TAG,"Setting GameEngine param in BoardView");
        board_view.setGameEngine(game_engine);

        Log.d(TAG, "Setting GameBoard param in BoardView");
        board_view.setGameBoard(this);

        Log.d(TAG, "Receiving intents from PlayMenu:");
        Intent intent = getIntent();
        playerXName = intent.getStringExtra(XNAME);

        if (playerXName.equals(""))
            playerXName = "X Player";

        playerOName = intent.getStringExtra(ONAME);

        if (playerOName.equals(""))
            playerOName = "O Player";

        boolean playerVCPU = intent.getBooleanExtra(PVPSWITCH, false);
        boolean xGoesFirst = intent.getBooleanExtra(XOSWITCH, false);

        Log.d(TAG, "*P1Name: " + playerXName + "\n*P2Name: " + playerOName + "\n*playerVCPU = " + playerVCPU + "\n*xGoesFirst = " + xGoesFirst);

        TextView textView = findViewById(R.id.xPlayerNameView);
        textView.setText(playerXName);
        TextView textView2 = findViewById(R.id.oPlayerNameView);
        textView2.setText(playerOName);

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
        vibrate();
    }

    public void incrementOScore()
    {
        oWins++;
        player2Score.setText(Integer.toString(oWins));
        vibrate();
    }

    public void vibrate()
    {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("com.example.zachl.tcubed", Context.MODE_PRIVATE);

        String rumbleOffKey = "com.example.zachl.tcubed.noRumbleKey";

        try {
            if (Build.VERSION.SDK_INT >= 26 && !prefs.getBoolean(rumbleOffKey, false)) {
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(500, 255));
            } else {
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(150);
            }
        }
        catch(java.lang.NullPointerException e)
        {
            Log.d(TAG, "NullPointerException at Vibrator call");
        }
    }

    public String getPlayerXName()
    {
        return playerXName;
    }

    public String getPlayerOName()
    {
        return playerOName;
    }
}