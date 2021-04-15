package com.example.earthquakedataproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.earthquakedataproject.model.Earthquake;


public class DetailedEarthquakeActivity extends Activity implements View.OnClickListener {

    TextView locationView;
    TextView magnitudeView;
    TextView dateView;
    TextView latLongView;
    TextView depthView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_earthquake_activity);

        locationView = (TextView) findViewById(R.id.location_view);
        magnitudeView = (TextView) findViewById(R.id.magnitude_view);
        dateView = (TextView) findViewById(R.id.date_view);
        latLongView = (TextView) findViewById(R.id.lat_long_view);
        depthView = (TextView) findViewById(R.id.depth_view);

        locationView.setText(getIntent().getStringExtra("location"));
        magnitudeView.setText(getIntent().getStringExtra("magnitude"));
        dateView.setText(getIntent().getStringExtra("date"));
        latLongView.setText(getIntent().getStringExtra("latLong"));
        depthView.setText(getIntent().getStringExtra("depth"));
    }

    @Override
    public void onClick(View view) {
    }

    public static void startActivity(Context context, Earthquake earthquake) {
        Intent intent = new Intent(context, DetailedEarthquakeActivity.class);
        intent.putExtra("location", earthquake.getLocation());
        intent.putExtra("magnitude", earthquake.getMagnitude());
        intent.putExtra("date", earthquake.getPubDate());
        intent.putExtra("latLong", earthquake.getLatLong());
        intent.putExtra("depth", earthquake.getDepth());
        context.startActivity(intent);
    }
}
