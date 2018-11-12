package com.example.zachl.tcubed;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DirectionsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_menu);
    }

    // Intent for Back Button
    public void back(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }
}
