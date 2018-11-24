package com.ict376.tym.databaseview;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Purpose: Fragment to receive weight value from user then updates host activity with weight
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
/**
 * A simple {@link Fragment} subclass.
 */
public class CheckinWeightFrag extends Fragment {
    Button mSubmit;
    EditText mWeight;
    HeroDBProvider heroDB;
    OnHeadlineSelectedListener mCallback;

    public CheckinWeightFrag() {
        // Required empty public constructor
    }
    public interface OnHeadlineSelectedListener { //Interface for interacting with host activity
        void continueStory(int part);
        void setWeight(float inWeight);
    }
    public static CheckinWeightFrag NewInstance(){
        CheckinWeightFrag fragment = new CheckinWeightFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context){ //Set up interface
        super.onAttach(context);
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkin, container, false);
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){ //Get weight value and send it back to activity then continue story
        heroDB = new HeroDBProvider(getActivity());
        if(getView() != null){
            mWeight = getView().findViewById(R.id.EnterWeight);
            mSubmit = getView().findViewById(R.id.SubmitBut);
            mSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mWeight.getText().toString().isEmpty()){
                        Toast toast = Toast.makeText(getContext(), "Weight must be a number", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        float weight = Float.parseFloat(mWeight.getText().toString());
                        if(weight <= 0 | weight == 1){
                            Toast toast = Toast.makeText(getContext(), "Weight must be positive", Toast.LENGTH_SHORT);
                            toast.show();
                        }else{
                            mCallback.continueStory(1);
                            mCallback.setWeight(weight);
                            FragmentManager fragMan = getFragmentManager();
                            CheckinActionFrag actFrag = CheckinActionFrag.NewInstance();
                            fragMan.beginTransaction().replace(R.id.secFrag_container, actFrag).commit();
                        }
                    }
                }
            });
        }
    }
}
