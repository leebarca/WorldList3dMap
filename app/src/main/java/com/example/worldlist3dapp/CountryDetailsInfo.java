package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CountryDetailsInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_info);

        // Find the TextView to display country info
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView countryInfoTextView = findViewById(R.id.textView_country_info);
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

        int[] bestTimesToVisit = getIntent().getIntArrayExtra("bestTimesToVisit");

        // Define your TextView references
        TextView[] monthTextViews = {jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec};

        // Define colors
        int colorBestTime = ContextCompat.getColor(this, R.color.best_time_color);
        int colorAverageTime = ContextCompat.getColor(this, R.color.average_time_color);
        int colorWorstTime = ContextCompat.getColor(this, R.color.worst_time_color);

        // Loop through each month and set the background color based on the value
        for (int i = 0; i < bestTimesToVisit.length; i++) {
            int visitValue = bestTimesToVisit[i];
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

        // Retrieve the country information from the Intent
        String countryInfo = getIntent().getStringExtra("countryDescription");

        // Set the country information in the TextView
        countryInfoTextView.setText(countryInfo);
    }
}