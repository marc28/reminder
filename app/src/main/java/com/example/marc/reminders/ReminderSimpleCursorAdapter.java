package com.example.marc.reminders;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

/**
 * This is used BIND database values returned to the list view.
 * I'm are gonna pull the data out of the DB and into the views.
 */
public class ReminderSimpleCursorAdapter extends SimpleCursorAdapter {

    //Constructor
    public ReminderSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    //to use a viewholder, Overwrite the following 2 methods
    @Override
    public View newView(Context context, Cursor c, ViewGroup parent) {
        return super.newView(context, c, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
    }




}
