package com.example.researchbeast.myspectrum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.researchbeast.myspectrum.models.LinkModel;

import java.util.ArrayList;

/**
 * Created by willi on 11/14/2015.
 */
public class LinkListAdapter extends ArrayAdapter<LinkModel> {
    public LinkListAdapter(Context context, ArrayList<LinkModel> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        LinkModel link = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.link_layout, parent, false);
        }
        // Lookup view for data population
        TextView nameView = (TextView) convertView.findViewById(R.id.name);
        // Populate the data into the template view using the data object
        nameView.setText(link.name);
        // Return the completed view to render on screen
        return convertView;
    }
}
