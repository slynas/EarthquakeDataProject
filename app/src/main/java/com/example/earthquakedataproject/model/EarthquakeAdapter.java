package com.example.earthquakedataproject.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakedataproject.util.ColourCheck;
import com.example.earthquakedataproject.DetailedEarthquakeActivity;
import com.example.earthquakedataproject.R;

import java.util.List;


public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {

    private List<Earthquake> earthquakes;
    ColourCheck colourCheck = new ColourCheck();

    public EarthquakeAdapter (List<Earthquake> earthquakeList) {
        earthquakes = earthquakeList;
    }

    public class EarthquakeViewHolder extends RecyclerView.ViewHolder {

        private final TextView location;
        private final TextView magnitude;

        public EarthquakeViewHolder(@NonNull View view) {
            super(view);
            location = view.findViewById(R.id.location_row);
            magnitude = view.findViewById(R.id.magnitude_row);
        }

        public EarthquakeViewHolder(@NonNull View itemView, TextView location, TextView magnitude) {
            super(itemView);
            this.location = location;
            this.magnitude = magnitude;
        }

        public TextView getLocation() {
            return location;
        }

        public TextView getMagnitude() {
            return magnitude;
        }

        public void bindData(Earthquake earthquake) {
            location.setText(earthquake.getLocation());
            magnitude.setText(String.valueOf(earthquake.getMagnitude()));
        }
    }

    @Override
    public EarthquakeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);
        return new EarthquakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        holder.bindData(earthquakes.get(position));
        holder.itemView.setOnClickListener(
                view -> {
                    DetailedEarthquakeActivity.startActivity(view.getContext(), earthquakes.get(position));
                }
        );
        for (Earthquake earthquake : earthquakes)
            holder.itemView.setBackgroundResource(colourCheck.getColourForMagnitude(earthquake.getMagnitude()));
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.text_row_item;
    }
}
