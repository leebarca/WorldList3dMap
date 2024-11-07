package com.example.worldlist3dapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailsInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_info);

        // Find the TextView to display country info
        TextView countryInfoTextView = findViewById(R.id.textView_country_info);

        // Retrieve the country information from the Intent
        String countryInfo = getIntent().getStringExtra("countryDescription");

        // Set the country information in the TextView
        countryInfoTextView.setText(countryInfo);
    }
}