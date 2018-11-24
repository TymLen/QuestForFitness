package com.ict376.tym.databaseview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
//Purpose: Database for the application. All character values are stored here
//Author: Tymothy Alexis Lenton
//Modified: 03/11/2018

public class HeroDBProvider extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HeroDB";
    private static final String RECORDS_TABLE_NAME = "records";
    private static final String RECORDS_ID = "id";
    private static final String RECORDS_LEVEL = "level";
    private static final String RECORDS_FEAT = "feat";
    private static final String RECORDS_DATE = "date";
    private static final String RECORDS_WEIGHT = "weight";

    private static final String LEVELS_TABLE_NAME = "levels";
    private static final String LEVELS_ID = "id";
    private static final String LEVELS_NUMBER = "number";
    private static final String LEVELS_START = "startDate";
    private static final String LEVELS_END = "endDate";
    private static final String LEVELS_PHOTO = "photo";

    private static final String HERO_TABLE_NAME = "hero";
    private static final String HERO_ID = "id";
    private static final String HERO_LEVEL = "level";
    private static final String HERO_EXP = "exp";

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final int EXP_MAX = 7;

    public HeroDBProvider(Context context){
        super(context, DATABASE_NAME, null, 22);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "create table " + LEVELS_TABLE_NAME + "("+
                        LEVELS_ID + " integer primary key, "+
                        LEVELS_NUMBER + " integer, " +
                        LEVELS_START + " datetime, "+
                        LEVELS_END + " datetime, "+
                        LEVELS_PHOTO + " BLOB)"
        );
        db.execSQL(
                "create table " + HERO_TABLE_NAME + "("+
                        HERO_ID + " integer primary key, "+
                        HERO_LEVEL + " integer, " +
                        HERO_EXP + " integer)"
        );
        db.execSQL(
                "create table " + RECORDS_TABLE_NAME + "("+
                RECORDS_ID + " integer primary key, "+
                RECORDS_LEVEL + " integer, " +
                RECORDS_FEAT + " text, "+
                RECORDS_WEIGHT + " float, "+
                RECORDS_DATE + " datetime)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVerions){
        db.execSQL("DROP TABLE IF EXISTS "+RECORDS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LEVELS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+HERO_TABLE_NAME);
        onCreate(db);
    }

    public void createHero(){ //If no heroes in database this is used to create one. Later may be used to create multiple heroes outside of database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HERO_LEVEL, 1);
        cv.put(HERO_EXP, 0);
        db.insert(HERO_TABLE_NAME, null, cv);
    }

    public int getHeroNumber(){ //Checks to see if there are heroes in database
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, HERO_TABLE_NAME);
    }

    public boolean insertRecord(String feat, float weight, String inDate){ //Inserts daily activity into Records table
        if(getHeroNumber() == 0){
            createHero();
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+HERO_TABLE_NAME, null);
        res.moveToFirst();
        int level = res.getInt(res.getColumnIndex(HERO_LEVEL));
        if(!checkDate(inDate)){
            ContentValues cv = new ContentValues();
            cv.put(RECORDS_FEAT, feat);
            cv.put(RECORDS_WEIGHT, weight);
            cv.put(RECORDS_DATE, inDate);
            cv.put(RECORDS_LEVEL, level);
            db.insert(RECORDS_TABLE_NAME, null, cv);
            res.close();
            return true;
        }
        else{
            res.close();
            return false;
        }
    }

    public int numberOfRecords(){ //Checks number of records
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, RECORDS_TABLE_NAME);
    }

    public boolean checkDate(String checkThis){ //Checks that date is unique
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+RECORDS_TABLE_NAME+" where "+ RECORDS_DATE+" = ?", new String[]{checkThis});
        if(res !=null&& res.getCount()>0){
            res.moveToFirst();
            res.close();
            return true;
        }
        else{
            if(res != null){
                res.close();
            }
            return false;
        }
    }

    public boolean checkExp(){ //Checks experience number for blocking level up
        SQLiteDatabase db = this.getReadableDatabase();
        if(getHeroNumber() == 0){
            createHero();
        }
        Cursor res = db.rawQuery("select * from "+HERO_TABLE_NAME, null);
        res.moveToFirst();
        int exp = res.getInt(res.getColumnIndex(HERO_EXP));
        res.close();
        return exp < EXP_MAX;
    }

    public void incExp(){ //Increases experience so after a certain number of adventures the hero can level up
        int idHero = 1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+HERO_TABLE_NAME, null);
        res.moveToFirst();
        int exp = res.getInt(res.getColumnIndex(HERO_EXP));
        exp = exp +1;
        db.execSQL("UPDATE "+HERO_TABLE_NAME+" SET "+HERO_EXP+"= "+exp+" WHERE "+HERO_ID+"= "+idHero);
        res.close();
    }

    public Cursor getRecords(){ //Get all records
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+RECORDS_TABLE_NAME, null);
        return res;
    }

    public Cursor getLevelRecords(int inLevel){ //Get specific records for a level
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+RECORDS_TABLE_NAME+ " WHERE "+RECORDS_LEVEL+"= "+inLevel, null);
        return res;
    }

    public Cursor getLevels(){ //Get all levels
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+LEVELS_TABLE_NAME, null);
        return res;
    }

    public int numberOfLevels(){ //Get number of levels.
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, LEVELS_TABLE_NAME);
    }

    public boolean insertBeforeLevel(){ //Was used when date was used to determine activities but moved to just setting levels in the records themselves
        SQLiteDatabase db = this.getWritableDatabase();
        Date cur = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat(DATE_FORMAT);
        Cursor res = db.rawQuery("select * from "+HERO_TABLE_NAME, null);
        res.moveToFirst();
        int level = res.getInt(res.getColumnIndex(HERO_LEVEL));
        res.close();
        ContentValues cv = new ContentValues();
        cv.put(LEVELS_NUMBER, level);
        cv.put(LEVELS_START, dateForm.format(cur));
        db.insert(LEVELS_TABLE_NAME, null, cv);
        res.close();
        return true;
    }

    public boolean newLevelup(String startDate, String endDate, int level, byte[] inPhoto){ //Levels up the hero
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LEVELS_NUMBER, level);
        cv.put(LEVELS_START, startDate);
        cv.put(LEVELS_END, endDate);
        cv.put(LEVELS_PHOTO, inPhoto);
        db.insert(LEVELS_TABLE_NAME, null, cv);
        db.execSQL("UPDATE "+HERO_TABLE_NAME+" SET "+HERO_EXP+"= 0 WHERE "+HERO_ID+"= 1");
        int newLevel = level+1;
        db.execSQL("UPDATE "+HERO_TABLE_NAME+" SET "+HERO_LEVEL+"= "+newLevel+" WHERE "+HERO_ID+"= 1");
        Cursor res = db.rawQuery("select * from "+HERO_TABLE_NAME, null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            res.moveToNext();
        }
        return true;
    }

    public Cursor getCurrentRecords(){ //Gets records based upon current hero level
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+HERO_TABLE_NAME, null);
        res.moveToFirst();
        int level = res.getInt(res.getColumnIndex(HERO_LEVEL));
        res.close();
        res = db.rawQuery("select * from "+RECORDS_TABLE_NAME+ " where "+RECORDS_LEVEL+"= "+level, null);
        return res;
    }

    public byte[] getPhoto(int index){ //gets photo from database
        SQLiteDatabase db = this.getReadableDatabase();
        byte[] heroPhoto = new byte[]{};
        Cursor res = db.rawQuery("select * from "+LEVELS_TABLE_NAME, null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            if(res.getInt(res.getColumnIndex(LEVELS_ID)) == index){
                heroPhoto = res.getBlob(res.getColumnIndex(LEVELS_PHOTO));
            }
            res.moveToNext();
        }
        res.close();
        return heroPhoto;
    }
}
