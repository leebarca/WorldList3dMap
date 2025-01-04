package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Objects;

public class CountryDetailsLanguages extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_languages);

        // Find the TextView to display country info
        ImageButton back_button = findViewById(R.id.back_button);

        String country_name = getIntent().getStringExtra("countryName");
        String languages = getIntent().getStringExtra("languages");

        // Define your TextView references
        //TextView[] monthTextViews = {jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec};

        back_button.setOnClickListener(v -> {
            finish();
        });
    }
}