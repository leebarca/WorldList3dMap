package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // Search Function: Allow users to search for countries by name, continent, or even by top attractions or weather conditions.
        // Filter Options: Include filtering options like country size, population, region, or best time to visit.

        // Featured Countries
        // Trending Countries: A list of countries that are currently trending (perhaps based on user activity or global events).
        // Recommended Countries: A section that suggests countries based on the user's preferences or activity.

        // Country Categories
        // Top Destinations: Show the top destinations or most popular countries in the app.
        // New Additions: List newly added countries with 3D images and other info.
        // Regions: Show popular regions (e.g., Europe, Asia, North America) and allow users to browse by region.

        // Country Comparison
        // Compare Countries: Allow users to compare countries side by side in terms of population, size, best time to visit, languages spoken, etc.

        // Map View (Optional)
        // A map view to visualize the countries and click to explore each one. This could be a globe or a flat map view.

        // Country Details Icon
        ImageView countryDetailsIcon = findViewById(R.id.country_details_icon);
        countryDetailsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Map Icon (current activity, so no need for action here)
        @SuppressLint({"MissingInflatedId",
                "LocalSuppress"}) ImageView mapIcon = findViewById(R.id.explore_icon);
        mapIcon.setOnClickListener(v -> {
            // No action, already on the Map page
        });

        // Settings Icon
        ImageView settingsIcon = findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Profile Icon
        ImageView profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });
    }
}