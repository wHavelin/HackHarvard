package com.example.researchbeast.myspectrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewEventActivity extends AppCompatActivity {

    private TextView dateView;
    private TextView timeView;
    private TextView durationView;
    private TextView intensityView;
    private TextView notesView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        dateView = (TextView)findViewById(R.id.viewEventDate);
        timeView = (TextView)findViewById(R.id.viewEventTime);
        durationView = (TextView)findViewById(R.id.viewEventDuration);
        intensityView = (TextView)findViewById(R.id.viewEventIntensity);
        notesView = (TextView)findViewById(R.id.viewEventNotes);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String date =(String) b.get("date");
            dateView.setText("Date: " + date);
            String time =(String) b.get("time");
            timeView.setText("Time: " + time);
            String duration =(String) b.get("duration");
            durationView.setText("Duration: " + duration);
            String intensity =(String) b.get("intensity");
            intensityView.setText("Intensity: " + intensity);
            String notes =(String) b.get("notes");
            notesView.setText("Notes: " + notes);
        }

    }
}
