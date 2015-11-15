package com.example.researchbeast.myspectrum.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.researchbeast.myspectrum.R;
import com.example.researchbeast.myspectrum.linkListAdapter;
import com.example.researchbeast.myspectrum.models.linkModel;

import java.util.ArrayList;

import static com.example.researchbeast.myspectrum.R.id.eventListView;

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<linkModel> linkArray = new ArrayList<linkModel>();
        linkArray.add(new linkModel("http://www.healthfulchat.org/anxiety-chat-room.html", "Anxiety Chat Room"));
        linkArray.add(new linkModel("https://www.autismspeaks.org/family-services/resource-library/toys-games", "Games at Autism Speaks"));
        linkArray.add(new linkModel("http://www.autismgames.com.au/", "Autism Games"));
        linkArray.add(new linkModel("https://www.youtube.com/watch?v=XlMjpeTz3MY", "Calming Sensory Room Video"));
        linkArray.add(new linkModel("https://www.youtube.com/watch?v=mdOZIOc8SA4", "Relaxing Music"));
        linkListAdapter adapter = new linkListAdapter(this, linkArray);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        ListView listView = (ListView) findViewById(R.id.gamesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(linkArray.get(position).url));
                startActivity(i);
            }
        });
    }


}
