package com.ict376.tym.databaseview;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;
//Purpose: Host Activity to store both the TextBoxFrag and Weight/Action Frag
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018

public class CheckinHost extends AppCompatActivity implements CheckinWeightFrag.OnHeadlineSelectedListener, CheckinActionFrag.OnHeadlineSelectedListener{
    private CheckinTextBoxFrag textBox;
    public float weight = 0;
    public String action = "";
    private String advDate;
    private HeroDBProvider heroDB;
    private NotificationCompat.Builder mBuilder;
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //https://stackoverflow.com/questions/4149415/onscreen-keyboard-opens-automatically-when-activity-starts
        setContentView(R.layout.activity_checkin_host);
        FragmentManager fragMan = getFragmentManager();
        CheckinWeightFrag frag = CheckinWeightFrag.NewInstance();
        if(getIntent() != null){
            Intent intent = getIntent();
            advDate = intent.getStringExtra("advDate");
        }else{
            Date cur = new Date();
            SimpleDateFormat dateForm = new SimpleDateFormat(DATE_FORMAT);
            advDate = dateForm.format(cur);
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(getResources().getString(R.string.CheckIn));
        }
        if(savedInstanceState != null){
            weight = savedInstanceState.getFloat("weight");
            action = savedInstanceState.getString("action");
            textBox = (CheckinTextBoxFrag)fragMan.findFragmentByTag("TextBox");
        }else{
            textBox = CheckinTextBoxFrag.NewInstance(weight, action);
            fragMan.beginTransaction().add(R.id.mainFrag_container, textBox, "TextBox").commit();
            fragMan.beginTransaction().add(R.id.secFrag_container, frag).commit();
        }
    }
    public void setWeight(float inWeight){
        this.weight = inWeight;
    }
    public void continueStory(int part){ //Updates textbox as story progresses
        if(part == 1){
            textBox.Battle();
        }
        if(part == 2){
            textBox.setWeight(weight);
            textBox.setAction(action);
            textBox.Victory();
        }
    }
    public void setAction (String inAction){
        action = inAction;
    }
    public void restart(){ //If restart option selected, restarts activity.
        textBox.stopHandler();
        finish();
        startActivity(getIntent());                                             //https://stackoverflow.com/questions/3053761/reload-activity-in-android
    }
    public void recordAdventure() { //attempts to record adventure and if successful increases experience.
        heroDB = new HeroDBProvider(this);
        boolean result = heroDB.insertRecord(action, weight, advDate);
        if(result) {
            heroDB.incExp();
        }
    }
    @Override
    public void finish(){
        textBox.stopHandler();
        super.finish();
    }
    @Override
    public void onSaveInstanceState (Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putFloat("weight", weight);
        bundle.putString("action", action);
    }
}
