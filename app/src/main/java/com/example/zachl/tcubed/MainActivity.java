package com.example.zachl.tcubed;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import static com.example.zachl.tcubed.R.id.board;

//Game code tutorial: https://medium.com/@ssaurel/learn-to-create-a-tic-tac-toe-game-for-android-82c7bf2369de

public class MainActivity extends AppCompatActivity {
    Dialog myDialog;

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
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        if (myDialog.getWindow() != null)
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

        //TODO: Make the settings do something (volume, rumble, etc)
    }
}