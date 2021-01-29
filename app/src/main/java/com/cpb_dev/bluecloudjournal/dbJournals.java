package com.cpb_dev.bluecloudjournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbJournals extends SQLiteOpenHelper {

    /*  Database Set-up */
    public static final String DB_Name = "journals.db";
    public static final String TABLE_NAME = "journals";

    public static final String COL1 = "ID";
    public static final String COL2 = "Date";
    public static final String COL3 = "Mood";
    public static final String COL4 = "WhatWentWell";
    public static final String COL5 = "WhatWentWrong";

    public dbJournals(@Nullable Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " ( " + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " DATE, " + COL3 + " TEXT, " + COL4 + " TEXT, " +
                COL5 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addJournal(String Date, String Mood,
                           String WhatWentWell, String WhatWentWrong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, Date);
        cv.put(COL3, Mood);
        cv.put(COL4, WhatWentWell);
        cv.put(COL5, WhatWentWrong);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1) {
            return false;
        } else{
            return true;
        }
    }

    public Cursor getJournals(){
        /* Method to retrieve all Journals from database */
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = null;
        if(db != null) {
            c = db.rawQuery(query, null);
        }
        return c;
    }

    public void deleteJournal(String id) {
        /* Method to delete selected goal from db */
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 +
                " = " + id;
        db.execSQL(query);
    }
}
