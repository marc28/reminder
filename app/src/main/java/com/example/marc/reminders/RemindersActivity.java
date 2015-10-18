package com.example.marc.reminders;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RemindersActivity extends ActionBarActivity {

    private ListView mListView;
    private RemindersDbAdapter mDbAdapter; //communicate with DB
    private ReminderSimpleCursorAdapter mCursorAdapter; //binds cursor to text view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //leave as default
        setContentView(R.layout.activity_reminders); //this is the entire screen view
        mListView = (ListView) findViewById(R.id.reminders_list_view); //The list within the entire screen
        mListView.setDivider(null);
        mDbAdapter = new RemindersDbAdapter(this); //this refers to this Activity Context
        mDbAdapter.open(); //opening connection to db

        if (savedInstanceState == null) {
            insertSampleReminders();
        }
        Cursor cursor = mDbAdapter.fetchAllReminders();
        //From columns in db -->
        String[] from = {RemindersDbAdapter.COL_CONTENT};
        //--> to the views in layout
        int[] to = {R.id.row_text};

        //Now bind them with the Adapter I created: ReminderSimpleCursorAdapter
        mCursorAdapter = new ReminderSimpleCursorAdapter(
                RemindersActivity.this,//context
                R.layout.reminders_row, // int layout. The entire Row
                cursor, //cursor
                from,
                to,
                0
        );
        //mCursorAdapter (Controller) now updting listView (view)
        //wth data from the db (model)
        mListView.setAdapter(mCursorAdapter);

        //Creating on click listener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(RemindersActivity.this, "Position clicked: " + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminders, menu);
        Log.d("OVERFLOW-MENU", "Hit a menu button marc");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                Log.d("NEW-REMINDER", "create new Reminder");
                return true;
            case R.id.action_exit:
                Log.d("EXIT", "Bye Bye");
                finish();
                return true;
            default:
                return false;
        }
    }

    private void insertSampleReminders() {
        mDbAdapter.deleteAllReminders();
        mDbAdapter.createReminder("Weed the garden", true);
        mDbAdapter.createReminder("Wash Dishes", false);
        mDbAdapter.createReminder("Clean the Car", true);
        mDbAdapter.createReminder("Wash the living room", false);
        mDbAdapter.createReminder("Water the plants", false);
        mDbAdapter.createReminder("Bring out the rubbish", false);
    }

}

//ArrayAdapter is the controller in model-view-controller
       /* ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, //this refers to this activity
                R.layout.reminders_row, //each entire row
                R.id.row_text, //row (view) Reminder Text
                new String[]{"first","second","third"} //records
        );
        mListView.setAdapter(arrayAdapter);*/