package com.example.researchbeast.myspectrum;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.researchbeast.myspectrum.models.NewEventModel;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.Map;
import java.util.Set;

import static com.example.researchbeast.myspectrum.R.id.eventListView;

public class viewHistory extends AppCompatActivity {

    SharedPreferences mprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mprefs = PreferenceManager.getDefaultSharedPreferences(this);

        String[] myStringArray = {"something", "somethingElse", "third", "fgd"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, myStringArray);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        ListView listView = (ListView) findViewById(eventListView);
        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get shared settings
        Map<String, ?> entries = mprefs.getAll();
        Set<String> keys = entries.keySet();
        Gson gson = new Gson();

        for(String key : keys) {
            NewEventModel model =  gson.fromJson((String) entries.get(key), NewEventModel.class);

            //
        }
    }
}