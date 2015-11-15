package com.example.researchbeast.myspectrum;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.researchbeast.myspectrum.models.NewEventModel;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewEventActivity extends AppCompatActivity {
    @Bind(R.id.date_edittext) EditText mDateEditText;
    @Bind(R.id.time_edittext) EditText mTimeEditText;
    @Bind(R.id.duration_edittext) EditText mDurationEditText;
    @Bind(R.id.rating_edittext) EditText mRatingEditText;
    @Bind(R.id.notes_edittext) EditText mNotesEditText;
    @Bind(R.id.save_button) Button mSaveButton;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    SharedPreferences mPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Stetho.initializeWithDefaults(this);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Record Event");
        setDateTime();
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor mPrefEdit = mPrefs.edit();
                Gson gson = new Gson();
                String date = mDateEditText.getText().toString();
                String time = mTimeEditText.getText().toString();
                String duration = mDurationEditText.getText().toString();
                String rating = mRatingEditText.getText().toString();
                String notes = mNotesEditText.getText().toString();
                NewEventModel eventModel = new NewEventModel(date,time,duration, rating,notes);
                String json = gson.toJson(eventModel);
                mPrefEdit.putString(date + time, json);
                mPrefEdit.commit();
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                goBack();
            }
        });
    }

    /**
     * Sets current date and time to the edit text's
     */
    private void setDateTime() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        mDateEditText.setText(String.format("%d / %d / %d", today.month, today.monthDay, today.year));
        mTimeEditText.setText(String.format("%d:%d", today.hour, today.minute));
    }

    /**
     * Go back to the main activity
     */
    private void goBack() {
        onBackPressed();
        this.finish();
    }

}
