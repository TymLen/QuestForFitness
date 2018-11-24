package com.ict376.tym.databaseview;

import android.app.FragmentManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

//Purpose: Host Activity to store the main menu
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
public class MainMenuHost extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //https://stackoverflow.com/questions/4149415/onscreen-keyboard-opens-automatically-when-activity-starts
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(getResources().getString(R.string.MainMenu));
        }
        FragmentManager fragMan = getFragmentManager();
        MainMenuFrag mainmenu = MainMenuFrag.newInstance();
        fragMan.beginTransaction().add(R.id.mainFrag_container, mainmenu).commit();
    }
}
