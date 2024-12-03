package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TripPlannerActivity extends AppCompatActivity {

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
        // Fetch trips and their IDs from the database
        List<Long> tripIds = dbHelper.getAllTripIds();
        List<String> tripDetails = dbHelper.getAllTrips();

        if (tripDetails.isEmpty()) {
            // Show empty message and hide the list
            empty_trip_text.setVisibility(View.VISIBLE);
            tripListView.setVisibility(View.GONE);
        } else {
            // Hide empty message and show the list
            empty_trip_text.setVisibility(View.GONE);
            tripListView.setVisibility(View.VISIBLE);

            // Set up the adapter with trip details and IDs
            tripAdapter = new TripAdapter(this, tripIds, tripDetails, this::loadTrips);
            tripListView.setAdapter(tripAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TRIP_REQUEST_CODE && resultCode == RESULT_OK) {
            loadTrips();
            Toast.makeText(this, R.string.trip_successful, Toast.LENGTH_SHORT).show();
        }
    }
}