package com.example.researchbeast.myspectrum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewEventActivity extends AppCompatActivity {
    @Bind(R.id.date_edittext) EditText mDateEditText;
    @Bind(R.id.time_edittext) EditText mTimeEditText;
    @Bind(R.id.rating_edittext) EditText mRatingEditText;
    @Bind(R.id.notes_edittext) EditText mNotesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        ButterKnife.bind(this);
        setDateTime();
    }

    private void setDateTime() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        mDateEditText.setText(String.format("%d / %d / %d", today.month, today.monthDay, today.year));
        mTimeEditText.setText(String.format("%d:%d",today.hour,today.minute));
    }

}
