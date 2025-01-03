package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class LanguageActivity extends BaseActivity {

    private static final String PREFERENCES_NAME = "AppPreferences";
    private static final String LANGUAGE_KEY = "SelectedLanguage";

    private String selectedLanguage;
    private int selectedPosition = -1;
    private LinearLayout iconsBottom, home_layout_button, explore_layout_button, planner_layout_button, language_layout_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply saved language
        applySavedLanguage();

        setContentView(R.layout.activity_languages);

        iconsBottom = findViewById(R.id.icons_bottom);
        home_layout_button = findViewById(R.id.home_layout_button);
        explore_layout_button = findViewById(R.id.explore_layout_button);
        planner_layout_button = findViewById(R.id.planner_layout_button);
        language_layout_button = findViewById(R.id.language_layout_button);

        // Language list and adapter
        @SuppressLint({"MissingInflatedId",
                "LocalSuppress"}) ListView languageListView = findViewById(R.id.language_list_view);
        Button saveLanguageButton = findViewById(R.id.save_language_button);

        String[] languages = {
                "English", "Español", "Français", "Português", "中文", "日本語",
                "한국어", "हिंदी", "Русский", "العربية"
        };

        String[] languageCodes = {
                "en", "es", "fr", "pt", "zh", "ja",
                "ko", "hi", "ru", "ar"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, languages) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Center the text and set font color to black
                TextView textView = (TextView) view;
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(getResources().getColor(android.R.color.black));
                textView.setTextSize(16);

                // Highlight the selected item
                if (position == selectedPosition) {
                    view.setBackgroundColor(getResources().getColor(R.color.button_border_colour));
                } else {
                    view.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }

                view.setPadding(0, 0, 0, 0);

                return view;
            }
        };

        languageListView.setAdapter(adapter);

        // Retrieve saved language from SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        String savedLanguage = preferences.getString(LANGUAGE_KEY, null);
        if (savedLanguage != null) {
            selectedLanguage = savedLanguage;
            selectedPosition = getLanguagePosition(languageCodes, savedLanguage);

            adapter.notifyDataSetChanged();
        }

        // Handle language selection
        languageListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            // Update selected language and position
            selectedLanguage = languageCodes[position];
            selectedPosition = position;

            // Refresh the list to immediately highlight the selection
            adapter.notifyDataSetChanged();
        });

        // Save button logic
        saveLanguageButton.setOnClickListener(v -> {
            // Fallback to default language if nothing is selected
            if (selectedPosition == -1) {
                selectedLanguage = "en"; // Default to English
                selectedPosition = 0; // Default to the first language in the list

                adapter.notifyDataSetChanged();
            }

            // Save the selected language to SharedPreferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(LANGUAGE_KEY, selectedLanguage);
            editor.apply();

            // Apply the new language
            applyLocale(selectedLanguage);

            // Display a confirmation message
            Toast.makeText(this, getString(R.string.language_saved) + " " + languages[selectedPosition], Toast.LENGTH_SHORT).show();

            // Recreate the activity to apply the language change
            recreate();

        });

        // Bottom navigation setup
        setupBottomNavigation();
    }

    private void applySavedLanguage() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        String savedLanguage = preferences.getString(LANGUAGE_KEY, "en"); // Default to English
        if (savedLanguage == null || savedLanguage.isEmpty()) {
            savedLanguage = "en"; // Fallback to English
        }
        applyLocale(savedLanguage);
    }


    private void applyLocale(String languageCode) {
        // Fallback to English if the provided language code is null or unsupported
        if (languageCode == null || languageCode.isEmpty()) {
            languageCode = "en"; // Default to English
        }

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);

        // Update configuration for application context
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Update configuration for the current activity
        getApplicationContext().getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private int getLanguagePosition(String[] languageCodes, String languageCode) {
        for (int i = 0; i < languageCodes.length; i++) {
            if (languageCodes[i].equals(languageCode)) {
                return i;
            }
        }
        return -1;
    }

    private void setupBottomNavigation() {
        home_layout_button.setOnClickListener(v -> {
            Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        explore_layout_button.setOnClickListener(v -> {
            Intent intent = new Intent(LanguageActivity.this, ExploreActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });

        planner_layout_button.setOnClickListener(v -> {
            Intent intent = new Intent(LanguageActivity.this, TripPlannerActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        });
    }
}