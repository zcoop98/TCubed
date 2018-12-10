package com.example.zachl.tcubed;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

//Game code tutorial: https://medium.com/@ssaurel/learn-to-create-a-tic-tac-toe-game-for-android-82c7bf2369de

public class MainActivity extends AppCompatActivity {
    Dialog myDialog;
    private static final String TAG = "MainActivity";
    private static final String MUTE_KEY = "com.example.zachl.tcubed.muteKey";
    private static final String NO_RUMBLE_KEY = "com.example.zachl.tcubed.noRumbleKey";
    private static final String sharedPrefFile = "com.example.zachl.tcubed.sharedPrefs";
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        mPreferences = getApplicationContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
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

        Switch muteSwitch = myDialog.findViewById(R.id.muteSwitch);
        Switch noRumbleSwitch = myDialog.findViewById(R.id.noRumbleSwitch);

        muteSwitch.setChecked(mPreferences.getBoolean(MUTE_KEY, false));
        noRumbleSwitch.setChecked(mPreferences.getBoolean(NO_RUMBLE_KEY, false));

        Log.d(TAG, "Starting muteSwitch listener");

        muteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPreferences.edit().putBoolean(MUTE_KEY, isChecked).apply();
                Log.d(TAG, "Updated Mute");
            }
        });

        Log.d(TAG, "Starting rumbleSwitch listener");

        noRumbleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPreferences.edit().putBoolean(NO_RUMBLE_KEY, isChecked).apply();
                Log.d(TAG, "Updated Rumble");
            }
        });

        myDialog.show();    //Show popup
    }
}