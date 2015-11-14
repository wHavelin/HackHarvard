package com.example.researchbeast.myspectrum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.researchbeast.myspectrum.models.NewEventModel;

import java.util.List;

/**
 * Created by willi on 11/14/2015.
 */
public class EventListAdapter extends ArrayAdapter<NewEventModel> {
    public EventListAdapter(Context context, NewEventModel[] objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NewEventModel event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_layout, parent, false);
        }
        // Lookup view for data population
        TextView datetime = (TextView) convertView.findViewById(R.id.datetime);
        TextView intensity = (TextView) convertView.findViewById(R.id.intensity);
        // Populate the data into the template view using the data object
        datetime.setText(event.date);
        intensity.setText(event.rating);
        // Return the completed view to render on screen
        return convertView;
    }
}
