package com.example.worldlist3dapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailActivity extends AppCompatActivity {

    private ImageView countryImage;
    private TextView countryName, countryCapital, countryPopulation, countryLanguage, countryInfoText;
    private LinearLayout attractionsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        // Bind views
        countryImage = findViewById(R.id.map_container);
        countryName = findViewById(R.id.countryName);
        countryCapital = findViewById(R.id.textView_capital);
        countryPopulation = findViewById(R.id.textView_population);
        countryLanguage = findViewById(R.id.textView_languages);
        countryInfoText = findViewById(R.id.textView_country_info);
        attractionsContainer = findViewById(R.id.attractions_container);

        // Get country info from Intent
        countryImage.setImageResource(getIntent().getIntExtra("countryImageResId", 0));
        countryName.setText(getIntent().getStringExtra("countryName"));
        countryCapital.setText(getIntent().getStringExtra("countryCapital"));
        countryPopulation.setText(getIntent().getStringExtra("countryPopulation"));
        countryLanguage.setText(getIntent().getStringExtra("countryLanguage"));
        countryInfoText.setText(getIntent().getStringExtra("countryDescription"));

        // Get attraction info from Intent
        String[] attractionNames = {
                getIntent().getStringExtra("firstAttractionName"),
                getIntent().getStringExtra("secondAttractionName"),
                getIntent().getStringExtra("thirdAttractionName")
        };

        String[] attractionDetails = {
                getIntent().getStringExtra("firstAttractionDetails"),
                getIntent().getStringExtra("secondAttractionDetails"),
                getIntent().getStringExtra("thirdAttractionDetails")
        };

        // Add attractions to the container
        addAttractions(attractionNames, attractionDetails);
    }

    private void addAttractions(String[] names, String[] details) {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].isEmpty()) {
                continue; // Skip if attraction name is empty
            }

            View attractionView = inflater.inflate(R.layout.item_attraction, attractionsContainer, false);

            TextView attractionName = attractionView.findViewById(R.id.attraction_name);
            TextView attractionDetails = attractionView.findViewById(R.id.attraction_details);

            // Set the name and details for each attraction
            attractionName.setText(names[i]);
            attractionDetails.setText(details[i]);

            // Set up click listener to toggle visibility of details
            attractionView.setOnClickListener(v -> {
                if (attractionDetails.getVisibility() == View.GONE) {
                    attractionDetails.setVisibility(View.VISIBLE);
                } else {
                    attractionDetails.setVisibility(View.GONE);
                }
            });

            // Add the view to the container
            attractionsContainer.addView(attractionView);
        }
    }
}
