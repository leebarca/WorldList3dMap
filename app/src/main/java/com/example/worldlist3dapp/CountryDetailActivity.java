package com.example.worldlist3dapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailActivity extends AppCompatActivity {

    private ImageView countryImage;
    private TextView countryName, countryCapital, countryPopulation, countryLanguage, countryInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        countryImage = findViewById(R.id.map_container);
        countryName = findViewById(R.id.countryName);
        countryCapital = findViewById(R.id.textView_capital);
        countryPopulation = findViewById(R.id.textView_population);
        countryLanguage = findViewById(R.id.textView_languages);
        countryInfoText = findViewById(R.id.textView_country_info);

        // Get extras from Intent
        countryImage.setImageResource(getIntent().getIntExtra("countryImageResId", 0));
        countryName.setText(getIntent().getStringExtra("countryName"));
        countryCapital.setText(getIntent().getStringExtra("countryCapital"));
        countryPopulation.setText(getIntent().getStringExtra("countryPopulation"));
        countryLanguage.setText(getIntent().getStringExtra("countryLanguage"));
        countryInfoText.setText(getIntent().getStringExtra("countryDescription"));
    }
}
