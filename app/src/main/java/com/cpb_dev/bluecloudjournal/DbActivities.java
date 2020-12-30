package com.cpb_dev.bluecloudjournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbActivities extends SQLiteOpenHelper {

    /* Setting up the Database */
    public static final String DB_NAME = "activities.db";
    public static final String TABLE_NAME = "activities";

    public static final String COL1 = "ID"; //Table data
    public static final String COL2 = "ActivityName";

    public DbActivities(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create method for the database table
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " ( " + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT)";
        db.execSQL(createTable);
        basicActivities();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addActivity(String ActivityName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, ActivityName);

        /* Checking to ensure data is processed correctly */
        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){ //IF Statement for allowing the boolean to proceed
            return false;
        } else {
            return true;
        }
    }

    private void basicActivities(){
        /* Making a list of prebuilt activities for the user to choose from */
        addActivity("Socialise");
        addActivity("Shopping");
        addActivity("Studying");
        addActivity("Sports");
        addActivity("Games");
        addActivity("Reading");
        addActivity("Cooking");
    }

    public List<String> getActivitiesSpinner(){
        List<String> activities = new ArrayList<String>();

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                activities.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return activities;
    }

    public Cursor getActivities(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public void deleteActivity(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = " + id;
        db.execSQL(query);
    }

}
