package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CountryDetailActivity extends BaseActivity {

    private LinearLayout infoIcon;
    private LinearLayout attractionsIcon;
    private LinearLayout planningIcon;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        ImageView countryImage = findViewById(R.id.map_container);
        TextView countryName = findViewById(R.id.countryName);
        ImageView countryFlag = findViewById(R.id.country_flag);
        TextView countryCapital = findViewById(R.id.textView_capital);
        TextView countryPopulation = findViewById(R.id.textView_population);
        TextView countryLanguage = findViewById(R.id.textView_languages);
        TextView countryReligion = findViewById(R.id.textView_religion);
        TextView countryContinent = findViewById(R.id.textView_continent);
        TextView countryArea = findViewById(R.id.textView_area);
        infoIcon = findViewById(R.id.open_info_element);
        attractionsIcon = findViewById(R.id.open_attractions_element);
        planningIcon = findViewById(R.id.open_offers_element);

        // Retrieve values from Intent
        countryImage.setImageResource(getIntent().getIntExtra("countryImageResId", 0));
        countryName.setText(getIntent().getStringExtra("countryName"));
        countryFlag.setImageResource(getIntent().getIntExtra("countryFlag", 0));
        countryCapital.setText(getIntent().getStringExtra("countryCapital"));
        countryPopulation.setText(getIntent().getStringExtra("countryPopulation"));
        countryLanguage.setText(getIntent().getStringExtra("countryLanguage"));
        countryReligion.setText(getIntent().getStringExtra("countryReligion"));
        countryContinent.setText(getIntent().getStringExtra("countryContinent"));
        countryArea.setText(getIntent().getStringExtra("countryArea"));

        // Get Info to pass onto other classes
        String country_info = getIntent().getStringExtra("countryDescription");
        int[] bestTimesToVisit = getIntent().getIntArrayExtra("bestTimeVisitArray");
        String firstAttractionName = getIntent().getStringExtra("firstAttractionName");
        String secondAttractionName = getIntent().getStringExtra("secondAttractionName");
        String thirdAttractionName = getIntent().getStringExtra("thirdAttractionName");
        String firstAttractionDetails = getIntent().getStringExtra("firstAttractionDetails");
        String secondAttractionDetails = getIntent().getStringExtra("secondAttractionDetails");
        String thirdAttractionDetails = getIntent().getStringExtra("thirdAttractionDetails");
        int firstAttractionImage = getIntent().getIntExtra("firstAttractionImage", 0);
        int secondAttractionImage = getIntent().getIntExtra("secondAttractionImage", 0);
        int thirdAttractionImage = getIntent().getIntExtra("thirdAttractionImage", 0);
        String facts = getIntent().getStringExtra("factInfo");
        String websites = getIntent().getStringExtra("websiteInfo");
        String[] weather = getIntent().getStringArrayExtra("weatherInfo");
        String[] cuisine = getIntent().getStringArrayExtra("cuisineArray");
        String[] safety = getIntent().getStringArrayExtra("safetyArray");
        String currency = getIntent().getStringExtra("currency");
        String country_name = getIntent().getStringExtra("countryName");

        infoIcon.setOnClickListener(v -> {
            // Handle click event
            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsInfo.class);
            intent.putExtra("countryDescription", country_info);
            intent.putExtra("bestTimesToVisit", bestTimesToVisit);
            intent.putExtra("fact_info", facts);
            intent.putExtra("website_info", websites);
            intent.putExtra("weather_info", weather);
            intent.putExtra("cuisine_info", cuisine);
            intent.putExtra("safety_info", safety);
            intent.putExtra("currency", currency);
            startActivity(intent);
        });

        attractionsIcon.setOnClickListener(v -> {
            // Handle click event
            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsAttractions.class);
            intent.putExtra("firstAttractionName", firstAttractionName);
            intent.putExtra("secondAttractionName", secondAttractionName);
            intent.putExtra("thirdAttractionName", thirdAttractionName);
            intent.putExtra("firstAttractionDetails", firstAttractionDetails);
            intent.putExtra("secondAttractionDetails", secondAttractionDetails);
            intent.putExtra("thirdAttractionDetails", thirdAttractionDetails);
            intent.putExtra("firstAttractionImage", firstAttractionImage);
            intent.putExtra("secondAttractionImage", secondAttractionImage);
            intent.putExtra("thirdAttractionImage", thirdAttractionImage);
            startActivity(intent);
        });

        planningIcon.setOnClickListener(v -> {
            // Handle click event
            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsPlanning.class);
            intent.putExtra("country_name", country_name);
            startActivity(intent);
        });
    }
}
