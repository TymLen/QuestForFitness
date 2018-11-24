package com.ict376.tym.databaseview;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
//Purpose: A service that any activity can access that starts/stops background music
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018

public class BGMusic extends Service {
    private MediaPlayer BGMusic;
    private int mRawID = 0;

    public BGMusic(){

    }
    private void initializeMediaPlayer(){
        if(BGMusic == null){
            BGMusic = new MediaPlayer();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){ //Start command. Checks if song is playing and if not releases and starts the new song.
        if(mRawID == intent.getIntExtra("rawID", 0)){
        }else{
            release();
            mRawID = intent.getIntExtra("rawID", 0);
            loadMedia(mRawID);
            play();
        }
        return Service.START_NOT_STICKY;
    }
    @Override
    public void onDestroy(){
        release();
    }
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    public void loadMedia(int rawID){
        initializeMediaPlayer();
        BGMusic = MediaPlayer.create(getApplicationContext(), rawID);
    }

    public void play(){ //Set looping and plays the song
        if(BGMusic != null && !BGMusic.isPlaying()){
            BGMusic.setLooping(true);
            BGMusic.start();
        }
    }
    public boolean isPlaying(){
        if(BGMusic != null){
            return BGMusic.isPlaying();
        }
        return false;
    }
    public void pause(){
        if(BGMusic != null && BGMusic.isPlaying()){
            BGMusic.pause();
        }
    }
    public void release(){
        if(BGMusic !=null){
            BGMusic.release();
            BGMusic = null;
        }
    }
}
