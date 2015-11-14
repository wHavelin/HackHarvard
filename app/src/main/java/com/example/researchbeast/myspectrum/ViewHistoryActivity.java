package com.example.researchbeast.myspectrum;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.researchbeast.myspectrum.models.NewEventModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static com.example.researchbeast.myspectrum.R.id.eventListView;

public class ViewHistoryActivity extends AppCompatActivity {

    SharedPreferences mprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> entries = mprefs.getAll();
        Set<String> keys = entries.keySet();
        Gson gson = new Gson();

        ArrayList<NewEventModel> myEventArray = new ArrayList<NewEventModel>();

        for(String key : keys) {
            NewEventModel model =  gson.fromJson((String) entries.get(key), NewEventModel.class);
            myEventArray.add(model);
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get shared settings

    }
}