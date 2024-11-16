package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        String country_name = getIntent().getStringExtra("country_name");

        // Load the Flights fragment by default
        replaceFragment(new FlightFragment(), R.id.flights_element, country_name);

        // Set up button click listeners for GridLayout buttons
        findViewById(R.id.flights_element).setOnClickListener(v -> replaceFragment(new FlightFragment(), R.id.flights_element, country_name));
        findViewById(R.id.hotels_element).setOnClickListener(v -> replaceFragment(new HotelsFragment(), R.id.hotels_element, country_name));
        findViewById(R.id.packages_element).setOnClickListener(v -> replaceFragment(new PackagesFragment(), R.id.packages_element, country_name));
        findViewById(R.id.activities_element).setOnClickListener(v -> replaceFragment(new ActivitiesFragment(), R.id.activities_element, country_name));
    }

    private void replaceFragment(Fragment fragment, int activeButtonId, String country) {
        // Create a Bundle to pass the data
        Bundle args = new Bundle();
        args.putString("country", country);

        // Set the arguments to the fragment
        fragment.setArguments(args);

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