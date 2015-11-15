package com.example.researchbeast.myspectrum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.researchbeast.myspectrum.models.NewDraftModel;

import java.util.ArrayList;

/**
 * Created by ResearchBeast on 11/14/2015.
 */
public class DraftListAdapter extends ArrayAdapter<NewDraftModel> {
    public DraftListAdapter(Context context, ArrayList<NewDraftModel> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NewDraftModel event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.draft_layout, parent, false);
        }
        // Lookup view for data population
        TextView contactView = (TextView) convertView.findViewById(R.id.contactName);
        TextView messageView = (TextView) convertView.findViewById(R.id.messageText);
        // Populate the data into the template view using the data object
        contactView.setText(event.name);
        messageView.setText(event.message);
        // Return the completed view to render on screen
        return convertView;
    }
}
