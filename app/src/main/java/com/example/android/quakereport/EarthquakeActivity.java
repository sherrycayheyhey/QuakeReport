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

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create an ArrayList of Earthquake objects and add some Earthquake objects to it
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("San Francisco", "Feb 2, 2016", "7.2"));
        earthquakes.add(new Earthquake("London", "July 20, 2015", "6.1"));
        earthquakes.add(new Earthquake("Tokyo", "Nov 10, 2014", "3.9"));
        earthquakes.add(new Earthquake("Mexico City", "May 3, 2014", "5.4"));
        earthquakes.add(new Earthquake("Moscow", "Jan 31, 2013", "2.8"));
        earthquakes.add(new Earthquake("Rio De Janeiro", "Aug 19, 2012", "4.9"));
        earthquakes.add(new Earthquake("Paris", "Oct 30, 2011", "1.6"));

        // Create an {@link EarthquakeAdapter}, whose data source is a list of
        // {@link Earthquake}s. The adapter knows how to create list item views for each item
        // in the list.
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(earthquakeAdapter);
    }
}
