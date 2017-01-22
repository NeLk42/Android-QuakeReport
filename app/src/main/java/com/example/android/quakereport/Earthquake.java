package com.example.android.quakereport;

/**
 * Created by NeLk on 22/01/2017.
 */

public class Earthquake {

    private String magnitude;
    private String time;
    private String place;

    /**
     * Create a new Earthquake object.
     *
     * @param magnitude represents the strength of the earthquake.
     * @param time represents the time the earthquake happened.
     * @param place represents the location the earthquake occurred.
     */
    public Earthquake(String magnitude, String time, String place){
        this.magnitude = magnitude;
        this.time = time;
        this.place = place;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }
}
