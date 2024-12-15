package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExploreActivity extends BaseActivity {

    private OrderedCountriesAdapter adapter, monthAdapter;
    private NonScrollListView countryListView, this_month_countries;
    private List<CountryInfo> countries;
    private LinearLayout iconsBottom, home_layout_button, explore_layout_button, planner_layout_button, language_layout_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // Initialize data
        countryListView = findViewById(R.id.top_countries_list);
        this_month_countries = findViewById(R.id.this_month_countries);
        Spinner categorySpinner = findViewById(R.id.category_spinner);
        Spinner currentMonthCategorySpinner = findViewById(R.id.current_month_category_spinner);
        iconsBottom = findViewById(R.id.icons_bottom);
        home_layout_button = findViewById(R.id.home_layout_button);
        explore_layout_button = findViewById(R.id.explore_layout_button);
        planner_layout_button = findViewById(R.id.planner_layout_button);
        language_layout_button = findViewById(R.id.language_layout_button);

        countries = CountryData.getCountriesInfo(this);

        // Adapter for top countries
        adapter = new OrderedCountriesAdapter(this, new ArrayList<>());
        countryListView.setAdapter(adapter);

        // Adapter for "this month's countries"
        monthAdapter = new OrderedCountriesAdapter(this, new ArrayList<>());
        this_month_countries.setAdapter(monthAdapter);

        // Add fade at bottom of list view
        this_month_countries.setVerticalFadingEdgeEnabled(true);
        this_month_countries.setFadingEdgeLength(50);

        // Update the list for the current month
        updateCurrentMonthListView();

        // Set OnItemClickListener for both ListViews
        setOnItemClickListener(countryListView);
        setOnItemClickListener(this_month_countries);

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

        // Spinner setup for main categories
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, categories);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);

        // Main category selection logic
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];
                updateListView(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });

        String[] current_month_categories = {
                getString(R.string.summer_escapes),
                getString(R.string.winter_escapes),
        };

        // Spinner Adapter
        ArrayAdapter<String> monthCategoryAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, current_month_categories);
        monthCategoryAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        currentMonthCategorySpinner.setAdapter(monthCategoryAdapter);


        // Current month category selection logic
        currentMonthCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateCurrentMonthListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
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

    private void updateCurrentMonthListView() {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH); // 0 for January, 11 for December
        Spinner currentMonthCategorySpinner = findViewById(R.id.current_month_category_spinner);

        Object selectedItem = currentMonthCategorySpinner.getSelectedItem();
        if (selectedItem == null) {
            Log.e("ExploreActivity", "currentMonthCategorySpinner has no selected item.");
            return; // Exit the method to prevent further errors
        }
        String selectedOption = selectedItem.toString();

        Map<Integer, List<String>> coolGetawayMap = new HashMap<>();
        Map<Integer, List<String>> warmGetawayMap = new HashMap<>();

        // Create a map for Summer countries based on the month
        warmGetawayMap.put(0, List.of(getString(R.string.thailand), getString(R.string.australia), getString(R.string.south_africa), getString(R.string.maldives), getString(R.string.costa_rica)));
        warmGetawayMap.put(1, List.of(getString(R.string.brazil), getString(R.string.new_zealand), getString(R.string.vietnam), getString(R.string.sri_lanka), getString(R.string.kenya)));
        warmGetawayMap.put(2, List.of(getString(R.string.morocco), getString(R.string.india), getString(R.string.peru), getString(R.string.united_arab_emirates), getString(R.string.philippines)));
        warmGetawayMap.put(3, List.of(getString(R.string.jordan), getString(R.string.cuba), getString(R.string.mexico), getString(R.string.bahamas), getString(R.string.greece)));
        warmGetawayMap.put(4, List.of(getString(R.string.turkey), getString(R.string.italy), getString(R.string.portugal), getString(R.string.united_arab_emirates), getString(R.string.japan)));
        warmGetawayMap.put(5, List.of(getString(R.string.spain), getString(R.string.indonesia), getString(R.string.singapore), getString(R.string.malaysia), getString(R.string.tanzania)));
        warmGetawayMap.put(6, List.of(getString(R.string.turkey), getString(R.string.croatia), getString(R.string.south_africa), getString(R.string.kenya), getString(R.string.brazil)));
        warmGetawayMap.put(7, List.of(getString(R.string.maldives), getString(R.string.fiji), getString(R.string.indonesia), getString(R.string.thailand), getString(R.string.mexico)));
        warmGetawayMap.put(8, List.of(getString(R.string.costa_rica), getString(R.string.united_states), getString(R.string.egypt), getString(R.string.greece), getString(R.string.morocco)));
        warmGetawayMap.put(9, List.of(getString(R.string.japan), getString(R.string.chile), getString(R.string.australia), getString(R.string.united_arab_emirates), getString(R.string.argentina)));
        warmGetawayMap.put(10, List.of(getString(R.string.thailand), getString(R.string.mexico), getString(R.string.vietnam), getString(R.string.cambodia), getString(R.string.south_africa)));
        warmGetawayMap.put(11, List.of(getString(R.string.australia), getString(R.string.new_zealand), getString(R.string.fiji), getString(R.string.brazil), getString(R.string.sri_lanka)));

        coolGetawayMap.put(0, List.of(getString(R.string.thailand), getString(R.string.australia), getString(R.string.switzerland), getString(R.string.south_africa), getString(R.string.costa_rica)));
        coolGetawayMap.put(1, List.of(getString(R.string.brazil), getString(R.string.new_zealand), getString(R.string.japan), getString(R.string.maldives), getString(R.string.vietnam)));
        coolGetawayMap.put(2, List.of(getString(R.string.morocco), getString(R.string.argentina), getString(R.string.ireland), getString(R.string.india), getString(R.string.peru)));
        coolGetawayMap.put(3, List.of(getString(R.string.netherlands), getString(R.string.japan), getString(R.string.greece), getString(R.string.south_korea), getString(R.string.jordan)));
        coolGetawayMap.put(4, List.of(getString(R.string.italy), getString(R.string.canada), getString(R.string.united_kingdom), getString(R.string.nepal), getString(R.string.botswana)));
        coolGetawayMap.put(5, List.of(getString(R.string.iceland), getString(R.string.france), getString(R.string.kenya), getString(R.string.portugal), getString(R.string.indonesia)));
        coolGetawayMap.put(6, List.of(getString(R.string.united_states), getString(R.string.tanzania), getString(R.string.norway), getString(R.string.peru), getString(R.string.canada)));
        coolGetawayMap.put(7, List.of(getString(R.string.norway), getString(R.string.ecuador), getString(R.string.south_africa), getString(R.string.croatia), getString(R.string.japan)));
        coolGetawayMap.put(8, List.of(getString(R.string.italy), getString(R.string.turkey), getString(R.string.south_africa), getString(R.string.canada), getString(R.string.china)));
        coolGetawayMap.put(9, List.of(getString(R.string.japan), getString(R.string.greece), getString(R.string.united_states), getString(R.string.bhutan), getString(R.string.morocco)));
        coolGetawayMap.put(10, List.of(getString(R.string.thailand), getString(R.string.mexico), getString(R.string.vietnam), getString(R.string.egypt), getString(R.string.argentina)));
        coolGetawayMap.put(11, List.of(getString(R.string.finland), getString(R.string.australia), getString(R.string.mexico), getString(R.string.austria), getString(R.string.south_africa)));

        List<String> currentMonthCountries;
        if (selectedOption.equals(getString(R.string.winter_escapes))) {
            if (coolGetawayMap.containsKey(currentMonth)) {
                currentMonthCountries = coolGetawayMap.get(currentMonth);
            } else {
                currentMonthCountries = new ArrayList<>();
            }
        } else {
            if (warmGetawayMap.containsKey(currentMonth)) {
                currentMonthCountries = warmGetawayMap.get(currentMonth);
            } else {
                currentMonthCountries = new ArrayList<>();
            }
        }

        List<CountryInfo> filteredCountries = getOrderedCountries(currentMonthCountries, countries);
        monthAdapter.updateCountries(filteredCountries);
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

    private void setOnItemClickListener(ListView listView) {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CountryInfo selectedCountry = (CountryInfo) parent.getItemAtPosition(position);

            Intent intent = new Intent(ExploreActivity.this, CountryDetailActivity.class);
            intent.putExtra("countryImageResId", selectedCountry.getImageResId());
            intent.putExtra("countryName", selectedCountry.getName());
            intent.putExtra("countryCapital", selectedCountry.getCapital());
            intent.putExtra("countryPopulation", selectedCountry.getPopulation());
            intent.putExtra("countryLanguage", selectedCountry.getLanguage());
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
    }

    private void setupBottomNavigation() {
        home_layout_button.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        language_layout_button.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, LanguageActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        planner_layout_button.setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, TripPlannerActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });
    }
}