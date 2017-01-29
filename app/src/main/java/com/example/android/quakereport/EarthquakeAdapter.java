package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by NeLk on 22/01/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    public static final String LOCATION_SEPARATOR = " of ";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        QuakeUtils quakeUtils = new QuakeUtils();

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        String location = currentEarthquake.getPlace();
        String offsetLocation;
        String primaryLocation;

        // Set up Location (Offset & Location)
        if(location.contains(LOCATION_SEPARATOR)){
            String[] parts = location.split(LOCATION_SEPARATOR);
            offsetLocation = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }

        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.offset);
        offsetLocationTextView.setText(offsetLocation);

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(primaryLocation);

        // Set up Magnitude
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitudeTruncated = formatter.format(currentEarthquake.getMagnitude());

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(magnitudeTruncated);

        //Set up Magnitude Circle Color
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = quakeUtils.getMagnitudeColor(getContext(), currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        // Set up Date (Day & Time)
        Date dateObject = new Date(currentEarthquake.getTime());
        String dayDisplay = quakeUtils.formatDate(dateObject, "MMM DD, yyyy");
        String timeDisplay = quakeUtils.formatDate(dateObject, "HH:mm");

        TextView dayTextView = (TextView) listItemView.findViewById(R.id.day);
        dayTextView.setText(dayDisplay);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(timeDisplay);

        return listItemView;
    }
}