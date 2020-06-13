package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* JSON Formatters:
 * https://jsonformatter.curiousconcept.com/
 * http://jsonprettyprint.com/
 */


/*
 * {@link EarthquakeAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Earthquake} objects.
 * */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    //variables for the location string split
    String locationOffset;
    String primaryLocation;

    // constant for splitting the location string
    private static final String LOCATION_SEPARATOR = " of ";


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param earthquakes A List of Earthquake objects to display in a list
     */
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        // Here we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for 3 TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID magnitude
        TextView magTextView = listItemView.findViewById(R.id.magnitude);
        // Get the magnitude from the current Earthquake object and
        // set this text on the mag TextView
        magTextView.setText(currentEarthquake.getMagnitude());



        //get the location String from the Earthquake then send it to the splitLocation() helper method to be split
        String locationString = currentEarthquake.getLocation();
        splitLocation(locationString);

        // Find the TextView in the list_item.xml layout with the IDs primary_location and location_offset
        TextView primaryLocationTextView = listItemView.findViewById(R.id.primary_location);
        TextView locationOffsetTextView = listItemView.findViewById(R.id.location_offset);

        // set the text on the primary_location and location_offset TextViews
        locationOffsetTextView.setText(locationOffset);
        primaryLocationTextView.setText(primaryLocation);







        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView in the list_item.xml layout with the ID date
        TextView dateTextView = listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);

        // Find the TextView in the list_item.xml layout with view ID time
        TextView timeView = listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the split location string  from a location String
     */
    private void splitLocation(String location) {
        if(location.contains(LOCATION_SEPARATOR)) {
            int split = location.indexOf(LOCATION_SEPARATOR);
            locationOffset = location.substring(0, split + 3);
            primaryLocation = location.substring(split + LOCATION_SEPARATOR.length());
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }
    }
}

