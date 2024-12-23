package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Objects;

public class CountryDetailsInfo extends BaseActivity {

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
        TextView facts = findViewById(R.id.textView_fact_content);
        TextView website = findViewById(R.id.textView_website_link);
        TextView spring = findViewById(R.id.spring_text);
        TextView summer = findViewById(R.id.summer_text);
        TextView autumn = findViewById(R.id.autumn_text);
        TextView winter = findViewById(R.id.winter_text);
        TextView food = findViewById(R.id.food_text);
        TextView drink = findViewById(R.id.drink_text);
        ImageView safety_value = findViewById(R.id.safety_icon);
        TextView safety_text = findViewById(R.id.safety_and_security);
        TextView summary_expand = findViewById(R.id.button_expand_summary);
        LinearLayout summary_expand_layout = findViewById(R.id.summary_expand_layout);
        ImageButton back_button = findViewById(R.id.back_button);

        int[] bestTimesToVisit = getIntent().getIntArrayExtra("bestTimesToVisit");
        String fact_info = getIntent().getStringExtra("fact_info");
        String website_info = getIntent().getStringExtra("website_info");
        String countryInfo = getIntent().getStringExtra("countryDescription");
        String[] weather = getIntent().getStringArrayExtra("weather_info");
        String[] cuisine = getIntent().getStringArrayExtra("cuisine_info");
        String[] safety = getIntent().getStringArrayExtra("safety_info");

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

        for (int i = 0; i < weather.length; i++) {

            // Apply text for each of the TextView elements
            if (i == 0) {
                spring.setText(weather[i]);
            } else if (i == 1) {
                summer.setText(weather[i]);
            } else if (i == 2) {
                autumn.setText(weather[i]);
            } else if (i == 3) {
                winter.setText(weather[i]);
            }
        }

        for (int i = 0; i < cuisine.length; i++) {

            // Apply text for each of the TextView elements
            if (i == 0) {
                food.setText(cuisine[i]);
            } else if (i == 1) {
                drink.setText(cuisine[i]);
            }
        }

        for (int i = 0; i < safety.length; i++) {

            // Apply text for each of the TextView elements
            if (i == 0) {
                if (Objects.equals(safety[i],
                                   getString(R.string.safe))){
                    safety_value.setBackgroundResource(R.drawable.safe);
                }
                else if (Objects.equals(safety[i],
                                        getString(R.string.ok))){
                    safety_value.setBackgroundResource(R.drawable.ok);
                }
                else if (Objects.equals(safety[i],
                                        getString(R.string.dangerous))){
                    safety_value.setBackgroundResource(R.drawable.dangerous);
                }
            } else if (i == 1) {
                safety_text.setText(safety[i]);
            }
        }

        summary_expand_layout.setOnClickListener(v -> {
            if (countryInfoTextView.getMaxLines() == 4) {
                countryInfoTextView.setMaxLines(Integer.MAX_VALUE);
                summary_expand.setText(R.string.read_less);// Expand
            } else {
                countryInfoTextView.setMaxLines(4); // Collapse
                summary_expand.setText(R.string.read_more);
            }
        });

        // Set the country information in the TextView
        countryInfoTextView.setText(countryInfo);
        facts.setText(fact_info);
        website.setText(website_info);

        back_button.setOnClickListener(v -> {
            finish();
        });
    }
}