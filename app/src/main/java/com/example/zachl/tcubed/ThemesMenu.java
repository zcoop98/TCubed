package com.example.zachl.tcubed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThemesMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes_menu);
    }

    // Intent for backgrounds button
    public void backgrounds (View view) {

    }

    // Intent for icons button
    public void icons (View view) {

    }

    // Intent for themes button
    public void themes (View view) {

    }

    // Intent for Back Button
    public void back(View view) {
        Intent main_menu = new Intent(this, MainActivity.class);
        startActivity(main_menu);
    }
}
