package com.ict376.tym.databaseview;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;

//Purpose: Host Activity to store the photo frag in portrait mode
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
public class LevelUpPhotoHost extends Activity {
    LevelUpPhotoFrag levelFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_host);
        FragmentManager fragMan = getFragmentManager();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
        }
        if(getIntent() != null){
            if(getIntent().getExtras() != null){
                String start = getIntent().getStringExtra("start");
                String end = getIntent().getStringExtra("end");
                int level = getIntent().getIntExtra("level", 0);
                levelFrag = LevelUpPhotoFrag.NewInstance(start, end, level);
                fragMan.beginTransaction().add(R.id.mainFrag_container, levelFrag).commit();
            }
        }
    }
    @Override
    public void onBackPressed(){
        levelFrag.setIntended(true);
        super.onBackPressed();
    }
}
