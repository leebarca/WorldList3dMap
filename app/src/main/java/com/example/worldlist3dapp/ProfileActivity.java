package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Personal Information
        // Profile Picture: An option for users to upload and change their profile picture.
        // Name & Email: Display and allow users to edit their name and email address.
        // Bio: Option for users to add a short bio or description about themselves.
        // Country Preferences: Section where users can define preferred countries or regions (e.g., favorite countries they want to receive updates for).

        // Saved Countries or Destinations
        // Favorites: A list of saved or favorited countries, showing a thumbnail image, name, and brief details (e.g., flag, population).
        // Visited Countries: A record of the countries that the user has visited (could be shown with a different icon or color).
        // Wish List: A section where users can mark countries they wish to visit in the future.

        // Activity History
        // Visited Attractions: If users have interacted with the attractions for each country, show a list of attractions they've explored.
        // Search History: Display a history of past searches (e.g., previously viewed countries or regions).

        // Social Sharing (Optional)
        // Share Profile: Allow users to share their profile or certain favorite countries on social media or within the app.
        // Leaderboard: If applicable, show a leaderboard of countries or destinations the user has visited or interacted with the most.

        // Country Details Icon
        ImageView countryDetailsIcon = findViewById(R.id.country_details_icon);
        countryDetailsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Map Icon (current activity, so no need for action here)
        @SuppressLint({"MissingInflatedId",
                "LocalSuppress"}) ImageView mapIcon = findViewById(R.id.explore_icon);
        mapIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ExploreActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Settings Icon
        ImageView settingsIcon = findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
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