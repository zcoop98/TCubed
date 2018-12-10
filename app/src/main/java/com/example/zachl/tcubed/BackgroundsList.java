package com.example.zachl.tcubed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BackgroundsList extends AppCompatActivity {

    private static final String sharedPrefFile = "com.example.zachl.tcubed.sharedPrefs";
    private SharedPreferences mPreferences;
    private static final String BACKGROUND_KEY = "com.example.zachl.tcubed.backgroundKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgrounds_list);

        mPreferences = getApplicationContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }

    public void back(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }

    // Intent for Background 1
    public void defaultBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.Background).apply();
    }

    // Intent for Background 2
    public void whiteBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background01_white).apply();
    }

    // Intent for Background 3
    public void pinkBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background02_pink).apply();
    }

    // Intent for Background 4
    public void redBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background03_red).apply();
    }

    // Intent for Background 5
    public void orangeBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background04_orange).apply();
    }

    // Intent for Background 6
    public void yellowBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background05_yellow).apply();
    }

    // Intent for Background 7
    public void greenBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background06_green).apply();
    }

    // Intent for Background 8
    public void lightBlueBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background07_light_blue).apply();
    }

    // Intent for Background 9
    public void blueBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background08_dark_blue).apply();
    }

    // Intent for Background 10
    public void purpleBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background09_purple).apply();
    }

    public void grayBackground(View view) {
        mPreferences.edit().putInt(BACKGROUND_KEY, R.color.background10_gray).apply();
    }
}
