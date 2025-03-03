package com.leebarcaglobal.worldtravel3d.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.leebarcaglobal.worldtravel3d.R;

import java.util.Locale;

public class LanguageFragment extends Fragment {

    private static final String PREFERENCES_NAME = "AppPreferences";
    private static final String LANGUAGE_KEY = "SelectedLanguage";

    private String selectedLanguage;
    private int selectedPosition = -1;
    ImageView remove_ads_button;
    private LinearLayout iconsBottom, home_layout_button, explore_layout_button, planner_layout_button, language_layout_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.lang_frg,container,false);


        // Apply saved language
        //applySavedLanguage();


        // Language list and adapter
//        @SuppressLint({"MissingInflatedId",
//                "LocalSuppress"})
        ListView languageListView = frgView.findViewById(R.id.language_list_view);
        Button saveLanguageButton = frgView.findViewById(R.id.save_language_button);

        String[] languages = {
                "English", "Español", "Français", "Português", "中文", "日本語",
                "한국어", "हिंदी", "Русский", "العربية"
        };

        String[] languageCodes = {
                "en", "es", "fr", "pt", "zh", "ja",
                "ko", "hi", "ru", "ar"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, languages) {
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
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
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
            Log.d("Langggg_setOnItemClick",selectedLanguage);

            // Refresh the list to immediately highlight the selection
            adapter.notifyDataSetChanged();
        });

        // Save button logic
        saveLanguageButton.setOnClickListener(v -> {


            if (selectedPosition == -1) {
                selectedLanguage = "en"; // Default to English
                selectedPosition = 0;
            }

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(LANGUAGE_KEY, selectedLanguage);
            editor.apply();

            Log.d("Langggg_saveLanguage", "Saved language: " + selectedLanguage);

            // Apply the new language
            applyLocale(selectedLanguage);

            // Show confirmation
            Toast.makeText(requireActivity(), getString(R.string.language_saved) + " " + languages[selectedPosition], Toast.LENGTH_SHORT).show();

            // Restart the activity properly
            requireActivity().recreate();

//            if (selectedPosition == -1) {
//                selectedLanguage = "en"; // Default to English
//                selectedPosition = 0; // Default to the first language in the list
//
////                adapter.notifyDataSetChanged();
//            }
//
//            // Save the selected language to SharedPreferences
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString(LANGUAGE_KEY, selectedLanguage);
//            editor.apply();
//
//            Log.d("Langggg_saveLanguage",selectedLanguage);
//            // Apply the new language
//            applyLocale(selectedLanguage);
//
//            // Display a confirmation message
//            Toast.makeText(requireActivity(), getString(R.string.language_saved) + " " + languages[selectedPosition], Toast.LENGTH_SHORT).show();
//
//
//            Intent intent = requireActivity().getIntent();
//            requireActivity().finish();
//            requireActivity().startActivity(intent);

        });




        return frgView;
    }

    private void applySavedLanguage() {
        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        String savedLanguage = preferences.getString(LANGUAGE_KEY, "en"); // Default to English
        if (savedLanguage == null || savedLanguage.isEmpty()) {
            savedLanguage = "en"; // Fallback to English
        }
        //applyLocale(savedLanguage);
    }


//    private void applyLocale(String languageCode) {
//        if (languageCode == null || languageCode.isEmpty()) {
//            languageCode = "en";
//        }
//
//        Locale locale = new Locale(languageCode);
//        Locale.setDefault(locale);
//
//        Configuration config = getResources().getConfiguration();
//        config.setLocale(locale);
//
//        Log.d("Langggg_applyLocale",languageCode);
//        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
//
//        requireActivity().getApplicationContext().getResources().updateConfiguration(config, getResources().getDisplayMetrics());
//    }

    private void applyLocale(String languageCode) {
        if (languageCode == null || languageCode.isEmpty()) {
            languageCode = "en"; // Default to English
        }

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        // Update the configuration for the entire application context
        requireActivity().getApplicationContext().getResources().updateConfiguration(config, requireActivity().getResources().getDisplayMetrics());

        // Recreate the activity to apply the new locale
        requireActivity().recreate();

        Log.d("Langggg_applyLocale", "Applied locale: " + languageCode);
    }



    private int getLanguagePosition(String[] languageCodes, String languageCode) {
        for (int i = 0; i < languageCodes.length; i++) {
            if (languageCodes[i].equals(languageCode)) {
                return i;
            }
        }
        return -1;
    }


}

