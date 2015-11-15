package com.example.researchbeast.myspectrum;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.researchbeast.myspectrum.models.NewDraftModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static com.example.researchbeast.myspectrum.R.id.draftListView;

public class ViewTextDraftHistoryActivity extends AppCompatActivity {

    SharedPreferences mprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // get shared settings
        mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> entries = mprefs.getAll();
        Set<String> keys = entries.keySet();
        Gson gson = new Gson();

        ArrayList<NewDraftModel> myEventArray = new ArrayList<>();

        for (String key : keys) {
            NewDraftModel model = gson.fromJson((String) entries.get(key), NewDraftModel.class);
            myEventArray.add(model);
        }

        DraftListAdapter adapter = new DraftListAdapter(this, myEventArray);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_text_draft_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.draftToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("History");

        ListView listView = (ListView) findViewById(draftListView);
        listView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
