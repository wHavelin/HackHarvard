package com.example.researchbeast.myspectrum.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.researchbeast.myspectrum.R;
import com.example.researchbeast.myspectrum.linkListAdapter;
import com.example.researchbeast.myspectrum.models.linkModel;

import java.util.ArrayList;

public class infoSitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<linkModel> linkArray = new ArrayList<linkModel>();
        linkArray.add(new linkModel("https://www.autismspeaks.org/", "Autism Speaks"));
        linkArray.add(new linkModel("https://www.autism-society.org", "Autism Society"));
        linkArray.add(new linkModel("http://www.autism.com", "Autism.com"));
        linkArray.add(new linkModel("http://www.ahany.org", "Asperger Syndrome and High Functioning Autism Association"));
        linkListAdapter adapter = new linkListAdapter(this, linkArray);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sites);

        ListView listView = (ListView) findViewById(R.id.infoSitesList);
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
