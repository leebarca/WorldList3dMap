package com.example.worldlist3dapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailActivity extends AppCompatActivity {

    private LinearLayout attractionsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        // Other setup for country details omitted for brevity

        ImageView countryImage = findViewById(R.id.map_container);
        TextView countryName = findViewById(R.id.countryName);
        TextView countryCapital = findViewById(R.id.textView_capital);
        TextView countryPopulation = findViewById(R.id.textView_population);
        TextView countryLanguage = findViewById(R.id.textView_languages);
        TextView countryReligion = findViewById(R.id.textView_religion);
        TextView countryContinent = findViewById(R.id.textView_continent);
        TextView countryArea = findViewById(R.id.textView_area);
        ImageView countryFlag = findViewById(R.id.country_flag);
        ImageView countryOutline = findViewById(R.id.country_outline);
        TextView countryInfoText = findViewById(R.id.textView_country_info);
        attractionsContainer = findViewById(R.id.attractions_container);

        // Retrieve values from Intent
        countryImage.setImageResource(getIntent().getIntExtra("countryImageResId", 0));
        countryName.setText(getIntent().getStringExtra("countryName"));
        countryCapital.setText(getIntent().getStringExtra("countryCapital"));
        countryPopulation.setText(getIntent().getStringExtra("countryPopulation"));
        countryLanguage.setText(getIntent().getStringExtra("countryLanguage"));
        countryReligion.setText(getIntent().getStringExtra("countryReligion"));
        countryContinent.setText(getIntent().getStringExtra("countryContinent"));
        countryArea.setText(getIntent().getStringExtra("countryArea"));
        countryFlag.setImageResource(getIntent().getIntExtra("countryFlag", 0));
        countryOutline.setImageResource(getIntent().getIntExtra("countryOutline", 0));
        countryInfoText.setText(getIntent().getStringExtra("countryDescription"));

        // Example attraction data (could be retrieved dynamically)
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
        int[] attractionImages = {
                getIntent().getIntExtra("firstAttractionImage", 0),
                getIntent().getIntExtra("secondAttractionImage", 0),
                getIntent().getIntExtra("thirdAttractionImage", 0)
        };

        // Add attractions with expand/collapse functionality
        addAttractions(attractionNames, attractionDetails, attractionImages);
    }

    private void addAttractions(String[] names, String[] details, int[] images) {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].isEmpty()) continue;

            View attractionView = inflater.inflate(R.layout.item_attraction, attractionsContainer, false);
            TextView attractionName = attractionView.findViewById(R.id.attraction_name);
            TextView attractionDetails = attractionView.findViewById(R.id.attraction_details);
            ImageView attractionImage = attractionView.findViewById(R.id.attraction_image);
            ImageView arrowIcon = attractionView.findViewById(R.id.arrow_icon);
            LinearLayout detailsContainer = attractionView.findViewById(R.id.attraction_details_container);

            // Set data for each attraction
            attractionName.setText(names[i]);
            attractionDetails.setText(details[i]);
            attractionImage.setImageResource(images[i]);

            // Set up click listener to expand/collapse details and rotate arrow
            attractionView.setOnClickListener(v -> {
                if (detailsContainer.getVisibility() == View.GONE) {
                    detailsContainer.setVisibility(View.VISIBLE);
                    detailsContainer.setBackgroundResource(R.drawable.attraction_dropdown_item_background);
                    attractionView.setBackgroundResource(R.drawable.attraction_dropdown_item_background);
                    arrowIcon.setRotation(90); // Rotate arrow downwards
                } else {
                    detailsContainer.setVisibility(View.GONE);
                    arrowIcon.setRotation(0); // Rotate arrow back to initial position
                    attractionView.setBackgroundResource(R.drawable.attraction_item_background);
                }
            });

            attractionsContainer.addView(attractionView);
        }
    }
}
