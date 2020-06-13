package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a single earthquake.
 * Each object has 3 properties: city name, date, and magnitude.
 */

public class Earthquake {

    // city of the earthquake
    private String mCityName;

    // date of the earthquake
    private long mTimeInMilliseconds;

    // magnitude of the earthquake
    private String mMagnitude;

    /**
     * Create a new {@link Earthquake} object.
     *
     * @param city is the name of the city the earthquake happened in
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *    *  earthquake happened
     * @param magnitude is the magnitude of the earthquake
     * */
    public Earthquake(String city, long timeInMilliseconds, String magnitude)
    {
        mCityName = city;
        mTimeInMilliseconds = timeInMilliseconds;
        mMagnitude = magnitude;
    }

    /**
     * Get the name of the city
     */
    public String getCityName() {
        return mCityName;
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
