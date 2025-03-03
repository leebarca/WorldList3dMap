package com.leebarcaglobal.worldtravel3d.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.fragments.PlannerFragment;
import com.leebarcaglobal.worldtravel3d.utils.TripDatabaseHelper;

import java.util.List;

public class TripAdapter extends BaseAdapter {

    private Context context;
    private List<Long> tripIds;
    private List<String> tripDetails; // Full trip details (e.g., "Country (start - end)")
    private TripDatabaseHelper dbHelper;
    private Runnable refreshCallback;

    private PlannerFragment plannerFragment;

    public TripAdapter(Context context, List<Long> tripIds, List<String> tripDetails, Runnable refreshCallback,
                       PlannerFragment _plannerFragment) {
        this.context = context;
        this.tripIds = tripIds;
        this.tripDetails = tripDetails;
        this.dbHelper = new TripDatabaseHelper(context);
        this.refreshCallback = refreshCallback;
        this.plannerFragment = _plannerFragment;
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
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        // Set trip details
        tripLabel.setText(tripDetails.get(position));

        // OnClickListener for the row
        convertView.setOnClickListener(v -> {
            long tripId = tripIds.get(position);

            // Intent to View/Edit Trip
//            Intent intent = new Intent(context, ViewEditTripActivity.class);
//            intent.putExtra("trip_id", tripId); // Pass trip ID
//            context.startActivity(intent);

            plannerFragment.callViewEditTripFragment(tripId);
        });

        // Delete button functionality
        deleteButton.setOnClickListener(v -> {
            long tripId = tripIds.get(position);
            dbHelper.deleteTrip(tripId);

            // Update lists
            tripDetails.remove(position);
            tripIds.remove(position);

            // Notify adapter and refresh view
            notifyDataSetChanged();
            refreshCallback.run();
        });

        return convertView;
    }
}