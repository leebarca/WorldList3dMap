package com.leebarcaglobal.worldtravel3d.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.utils.FragmentTags;


/** @noinspection ALL*/
public class CountryDetailFragment extends Fragment {

    private LinearLayout infoIcon;
    private LinearLayout attractionsIcon;
    private LinearLayout mapIcon;
    private LinearLayout languagesIcon;
    private LinearLayout currencyIcon;
    private LinearLayout capitalIcon;
    private TextView toolbar_title;
    String country_name="",capital ="",currency = "", countryLang = "";
    String country_info = "", facts = "",websites = "";
    int[] bestTimesToVisit;
    String[] cuisine;

    String[] safety;
    String[] weather;
    String firstAttractionName,secondAttractionName,
            thirdAttractionName,firstAttractionDetails,secondAttractionDetails,thirdAttractionDetails = "";

    int firstAttractionImage,secondAttractionImage,thirdAttractionImage = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.country_detail_frg,container,false);



        ImageView countryImage = frgView.findViewById(R.id.map_container);
        TextView countryName = frgView.findViewById(R.id.countryName);
        ImageView countryFlag = frgView.findViewById(R.id.country_flag);
        TextView countryCapital = frgView.findViewById(R.id.textView_capital);
        TextView countryPopulation = frgView.findViewById(R.id.textView_population);
        TextView countryLanguage = frgView.findViewById(R.id.textView_languages);
        TextView countryCurrency = frgView.findViewById(R.id.textView_currency);
        TextView countryContinent = frgView.findViewById(R.id.textView_continent);
        TextView countryArea = frgView.findViewById(R.id.textView_area);
        TextView toolbar_title = frgView.findViewById(R.id.toolbar_title);
        infoIcon = frgView.findViewById(R.id.open_info_element);
        attractionsIcon = frgView.findViewById(R.id.open_attractions_element);
        mapIcon = frgView.findViewById(R.id.button_expand_map);
        languagesIcon = frgView.findViewById(R.id.open_languages_element);
        currencyIcon = frgView.findViewById(R.id.open_currency_element);
        capitalIcon = frgView.findViewById(R.id.open_capital_element);
        ImageButton back_button = frgView.findViewById(R.id.back_button);

        if (getArguments() != null) {

            countryImage.setImageResource(getArguments().getInt("countryImageResId", 0));
            countryName.setText(getArguments().getString("countryName"));

            country_name = getArguments().getString("countryName");
            toolbar_title.setText(country_name);
            countryFlag.setImageResource(getArguments().getInt("countryFlag", 0));
            countryCapital.setText(getArguments().getString("countryCapital"));
            capital = getArguments().getString("countryCapital");

            countryPopulation.setText(getArguments().getString("countryPopulation"));
            countryLanguage.setText(getArguments().getString("countryLanguage"));
            countryLang = getArguments().getString("countryLanguage");
            countryCurrency.setText(getArguments().getString("currency"));

            currency = getArguments().getString("currency");
            countryContinent.setText(getArguments().getString("countryContinent"));
            countryArea.setText(getArguments().getString("countryArea"));

            // Get Info to pass onto other classes
            country_info = getArguments().getString("countryDescription");
            bestTimesToVisit = getArguments().getIntArray("bestTimeVisitArray");
            firstAttractionName = getArguments().getString("firstAttractionName");
            secondAttractionName = getArguments().getString("secondAttractionName");
            thirdAttractionName = getArguments().getString("thirdAttractionName");
            firstAttractionDetails = getArguments().getString("firstAttractionDetails");
            secondAttractionDetails = getArguments().getString("secondAttractionDetails");
            thirdAttractionDetails = getArguments().getString("thirdAttractionDetails");
            firstAttractionImage = getArguments().getInt("firstAttractionImage", 0);
            secondAttractionImage = getArguments().getInt("secondAttractionImage", 0);
            thirdAttractionImage = getArguments().getInt("thirdAttractionImage", 0);
            facts = getArguments().getString("factInfo");
            websites = getArguments().getString("websiteInfo");
            weather = getArguments().getStringArray("weatherInfo");
            cuisine = getArguments().getStringArray("cuisineArray");
            safety = getArguments().getStringArray("safetyArray");





        }

        infoIcon.setOnClickListener(v -> {
            // Handle click event
//            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsInfo.class);
//            intent.putExtra("countryDescription", country_info);
//            intent.putExtra("bestTimesToVisit", bestTimesToVisit);
//            intent.putExtra("fact_info", facts);
//            intent.putExtra("website_info", websites);
//            intent.putExtra("weather_info", weather);
//            intent.putExtra("cuisine_info", cuisine);
//            intent.putExtra("safety_info", safety);
//            startActivity(intent);



            CountryDetailInfoFrg fragment = new CountryDetailInfoFrg();
            Bundle args = new Bundle();

            args.putString("countryDescription",country_info);
            args.putIntArray("bestTimesToVisit", bestTimesToVisit);
            args.putString("fact_info",facts);
            args.putString("website_info", websites);
            args.putStringArray("weather_info", weather);
            args.putStringArray("cuisine_info", cuisine);
            args.putStringArray("safety_info",safety);
            args.putString("country_name",country_name);


            fragment.setArguments(args);







            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, FragmentTags.COUNTRY_INFO_DETAILS_FRAGMENT_TAG)
                    .addToBackStack(FragmentTags.COUNTRY_INFO_DETAILS_FRAGMENT_TAG)
                    .commit();

        });

        attractionsIcon.setOnClickListener(v -> {
            // Handle click event
//            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsAttractions.class);
//            intent.putExtra("firstAttractionName", firstAttractionName);
//            intent.putExtra("secondAttractionName", secondAttractionName);
//            intent.putExtra("thirdAttractionName", thirdAttractionName);
//            intent.putExtra("firstAttractionDetails", firstAttractionDetails);
//            intent.putExtra("secondAttractionDetails", secondAttractionDetails);
//            intent.putExtra("thirdAttractionDetails", thirdAttractionDetails);
//            intent.putExtra("firstAttractionImage", firstAttractionImage);
//            intent.putExtra("secondAttractionImage", secondAttractionImage);
//            intent.putExtra("thirdAttractionImage", thirdAttractionImage);
//            startActivity(intent);

            CountryDetailsAttractionsFragment countryDetailsAttractionsFragment = new CountryDetailsAttractionsFragment();
            Bundle args = new Bundle();


            args.putString("firstAttractionName", country_name);
            args.putString("secondAttractionName",countryLang);

            args.putString("firstAttractionName", firstAttractionName);
            args.putString("secondAttractionName", secondAttractionName);
            args.putString("thirdAttractionName", thirdAttractionName);
            args.putString("firstAttractionDetails", firstAttractionDetails);
            args.putString("secondAttractionDetails", secondAttractionDetails);
            args.putString("thirdAttractionDetails", thirdAttractionDetails);
            args.putInt("firstAttractionImage", firstAttractionImage);
            args.putInt("secondAttractionImage", secondAttractionImage);
            args.putInt("thirdAttractionImage", thirdAttractionImage);


            args.putString("country_name",country_name);
            countryDetailsAttractionsFragment.setArguments(args);


            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, countryDetailsAttractionsFragment, FragmentTags.COUNTRY_DETAILS_ATTR_FRAGMENT_TAG)
                    .addToBackStack(FragmentTags.COUNTRY_DETAILS_ATTR_FRAGMENT_TAG)
                    .commit();


        });

        mapIcon.setOnClickListener(v -> {
            // Replace "CountryName" with the name of the country you want to show
            Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(country_name) + "&basemap=satellite");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            // Attempt to directly open Google Maps
            mapIntent.setPackage("com.google.android.apps.maps");

            try {
                startActivity(mapIntent);
            } catch (ActivityNotFoundException e) {
                // Redirect user to Google Play Store to install or update Google Maps
                try {
                    Uri playStoreUri = Uri.parse("market://details?id=com.google.android.apps.maps");
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, playStoreUri);
                    startActivity(playStoreIntent);
                } catch (ActivityNotFoundException e2) {
                    // If Play Store is not available, open in a browser
                    Uri browserUri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, browserUri);
                    startActivity(browserIntent);
                }
            }
        });

        languagesIcon.setOnClickListener(v -> {
            // Handle click event
//            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsLanguages.class);
//            intent.putExtra("countryName", country_name);
//            intent.putExtra("languages", languages);
//            startActivity(intent);


            CountryDetailsLanguagesFragment countryDetailsLangFragment = new CountryDetailsLanguagesFragment();

            Bundle args = new Bundle();


            args.putString("countryName", country_name);
            args.putString("languages",countryLang);
            countryDetailsLangFragment.setArguments(args);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, countryDetailsLangFragment, FragmentTags.COUNTRY_DETAILS_LANG_FRAGMENT_TAG)
                    .addToBackStack(FragmentTags.COUNTRY_DETAILS_LANG_FRAGMENT_TAG)
                    .commit();



        });

        currencyIcon.setOnClickListener(v -> {
            // Handle click event
//            Intent intent = new Intent(CountryDetailActivity.this, CountryDetailsCurrency.class);
//            intent.putExtra("countryName", country_name);
//            intent.putExtra("currency", currency);
//            startActivity(intent);



            CountryDetailCurrencyFragment countryDetailCurrencyFragment = new CountryDetailCurrencyFragment();
            Bundle args = new Bundle();


            args.putString("countryName", country_name);
            args.putString("currency",currency);


            countryDetailCurrencyFragment.setArguments(args);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, countryDetailCurrencyFragment, FragmentTags.COUNTRY_DETAILS_CURRENCY_FRAGMENT_TAG)
                    .addToBackStack(FragmentTags.COUNTRY_DETAILS_CURRENCY_FRAGMENT_TAG)
                    .commit();
        });

        capitalIcon.setOnClickListener(v -> {
            // Replace "CountryName" with the name of the country you want to show
            Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(capital) + "&basemap=satellite");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            // Attempt to directly open Google Maps
            mapIntent.setPackage("com.google.android.apps.maps");

            try {
                startActivity(mapIntent);
            } catch (ActivityNotFoundException e) {
                // Redirect user to Google Play Store to install or update Google Maps
                try {
                    Uri playStoreUri = Uri.parse("market://details?id=com.google.android.apps.maps");
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, playStoreUri);
                    startActivity(playStoreIntent);
                } catch (ActivityNotFoundException e2) {
                    // If Play Store is not available, open in a browser
                    Uri browserUri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, browserUri);
                    startActivity(browserIntent);
                }
            }
        });

        back_button.setOnClickListener(v -> {
            // Handle click event
            requireActivity().onBackPressed();
        });


        return frgView;
    }
}
