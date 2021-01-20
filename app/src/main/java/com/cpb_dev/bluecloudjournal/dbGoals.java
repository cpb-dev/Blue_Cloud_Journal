package com.cpb_dev.bluecloudjournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbGoals extends SQLiteOpenHelper {

    /*  Database Set-up */
    public static final String DB_Name = "goals.db";
    public static final String TABLE_NAME = "goals";

    public static final String COL1 = "ID";
    public static final String COL2 = "Title";
    public static final String COL3 = "Date";
    public static final String COL4 = "Description";
    public static final String COL5 = "Progress";

    public dbGoals(@Nullable Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " ( " + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " DATE, " + COL4 + " TEXT, " +
                COL5 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addGoal(String GoalTitle, String GoalDate,
            String GoalDesc, String GoalProgress){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, GoalTitle);
        cv.put(COL3, GoalDate);
        cv.put(COL4, GoalDesc);
        cv.put(COL5, GoalProgress);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1) {
            return false;
        } else{
            return true;
        }

    }

    public Cursor getGoals(){
        /* Method to retrieve all goals from database */
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = null;
        if(db != null){ //Makes sure there is dat within the database
            c = db.rawQuery(query, null);
        }
        return c; //Returns the query
    }

    public void deleteGoal(String id) {
        /* Method to delete selected goal from db */
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 +
                " = " + id;
        db.execSQL(query);
    }

}
