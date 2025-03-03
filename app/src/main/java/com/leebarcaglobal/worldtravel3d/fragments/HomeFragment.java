package com.leebarcaglobal.worldtravel3d.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.leebarcaglobal.worldtravel3d.MainActivity;
import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.adapters.CountryAdapter;
import com.leebarcaglobal.worldtravel3d.dataProviders.CountryData;
import com.leebarcaglobal.worldtravel3d.models.CountryInfo;
import com.leebarcaglobal.worldtravel3d.utils.FragmentTags;
import com.leebarcaglobal.worldtravel3d.utils.UtilClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeFragment extends Fragment {

    private ListView countryListView;
    private EditText searchBar;
    private ImageView filter;
    private List<CountryInfo> countries;
    private CountryAdapter adapter;
    private ScrollView filterScrollContainer;
    private Set<String> selectedLanguages = new HashSet<>();
    private Set<String> selectedContinents = new HashSet<>();
    private Set<String> selectedMonths = new HashSet<>();
    TextView noCountriesFoundTextView;

    ChipGroup chipLangGroup,chipContinentGroup,chipMonthGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.home_frg,container,false);

        countryListView = frgView.findViewById(R.id.country_list_view);
        searchBar =  frgView.findViewById(R.id.search_bar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterCountries();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        filter =  frgView.findViewById(R.id.filter_icon);
        filterScrollContainer =  frgView.findViewById(R.id.filter_scroll_container);
        chipLangGroup =  frgView.findViewById(R.id.chipLangGroup);
        chipContinentGroup =  frgView.findViewById(R.id.chipContinentGroup);
        chipMonthGroup =  frgView.findViewById(R.id.chipMonthGroup);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterScrollContainer.getVisibility() == View.GONE) {
                    filterScrollContainer.setVisibility(View.VISIBLE);
                } else {
                    filterScrollContainer.setVisibility(View.GONE);
                }
            }
        });
        noCountriesFoundTextView = frgView.findViewById(R.id.no_countries_found);


//        GridLayout languageGrid = frgView.findViewById(R.id.language_filter_grid);
//        GridLayout continentGrid = frgView.findViewById(R.id.continent_filter_grid);
//        GridLayout bestMonthsToVisitGrid = frgView.findViewById(R.id.month_filter_grid);


        String[] languages = {getString(R.string.english), getString(R.string.spanish), getString(R.string.french), getString(R.string.german), getString(R.string.italian), getString(R.string.portuguese), getString(R.string.dutch), getString(R.string.arabic), getString(R.string.russian), getString(R.string.chinese), getString(R.string.malay), getString(R.string.hindi), getString(R.string.korean)};
        String[] continents = {getString(R.string.north_america), getString(R.string.south_america), getString(R.string.europe), getString(R.string.africa), getString(R.string.asia), getString(R.string.australasia)};
        String[] months = {getString(R.string.january), getString(R.string.february), getString(R.string.march), getString(R.string.april), getString(R.string.may), getString(R.string.june), getString(R.string.july), getString(R.string.august), getString(R.string.september), getString(R.string.october), getString(R.string.november), getString(R.string.december)};

        addFilterChips(chipLangGroup, languages, selectedLanguages);
        addFilterChips(chipContinentGroup, continents, selectedContinents);
        addFilterChips(chipMonthGroup, months, selectedMonths);


//        addFilterButtons(languageGrid, languages, selectedLanguages);
//        addFilterButtons(continentGrid, continents, selectedContinents);
//        addFilterButtons(bestMonthsToVisitGrid, months, selectedMonths);

        countries = CountryData.getCountriesInfo(requireActivity().getApplicationContext());
        adapter = new CountryAdapter(requireActivity().getApplicationContext(), countries);
        countryListView.setAdapter(adapter);

        // Set up item click listener to open CountryDetailActivity
        countryListView.setOnItemClickListener((parent, view, position, id) -> {



            if (!UtilClass.isAdRemovalPurchased(requireActivity())) { // not Purchased Show Ads

                if(MainActivity.adClickCounter>1 && MainActivity.adClickCounter%4==0){
                    ((MainActivity) requireActivity()).showAdmobAd();
                }
                MainActivity.adClickCounter++;
            }


            CountryInfo selectedCountry = adapter.getItem(position);


            CountryDetailFragment fragment = new CountryDetailFragment();
            Bundle args = new Bundle();

            args.putInt("countryImageResId", selectedCountry.getImageResId());
            args.putString("countryName", selectedCountry.getName());
            args.putString("countryCapital", selectedCountry.getCapital());
            args.putString("countryPopulation", selectedCountry.getPopulation());
            args.putString("countryLanguage", selectedCountry.getLanguage());
            args.putString("countryContinent", selectedCountry.getContinent());
            args.putString("countryArea", selectedCountry.getArea());
            args.putInt("countryFlag", selectedCountry.getFlagResId());
            args.putString("countryDescription", selectedCountry.getDescription());
            args.putString("firstAttractionName", selectedCountry.getFirstAttractionName());
            args.putString("firstAttractionDetails", selectedCountry.getFirstAttractionDetails());
            args.putInt("firstAttractionImage", selectedCountry.getFirstAttractionImage());
            args.putString("secondAttractionName", selectedCountry.getSecondAttractionName());
            args.putString("secondAttractionDetails", selectedCountry.getSecondAttractionDetails());
            args.putInt("secondAttractionImage", selectedCountry.getSecondAttractionImage());
            args.putString("thirdAttractionName", selectedCountry.getThirdAttractionName());
            args.putString("thirdAttractionDetails", selectedCountry.getThirdAttractionDetails());
            args.putInt("thirdAttractionImage", selectedCountry.getThirdAttractionImage());
            args.putIntArray("bestTimeVisitArray", selectedCountry.getBestTimeVisit());
            args.putString("factInfo", selectedCountry.getFact());
            args.putString("websiteInfo", selectedCountry.getWebsite());
            args.putStringArray("weatherInfo", selectedCountry.getWeather());
            args.putStringArray("cuisineArray", selectedCountry.getCuisine());
            args.putStringArray("safetyArray", selectedCountry.getSafety());
            args.putString("currency", selectedCountry.getCurrency());

            fragment.setArguments(args);







            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, FragmentTags.COUNTRY_DETAILS_FRAGMENT_TAG)
                    .addToBackStack(FragmentTags.COUNTRY_DETAILS_FRAGMENT_TAG)
                    .commit();

//
//            Intent intent = new Intent(MainActivity.this, CountryDetailActivity.class);
//            intent.putExtra("countryImageResId", selectedCountry.getImageResId());
//            intent.putExtra("countryName", selectedCountry.getName());
//            intent.putExtra("countryCapital", selectedCountry.getCapital());
//            intent.putExtra("countryPopulation", selectedCountry.getPopulation());
//            intent.putExtra("countryLanguage", selectedCountry.getLanguage());
//            intent.putExtra("countryContinent", selectedCountry.getContinent());
//            intent.putExtra("countryArea", selectedCountry.getArea());
//            intent.putExtra("countryFlag", selectedCountry.getFlagResId());
//            intent.putExtra("countryDescription", selectedCountry.getDescription());
//            intent.putExtra("firstAttractionName", selectedCountry.getFirstAttractionName());
//            intent.putExtra("firstAttractionDetails", selectedCountry.getFirstAttractionDetails());
//            intent.putExtra("firstAttractionImage", selectedCountry.getFirstAttractionImage());
//            intent.putExtra("secondAttractionName", selectedCountry.getSecondAttractionName());
//            intent.putExtra("secondAttractionDetails", selectedCountry.getSecondAttractionDetails());
//            intent.putExtra("secondAttractionImage", selectedCountry.getSecondAttractionImage());
//            intent.putExtra("thirdAttractionName", selectedCountry.getThirdAttractionName());
//            intent.putExtra("thirdAttractionDetails", selectedCountry.getThirdAttractionDetails());
//            intent.putExtra("thirdAttractionImage", selectedCountry.getThirdAttractionImage());
//            intent.putExtra("bestTimeVisitArray", selectedCountry.getBestTimeVisit());
//            intent.putExtra("factInfo", selectedCountry.getFact());
//            intent.putExtra("websiteInfo", selectedCountry.getWebsite());
//            intent.putExtra("weatherInfo", selectedCountry.getWeather());
//            intent.putExtra("cuisineArray", selectedCountry.getCuisine());
//            intent.putExtra("safetyArray", selectedCountry.getSafety());
//            intent.putExtra("currency", selectedCountry.getCurrency());
//
//            startActivity(intent);
        });

        return frgView;
    }

    // Function to add filter chips
    private void addFilterChips(ChipGroup chipGroup, String[] values, Set<String> selectedValues) {
        chipGroup.removeAllViews(); // Clear previous chips

        for (String value : values) {
            Chip chip = new Chip(requireActivity());
            chip.setText(value);
            chip.setCheckable(true); // Allow selection
            chip.setChipBackgroundColorResource(R.color.chip_unselected); // Default background color
            chip.setTextColor(getResources().getColor(R.color.black));
            chip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            chip.setPadding(8, 8, 8, 8);

            // Set margins programmatically
            ChipGroup.LayoutParams params = new ChipGroup.LayoutParams(
                    ChipGroup.LayoutParams.WRAP_CONTENT, // Width wraps content
                    ChipGroup.LayoutParams.WRAP_CONTENT  // Height wraps content
            );
            params.setMargins(8, 8, 8, 8);
            chip.setLayoutParams(params);

            // Set chip selection logic
            chip.setOnClickListener(v -> {
                if (selectedValues.contains(value)) {
                    selectedValues.remove(value);
                    chip.setChipBackgroundColorResource(R.color.chip_unselected); // Unselected color
                } else {
                    selectedValues.add(value);
                    chip.setChipBackgroundColorResource(R.color.chip_selected); // Selected color
                }
                filterCountries(); // Update the country list based on selected filters
            });

            chipGroup.addView(chip); // Add chip to the group
        }
    }

    private void filterCountries() {
        String query = searchBar.getText().toString().toLowerCase();
        List<CountryInfo> filteredCountries = new ArrayList<>();

        Map<String, Integer> monthToIndexMap = new HashMap<>();
        monthToIndexMap.put(getString(R.string.january), 0);
        monthToIndexMap.put(getString(R.string.february), 1);
        monthToIndexMap.put(getString(R.string.march), 2);
        monthToIndexMap.put(getString(R.string.april), 3);
        monthToIndexMap.put(getString(R.string.may), 4);
        monthToIndexMap.put(getString(R.string.june), 5);
        monthToIndexMap.put(getString(R.string.july), 6);
        monthToIndexMap.put(getString(R.string.august), 7);
        monthToIndexMap.put(getString(R.string.september), 8);
        monthToIndexMap.put(getString(R.string.october), 9);
        monthToIndexMap.put(getString(R.string.november), 10);
        monthToIndexMap.put(getString(R.string.december), 11);

        for (CountryInfo country : countries) {
            boolean matchesSearch = query.isEmpty() || country.getName().toLowerCase().contains(query);
            boolean matchesLanguage = false;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                matchesLanguage = selectedLanguages.isEmpty() ||
                        selectedLanguages.stream().anyMatch(language ->
                                country.getLanguage().toLowerCase().contains(language.toLowerCase()));
            }
            boolean matchesContinent = selectedContinents.isEmpty() || selectedContinents.contains(country.getContinent());
            boolean matchesMonth = true;
            if (!selectedMonths.isEmpty()) {
                matchesMonth = false; // Initialize to false, will set to true if we find a match

                for (String month : selectedMonths) {
                    int monthIndex = monthToIndexMap.get(month);

                    // Check if the best time to visit this month is marked as "1" (ideal)
                    if (country.getBestTimeVisit()[monthIndex] == 1) {
                        matchesMonth = true;
                        break;
                    }
                }
            }

            if (matchesSearch && matchesLanguage && matchesContinent && matchesMonth) {
                filteredCountries.add(country);
            }
        }

        adapter.updateCountries(filteredCountries);


        if (filteredCountries.isEmpty()) {
            noCountriesFoundTextView.setVisibility(View.VISIBLE);
            countryListView.setVisibility(View.GONE);
        } else {
            noCountriesFoundTextView.setVisibility(View.GONE);
            countryListView.setVisibility(View.VISIBLE);
        }


    }
}
