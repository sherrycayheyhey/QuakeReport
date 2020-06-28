/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/* How to make make a list with custom shit
 *1. create an xml for what each item on the list will look like which includes:
 *      --all the views with ids in probably a horizontal linear layout, this is list_item.xml
 *      --you'll also need another xml layout which will be populated with the list items and is probably
 *      a vertical linear layout
 *      --this is earthquake_activity.xml
 *
 * 2. create the class for the object you want to make:
 *      --this class will contain the instance variables, constructor, and getters,
 *      --this is Earthquake.java
 *
 * 3. create the class for the adapter:
 *      --this connects the list_item.xml views with the respective instance variable of the Earthquake object
 *      --this also deals with recycling the views
 *      --this is EarthquakeAdapter.java
 *
 * 4. finally, create an adapter and set the data source to be the object that was created in step 2
 *      and then get a reference to the ListView and attach the adapter to it
 *      --EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);
 *      --ListView listView = findViewById(R.id.list);
          listView.setAdapter(earthquakeAdapter);
 *      --this is done in EarthquakeActivity.java
 */


public class EarthquakeActivity extends AppCompatActivity {

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    // global variable in order to access and modify the instance of the EarthquakeAdapter ListView
    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Get a reference to the ListView, and attach the adapter to the listView.
        //this populates the user interface
        ListView listView = findViewById(R.id.list);
        //create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        //set the adapter on the {@link ListView}
        listView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                // an implicit intent is the better choice to use here because it will let the user's
                // defaults (like default web browser) be used to open the link instead of us explicitly
                // choosing which app should open the URL
                // to do this, we specify which action we want to perform (viewing something) and then
                // the URI will be passed and Android will sort out the best app to handle the content
                // which will probably be a browser in the case but might be mapping app if the URI
                // represented a location instead
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Start the AsyncTask to fetch the earthquake data
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    /*  In this app we want to use the inner class declaration:
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>{}
    because an AsyncTask should be used when getting data from the server so it isn't on the UI thread

    the generics used are:
        --String for the string of the URL that is sent to the task
        --Void because we aren't going to update the user on the progress
        --List<Earthquake> because we want a list of Earthquake objects back as the result of this background task

    In this case, List<Earthquake> is used instead of ArrayList<Earthquake> because we want to be flexible. It's best
    to use List whenever you need a list object (ArrayList or LinkedList) so the code is flexible and you can swap
    them out if needed. List is an interface and ArrayList is a concrete class so you CANNOT create an object instance
    of List because, as an interface, its methods aren't implemented. What you CAN do is create an object instance
    of ArrayList and specify a generic parameter for E, because it's a concrete class. For example:
        List<Earthquake> earthquakeList = new ArrayList<Earthquake>();
        List<Earthquake> earthquakeList = new LinkedList<Earthquake>();
 */

    //inner class to run the AsyncTask
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        /**
         * This method is invoked (or called) on a background thread, so we can perform long-running
         * operations like making a network request.
         *
         * It is NOT okay to update the UI from a background thread, so we just return an {@link List<Earthquake>}
         * object as the result.
         */

        @Override
        protected List<Earthquake> doInBackground(String... urls) { //urls so this works with any String url
            // Don't perform the request if there are no URLs, or the first URL is null.
            if(urls.length < 1 || urls[0] == null) {
                return null;
            }

            // Perform the HTTP request for earthquake data and process the response.
            List<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);
            //return the Event object
            return result;
        }

        // when we get here we need to update the ListView and the only way to do that is to update the
        // data set within the EarthquakeAdapter so in order to access and modify the instance of the
        // EarthquakeAdapter we need to make it a global variable in the EarthquakeActivity
        protected void onPostExecute(List<Earthquake> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
