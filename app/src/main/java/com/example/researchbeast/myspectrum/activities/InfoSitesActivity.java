package com.example.researchbeast.myspectrum.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.researchbeast.myspectrum.LinkListAdapter;
import com.example.researchbeast.myspectrum.R;
import com.example.researchbeast.myspectrum.models.LinkModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoSitesActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<LinkModel> linkArray = new ArrayList<LinkModel>();
        linkArray.add(new LinkModel("https://www.autismspeaks.org/", "Autism Speaks"));
        linkArray.add(new LinkModel("https://www.autism-society.org", "Autism Society"));
        linkArray.add(new LinkModel("http://www.autism.com", "Autism.com"));
        linkArray.add(new LinkModel("http://www.ahany.org", "Asperger Syndrome and High Functioning Autism Association"));
        LinkListAdapter adapter = new LinkListAdapter(this, linkArray);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sites);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Sites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
