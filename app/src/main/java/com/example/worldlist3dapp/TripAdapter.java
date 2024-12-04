package com.example.worldlist3dapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TripAdapter extends BaseAdapter {

    private Context context;
    private List<Long> tripIds;
    private List<String> tripDetails; // Full trip details (e.g., "Country (start - end)")
    private TripDatabaseHelper dbHelper;
    private Runnable refreshCallback;

    public TripAdapter(Context context, List<Long> tripIds, List<String> tripDetails, Runnable refreshCallback) {
        this.context = context;
        this.tripIds = tripIds;
        this.tripDetails = tripDetails;
        this.dbHelper = new TripDatabaseHelper(context);
        this.refreshCallback = refreshCallback;
    }

    @Override
    public int getCount() {
        return tripDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return tripDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tripIds.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_trip, parent, false);
        }

        TextView tripLabel = convertView.findViewById(R.id.trip_label);
        TextView tripDates = convertView.findViewById(R.id.trip_dates);
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        // Split the trip details into country and dates
        String fullDetails = tripDetails.get(position);
        String[] parts = fullDetails.split("\\(", 2); // Split at the first '('
        String country = parts[0].trim();
        String dates = parts.length > 1 ? parts[1].replace(")", "").trim() : "";

        // Set the country name and dates
        tripLabel.setText(country);
        tripDates.setText(dates);

        // Delete button functionality
        deleteButton.setOnClickListener(v -> {
            long tripId = tripIds.get(position);
            dbHelper.deleteTrip(tripId);

            tripDetails.remove(position);
            tripIds.remove(position);

            notifyDataSetChanged();
            refreshCallback.run();
        });

        return convertView;
    }
}