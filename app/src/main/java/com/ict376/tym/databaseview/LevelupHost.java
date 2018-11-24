package com.ict376.tym.databaseview;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

//Purpose: Host Activity to host LevelUpFrag
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
public class LevelupHost extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelup_host);
        FragmentManager fragMan = getFragmentManager();
        LevelUpFrag charFrag = LevelUpFrag.NewInstance();
        fragMan.beginTransaction().add(R.id.mainFrag_container, charFrag).commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            finish();
        }
    }
}
