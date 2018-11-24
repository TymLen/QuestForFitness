package com.ict376.tym.databaseview;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

//Purpose: Photo Frag that handles taking and storing photos
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018
/**
 * A simple {@link Fragment} subclass.
 */
public class LevelUpPhotoFrag extends Fragment {
    private Button mPhotoBut, mConfirmLevel;
    private boolean intended;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView mPhotoView;
    private Bitmap imageBitmap;
    private HeroDBProvider heroDB;
    private MediaPlayer bgMusic;
    private String start, end;
    private int level;
    private byte[] compressed;

    public LevelUpPhotoFrag() {
        // Required empty public constructor
    }

    public static LevelUpPhotoFrag NewInstance(String inStart, String inEnd, int level){
        LevelUpPhotoFrag fragment = new LevelUpPhotoFrag();
        Bundle args = new Bundle();
        args.putString("start", inStart);
        args.putString("end", inEnd);
        args.putInt("level", level);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        intended = false;
        Intent intent = new Intent(getContext(), BGMusic.class);
        intent.putExtra("rawID", R.raw.epicsong);
        getContext().startService(intent);
        return inflater.inflate(R.layout.fragment_level_up, container, false);
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
    public void setIntended(boolean inIntend){
        intended = inIntend;
    } //This is to ensure music stops when put to background but isn't interrupted in normal operation
    @Override
    public void onResume(){
        super.onResume();
        if(!intended){
            Intent intent = new Intent(getContext(), BGMusic.class);
            intent.putExtra("rawID", R.raw.epicsong);
            getContext().startService(intent);
        }
    }

    @Override
    public void onViewCreated(View v, Bundle args){
        start = getArguments().getString("start");
        end = getArguments().getString("end");
        level = getArguments().getInt("level");
        heroDB = new HeroDBProvider(getActivity());
        mPhotoBut = v.findViewById(R.id.TakePhoto);
        mPhotoView = v.findViewById(R.id.PhotoView);
        mPhotoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //Starts the camera activity (external)
                if(intentPic.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivityForResult(intentPic, REQUEST_IMAGE_CAPTURE);
                }
                mPhotoBut.setText(getString(R.string.RetakePhoto));
            }
        });
        mConfirmLevel = v.findViewById(R.id.ReturnPhoto);
        mConfirmLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handle = new Handler();
                handle.post(new Runnable(){
                    @Override
                    public void run(){
                        ByteArrayOutputStream outStream = new ByteArrayOutputStream();                      //https://stackoverflow.com/questions/11790104/how-to-storebitmap-image-and-retrieve-image-from-sqlite-database-in-android
                        imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, outStream);
                        compressed = outStream.toByteArray();
                        heroDB.newLevelup(start, end, level, compressed);
                        byte[] feedback = heroDB.getPhoto(heroDB.numberOfLevels());
                        Bitmap showMe = BitmapFactory.decodeByteArray(feedback, 0, feedback.length);
                        mPhotoView.setImageBitmap(showMe);
                    }
                });
                Intent result = new Intent();
                result.setClass(getContext(), MainMenuHost.class); //https://stackoverflow.com/questions/5794506/android-clear-the-back-stack
                result.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(result);
                getActivity().finish();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){ //Result of camera
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            if(extras != null){
                imageBitmap = (Bitmap) extras.get("data");
            }
            mPhotoView.setImageBitmap(imageBitmap);
        }
    }
}
