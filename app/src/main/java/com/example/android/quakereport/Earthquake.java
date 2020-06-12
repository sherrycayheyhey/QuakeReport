package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a single earthquake.
 * Each object has 3 properties: city name, date, and magnitude.
 */

public class Earthquake {

    // city of the earthquake
    private String mCityName;

    // date of the earthquake
    private String mDate;

    // magnitude of the earthquake
    private String mMagnitude;

    /*
     * Create a new Earthquake object.
     *
     * @param city is the name of the city the earthquake happened in
     * @param date is the date of the earthquake
     * @param magnitude is the magnitude of the earthquake
     * */
    public Earthquake(String city, String date, String magnitude)
    {
        mCityName = city;
        mDate = date;
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
    public String getDate() {
        return mDate;
    }

    /**
     * Get the magnitude
     */
    public String getMagnitude() {
        return mMagnitude;
    }
}
