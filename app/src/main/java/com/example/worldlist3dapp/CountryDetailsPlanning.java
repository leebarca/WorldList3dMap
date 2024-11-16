package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CountryDetailsPlanning extends AppCompatActivity {

    private View flightsButton;
    private View hotelsButton;
    private View packagesButton;
    private View activitiesButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_planning);

        // Initialize button references
        flightsButton = findViewById(R.id.flights_element);
        hotelsButton = findViewById(R.id.hotels_element);
        packagesButton = findViewById(R.id.packages_element);
        activitiesButton = findViewById(R.id.activities_element);

        // Load the Flights fragment by default
        replaceFragment(new FlightFragment(), R.id.flights_element);

        // Set up button click listeners for GridLayout buttons
        findViewById(R.id.flights_element).setOnClickListener(v -> replaceFragment(new FlightFragment(), R.id.flights_element));
        findViewById(R.id.hotels_element).setOnClickListener(v -> replaceFragment(new HotelsFragment(), R.id.hotels_element));
        findViewById(R.id.packages_element).setOnClickListener(v -> replaceFragment(new PackagesFragment(), R.id.packages_element));
        findViewById(R.id.activities_element).setOnClickListener(v -> replaceFragment(new ActivitiesFragment(), R.id.activities_element));
    }

    private void replaceFragment(Fragment fragment, int activeButtonId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,
                            fragment);
        transaction.commit();

        // Highlight the active button and reset others
        resetButtonColors();
        highlightActiveButton(activeButtonId);
    }

    private void resetButtonColors() {
        // Reset all buttons to default background color
        int defaultColour = ContextCompat.getColor(this, R.color.app_background_color);
        flightsButton.setBackgroundColor(defaultColour);
        hotelsButton.setBackgroundColor(defaultColour);
        packagesButton.setBackgroundColor(defaultColour);
        activitiesButton.setBackgroundColor(defaultColour);
    }

    private void highlightActiveButton(int buttonId) {
        // Set the active button to a dark gray background
        int activeColour = ContextCompat.getColor(this, R.color.list_background_colour);
        findViewById(buttonId).setBackgroundColor(activeColour);
    }
}