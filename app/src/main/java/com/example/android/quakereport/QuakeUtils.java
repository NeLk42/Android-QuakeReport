package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NeLk on 29/01/2017.
 */

public class QuakeUtils {

    public int getMagnitudeColor(Context context, Double magnitude) {
        int color;
        int magFloor = (int) Math.floor(magnitude);
        switch (magFloor){
            case 0:
                color = ContextCompat.getColor(context, R.color.magnitude1);
                break;
            case 1:
                color = ContextCompat.getColor(context, R.color.magnitude1);
                break;
            case 2:
                color = ContextCompat.getColor(context, R.color.magnitude2);
                break;
            case 3:
                color = ContextCompat.getColor(context, R.color.magnitude3);
                break;
            case 4:
                color = ContextCompat.getColor(context, R.color.magnitude4);
                break;
            case 5:
                color = ContextCompat.getColor(context, R.color.magnitude5);
                break;
            case 6:
                color = ContextCompat.getColor(context, R.color.magnitude6);
                break;
            case 7:
                color = ContextCompat.getColor(context, R.color.magnitude7);
                break;
            case 8:
                color = ContextCompat.getColor(context, R.color.magnitude8);
                break;
            case 9:
                color = ContextCompat.getColor(context, R.color.magnitude9);
                break;
            default:
                color = ContextCompat.getColor(context, R.color.magnitude10plus);
                break;
        }
        return color;
    }

    public String formatDate(Date dateObject, String pattern) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        return dateFormatter.format(dateObject);
    }
}
