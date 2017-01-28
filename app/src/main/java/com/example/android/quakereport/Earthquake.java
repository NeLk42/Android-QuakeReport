package com.example.android.quakereport;

/**
 * Created by NeLk on 22/01/2017.
 */

public class Earthquake {

    private Double magnitude;
    private Long time;
    private String place;

    /**
     * Create a new Earthquake object.
     *
     * @param magnitude represents the strength of the earthquake.
     * @param time represents the time the earthquake happened.
     * @param place represents the location the earthquake occurred.
     */
    public Earthquake(Double magnitude, Long time, String place){
        this.magnitude = magnitude;
        this.time = time;
        this.place = place;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public Long getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }
}
