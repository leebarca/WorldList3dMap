package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Language Selection: Allows users to choose their preferred language for the app (e.g., English, Spanish, French, etc.).
        // Theme: Options for switching between dark mode and light mode (or other themes) to suit the user's preference.
        // Units of Measurement: Users can choose between metric (Celsius, km, kg) or imperial (Fahrenheit, miles, pounds) units for country statistics and weather information.
        // Notifications: Enable or disable push notifications for the app (e.g., weather alerts, new country updates, etc.).

        // Location Settings
        // Location Access: Option for users to grant or deny location access for features such as displaying nearby countries or local information.
        // Map Settings: Allow users to adjust map display preferences, such as satellite view, terrain view, or 3D maps.

        // Privacy Settings
        // Data Sharing: Let users control how their data is shared with third parties (e.g., opting into sharing location data for improved recommendations).
        // Account Privacy: Users can enable/disable options like sharing their country preferences or profile information with other users.

        // Account Settings
        // Sign Out: Allow users to log out of their account, if they are signed in.
        // Change Password: Provide users with the option to change their password (if applicable).
        // Link Social Accounts: Users can link their social media accounts (e.g., Facebook, Google) to streamline sign-ins or share country details.

        // Country Details Icon
        ImageView countryDetailsIcon = findViewById(R.id.country_details_icon);
        countryDetailsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Map Icon (current activity, so no need for action here)
        @SuppressLint({"MissingInflatedId",
                "LocalSuppress"}) ImageView mapIcon = findViewById(R.id.explore_icon);
        mapIcon.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, ExploreActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Settings Icon
        ImageView settingsIcon = findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(v -> {
            // No action, already on the Map page
        });

        // Profile Icon
        ImageView profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });
    }
}