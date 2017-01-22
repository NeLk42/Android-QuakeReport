package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NeLk on 22/01/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(currentEarthquake.getPlace());

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(currentEarthquake.getMagnitude());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentEarthquake.getTime());

        return listItemView;
    }
}
