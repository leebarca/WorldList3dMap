package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ExpediaSearch extends BaseActivity {

    private View flightsButton;
    private View hotelsButton;
    private View activitiesButton;
    private ImageButton back_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_planning);

        // Initialize button references
        flightsButton = findViewById(R.id.flights_element);
        hotelsButton = findViewById(R.id.hotels_element);
        activitiesButton = findViewById(R.id.activities_element);
        back_button = findViewById(R.id.back_button);

        flightsButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        hotelsButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        activitiesButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));

        highlightActiveButton(flightsButton);

        // Load the Flights fragment by default
        replaceFragment(new FlightFragment(), flightsButton);

        // Set up button click listeners for GridLayout buttons
        findViewById(R.id.flights_element).setOnClickListener(v -> replaceFragment(new FlightFragment(), flightsButton));
        findViewById(R.id.hotels_element).setOnClickListener(v -> replaceFragment(new HotelsFragment(), hotelsButton));
        findViewById(R.id.activities_element).setOnClickListener(v -> replaceFragment(new ActivitiesFragment(), activitiesButton));

        back_button.setOnClickListener(v -> {
            // Handle click event
            finish();
        });
    }

    private void replaceFragment(Fragment fragment, View activeButton) {

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
        activitiesButton.setSelected(false);
    }

    private void highlightActiveButton(View button) {
        // Set the active button to a dark gray background
        resetButtonColors(); // Ensure only one button is highlighted
        button.setSelected(true);
    }
}