package com.ict376.tym.databaseview;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
//Purpose: Main menu fragment that displays main menu options
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018

public class MainMenuFrag extends Fragment {
    private Button mLogbook, mCheckin, mLevelup, mExit;
    private EditText mDate;
    private TextView mTitleText;
    private HeroDBProvider heroDB;
    private boolean intended;
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static MainMenuFrag newInstance() {

        MainMenuFrag fragment = new MainMenuFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        intended = false;
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getContext(), BGMusic.class);
        intent.putExtra("rawID", R.raw.inspiration);
        getContext().startService(intent);
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

        Intent intent = new Intent(getContext(), BGMusic.class);
        intent.putExtra("rawID", R.raw.inspiration);
        getContext().startService(intent);
        intended = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated (View v, Bundle savedInstanceState){
        heroDB = new HeroDBProvider(getActivity());
        if(getView()!= null){
            mDate = getView().findViewById(R.id.debugDate);
        }
        Date cur = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat(DATE_FORMAT);                  //Gets current date and shows it in the debug box so date can be more easily altered
        mDate.setText(dateForm.format(cur));
        mTitleText = getView().findViewById(R.id.TitleText);
        mTitleText.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Enchanted Land DEMO.otf")); //https://stackoverflow.com/questions/27588965/how-to-use-custom-font-in-a-project-written-in-android-studio
        mCheckin = getView().findViewById(R.id.CheckinBut);
        mCheckin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!heroDB.checkDate(mDate.getText().toString())){
                    if(heroDB.checkExp()){
                        intended = true;
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), CheckinHost.class);
                        intent.putExtra("advDate", mDate.getText().toString());
                        startActivity(intent);
                    }
                    else{
                        Toast toast = Toast.makeText(getContext(), "You can level up", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else{
                    Toast toast = Toast.makeText(getContext(), "Adventure again tomorrow", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        mLevelup = getView().findViewById(R.id.LevelUpBut);
        mLevelup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!heroDB.checkExp()){
                    intended = true;
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LevelupHost.class);
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(getContext(), "Need more experience", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        mLogbook = getView().findViewById(R.id.LogbookBut);
        mLogbook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(heroDB.numberOfLevels() <= 0){
                    Toast toast = Toast.makeText(getContext(), "You need to level first", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    intended = true;
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LogBookHost.class);
                    startActivity(intent);
                }
            }
        });
        mExit = getView().findViewById(R.id.ExitBut);
        mExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.exit(0);
            }
        });
    }
}
