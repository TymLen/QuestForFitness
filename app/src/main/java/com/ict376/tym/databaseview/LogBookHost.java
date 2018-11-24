package com.ict376.tym.databaseview;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Purpose: Host Activity to the List fragment
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
public class LogBookHost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        FragmentManager fragMan = getFragmentManager();

        if(getSupportActionBar() !=null){
            getSupportActionBar().setTitle(getResources().getString(R.string.Logbook));
        }
        if(fragMan.findFragmentById(android.R.id.content) == null){
            LogBookListFrag listLevels = new LogBookListFrag();
            fragMan.beginTransaction().add(R.id.mainFrag_container, listLevels).commit();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result){
        if(resultCode == Activity.RESULT_OK){
            if(findViewById(R.id.secFrag_container) != null){
                if(result.getExtras()!= null){
                    int index = result.getIntExtra("index", 0);
                    FragmentManager fragMan = getFragmentManager();
                    LogBookDetailFrag detail = LogBookDetailFrag.newInstance(index);
                    fragMan.beginTransaction().add(R.id.secFrag_container, detail).commit();
                }
            }
        }
    }
}
