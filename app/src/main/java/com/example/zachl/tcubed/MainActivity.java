package com.example.zachl.tcubed;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import static com.example.zachl.tcubed.R.id.board;

//Game code tutorial: https://medium.com/@ssaurel/learn-to-create-a-tic-tac-toe-game-for-android-82c7bf2369de

public class MainActivity extends AppCompatActivity {
    Dialog myDialog;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);
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
    public void mainSettings(View view) { //https://awsrh.blogspot.com/2017/10/custom-pop-up-window-with-android-studio.html
        ImageButton imageButton;
        myDialog.setContentView(R.layout.main_menu_popup);
        imageButton = myDialog.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        if (myDialog.getWindow() != null)   //Safety net to settle a warning
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Log.d(TAG, "Storing prefs");
        SharedPreferences prefs = this.getSharedPreferences("com.example.zachl.tcubed", Context.MODE_PRIVATE);

        Log.d(TAG, "Completed pref store");

        String muteKey = "com.example.zachl.tcubed.muteKey";
        String noRumbleKey = "com.example.zachl.tcubed.noRumbleKey";

        Log.d(TAG, "Stored keys");

        boolean mute = prefs.getBoolean(muteKey, false);
        boolean rumbleOff = prefs.getBoolean(noRumbleKey, false);

        Log.d(TAG, "Stored switch vals from prefs");

        Switch muteSwitch = findViewById(R.id.muteSwitch);
        Switch noRumbleSwitch = findViewById(R.id.noRumbleSwitch);

        if (muteSwitch != null && noRumbleSwitch != null)
        {
            Log.d(TAG, "Stored switch objects");

            muteSwitch.setChecked(mute);
            noRumbleSwitch.setChecked(rumbleOff);

            Log.d(TAG, "Set dialog switch vals");

            Log.d(TAG, "Starting muteSwitch listener");

            muteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    muteChange(isChecked);
                }
            });

            Log.d(TAG, "Starting rumbleSwitch listener");

            noRumbleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    rumbleChange(isChecked);
                }
            });

        }

        myDialog.show();    //Show popup
    }

    public void muteChange(boolean changedTo)
    {
        Log.d(TAG, "muteChange run");

        SharedPreferences prefs = this.getSharedPreferences("com.example.zachl.tcubed", Context.MODE_PRIVATE);

        String muteKey = "com.example.zachl.tcubed.muteKey";

        prefs.edit().putBoolean(muteKey, changedTo).apply();
    }

    public void rumbleChange(boolean changedTo)
    {
        Log.d(TAG, "rumbleChange running");

        SharedPreferences prefs = this.getSharedPreferences("com.example.zachl.tcubed", Context.MODE_PRIVATE);

        String noRumbleKey = "com.example.zachl.tcubed.noRumbleKey";

        prefs.edit().putBoolean(noRumbleKey, changedTo).apply();
    }
}