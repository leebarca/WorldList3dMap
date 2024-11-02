package com.example.worldlist3dapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView countryListView;
    private List<CountryInfo> countries;
    private CountryAdapter adapter; // Class-level adapter declaration
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryListView = findViewById(R.id.country_list_view);
        searchBar = findViewById(R.id.search_bar);

        countries = CountryData.getCountriesInfo();
        adapter = new CountryAdapter(this, countries); // Initialize the class-level adapter
        countryListView.setAdapter(adapter);

        // Search bar filtering
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterCountries(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Set up item click listener to open CountryDetailActivity
        countryListView.setOnItemClickListener((parent, view, position, id) -> {
            CountryInfo selectedCountry = adapter.getItem(position); // Using adapter instead of countries

            Intent intent = new Intent(MainActivity.this, CountryDetailActivity.class);
            intent.putExtra("countryImageResId", selectedCountry.getImageResId());
            intent.putExtra("countryName", selectedCountry.getName());
            intent.putExtra("countryCapital", selectedCountry.getCapital());
            intent.putExtra("countryPopulation", selectedCountry.getPopulation());
            intent.putExtra("countryLanguage", selectedCountry.getLanguage());
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
            intent.putExtra("countryFlag", selectedCountry.getFlagResId());

            startActivity(intent);
        });
    }

    private void filterCountries(String query) {
        List<CountryInfo> filteredCountries = new ArrayList<>();
        for (CountryInfo country : countries) {
            if (country.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCountries.add(country);
            }
        }
        // Update the adapter with the filtered list]
        adapter.updateCountries(filteredCountries);

        // Display the "No countries found" message if the filtered list is empty
        TextView noCountriesFoundTextView = findViewById(R.id.no_countries_found);
        if (filteredCountries.isEmpty()) {
            noCountriesFoundTextView.setVisibility(View.VISIBLE);
            countryListView.setVisibility(View.GONE);
        } else {
            noCountriesFoundTextView.setVisibility(View.GONE);
            countryListView.setVisibility(View.VISIBLE);
        }
    }
}