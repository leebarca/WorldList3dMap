package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class TripPlannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_planner);

        // A place for users to plan or track their trips.
        // Features:
        // Users can add planned trips with:
        // Country name.
        // Travel dates.
        // Notes or activities to do in the country.
        // Display a list of upcoming trips.

        // Use AI to generate trip itinerary for user depending on the country they choose and duration

        // Country Details Icon
        ImageView countryDetailsIcon = findViewById(R.id.country_details_icon);
        countryDetailsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(TripPlannerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Map Icon (current activity, so no need for action here)
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
}