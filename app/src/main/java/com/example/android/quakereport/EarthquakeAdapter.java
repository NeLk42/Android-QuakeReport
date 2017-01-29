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
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        // Set up Date (Day & Time)
        Date dateObject = new Date(currentEarthquake.getTime());
        String dayDisplay = formatDate(dateObject, "MMM DD, yyyy");
        String timeDisplay = formatDate(dateObject, "HH:mm");

        TextView dayTextView = (TextView) listItemView.findViewById(R.id.day);
        dayTextView.setText(dayDisplay);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(timeDisplay);

        return listItemView;
    }

    private int getMagnitudeColor(Double magnitude) {
        int color;
        int magFloor = (int) Math.floor(magnitude);
        switch (magFloor){
            case 0:
                color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 1:
                color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                color = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                color = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                color = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                color = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                color = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                color = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                color = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                color = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return color;
    }

    private String formatDate(Date dateObject, String pattern) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        return dateFormatter.format(dateObject);
    }
}