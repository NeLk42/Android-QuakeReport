package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by NeLk on 22/01/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    public static final String LOCATION_SEPARATOR = " of ";

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

        String location = currentEarthquake.getPlace();
        String offsetLocation;
        String primaryLocation;

        // Set up Locations
        if(location.contains(LOCATION_SEPARATOR)){
            String[] parts = location.split(LOCATION_SEPARATOR);
            offsetLocation = parts[0] = LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }

        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.offset);
        offsetLocationTextView.setText(offsetLocation);

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(primaryLocation);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(currentEarthquake.getMagnitude());

        // Set up Dates
        Date dateObject = new Date(currentEarthquake.getTime());
        String dayDisplay = formatDate(dateObject, "MMM DD, yyyy");
        String timeDisplay = formatDate(dateObject, "HH:mm");

        TextView dayTextView = (TextView) listItemView.findViewById(R.id.day);
        dayTextView.setText(dayDisplay);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(timeDisplay);

        return listItemView;
    }

    private String formatDate(Date dateObject, String pattern) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        return dateFormatter.format(dateObject);
    }
}