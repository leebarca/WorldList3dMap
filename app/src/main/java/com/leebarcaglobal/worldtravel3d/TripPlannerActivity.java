package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TripPlannerActivity extends BaseActivity {

    private NonScrollListView tripListView;
    private Button addTripButton;
    private TextView empty_trip_text;

    private TripAdapter tripAdapter;
    private TripDatabaseHelper dbHelper;

    private static final int ADD_TRIP_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_planner);

        tripListView = findViewById(R.id.trip_list_view);
        addTripButton = findViewById(R.id.add_trip_button);
        empty_trip_text = findViewById(R.id.empty_trip_text);

        dbHelper = new TripDatabaseHelper(this);

        loadTrips();

        // Add Trip button
        addTripButton.setOnClickListener(v -> {
            Intent intent = new Intent(TripPlannerActivity.this, AddTrip.class);
            startActivityForResult(intent, ADD_TRIP_REQUEST_CODE);
        });

        tripListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(TripPlannerActivity.this, ViewEditTripActivity.class);
            long tripId = dbHelper.getAllTripIds().get(position);
            intent.putExtra("trip_id", tripId);
            startActivity(intent);
        });

        // Country Details Icon
        ImageView countryDetailsIcon = findViewById(R.id.country_details_icon);
        countryDetailsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(TripPlannerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Home page
        @SuppressLint({"MissingInflatedId",
                "LocalSuppress"}) ImageView mapIcon = findViewById(R.id.explore_icon);
        mapIcon.setOnClickListener(v -> {
            Intent intent = new Intent(TripPlannerActivity.this, ExploreActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Languages Icon
        ImageView settingsIcon = findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(TripPlannerActivity.this, LanguageActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Profile Icon
        ImageView profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(v -> {
            // No action, already on the Map page
        });
    }

    private void loadTrips() {
        List<Long> tripIds = dbHelper.getAllTripIds();
        List<String> tripDetails = dbHelper.getAllTrips();

        if (tripDetails.isEmpty()) {
            empty_trip_text.setVisibility(View.VISIBLE);
            tripListView.setVisibility(View.GONE);
        } else {
            empty_trip_text.setVisibility(View.GONE);
            tripListView.setVisibility(View.VISIBLE);
            tripAdapter = new TripAdapter(this, tripIds, tripDetails, this::loadTrips);
            tripListView.setAdapter(tripAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TRIP_REQUEST_CODE && resultCode == RESULT_OK) {
            loadTrips();
        }
    }
}