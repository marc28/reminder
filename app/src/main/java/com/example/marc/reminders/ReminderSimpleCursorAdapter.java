package com.example.marc.reminders;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

/**
 * This is used BIND database values returned to the list view.
 * I'm are gonna pull the data out of the DB and into the views.
 *
 * A SimpleCursorAdapter maps columns for a cursor to TextView.
 * The cursor is used to keep track of rows in the table
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

    //This method will be called by ListView during runtime
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor); //maps valuesf form cursor to elements in the view
        ViewHolder holder = (ViewHolder)view.getTag();
        if(holder == null){ //has a holder already been attached to the view??
            holder = new ViewHolder();
            holder.colImp = cursor.getColumnIndexOrThrow(RemindersDbAdapter.COL_IMPORTANT); //gets index of important column
            holder.listTab = view.findViewById(R.id.row_tab);
            view.setTag(holder);
        }
        if(cursor.getInt(holder.colImp) > 0){
            holder.listTab.setBackgroundColor(context.getResources().getColor(R.color.orange));
        }else{
            holder.listTab.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
    }

    static class ViewHolder{
        int colImp; //store column index of "important" from db
        View listTab; //store view
    }


}
