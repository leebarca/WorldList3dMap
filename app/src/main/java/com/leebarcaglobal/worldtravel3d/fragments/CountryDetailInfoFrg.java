package com.leebarcaglobal.worldtravel3d.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.leebarcaglobal.worldtravel3d.R;

import java.util.Objects;

public class CountryDetailInfoFrg extends Fragment {

    @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView countryInfoTextView;
    TextView jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec,toolbar_title,
            facts,website,spring,summer,autumn,winter,food,drink,safety_text,summary_expand;
    LinearLayout summary_expand_layout;
    ImageButton back_button;
    ImageView safety_value;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.country_info_det_frg,container,false);


        countryInfoTextView = frgView.findViewById(R.id.textView_country_info);
        jan = frgView.findViewById(R.id.jan);
        feb = frgView.findViewById(R.id.feb);
        mar = frgView.findViewById(R.id.mar);
        apr = frgView.findViewById(R.id.apr);
        may = frgView.findViewById(R.id.may);
        jun = frgView.findViewById(R.id.jun);
        jul = frgView.findViewById(R.id.jul);
        aug = frgView.findViewById(R.id.aug);
        sep = frgView.findViewById(R.id.sep);
        oct = frgView.findViewById(R.id.oct);
        nov = frgView.findViewById(R.id.nov);
        dec = frgView.findViewById(R.id.dec);
        facts = frgView.findViewById(R.id.textView_fact_content);
        website = frgView.findViewById(R.id.textView_website_link);
        spring = frgView.findViewById(R.id.spring_text);
        summer = frgView.findViewById(R.id.summer_text);
        autumn = frgView.findViewById(R.id.autumn_text);
        winter = frgView.findViewById(R.id.winter_text);
        food = frgView.findViewById(R.id.food_text);
        drink = frgView.findViewById(R.id.drink_text);
        safety_value = frgView.findViewById(R.id.safety_icon);
        safety_text = frgView.findViewById(R.id.safety_and_security);
        summary_expand = frgView.findViewById(R.id.button_expand_summary);
        summary_expand_layout = frgView.findViewById(R.id.summary_expand_layout);
        back_button = frgView.findViewById(R.id.back_button);
        toolbar_title = frgView.findViewById(R.id.toolbar_title);

        int[] bestTimesToVisit = getArguments().getIntArray("bestTimesToVisit");
        String fact_info = getArguments().getString("fact_info");
        String website_info = getArguments().getString("website_info");
        String countryInfo = getArguments().getString("countryDescription");
        String[] weather = getArguments().getStringArray("weather_info");
        String[] cuisine = getArguments().getStringArray("cuisine_info");
        String[] safety = getArguments().getStringArray("safety_info");
        String country_name = getArguments().getString("country_name");



        toolbar_title.setText(country_name);
        // Define your TextView references
        TextView[] monthTextViews = {jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec};

        // Define colors
        int colorBestTime = ContextCompat.getColor(requireContext(), R.color.best_time_color);
        int colorAverageTime = ContextCompat.getColor(requireContext(), R.color.average_time_color);
        int colorWorstTime = ContextCompat.getColor(requireContext(), R.color.worst_time_color);

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
            requireActivity().onBackPressed();
        });



        return frgView;
    }
}
