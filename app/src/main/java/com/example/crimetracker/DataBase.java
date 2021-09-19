package com.example.crimetracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBase extends SQLiteOpenHelper {

    public static final String USER_REPORT_TABLE = "userReport";
    public static final String TYPE_OF_CRIME_COL = "typeOfCrime";
    public static final String DESCRIPTION_COL = "description";
    public static final String DATE_AND_TIME_COL = "dateAndTime";
    public static final String LAT_COORD_COL = "LatCoord";
    public static final String LONG_COORD_COL = "longCoord";
    public static final String ID_COL = "id";

    public DataBase(@Nullable Context context) {
        super(context, "crimeDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE IF NOT EXISTS " + USER_REPORT_TABLE + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TYPE_OF_CRIME_COL + " TEXT, "+ DESCRIPTION_COL + " TEXT, " + DATE_AND_TIME_COL + " TEXT, " + LAT_COORD_COL + " REAL DEFAULT 0 , " + LONG_COORD_COL + " REAL DEFAULT 0)";

        db.execSQL(createTableStatement);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @SuppressLint("SimpleDateFormat")
    public boolean addOne(UserReport userReport) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TYPE_OF_CRIME_COL, userReport.getTypeOfCrime());
        cv.put(DESCRIPTION_COL, userReport.getDescription());
        cv.put(DATE_AND_TIME_COL, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //TODO: get location so that the coodrdinates can get added to the DB

        long insert = db.insert(USER_REPORT_TABLE, null, cv);
        return insert != -1;
    }

}
