package com.example.worldlist3dapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "AppPreferences";
    private static final String LANGUAGE_KEY = "SelectedLanguage";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(updateLanguageContext(newBase));
    }

    private Context updateLanguageContext(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        String savedLanguage = preferences.getString(LANGUAGE_KEY, "en"); // Default to English
        Locale locale = new Locale(savedLanguage);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        return context.createConfigurationContext(config);
    }
}
