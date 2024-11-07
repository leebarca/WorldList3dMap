package com.example.worldlist3dapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailsOffers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_offers);

        // Find the TextView to display offers information
        TextView countryOffersTextView = findViewById(R.id.textView_country_offers);

        // Retrieve the offers information from the Intent
        String countryOffers = getIntent().getStringExtra("countryOffers");

        // Set the offers information in the TextView
        countryOffersTextView.setText(countryOffers);
    }
}