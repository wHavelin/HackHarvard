package com.example.researchbeast.myspectrum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.researchbeast.myspectrum.models.NewEventModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;

import static com.example.researchbeast.myspectrum.R.id.eventListView;
import static com.example.researchbeast.myspectrum.R.id.keywordToSearch;

public class ViewHistoryActivity extends AppCompatActivity {

    SharedPreferences mprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String filter = "";
        if(b!=null)
        {
            filter =(String) b.get("filter");
        }
        mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> entries = mprefs.getAll();
        Set<String> keys = entries.keySet();
        Gson gson = new Gson();


        final ArrayList<NewEventModel> myEventArray = new ArrayList<NewEventModel>();

        for(String key : keys) {
            NewEventModel model =  gson.fromJson((String) entries.get(key), NewEventModel.class);
            if(filter.equals("") || model.notes.contains(filter)){
                myEventArray.add(model);
            }
        }

        Collections.sort(myEventArray);

        EventListAdapter adapter = new EventListAdapter(this, myEventArray);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("History");

        ListView listView = (ListView) findViewById(eventListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(ViewHistoryActivity.this, ViewEventActivity.class);
                //If you wanna send any data to nextActicity.class you can use
                NewEventModel thisModel = myEventArray.get(position);
                i.putExtra("date",thisModel.date);
                i.putExtra("time",thisModel.time);
                i.putExtra("duration",thisModel.duration);
                i.putExtra("intensity",thisModel.rating);
                i.putExtra("notes",thisModel.notes);
                startActivity(i);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get shared settings

    }

    public void searchHistory(View v){
        Intent i = new Intent(ViewHistoryActivity.this, ViewHistoryActivity.class);
        TextView someView = (TextView)findViewById(keywordToSearch);
        Log.d("STATE", someView.getText().toString());
        i.putExtra("filter", someView.getText().toString());
        startActivity(i);
    }
}