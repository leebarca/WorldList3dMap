package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity {

    private CountryAdapter countryAdapter;
    private List<CountryInfo> countries;
    private List<CountryInfo> filteredCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // Initialize data
        countries = CountryData.getCountriesInfo(this);
        filteredCountries = new ArrayList<>();

        // ListView and Adapter
        ListView countriesListView = findViewById(R.id.top_countries_list);
        countryAdapter = new CountryAdapter(this, filteredCountries);
        countriesListView.setAdapter(countryAdapter);

        // Spinner for category selection
        Spinner categorySpinner = findViewById(R.id.category_spinner);

        // Categories for Spinner
        String[] categories = {
                getString(R.string.biggest_countries),
                getString(R.string.smallest_countries),
                getString(R.string.beach_countries),
                getString(R.string.historic_countries),
                getString(R.string.dangerous_countries),
                getString(R.string.safest_countries),
                getString(R.string.largest_population_countries),
                getString(R.string.smallest_population_countries)
        };

        // Spinner Adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);

        // Spinner item selection logic
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];
                updateListView(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Bottom navigation setup (reuse your existing setup)
        setupBottomNavigation();
    }

    private void updateListView(String category) {
        filteredCountries.clear();

        if (category.equals(getString(R.string.biggest_countries))) {
            for (CountryInfo country : countries) {
                if (Integer.parseInt(country.getArea()) > 1000000) {
                    filteredCountries.add(country);
                }
            }
        } else if (category.equals(getString(R.string.smallest_countries))) {
            for (CountryInfo country : countries) {
                if (Integer.parseInt(country.getArea()) < 1000) {
                    filteredCountries.add(country);
                }
            }
        } else if (category.equals(getString(R.string.beach_countries))) {
            // Add your custom filtering logic for beach countries
        } else if (category.equals(getString(R.string.historic_countries))) {
            // Add your custom filtering logic for historic countries
        }
        // ... Add logic for other categories

        // Limit to top 5 results
        if (filteredCountries.size() > 5) {
            filteredCountries = filteredCountries.subList(0, 5);
        }

        // Notify adapter of changes
        countryAdapter.updateCountries(filteredCountries);
    }

    private void setupBottomNavigation() {
        ImageView countryDetailsIcon = findViewById(R.id.country_details_icon);
        countryDetailsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        ImageView exploreIcon = findViewById(R.id.explore_icon);
        exploreIcon.setOnClickListener(v -> {
            // Already in ExploreActivity
        });

        ImageView settingsIcon = findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, LanguageActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        ImageView trip_planning = findViewById(R.id.profile_icon);
        trip_planning.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, TripPlannerActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });
    }
}