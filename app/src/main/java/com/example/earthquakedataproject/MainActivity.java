package com.example.earthquakedataproject;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakedataproject.model.Earthquake;
import com.example.earthquakedataproject.model.EarthquakeAdapter;
import com.example.earthquakedataproject.parser.EarthquakeXmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    RecyclerView recyclerView;
    private Button startButton;
    private Button dateRangeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        startButton = (Button) findViewById(R.id.button);
        startButton.setOnClickListener(this);

        dateRangeButton = (Button) findViewById(R.id.pick_date_button);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        try {
            startProgress();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startProgress() throws MalformedURLException {
        List<Earthquake> earthquakes = null;
        earthquakes = new Task().doInBackground();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewData);

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(earthquakes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(earthquakeAdapter);
    }

    static class Task extends AsyncTask<String, Void, List<Earthquake>> {

        private URL url = new URL("http://quakes.bgs.ac.uk/feeds/MhSeismology.xml");

        Task() throws MalformedURLException {
        }

        @Override
        protected List<Earthquake> doInBackground(String... strings) {

            List<Earthquake> earthquakes = null;
            try {
                EarthquakeXmlParser parser = new EarthquakeXmlParser();
                InputStream is = url.openStream();
                earthquakes = parser.parseDescription(is);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return earthquakes;
        }
    }
}