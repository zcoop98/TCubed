package com.example.zachl.tcubed;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ThemesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes_list);
    }

    public void back(View view) {
        NavUtils.navigateUpFromSameTask(this);
    }

    // Intent for Theme 1
    public void Theme1(View view) {
        statusMessage("Example Theme 1");
        back(view);
    }

    // Intent for Theme 2
    public void Theme2(View view) {
        statusMessage("Example Theme 2");
        back(view);
    }

    // Intent for Theme 3
    public void Theme3(View view) {
        statusMessage("Example Theme 3");
        back(view);
    }

    // Intent for Theme 4
    public void Theme4(View view) {
        statusMessage("Example Theme 4");
        back(view);
    }

    // Intent for Theme 5
    public void Theme5(View view) {
        statusMessage("Example Theme 5");
        back(view);
    }

    // Intent for Theme 6
    public void Theme6(View view) {
        statusMessage("Example Theme 6");
        back(view);
    }

    // Intent for Theme 7
    public void Theme7(View view) {
        statusMessage("Example Theme 7");
        back(view);
    }

    // Intent for Theme 8
    public void Theme8(View view) {
        statusMessage("Example Theme 8");
        back(view);
    }

    // Intent for Theme 9
    public void Theme9(View view) {
        statusMessage("Example Theme 9");
        back(view);
    }

    // Intent for Theme 10
    public void Theme10(View view) {
        statusMessage("Example Theme 10");
        back(view);
    }

    public void statusMessage(String icon)
    {
        Toast.makeText(getApplicationContext(), icon + " Selected", Toast.LENGTH_SHORT).show();
    }
}
