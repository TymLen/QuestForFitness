package com.ict376.tym.databaseview;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//Purpose: Level up Frag that shows the "weekly" statistics and allows user to go to photo frag or skip photo
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
/**
 * A simple {@link Fragment} subclass.
 */
public class LevelUpFrag extends Fragment {
    HeroDBProvider heroDB;
    private boolean intended;
    TextView mLevels, mStrStat, mDexStat, mRestStat, mDateStart, mDateEnd, mWeightChange;
    Button mSkipPhoto, mRecordLevel;
    int str = 0, dex = 0, rest = 0, level = 0;
    String feat, startDate, endDate;

    public LevelUpFrag() {
        // Required empty public constructor
    }
    public static LevelUpFrag NewInstance(){
        LevelUpFrag fragment = new LevelUpFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent intent = new Intent(getContext(), BGMusic.class);
        intent.putExtra("rawID", R.raw.epicsong);
        getContext().startService(intent);
        intended = false;
        return inflater.inflate(R.layout.fragment_character, container, false);
    }

    @Override
    public void onPause(){
        super.onPause();
        if(!intended){
            if(!getActivity().isChangingConfigurations()){
                Intent intent = new Intent(getContext(), BGMusic.class);
                getContext().stopService(intent);
            }
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        if(!intended){
            Intent intent = new Intent(getContext(), BGMusic.class);
            intent.putExtra("rawID", R.raw.epicsong);
            getContext().startService(intent);
        }
        else{
            intended = true;
        }
    }

    @Override
    public void onViewCreated(View v, Bundle args){
        heroDB = new HeroDBProvider(getActivity());

        mLevels = v.findViewById(R.id.LevelChange);
        mStrStat = v.findViewById(R.id.StrStat);
        mDexStat = v.findViewById(R.id.DexStat);
        mRestStat = v.findViewById(R.id.RestStat);
        mDateStart = v.findViewById(R.id.StartDate);
        mDateEnd = v.findViewById(R.id.EndDate);
        mRecordLevel = v.findViewById(R.id.PhotoButton);
        mWeightChange = v.findViewById(R.id.WeightChange);

        Cursor res = heroDB.getCurrentRecords();
        res.moveToFirst();
        float weight = res.getInt(res.getColumnIndex("weight"));
        startDate = res.getString(res.getColumnIndex("date"));
        mDateStart.setText(getString(R.string.FirstDate, startDate));
        level = res.getInt(res.getColumnIndex("level"));
        while(!res.isAfterLast()){                                      //Gathering and displaying data
            feat = res.getString(res.getColumnIndex("feat"));
            switch(feat){
                case "Use Upper Body":
                    str++;
                    break;
                case "Use Lower Body":
                    dex++;
                    break;
                case "Retreat":
                    rest++;
                    break;
                default:
                    break;
            }
            res.moveToNext();
        }
        res.moveToLast();
        endDate = res.getString(res.getColumnIndex("date"));
        weight = weight - res.getInt(res.getColumnIndex("weight"));
        String textWeight = "";
        if(weight <0){
            textWeight = "+"+Math.abs(weight);
        }else{
            textWeight = "-"+Math.abs(weight);
        }
        mWeightChange.setText(getString(R.string.WeightChanged, textWeight));
        mDateEnd.setText(getString(R.string.LastDate, endDate));
        mStrStat.setText(getString(R.string.StrUsed, str));
        mDexStat.setText(getString(R.string.DexUsed, dex));
        mRestStat.setText(getString(R.string.RestUsed, rest));
        int newlevel = level+1;
        mLevels.setText(getString(R.string.LevelChange, newlevel));
        res.close();
        View detailsFrame = getActivity().findViewById(R.id.secFrag_container);
        if(detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE){
            LevelUpPhotoFrag photoFrag = LevelUpPhotoFrag.NewInstance(startDate, endDate, level);
            getActivity().getFragmentManager().beginTransaction().replace(R.id.secFrag_container, photoFrag).commit();
            mRecordLevel.setVisibility(View.INVISIBLE);
            mRecordLevel.setEnabled(false);
        }
        mRecordLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intended = true;
                Intent intent = new Intent();
                intent.setClass(getActivity(), LevelUpPhotoHost.class);
                intent.putExtra("level", level);
                intent.putExtra("start", startDate);
                intent.putExtra("end", endDate);
                startActivity(intent);
            }
        });
        mSkipPhoto = v.findViewById(R.id.skipPhotoBut);
        mSkipPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){                     //Adds null photo to level up record and closes fragment/activity
                Handler handle = new Handler();
                handle.post(new Runnable(){
                    @Override
                    public void run(){
                        heroDB = new HeroDBProvider(getActivity());
                        byte[] compressed = null;
                        heroDB.newLevelup(startDate, endDate, level, compressed);
                        getActivity().finish();
                    }
                });
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK){
            getActivity().finish();
        }
    }
}
