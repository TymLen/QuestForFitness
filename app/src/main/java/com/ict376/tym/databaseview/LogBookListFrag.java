package com.ict376.tym.databaseview;

import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;
//Purpose: ListFragment that shows all current levels in the database
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018

public class LogBookListFrag extends ListFragment {
    private HeroDBProvider heroDB;
    private boolean intended;
    private int index;
    private ListView mLevelList;


    public LogBookListFrag() {

    }

    @Override
    public void onPause(){
        super.onPause();
        if(!intended) {
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
            intent.putExtra("rawID", R.raw.passingtime);
            getContext().startService(intent);
        }
        if(intended){
            intended = false;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(savedInstanceState != null){
            index = savedInstanceState.getInt("index", 0);
            intended = savedInstanceState.getBoolean("intended", false);
            if(getActivity().findViewById(R.id.secFrag_container) != null){
                LogBookDetailFrag frag = LogBookDetailFrag.newInstance(index);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.secFrag_container, frag).commit();
            }
        }
        return inflater.inflate(R.layout.fragment_level_list, container, false);
    }
    @Override
    public void onSaveInstanceState (Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putInt("index", index);
        bundle.putBoolean("intended", intended);
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        Intent intent = new Intent(getContext(), BGMusic.class);
        intent.putExtra("rawID", R.raw.passingtime);
        getContext().startService(intent);
        heroDB = new HeroDBProvider(getActivity());
        mLevelList = v.findViewById(R.id.LevelList);
        mLevelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //If second frame exists put fragment there otherwise start host activity
                LogBookDetailFrag frag = LogBookDetailFrag.newInstance(i);
                index = i;
                View detailsFrame = getActivity().findViewById(R.id.secFrag_container);
                if(detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE){
                    frag.setIntended(true);
                    getActivity().getFragmentManager().beginTransaction().replace(R.id.secFrag_container, frag).commit();
                }
                else if(detailsFrame == null){
                    intended = true;
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LogBookDetailsHost.class);
                    intent.putExtra("index", i);
                    startActivity(intent);
                }
            }
        });
        List<String> levels = new ArrayList<>();
        Cursor res = heroDB.getLevels();
        res.moveToFirst();
        while(!res.isAfterLast()){
            levels.add("Level "+res.getInt(res.getColumnIndex("number")));
            res.moveToNext();
        }
        res.close();
        if(getView() != null){
            if(getView().getContext() != null){
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(), R.layout.single_paper_row, R.id.listText, levels);
                mLevelList.setAdapter(adapter);
            }
        }
    }
}
