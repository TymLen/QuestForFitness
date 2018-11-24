package com.ict376.tym.databaseview;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//Purpose: A fragment to display a list of actions representing exercise for preparing to add record to Database
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
/**
 * A simple {@link Fragment} subclass.
 */
public class CheckinActionFrag extends ListFragment {
    String[] actions = {"Use Upper Body", "Use Lower Body", "Retreat"};
    String[] options = {"Record Adventure", "Adventure Again", "Exit"};
    int []drawableIds = {R.drawable.biceps, R.drawable.hoodedfigure, R.drawable.sleepy};
    String selectAction;
    ListView mOptionList;

    CheckinActionFrag.OnHeadlineSelectedListener mCallback;


    public CheckinActionFrag() {
        // Required empty public constructor
    }
    public interface OnHeadlineSelectedListener { //Allows fragment to call methods from activity
        void continueStory(int part);
        void setAction(String action);
        void restart();
        void recordAdventure();
    }
    @Override
    public void onAttach(Context context){ //Sets up the interface
        super.onAttach(context);
        try {
            mCallback = (CheckinActionFrag.OnHeadlineSelectedListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
    }
    public static CheckinActionFrag NewInstance(){
        CheckinActionFrag fragment = new CheckinActionFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_action, container, false);
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){

        if(savedInstanceState != null){
            selectAction = savedInstanceState.getString("action", "");
        }
        mOptionList=v.findViewById(R.id.OptionList);
        if(!TextUtils.isEmpty(selectAction)){
            ExitOptions();
        }else{
            ActionAdapter actAdapt = new ActionAdapter(getContext(), actions, drawableIds); //sets up custom adapter ActionAdapter then sends the clicked index back to activity and continues story
            mOptionList.setAdapter(actAdapt);
            mOptionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    selectAction = actions[i];
                    mCallback.setAction(selectAction);
                    mCallback.continueStory(2);
                    ExitOptions();
                }
            });
        }
    }
    public void ExitOptions(){ //Normal adapter that just gives user choice of recording/restarting/exiting activity.
        if(getView() != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(), R.layout.single_black_row, R.id.listText, options);
            mOptionList.setAdapter(adapter);
            mOptionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //String[] options = {"Record Adventure", "Adventure Again", "Exit"};
                    if(i == 1){
                        mCallback.restart();
                    }
                    else if(i == 0){
                        mCallback.recordAdventure();
                        getActivity().finish();
                    }
                    else if(i == 2){
                        getActivity().finish();
                    }
                }
            });
        }
    }
    @Override
    public void onSaveInstanceState (Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString("action", selectAction);
    }
}
