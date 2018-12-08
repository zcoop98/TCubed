package com.example.zachl.tcubed;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThemesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes_list);
    }

    public void back(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }

    //TODO: Add in links to real themes

    // Intent for Theme 1
    public void Theme1(View view) {

    }

    // Intent for Theme 2
    public void Theme2(View view) {

    }

    // Intent for Theme 3
    public void Theme3(View view) {

    }

    // Intent for Theme 4
    public void Theme4(View view) {

    }

    // Intent for Theme 5
    public void Theme5(View view) {

    }

    // Intent for Theme 6
    public void Theme6(View view) {

    }

    // Intent for Theme 7
    public void Theme7(View view) {

    }

    // Intent for Theme 8
    public void Theme8(View view) {

    }

    // Intent for Theme 9
    public void Theme9(View view) {

    }

    // Intent for Theme 10
    public void Theme10(View view) {

    }
}
