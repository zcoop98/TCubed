package com.example.zachl.tcubed;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BackgroundsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgrounds_list);
    }

    public void back(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }

    // Intent for Background 1
    public void Background1(View view) {

    }

    // Intent for Background 2
    public void Background2(View view) {

    }

    // Intent for Background 3
    public void Background3(View view) {

    }

    // Intent for Background 4
    public void Background4(View view) {

    }

    // Intent for Background 5
    public void Background5(View view) {

    }

    // Intent for Background 6
    public void Background6(View view) {

    }

    // Intent for Background 7
    public void Background7(View view) {

    }

    // Intent for Background 8
    public void Background8(View view) {

    }

    // Intent for Background 9
    public void Background9(View view) {

    }

    // Intent for Background 10
    public void Background10(View view) {

    }
}
