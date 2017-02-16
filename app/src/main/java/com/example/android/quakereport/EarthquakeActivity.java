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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    ArrayList<Earthquake> earthquakesList = new ArrayList<>();
    private EarthquakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a list of earthquake locations.
        ArrayList<Earthquake> earthquakesList = new ArrayList<>();

        // Request latest top 10 Earthquakes in AsynBackgroundTask
        EarthquakeAsyncTask asyncTask = new EarthquakeAsyncTask();
        asyncTask.execute(QueryUtils.QUERY);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new EarthquakeAdapter(this, earthquakesList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        if (earthquakeListView != null) {
            earthquakeListView.setAdapter(adapter);

            //Display Earthquake info on click
            displayEarthquakeInfo(earthquakeListView, adapter);
        }
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {
        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            JSONObject jsonResponse = null;
            // MakeHTTPRequest with given query, assign it to jsonResponse
            try {
                jsonResponse = new JSONObject((HttpUtils.makeHttpRequest(urls[0])));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return QueryUtils.extractEarthquakes(jsonResponse);
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            // Clear the adapter of previous earthquake data
            adapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (earthquakes != null && !earthquakes.isEmpty()) {
                adapter.addAll(earthquakes);

//                # Option 2 : call the adapter.notifyDataSetChanged()
//                earthquakesList.addAll(earthquakes);
//                adapter.notifyDataSetChanged();
            }
        }
    }

    private void displayEarthquakeInfo(ListView earthquakeListView, final EarthquakeAdapter adapter) {
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Find the current earthquake that was clicked on
                Earthquake currentQuake = adapter.getItem(position);

                if (currentQuake != null) {
                    // Convert the String URL into a URI object (to pass into the Intent constructor)
                    Uri quakeUri = Uri.parse(currentQuake.getUrl());

                    // Create a new intent to view the earthquake URI
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, quakeUri);

                    // Send the intent to launch a new activity
                    startActivity(webIntent);
                }
            }
        });
    }
}
