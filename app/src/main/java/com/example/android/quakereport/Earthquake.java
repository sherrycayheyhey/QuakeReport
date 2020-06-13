package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a single earthquake.
 * Each object has 3 properties: location name, date, and magnitude.
 */

public class Earthquake {

    // city of the earthquake
    private String mLocationName;

    // date of the earthquake
    private long mTimeInMilliseconds;

    // magnitude of the earthquake
    private String mMagnitude;

    /**
     * Create a new {@link Earthquake} object.
     *
     * @param location is the name of the location the earthquake happened in
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *    *  earthquake happened
     * @param magnitude is the magnitude of the earthquake
     * */
    public Earthquake(String location, long timeInMilliseconds, String magnitude)
    {
        mLocationName = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mMagnitude = magnitude;
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
    public String getMagnitude() {
        return mMagnitude;
    }
}
