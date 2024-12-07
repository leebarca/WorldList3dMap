package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CountryDetailsPlanning extends BaseActivity {

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

        flightsButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        hotelsButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        packagesButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        activitiesButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));

        highlightActiveButton(flightsButton);

        String country_name = getIntent().getStringExtra("country_name");

        // Load the Flights fragment by default
        replaceFragment(new FlightFragment(), flightsButton, country_name);

        // Set up button click listeners for GridLayout buttons
        findViewById(R.id.flights_element).setOnClickListener(v -> replaceFragment(new FlightFragment(), flightsButton, country_name));
        findViewById(R.id.hotels_element).setOnClickListener(v -> replaceFragment(new HotelsFragment(), hotelsButton, country_name));
        findViewById(R.id.packages_element).setOnClickListener(v -> replaceFragment(new PackagesFragment(), packagesButton, country_name));
        findViewById(R.id.activities_element).setOnClickListener(v -> replaceFragment(new ActivitiesFragment(), activitiesButton, country_name));
    }

    private void replaceFragment(Fragment fragment, View activeButton, String country) {
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
        highlightActiveButton(activeButton);
    }

    private void resetButtonColors() {
        // Reset all buttons to default background color
        flightsButton.setSelected(false);
        hotelsButton.setSelected(false);
        packagesButton.setSelected(false);
        activitiesButton.setSelected(false);
    }

    private void highlightActiveButton(View button) {
        // Set the active button to a dark gray background
        resetButtonColors(); // Ensure only one button is highlighted
        button.setSelected(true);
    }
}