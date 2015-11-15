package com.example.researchbeast.myspectrum.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.researchbeast.myspectrum.LinkListAdapter;
import com.example.researchbeast.myspectrum.R;
import com.example.researchbeast.myspectrum.models.LinkModel;

import java.util.ArrayList;

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<LinkModel> linkArray = new ArrayList<LinkModel>();
        linkArray.add(new LinkModel("http://www.healthfulchat.org/anxiety-chat-room.html", "Anxiety Chat Room"));
        linkArray.add(new LinkModel("https://www.autismspeaks.org/family-services/resource-library/toys-games", "Games at Autism Speaks"));
        linkArray.add(new LinkModel("http://www.autismgames.com.au/", "Autism Games"));
        linkArray.add(new LinkModel("https://www.youtube.com/watch?v=XlMjpeTz3MY", "Calming Sensory Room Video"));
        linkArray.add(new LinkModel("https://www.youtube.com/watch?v=mdOZIOc8SA4", "Relaxing Music"));
        LinkListAdapter adapter = new LinkListAdapter(this, linkArray);
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
