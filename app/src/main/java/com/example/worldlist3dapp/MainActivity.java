package com.example.worldlist3dapp;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private Spinner countrySpinner;
    private TextView countryInfoText;
    private TextView countryCaptial;
    private TextView countryPopulation;
    private TextView countryLanguage;
    private ImageView countryImage;

    private List<CountryInfo> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countrySpinner = findViewById(R.id.spinner_country);
        countryInfoText = findViewById(R.id.textView_country_info);
        countryCaptial = findViewById(R.id.textView_capital);
        countryPopulation = findViewById(R.id.textView_population);
        countryLanguage = findViewById(R.id.textView_languages);
        countryImage = findViewById(R.id.map_container);

        // Get the list of CountryInfo objects from CountryData
        countries = CountryData.getCountriesInfo();

        // Set up Spinner with country names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getCountryNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);

        // Handle country selection
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                CountryInfo selectedCountry = countries.get(position);

                // Set the country image and description
                countryImage.setImageResource(selectedCountry.getImageResId());
                countryCaptial.setText(selectedCountry.getCapital());
                countryPopulation.setText(selectedCountry.getPopulation());
                countryLanguage.setText(selectedCountry.getLanguage());
                countryInfoText.setText(selectedCountry.getDescription());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    // Helper method to get country names for the Spinner
    private String[] getCountryNames() {
        String[] names = new String[countries.size()];
        for (int i = 0; i < countries.size(); i++) {
            names[i] = countries.get(i).getName();
        }
        return names;
    }
}