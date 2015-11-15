package com.example.researchbeast.myspectrum.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.researchbeast.myspectrum.CardAdapter;
import com.example.researchbeast.myspectrum.NewTextDraftActivity;
import com.example.researchbeast.myspectrum.R;
import com.example.researchbeast.myspectrum.ViewTextDraftHistoryActivity;
import com.example.researchbeast.myspectrum.events.StartActivityEvent;
import com.example.researchbeast.myspectrum.models.CardInformation;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CardViewActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.main_recyclerview) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("BUOY");

        CardAdapter adapter = new CardAdapter(CardInformation.items);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        setSupportActionBar(mToolbar);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(StartActivityEvent event) {
        Intent intent;
        switch (event.resourceId) {
            case 0:
                intent = new Intent(this, NewEventActivity.class);
                break;
            case 1:
                intent = new Intent(this, ViewHistoryActivity.class);
                break;
            case 2:
                intent = new Intent(this, NewTextDraftActivity.class);
                break;
            case 3:
                intent = new Intent(this, ViewTextDraftHistoryActivity.class);
                break;
            case 4:
                intent = new Intent(this,InfoSitesActivity.class);
                break;
            default:
                intent = null;
        }
        startActivity(intent);
    }
}
