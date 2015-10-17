package com.example.marc.reminders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.marc.sqlstatements.SqlStatements;

import java.sql.SQLException;

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
    public static final int INDEX_CONTENT = INDEX_ID + 1;
    public static final int INDEX_IMPORTANT = INDEX_ID + 2;
    //Used for logging
    private static final String TAG = "RemindersDbAdapter";
    private DatabaseHelper mDbHelper; //DatabaseHelper is a SQLite API class used to open and close the database
    private SQLiteDatabase mDb; //used for executing SQL Statements
    private static final String DATABASE_NAME = "dba_reminders";
    private static final String TABLE_NAME = "dba_remdrs";
    private static final int DATABASE_VERSION = 1;
    private final Context mctx; //an abstract Android class that provides access to the Android operating system

    //Constructor
    public RemindersDbAdapter(Context ctx) {
        this.mctx = ctx;
    }

    /**
     * This gets an instance of the database
     */
    public void open() throws SQLException {
        mDbHelper = new DatabaseHelper(mctx);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * Closes the connection to the db
     */
    public void close() {
        if (mDbHelper != null) {

        }
    }

    /**
     * CRUD OPERATIONS
     */
    // TODO CREATE
    public void createReminder(String content, int importance) {
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, content);
        values.put(COL_IMPORTANT, importance);
        mDb.insert(TABLE_NAME, null, values);
    }

    //overload create with reminder object
    public long creatReminder(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, reminder.getContent());
        values.put(COL_IMPORTANT, reminder.getContent());
        return mDb.insert(TABLE_NAME, null, values);
    }

    //TODO READ
    public Reminder fetchReminderById(int id) {
        String [] columnNames = {COL_ID, COL_CONTENT, COL_IMPORTANT};
        String whereClause = COL_ID + "=?";
        String [] whereArgs = {String.valueOf(COL_ID)};
        Cursor cursor = mDb.query(TABLE_NAME, columnNames, whereClause, whereArgs, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        //return Reminder Object
        return new Reminder(
                cursor.getInt(INDEX_ID),
                cursor.getString(INDEX_CONTENT),
                cursor.getInt(INDEX_IMPORTANT)
        );
    }

    //Move cursor to the first position
    public Cursor fetchAllReminders(){
        Cursor mCursor = mDb.query(TABLE_NAME,new String []{COL_ID,COL_CONTENT,COL_IMPORTANT},
                null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    //TODO UPDATE
    public void updateReminder(Reminder reminder){
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT,reminder.getContent());
        values.put(COL_IMPORTANT, reminder.getImportant());
        mDb.update(TABLE_NAME, values,COL_ID + "=?", new String[]{String.valueOf(reminder.getId())});
    }

    //TODO DELETE
    public void deleteReminder(int id){
        mDb.delete(TABLE_NAME,COL_ID + "=?",new String[]{String.valueOf(id)});
    }

    public void deleteAllReminders(){
        mDb.delete(TABLE_NAME,null,null);
    }


























    // SQLiteOpenHelper helps maintain the database with special callback methods
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, SqlStatements.createDatabase(TABLE_NAME, COL_ID, COL_CONTENT, COL_IMPORTANT));
            db.execSQL(SqlStatements.createDatabase(TABLE_NAME, COL_ID, COL_CONTENT, COL_IMPORTANT));
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
