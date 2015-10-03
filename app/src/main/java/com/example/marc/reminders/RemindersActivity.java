package com.example.marc.reminders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RemindersActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //leave as default
        setContentView(R.layout.activity_reminders); //this is the entire screen view
        mListView = (ListView) findViewById(R.id.reminders_list_view);
        //ArrayAdapter is the controller in model-view-controller
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, //this refers to this activity
                R.layout.reminders_row, //each entire row
                R.id.row_text, //row (view) Reminder Text
                new String[]{"first","second","third"} //records
        );
        mListView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
