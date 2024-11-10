package com.example.worldlist3dapp;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ListView countryListView;
    private List<CountryInfo> countries;
    private CountryAdapter adapter;
    private EditText searchBar;
    private ImageView filter;
    private ScrollView filterScrollContainer; // Updated to ScrollView
    private LinearLayout filterContainer;
    private Set<String> selectedLanguages = new HashSet<>();
    private Set<String> selectedContinents = new HashSet<>();
    private Set<String> selectedReligions = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryListView = findViewById(R.id.country_list_view);
        searchBar = findViewById(R.id.search_bar);
        filter = findViewById(R.id.filter_icon);
        filterScrollContainer = findViewById(R.id.filter_scroll_container); // Reference to ScrollView
        filterContainer = findViewById(R.id.filter_container);

        GridLayout languageGrid = findViewById(R.id.language_filter_grid);
        GridLayout continentGrid = findViewById(R.id.continent_filter_grid);
        GridLayout religionGrid = findViewById(R.id.religion_filter_grid);

        String[] languages = {"English", "Spanish", "French", "German", "Italian", "Portuguese", "Dutch", "Arabic", "Russian", "Chinese", "Malay", "Hindi", "Korean"};
        String[] continents = {"North America", "South America", "Europe", "Africa", "Asia", "Australasia"};
        String[] religions = {"Christianity", "Islam", "Buddhism", "Hinduism", "Judaism"};

        addFilterButtons(languageGrid, languages, selectedLanguages);
        addFilterButtons(continentGrid, continents, selectedContinents);
        addFilterButtons(religionGrid, religions, selectedReligions);

        countries = CountryData.getCountriesInfo();
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

            startActivity(intent);
        });
    }

    private void filterCountries() {
        String query = searchBar.getText().toString().toLowerCase();
        List<CountryInfo> filteredCountries = new ArrayList<>();
        for (CountryInfo country : countries) {
            boolean matchesSearch = query.isEmpty() || country.getName().toLowerCase().contains(query);
            boolean matchesLanguage = selectedLanguages.isEmpty() || selectedLanguages.contains(country.getLanguage());
            boolean matchesContinent = selectedContinents.isEmpty() || selectedContinents.contains(country.getContinent());
            boolean matchesReligion = selectedReligions.isEmpty() || selectedReligions.contains(country.getReligion());

            if (matchesSearch && matchesLanguage && matchesContinent && matchesReligion) {
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
    }

    private void addFilterButtons(GridLayout gridLayout, String[] values, Set<String> selectedValues) {
        int columnCount = gridLayout.getColumnCount(); // Get the number of columns

        for (String value : values) {
            Button button = new Button(this);
            button.setText(value);
            button.setBackgroundResource(R.drawable.button_background);
            button.setTextColor(getResources().getColor(R.color.black));
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