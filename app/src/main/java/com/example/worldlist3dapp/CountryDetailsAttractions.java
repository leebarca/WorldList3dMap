package com.example.worldlist3dapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class CountryDetailsAttractions extends BaseActivity {

    private ViewPager2 attractionsViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_attractions);

        attractionsViewPager = findViewById(R.id.attractions_view_pager);

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

        // Set up the adapter for the ViewPager
        AttractionsPagerAdapter adapter = new AttractionsPagerAdapter(attractionNames, attractionDetails, attractionImages);
        attractionsViewPager.setAdapter(adapter);
        attractionsViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
    }
}