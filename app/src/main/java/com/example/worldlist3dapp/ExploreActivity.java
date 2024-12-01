package com.example.worldlist3dapp;

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

    private OrderedCountriesAdapter adapter;
    private ListView countryListView;
    private List<CountryInfo> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // Initialize data

        // ListView and Adapter
        countryListView = findViewById(R.id.top_countries_list);
        countries = CountryData.getCountriesInfo(this);
        adapter = new OrderedCountriesAdapter(this, new ArrayList<>());
        countryListView.setAdapter(adapter);

        // Set OnItemClickListener for ListView
        countryListView.setOnItemClickListener((parent, view, position, id) -> {
            CountryInfo selectedCountry = adapter.getItem(position);

            Intent intent = new Intent(ExploreActivity.this, CountryDetailActivity.class);
            intent.putExtra("countryImageResId", selectedCountry.getImageResId());
            intent.putExtra("countryName", selectedCountry.getName());
            intent.putExtra("countryCapital", selectedCountry.getCapital());
            intent.putExtra("countryPopulation", selectedCountry.getPopulation());
            intent.putExtra("countryLanguage", selectedCountry.getLanguage());
            intent.putExtra("countryReligion", selectedCountry.getReligion());
            intent.putExtra("countryContinent", selectedCountry.getContinent());
            intent.putExtra("countryArea", selectedCountry.getArea());
            intent.putExtra("countryFlag", selectedCountry.getFlagResId());
            intent.putExtra("countryDescription", selectedCountry.getDescription());
            intent.putExtra("firstAttractionName", selectedCountry.getFirstAttractionName());
            intent.putExtra("firstAttractionDetails", selectedCountry.getFirstAttractionDetails());
            intent.putExtra("firstAttractionImage", selectedCountry.getFirstAttractionImage());
            intent.putExtra("secondAttractionName", selectedCountry.getSecondAttractionName());
            intent.putExtra("secondAttractionDetails", selectedCountry.getSecondAttractionDetails());
            intent.putExtra("secondAttractionImage", selectedCountry.getSecondAttractionImage());
            intent.putExtra("thirdAttractionName", selectedCountry.getThirdAttractionName());
            intent.putExtra("thirdAttractionDetails", selectedCountry.getThirdAttractionDetails());
            intent.putExtra("thirdAttractionImage", selectedCountry.getThirdAttractionImage());
            intent.putExtra("bestTimeVisitArray", selectedCountry.getBestTimeVisit());
            intent.putExtra("factInfo", selectedCountry.getFact());
            intent.putExtra("websiteInfo", selectedCountry.getWebsite());
            intent.putExtra("weatherInfo", selectedCountry.getWeather());
            intent.putExtra("cuisineArray", selectedCountry.getCuisine());
            intent.putExtra("safetyArray", selectedCountry.getSafety());
            intent.putExtra("currency", selectedCountry.getCurrency());

            startActivity(intent);
        });

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
                getString(R.string.smallest_population_countries),
                getString(R.string.most_visited_countries),
                getString(R.string.least_visited_countries)
        };

        // Spinner Adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, categories);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
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
        // Create a new list to store filtered countries
        List<CountryInfo> filteredCountries = new ArrayList<>();

        // Filter countries based on the selected category
        if (category.equals(getString(R.string.biggest_countries))) {
            List<String> country_list = List.of(getString(R.string.russia), getString(R.string.canada), getString(R.string.china), getString(R.string.united_states), getString(R.string.brazil));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.smallest_countries))) {
            List<String> country_list = List.of(getString(R.string.vatican_city), getString(R.string.monaco), getString(R.string.nauru), getString(R.string.tuvalu), getString(R.string.san_marino));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.beach_countries))) {
            List<String> country_list = List.of(getString(R.string.maldives), getString(R.string.seychelles), getString(R.string.thailand), getString(R.string.australia), getString(R.string.bahamas));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.historic_countries))) {
            List<String> country_list = List.of(getString(R.string.egypt), getString(R.string.greece), getString(R.string.italy), getString(R.string.china), getString(R.string.india));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.dangerous_countries))) {
            List<String> country_list = List.of(getString(R.string.yemen), getString(R.string.sudan), getString(R.string.south_sudan), getString(R.string.afghanistan), getString(R.string.drc_congo));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.safest_countries))) {
            List<String> country_list = List.of(getString(R.string.iceland), getString(R.string.ireland), getString(R.string.austria), getString(R.string.new_zealand), getString(R.string.singapore));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.largest_population_countries))) {
            List<String> country_list = List.of(getString(R.string.india), getString(R.string.china), getString(R.string.united_states), getString(R.string.indonesia), getString(R.string.pakistan));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.smallest_population_countries))) {
            List<String> country_list = List.of(getString(R.string.vatican_city), getString(R.string.tuvalu), getString(R.string.nauru), getString(R.string.palau), getString(R.string.san_marino));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.most_visited_countries))) {
            List<String> country_list = List.of(getString(R.string.france), getString(R.string.spain), getString(R.string.united_states), getString(R.string.china), getString(R.string.italy));
            filteredCountries = getOrderedCountries(country_list, countries);
        } else if (category.equals(getString(R.string.least_visited_countries))) {
            List<String> country_list = List.of(getString(R.string.nauru), getString(R.string.tuvalu), getString(R.string.marshallislands), getString(R.string.kiribati), getString(R.string.micronesia));
            filteredCountries = getOrderedCountries(country_list, countries);
        }

        // Update the adapter with the filtered list
        adapter.updateCountries(filteredCountries);
    }

    private List<CountryInfo> getOrderedCountries(List<String> orderedNames, List<CountryInfo> allCountries) {
        List<CountryInfo> orderedCountries = new ArrayList<>();
        for (String name : orderedNames) {
            for (CountryInfo country : allCountries) {
                if (country.getName().equals(name)) {
                    orderedCountries.add(country);
                    break;
                }
            }
        }
        return orderedCountries;
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