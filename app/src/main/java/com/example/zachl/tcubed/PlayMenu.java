package com.example.zachl.tcubed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class PlayMenu extends AppCompatActivity {
    public static final String XNAME = "com.zachl.tcubed.XNAME";
    public static final String ONAME = "com.zachl.tcubed.ONAME";
    public static final String PVPSWITCH = "com.zachl.tcubed.PVPSWITCH";
    public static final String XOSWITCH = "com.zachl.tcubed.XOSWITCH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);
    }

    // Intent for play button
    public void play (View view) {
       Intent game_board = new Intent(this, GameBoard.class);

       EditText editText = (EditText) findViewById(R.id.editText);
       EditText editText2 = (EditText) findViewById(R.id.editText2);
       Switch pvpSwitch = (Switch) findViewById(R.id.switch2);
       Switch xGoesSwitch = (Switch) findViewById(R.id.switch3);

       boolean playerVCPU = !pvpSwitch.isChecked();
       boolean xGoesFirst = !xGoesSwitch.isChecked();
       String player1Name = editText.getText().toString();
       String player2Name = editText2.getText().toString();

       game_board.putExtra(XNAME, player1Name);
       game_board.putExtra(ONAME, player2Name);
       game_board.putExtra(PVPSWITCH, playerVCPU);
       game_board.putExtra(XOSWITCH, xGoesFirst);

       startActivity(game_board);
    }

    // Intent for Back Button
    public void back(View view) {
        Intent main_menu = new Intent(this, MainActivity.class);
        startActivity(main_menu);
    }
}
