package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CountryDetailActivity extends BaseActivity {

    private LinearLayout infoIcon;
    private LinearLayout attractionsIcon;
    private LinearLayout mapIcon;
    private LinearLayout languagesIcon;
    private LinearLayout currencyIcon;
    private LinearLayout capitalIcon;

    @SuppressLint({"MissingInflatedId",
            "SetJavaScriptEnabled",
            "ClickableViewAccessibility"})
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
        TextView countryCurrency = findViewById(R.id.textView_currency);
        TextView countryContinent = findViewById(R.id.textView_continent);
        TextView countryArea = findViewById(R.id.textView_area);
        infoIcon = findViewById(R.id.open_info_element);
        attractionsIcon = findViewById(R.id.open_attractions_element);
        mapIcon = findViewById(R.id.button_expand_map);
        languagesIcon = findViewById(R.id.open_languages_element);
        currencyIcon = findViewById(R.id.open_currency_element);
        capitalIcon = findViewById(R.id.open_capital_element);
        ImageButton back_button = findViewById(R.id.back_button);

        // Retrieve values from Intent
        countryImage.setImageResource(getIntent().getIntExtra("countryImageResId", 0));
        countryName.setText(getIntent().getStringExtra("countryName"));
        countryFlag.setImageResource(getIntent().getIntExtra("countryFlag", 0));
        countryCapital.setText(getIntent().getStringExtra("countryCapital"));
        countryPopulation.setText(getIntent().getStringExtra("countryPopulation"));
        countryLanguage.setText(getIntent().getStringExtra("countryLanguage"));
        countryCurrency.setText(getIntent().getStringExtra("currency"));
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
        String country_name = getIntent().getStringExtra("countryName");
        String capital = getIntent().getStringExtra("countryCapital");
        String languages = getIntent().getStringExtra("countryLanguage");
        String currency = getIntent().getStringExtra("currency");

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

        mapIcon.setOnClickListener(v -> {
            // Replace "CountryName" with the name of the country you want to show
            Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(country_name) + "&basemap=satellite");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            // Attempt to directly open Google Maps
            mapIntent.setPackage("com.google.android.apps.maps");

            try {
                startActivity(mapIntent);
            } catch (ActivityNotFoundException e) {
                // Redirect user to Google Play Store to install or update Google Maps
                try {
                    Uri playStoreUri = Uri.parse("market://details?id=com.google.android.apps.maps");
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, playStoreUri);
                    startActivity(playStoreIntent);
                } catch (ActivityNotFoundException e2) {
                    // If Play Store is not available, open in a browser
                    Uri browserUri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, browserUri);
                    startActivity(browserIntent);
                }
            }
        });

        languagesIcon.setOnClickListener(v -> {
            // Handle click event
            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsLanguages.class);
            intent.putExtra("countryName", country_name);
            intent.putExtra("languages", languages);
            startActivity(intent);
        });

        currencyIcon.setOnClickListener(v -> {
            // Handle click event
            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsCurrency.class);
            intent.putExtra("countryName", country_name);
            intent.putExtra("currency", currency);
            startActivity(intent);
        });

        capitalIcon.setOnClickListener(v -> {
            // Replace "CountryName" with the name of the country you want to show
            Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(capital) + "&basemap=satellite");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            // Attempt to directly open Google Maps
            mapIntent.setPackage("com.google.android.apps.maps");

            try {
                startActivity(mapIntent);
            } catch (ActivityNotFoundException e) {
                // Redirect user to Google Play Store to install or update Google Maps
                try {
                    Uri playStoreUri = Uri.parse("market://details?id=com.google.android.apps.maps");
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, playStoreUri);
                    startActivity(playStoreIntent);
                } catch (ActivityNotFoundException e2) {
                    // If Play Store is not available, open in a browser
                    Uri browserUri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, browserUri);
                    startActivity(browserIntent);
                }
            }
        });

        back_button.setOnClickListener(v -> {
            // Handle click event
            finish();
        });
    }
}
