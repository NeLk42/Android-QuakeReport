package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    public static final String QUERY = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4&limit=10";

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(JSONObject query) {
        Log.i("QueryUtils", "Accessing extractQuakes");

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.

        try {
            JSONArray featuresJSONArray = query.optJSONArray("features");

            if (featuresJSONArray.length() > 0){
                Log.i(LOG_TAG, "QueryUtils.extractQuakes - features length : " + featuresJSONArray.length());
                for (int i = 0; i < featuresJSONArray.length(); i++){
                    JSONObject quakeJSON = featuresJSONArray.getJSONObject(i);
                    JSONObject properties = quakeJSON.getJSONObject("properties");
                    Double mag = properties.getDouble("mag");
                    Long time = properties.getLong("time");
                    String place = properties.getString("place");
                    String url = properties.getString("url");

                    Log.i(LOG_TAG, "Creating new Quake - " +
                            "mag " + mag +  ", " +
                            "time " + time +  ", " +
                            "place " + place +  ", " +
                            "url " + url
                    );

                    Earthquake quake = new Earthquake(mag, time, place, url);
                    earthquakes.add(quake);
                }
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}
