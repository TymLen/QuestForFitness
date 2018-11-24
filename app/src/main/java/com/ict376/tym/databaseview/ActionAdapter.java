package com.ict376.tym.databaseview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//Purpose: A custom adapter to show an image and text in a listview
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018

public class ActionAdapter extends BaseAdapter { //https://stackoverflow.com/questions/32573800/android-how-to-add-icon-on-each-listview-list-item-and-change-the-text-color-bac
    private Context mContext;
    private String [] action;
    private int[] icon;

    public ActionAdapter(Context context, String [] inText, int[] inImage){
        mContext = context;
        action = inText;
        icon = inImage;
    }

    public int getCount(){
        return action.length;
    }
    public Object getItem(int arg0){
        return null;
    }
    public long getItemId(int position){
        return position;
    }
    public View getView(int position, View v, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Create and inflate each row
        View row;
        row = inflater.inflate(R.layout.single_image_row, parent, false);
        TextView actionText;
        ImageView actionIcon;
        actionIcon = row.findViewById(R.id.listIcon);
        actionText = row.findViewById(R.id.listText);
        actionIcon.setImageResource(icon[position]);
        actionText.setText(action[position]);
        return(row);
    }
}
