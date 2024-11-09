package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CountryDetailActivity extends AppCompatActivity {

    private LinearLayout infoIcon;
    private LinearLayout attractionsIcon;
    private LinearLayout offersIcon;

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
        offersIcon = findViewById(R.id.open_offers_element);
        TextView jan = findViewById(R.id.jan);
        TextView feb = findViewById(R.id.feb);
        TextView mar = findViewById(R.id.mar);
        TextView apr = findViewById(R.id.apr);
        TextView may = findViewById(R.id.may);
        TextView jun = findViewById(R.id.jun);
        TextView jul = findViewById(R.id.jul);
        TextView aug = findViewById(R.id.aug);
        TextView sep = findViewById(R.id.sep);
        TextView oct = findViewById(R.id.oct);
        TextView nov = findViewById(R.id.nov);
        TextView dec = findViewById(R.id.dec);
        TextView best_time_key = findViewById(R.id.best_time_key);
        TextView avg_time_key = findViewById(R.id.avg_time_key);
        TextView worst_time_key = findViewById(R.id.worst_time_key);

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
        int[] bestTimeVisitArray = getIntent().getIntArrayExtra("bestTimeVisitArray");

        // Define your TextView references
        TextView[] monthTextViews = {jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec};

        // Define colors
        int colorBestTime = ContextCompat.getColor(this, R.color.best_time_color);
        int colorAverageTime = ContextCompat.getColor(this, R.color.average_time_color);
        int colorWorstTime = ContextCompat.getColor(this, R.color.worst_time_color);

        best_time_key.setBackgroundTintList(ColorStateList.valueOf(colorBestTime));
        avg_time_key.setBackgroundTintList(ColorStateList.valueOf(colorAverageTime));
        worst_time_key.setBackgroundTintList(ColorStateList.valueOf(colorWorstTime));

        // Loop through each month and set the background color based on the value
        for (int i = 0; i < bestTimeVisitArray.length; i++) {
            int visitValue = bestTimeVisitArray[i];
            TextView monthTextView = monthTextViews[i];

            // Apply color based on the value
            if (visitValue == 1) {
                monthTextView.setBackgroundTintList(ColorStateList.valueOf(colorBestTime));
            } else if (visitValue == 2) {
                monthTextView.setBackgroundTintList(ColorStateList.valueOf(colorAverageTime));
            } else if (visitValue == 3) {
                monthTextView.setBackgroundTintList(ColorStateList.valueOf(colorWorstTime));
            }
        }

        // Get Info to pass onto other classes
        String country_info = getIntent().getStringExtra("countryDescription");
        String firstAttractionName = getIntent().getStringExtra("firstAttractionName");
        String secondAttractionName = getIntent().getStringExtra("secondAttractionName");
        String thirdAttractionName = getIntent().getStringExtra("thirdAttractionName");
        String firstAttractionDetails = getIntent().getStringExtra("firstAttractionDetails");
        String secondAttractionDetails = getIntent().getStringExtra("secondAttractionDetails");
        String thirdAttractionDetails = getIntent().getStringExtra("thirdAttractionDetails");
        int firstAttractionImage = getIntent().getIntExtra("firstAttractionImage", 0);
        int secondAttractionImage = getIntent().getIntExtra("secondAttractionImage", 0);
        int thirdAttractionImage = getIntent().getIntExtra("thirdAttractionImage", 0);
        String country_offers = getIntent().getStringExtra("countryDescription");

        infoIcon.setOnClickListener(v -> {
            // Handle click event
            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsInfo.class);
            intent.putExtra("countryDescription", country_info);
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

        offersIcon.setOnClickListener(v -> {
            // Handle click event
            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsOffers.class);
            intent.putExtra("countryOffers", country_offers);
            startActivity(intent);
        });
    }
}
