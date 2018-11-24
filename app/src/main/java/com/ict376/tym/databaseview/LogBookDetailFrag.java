package com.ict376.tym.databaseview;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.List;

//Purpose: Fragment that displays a levels details
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
public class LogBookDetailFrag extends Fragment {
    TextView mDLevel, mDateRange, mStrCounter, mDexCounter, mRestCounter;
    int index = 0;
    private boolean intended = true;
    HeroDBProvider heroDB;
    ImageView mPhotoBox, mStrShape, mDexShape, mRestShape;
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public LogBookDetailFrag() {

    }

    public static LogBookDetailFrag newInstance(int newIndex){
        LogBookDetailFrag fragment = new LogBookDetailFrag();
        Bundle args = new Bundle();
        args.putInt("index", newIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        index = args.getInt("index", 0);
        if(getActivity().findViewById(R.id.secFrag_container) != null){
            intended = true;
        }
        if(container == null){
            return null;
        }
        return inflater.inflate(R.layout.fragment_level_detail, container, false);
    }
    @Override
    public void onPause(){
        super.onPause();
        if(!intended){
            if(!getActivity().isChangingConfigurations()){
                if(!getActivity().isFinishing()){
                    Intent intent = new Intent(getContext(), BGMusic.class);
                    getContext().stopService(intent);
                }
            }
        }
    }
    public void setIntended(boolean inIntend){
        intended = inIntend;
    }
    @Override
    public void onResume(){
        super.onResume();
        if(!intended){
            Intent intent = new Intent(getContext(), BGMusic.class);
            intent.putExtra("rawID", R.raw.passingtime);
            getContext().startService(intent);
        }
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        heroDB = new HeroDBProvider(getActivity());
        updateFrag(index);
    }

    public void updateFrag(int inIndex){ //Used to update the current frag rather than replaces the fragment
        if(getView() != null){
            index = inIndex;
            mDLevel = getView().findViewById(R.id.DLevel);
            mPhotoBox = getView().findViewById(R.id.PhotoView);
            mDateRange = getView().findViewById(R.id.DateRange);
            Cursor res = heroDB.getLevelRecords(index+1);
            res.moveToFirst();
            List<Integer> dp = new ArrayList<>();
            dp.clear();
            LineGraphSeries<DataPoint> weight = new LineGraphSeries<>(new DataPoint[]{}); //Create datapoints on the graph
            PointsGraphSeries<DataPoint> strength = new PointsGraphSeries<>(new DataPoint[]{});
            PointsGraphSeries<DataPoint> dex = new PointsGraphSeries<>(new DataPoint[]{});
            PointsGraphSeries<DataPoint> rest = new PointsGraphSeries<>(new DataPoint[]{});
            int i = 1;
            int strCount = 0;
            int dexCount = 0;
            int restCount = 0;
            List<String> dates = new ArrayList<String>(){};
            dates.clear();
            mDLevel.setText(getString(R.string.FitnessLevel, res.getInt(res.getColumnIndex("level"))));
            while(!res.isAfterLast()){                  //Gather data from Cursor and assigns them datapoints
                if(res.getString(res.getColumnIndex("feat")).equals("Use Upper Body")){
                    strength.appendData(new DataPoint(i, res.getFloat(res.getColumnIndex("weight"))), false, 8);
                    strCount++;
                }
                if(res.getString(res.getColumnIndex("feat")).equals("Use Lower Body")) {
                    dex.appendData(new DataPoint(i, res.getFloat(res.getColumnIndex("weight"))), false, 8);
                    dexCount++;
                }
                if(res.getString(res.getColumnIndex("feat")).equals("Retreat")) {
                    rest.appendData(new DataPoint(i, res.getFloat(res.getColumnIndex("weight"))), false, 8);
                    restCount++;
                }
                weight.appendData(new DataPoint(i, res.getFloat(res.getColumnIndex("weight"))), false, 8);
                dp.add(res.getInt(res.getColumnIndex("weight")));

                dates.add(res.getString(res.getColumnIndex("date")));
                res.moveToNext();
                i++;
            }
            while(dp.size() < 8){
                dp.add(0);
            }
            mStrCounter = getView().findViewById(R.id.StrCounter);
            mStrCounter.setText(getString(R.string.StrUsed, strCount));
            mDexCounter = getView().findViewById(R.id.DexCounter);
            mDexCounter.setText(getString(R.string.DexUsed, dexCount));
            mRestCounter = getView().findViewById(R.id.RestCounter);
            mRestCounter.setText(getString(R.string.RestUsed, restCount));
            mDateRange.setText(getString(R.string.DateRange,dates.get(0), dates.get(dates.size()-1)));
            Bundle args = getArguments();
            index = args.getInt("index", 0);
            if(heroDB.getPhoto(index+1) != null){ //Checks that there is a photo and if not doesn't display anything
                byte[] heroByte = heroDB.getPhoto(index+1);
                Bitmap heroPhoto = BitmapFactory.decodeByteArray(heroByte, 0, heroByte.length);
                mPhotoBox.setImageBitmap(heroPhoto);
            }
            GraphView graph = getView().findViewById(R.id.weightGraph);
            graph.getGridLabelRenderer().setNumHorizontalLabels(7); //Sets number of labels
            strength.setColor(Color.RED);                           //Sets shape/color/size of points representing activities
            strength.setShape(PointsGraphSeries.Shape.RECTANGLE);
            strength.setSize(15);
            dex.setColor(Color.GREEN);
            dex.setShape(PointsGraphSeries.Shape.TRIANGLE);
            dex.setSize(15);
            rest.setColor(Color.BLUE);
            rest.setSize(15);
            weight.setColor(Color.BLACK);
            graph.addSeries(weight);            //Adds line then the point datapoints to the graph
            graph.addSeries(strength);
            graph.addSeries(dex);
            graph.addSeries(rest);
            res.close();
        }
    }
}
