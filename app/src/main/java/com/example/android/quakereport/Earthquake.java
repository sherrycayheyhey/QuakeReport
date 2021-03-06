package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a single earthquake.
 * Each object has 4 properties: location name, date, magnitude, and url.
 */

public class Earthquake {

    // city of the earthquake
    private String mLocationName;

    // date of the earthquake
    private long mTimeInMilliseconds;

    // magnitude of the earthquake
    private double mMagnitude;

    //url of the earthquake
    private String mUrl;

    /**
     * Create a new {@link Earthquake} object.
     *
     * @param location is the name of the location the earthquake happened in
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *    *  earthquake happened
     * @param magnitude is the magnitude of the earthquake
     * @param url is the USGS webpage for the earthquake
     * */
    public Earthquake(String location, long timeInMilliseconds, double magnitude, String url)
    {
        mLocationName = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mMagnitude = magnitude;
        mUrl = url;
    }

    /**
     * Get the name of the location
     */
    public String getLocation() {
        return mLocationName;
    }

    /**
     * Get the date
     */
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /**
     * Get the magnitude
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Get the url
     */
    public String getUrl() {
        return mUrl;
    }
}
