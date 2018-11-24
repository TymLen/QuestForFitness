package com.ict376.tym.databaseview;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//Purpose: Host Activity to store the level details frag in portrait
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018

public class LogBookDetailsHost extends AppCompatActivity {
    LogBookDetailFrag detail;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_host);
        FragmentManager fragMan = getFragmentManager();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
        }
        if(getIntent()!= null){
            if(getIntent().getExtras()!=null){
                index = getIntent().getExtras().getInt("index", 0);
            }
        }
        if(fragMan.findFragmentByTag("details") != null){                           //Some of this is attempts to fix the bug by setting intended boolean
            detail = (LogBookDetailFrag)fragMan.findFragmentByTag("details");
            detail.setIntended(true);
            fragMan.beginTransaction().remove(detail).commit();
            detail.setIntended(false);
        }else{
            detail = LogBookDetailFrag.newInstance(index);
            detail.setIntended(true);
            fragMan.beginTransaction().add(R.id.mainFrag_container, detail, "details").commit();
            detail.setIntended(false);
        }

    }
    @Override
    public void onBackPressed(){
        detail.setIntended(true);
        super.onBackPressed();
    }
    @Override
    public void onStop(){
        if(isChangingConfigurations()){
            Intent result = new Intent();
            result.putExtra("index", index);
            setResult(RESULT_OK);
            finish();
        }
        super.onStop();
    }
}
