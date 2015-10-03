package com.example.marc.reminders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.marc.sqlstatements.SqlStatements;

/**
 * Created by marc on 03/10/2015.
 */
public class RemindersDbAdapter {

    //    Column Names
    public static final String COL_ID = "id";
    public static final String COL_CONTENT = "content";
    public static final String COL_IMPORTANT = "important";

    //    Corresponding indices
    public static final int INDEX_ID = 0;
    public static final int INDEX_CONTENT= INDEX_ID + 1;
    public static final int INDEX_IMPORTANT = INDEX_ID + 2;

    //Used for logging
    private static final String TAG = "RemindersDbAdapter";

    private DatabaseHelper mDbHelper; //DatabaseHelper is a SQLite API class used to open and close the database
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "dba_remndrs";
    private static final String TABLE_NAME = "dba_remdrs";
    private static final int DATABASE_VERSION = 1;

    //private final Context mctx; //an abstract Android class that provides access to the Android operating system



    // SQLiteOpenHelper helps maintain the database with special callback methods
    private static class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context){
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, SqlStatements.createDatabase(TABLE_NAME,COL_ID,COL_CONTENT,COL_IMPORTANT));
            db.execSQL(SqlStatements.createDatabase(TABLE_NAME,COL_ID,COL_CONTENT,COL_IMPORTANT));
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " +
                    newVersion + ", which will destroy  all old data");
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
            onCreate(db);
        }
    }

}
