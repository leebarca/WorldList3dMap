package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends BaseActivity {

    private ListView countryListView;
    private List<CountryInfo> countries;
    private CountryAdapter adapter;
    private EditText searchBar;
    private ImageView filter;
    private ScrollView filterScrollContainer; // Updated to ScrollView
    private LinearLayout iconsBottom;
    private Set<String> selectedLanguages = new HashSet<>();
    private Set<String> selectedContinents = new HashSet<>();
    private Set<String> selectedReligions = new HashSet<>();
    private Set<String> selectedMonths = new HashSet<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryListView = findViewById(R.id.country_list_view);
        searchBar = findViewById(R.id.search_bar);
        filter = findViewById(R.id.filter_icon);
        filterScrollContainer = findViewById(R.id.filter_scroll_container); // Reference to ScrollView
        iconsBottom = findViewById(R.id.icons_bottom);
        ImageView countryDetailsIcon = findViewById(R.id.country_details_icon);
        ImageView mapIcon = findViewById(R.id.explore_icon);
        ImageView settingsIcon = findViewById(R.id.settings_icon);
        ImageView profileIcon = findViewById(R.id.profile_icon);
        GridLayout languageGrid = findViewById(R.id.language_filter_grid);
        GridLayout continentGrid = findViewById(R.id.continent_filter_grid);
        GridLayout religionGrid = findViewById(R.id.religion_filter_grid);
        GridLayout bestMonthsToVisitGrid = findViewById(R.id.month_filter_grid);

        String[] languages = {getString(R.string.english), getString(R.string.spanish), getString(R.string.french), getString(R.string.german), getString(R.string.italian), getString(R.string.portuguese), getString(R.string.dutch), getString(R.string.arabic), getString(R.string.russian), getString(R.string.chinese), getString(R.string.malay), getString(R.string.hindi), getString(R.string.korean)};
        String[] continents = {getString(R.string.north_america), getString(R.string.south_america), getString(R.string.europe), getString(R.string.africa), getString(R.string.asia), getString(R.string.australasia)};
        String[] religions = {getString(R.string.christianity), getString(R.string.islam), getString(R.string.buddhism), getString(R.string.hinduism), getString(R.string.judaism)};
        String[] months = {getString(R.string.january), getString(R.string.february), getString(R.string.march), getString(R.string.april), getString(R.string.may), getString(R.string.june), getString(R.string.july), getString(R.string.august), getString(R.string.september), getString(R.string.october), getString(R.string.november), getString(R.string.december)};

        addFilterButtons(languageGrid, languages, selectedLanguages);
        addFilterButtons(continentGrid, continents, selectedContinents);
        addFilterButtons(religionGrid, religions, selectedReligions);
        addFilterButtons(bestMonthsToVisitGrid, months, selectedMonths);

        countries = CountryData.getCountriesInfo(this);
        adapter = new CountryAdapter(this, countries);
        countryListView.setAdapter(adapter);

        // Search bar filtering
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterCountries();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Toggle filter ScrollView visibility
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterScrollContainer.getVisibility() == View.GONE) {
                    filterScrollContainer.setVisibility(View.VISIBLE);
                } else {
                    filterScrollContainer.setVisibility(View.GONE);
                }
            }
        });

        // Set up item click listener to open CountryDetailActivity
        countryListView.setOnItemClickListener((parent, view, position, id) -> {
            CountryInfo selectedCountry = adapter.getItem(position);

            Intent intent = new Intent(MainActivity.this, CountryDetailActivity.class);
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

        // Set click listener for Country Details
        countryDetailsIcon.setOnClickListener(v -> {
            // Open the main country details activity (this might already be active)
            // Or navigate to a country details activity if necessary
            // No action, already on the Country Details / Home page
        });

        // Set click listener for Map Icon
        mapIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExploreActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Set click listener for Languages
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        // Set click listener for Profile
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TripPlannerActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

    }

    private void filterCountries() {
        String query = searchBar.getText().toString().toLowerCase();
        List<CountryInfo> filteredCountries = new ArrayList<>();

        Map<String, Integer> monthToIndexMap = new HashMap<>();
        monthToIndexMap.put(getString(R.string.january), 0);
        monthToIndexMap.put(getString(R.string.february), 1);
        monthToIndexMap.put(getString(R.string.march), 2);
        monthToIndexMap.put(getString(R.string.april), 3);
        monthToIndexMap.put(getString(R.string.may), 4);
        monthToIndexMap.put(getString(R.string.june), 5);
        monthToIndexMap.put(getString(R.string.july), 6);
        monthToIndexMap.put(getString(R.string.august), 7);
        monthToIndexMap.put(getString(R.string.september), 8);
        monthToIndexMap.put(getString(R.string.october), 9);
        monthToIndexMap.put(getString(R.string.november), 10);
        monthToIndexMap.put(getString(R.string.december), 11);

        for (CountryInfo country : countries) {
            boolean matchesSearch = query.isEmpty() || country.getName().toLowerCase().contains(query);
            boolean matchesLanguage = selectedLanguages.isEmpty() ||
                    selectedLanguages.stream().anyMatch(language ->
                            country.getLanguage().toLowerCase().contains(language.toLowerCase()));
            boolean matchesContinent = selectedContinents.isEmpty() || selectedContinents.contains(country.getContinent());
            boolean matchesReligion = selectedReligions.isEmpty() || selectedReligions.contains(country.getReligion());
            boolean matchesMonth = true;
            if (!selectedMonths.isEmpty()) {
                matchesMonth = false; // Initialize to false, will set to true if we find a match

                for (String month : selectedMonths) {
                    int monthIndex = monthToIndexMap.get(month);

                    // Check if the best time to visit this month is marked as "1" (ideal)
                    if (country.getBestTimeVisit()[monthIndex] == 1) {
                        matchesMonth = true;
                        break;
                    }
                }
            }

            if (matchesSearch && matchesLanguage && matchesContinent && matchesReligion && matchesMonth) {
                filteredCountries.add(country);
            }
        }

        adapter.updateCountries(filteredCountries);

        TextView noCountriesFoundTextView = findViewById(R.id.no_countries_found);
        if (filteredCountries.isEmpty()) {
            noCountriesFoundTextView.setVisibility(View.VISIBLE);
            countryListView.setVisibility(View.GONE);
        } else {
            noCountriesFoundTextView.setVisibility(View.GONE);
            countryListView.setVisibility(View.VISIBLE);
        }
        // Always keep the icons at the bottom visible
        iconsBottom.setVisibility(View.VISIBLE);
    }

    private void addFilterButtons(GridLayout gridLayout, String[] values, Set<String> selectedValues) {
        for (String value : values) {
            Button button = new Button(this);
            button.setText(value);
            button.setBackgroundResource(R.drawable.button_background);
            button.setTextColor(getResources().getColor(R.color.black));
            button.setTextSize(10);
            button.setPadding(8, 8, 8, 8);

            // Set button style and size
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0; // Width of 0dp to enable column weight
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f); // Set equal weight for each button
            params.setMargins(8, 8, 8, 8);
            button.setLayoutParams(params);

            button.setOnClickListener(v -> {
                if (selectedValues.contains(value)) {
                    selectedValues.remove(value);
                    button.setBackgroundResource(R.drawable.button_background); // Unselected color
                } else {
                    selectedValues.add(value);
                    button.setBackgroundResource(R.drawable.button_background_unselected); // Selected color
                }
                filterCountries(); // Update the country list based on selected filters
            });

            gridLayout.addView(button);
        }
    }

}