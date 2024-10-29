package com.example.worldlist3dapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView countryListView;
    private List<CountryInfo> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryListView = findViewById(R.id.country_list_view);

        countries = CountryData.getCountriesInfo();
        CountryAdapter adapter = new CountryAdapter(this, countries);
        countryListView.setAdapter(adapter);

        // Set up item click listener to open CountryDetailActivity
        countryListView.setOnItemClickListener((parent, view, position, id) -> {
            CountryInfo selectedCountry = countries.get(position);

            Intent intent = new Intent(MainActivity.this, CountryDetailActivity.class);
            intent.putExtra("countryImageResId", selectedCountry.getImageResId());
            intent.putExtra("countryName", selectedCountry.getName());
            intent.putExtra("countryCapital", selectedCountry.getCapital());
            intent.putExtra("countryPopulation", selectedCountry.getPopulation());
            intent.putExtra("countryLanguage", selectedCountry.getLanguage());
            intent.putExtra("countryDescription", selectedCountry.getDescription());

            startActivity(intent);
        });
    }
}
