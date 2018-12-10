package com.example.zachl.tcubed;

import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IconsList extends AppCompatActivity {

    private static final String sharedPrefFile = "com.example.zachl.tcubed.sharedPrefs";
    private SharedPreferences mPreferences;
    private static final String ICON_KEY = "com.example.zachl.tcubed.iconKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icons_list);

        mPreferences = getApplicationContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }

    public void back(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }

    // Intent for Icon 1
    public void defaultIcon(View view) {
        mPreferences.edit().putInt(ICON_KEY, 0).apply();
    }

    // Intent for Icon 2
    public void bakeryIcon(View view) {
        mPreferences.edit().putInt(ICON_KEY, 1).apply();
    }

    // Intent for Icon 3
    public void christmasIcon(View view) {
        mPreferences.edit().putInt(ICON_KEY, 2).apply();
    }

    // Intent for Icon 4
    public void galaxyIcon(View view) {
        mPreferences.edit().putInt(ICON_KEY, 3).apply();
    }

    // Intent for Icon 5
    public void halloweenIcon(View view) {
        mPreferences.edit().putInt(ICON_KEY, 4).apply();
    }
}
