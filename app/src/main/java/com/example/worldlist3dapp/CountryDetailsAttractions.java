package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailsAttractions extends AppCompatActivity {

    private LinearLayout attractionsContainer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_attractions);

        attractionsContainer = findViewById(R.id.attractions_container);

        // Retrieve attraction data from the Intent
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
                    arrowIcon.setRotation(90); // Rotate arrow downwards
                } else {
                    detailsContainer.setVisibility(View.GONE);
                    arrowIcon.setRotation(0); // Rotate arrow back to initial position
                }
            });

            attractionsContainer.addView(attractionView);
        }
    }
}