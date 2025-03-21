package com.leebarcaglobal.worldtravel3d.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;


import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.utils.TripDatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTripFragment extends Fragment {


    private Spinner countrySpinner;
    private EditText editDepartureDate, editReturnDate;
    private LinearLayout gridDays;
    private Button buttonCreateTrip;
    private ImageButton back_button;

    private final Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.add_trip_frg,container,false);


        // Initialize views
        countrySpinner = frgView.findViewById(R.id.category_spinner);
        editDepartureDate = frgView.findViewById(R.id.edit_departure_date);
        editReturnDate = frgView.findViewById(R.id.edit_return_date);
        gridDays = frgView.findViewById(R.id.grid_days);
        buttonCreateTrip = frgView.findViewById(R.id.button_search);
        back_button = frgView.findViewById(R.id.back_button);

        // Set gridDays visibility to GONE initially
        gridDays.setVisibility(View.GONE);

        String[] countriesList = {getString(R.string.afghanistan),
                getString(R.string.albania),
                getString(R.string.algeria),
                getString(R.string.andorra),
                getString(R.string.angola),
                getString(R.string.antigua_and_barbuda),
                getString(R.string.argentina),
                getString(R.string.armenia),
                getString(R.string.australia),
                getString(R.string.austria),
                getString(R.string.azerbaijan),
                getString(R.string.bahamas),
                getString(R.string.bahrain),
                getString(R.string.bangladesh),
                getString(R.string.barbados),
                getString(R.string.belarus),
                getString(R.string.belgium),
                getString(R.string.belize),
                getString(R.string.benin),
                getString(R.string.bhutan),
                getString(R.string.bolivia),
                getString(R.string.bosnia_and_herzegovina),
                getString(R.string.botswana),
                getString(R.string.brazil),
                getString(R.string.brunei),
                getString(R.string.bulgaria),
                getString(R.string.burkina_faso),
                getString(R.string.burundi),
                getString(R.string.cabo_verde),
                getString(R.string.cambodia),
                getString(R.string.cameroon),
                getString(R.string.canada),
                getString(R.string.central_african_republic),
                getString(R.string.chad),
                getString(R.string.chile),
                getString(R.string.china),
                getString(R.string.colombia),
                getString(R.string.comoros),
                getString(R.string.congo),
                getString(R.string.costa_rica),
                getString(R.string.croatia),
                getString(R.string.cuba),
                getString(R.string.cyprus),
                getString(R.string.czech_republic),
                getString(R.string.drc_congo),
                getString(R.string.denmark),
                getString(R.string.djibouti),
                getString(R.string.dominica),
                getString(R.string.dominican_republic),
                getString(R.string.east_timor),
                getString(R.string.ecuador),
                getString(R.string.egypt),
                getString(R.string.el_salvador),
                getString(R.string.equatorial_guinea),
                getString(R.string.eritrea),
                getString(R.string.estonia),
                getString(R.string.eswatini),
                getString(R.string.ethiopia),
                getString(R.string.fiji),
                getString(R.string.finland),
                getString(R.string.france),
                getString(R.string.gabon),
                getString(R.string.georgia),
                getString(R.string.germany),
                getString(R.string.ghana),
                getString(R.string.greece),
                getString(R.string.grenada),
                getString(R.string.guatemala),
                getString(R.string.guinea),
                getString(R.string.guineabissau),
                getString(R.string.guyana),
                getString(R.string.haiti),
                getString(R.string.honduras),
                getString(R.string.hungary),
                getString(R.string.iceland),
                getString(R.string.india),
                getString(R.string.indonesia),
                getString(R.string.iran),
                getString(R.string.iraq),
                getString(R.string.ireland),
                getString(R.string.israel),
                getString(R.string.italy),
                getString(R.string.ivory_coast),
                getString(R.string.jamaica),
                getString(R.string.japan),
                getString(R.string.jordan),
                getString(R.string.kazakhstan),
                getString(R.string.kenya),
                getString(R.string.kiribati),
                getString(R.string.kosovo),
                getString(R.string.kuwait),
                getString(R.string.kyrgyzstan),
                getString(R.string.laos),
                getString(R.string.latvia),
                getString(R.string.lebanon),
                getString(R.string.lesotho),
                getString(R.string.liberia),
                getString(R.string.libya),
                getString(R.string.liechtenstein),
                getString(R.string.lithuania),
                getString(R.string.luxembourg),
                getString(R.string.madagascar),
                getString(R.string.malawi),
                getString(R.string.malaysia),
                getString(R.string.maldives),
                getString(R.string.mali),
                getString(R.string.malta),
                getString(R.string.marshallislands),
                getString(R.string.mauritania),
                getString(R.string.mauritius),
                getString(R.string.mexico),
                getString(R.string.micronesia),
                getString(R.string.moldova),
                getString(R.string.monaco),
                getString(R.string.mongolia),
                getString(R.string.montenegro),
                getString(R.string.morocco),
                getString(R.string.mozambique),
                getString(R.string.myanmar),
                getString(R.string.namibia),
                getString(R.string.nauru),
                getString(R.string.nepal),
                getString(R.string.netherlands),
                getString(R.string.new_zealand),
                getString(R.string.nicaragua),
                getString(R.string.niger),
                getString(R.string.nigeria),
                getString(R.string.north_korea),
                getString(R.string.north_macedonia),
                getString(R.string.norway),
                getString(R.string.oman),
                getString(R.string.pakistan),
                getString(R.string.palau),
                getString(R.string.panama),
                getString(R.string.papua_new_guinea),
                getString(R.string.paraguay),
                getString(R.string.peru),
                getString(R.string.philippines),
                getString(R.string.poland),
                getString(R.string.portugal),
                getString(R.string.qatar),
                getString(R.string.romania),
                getString(R.string.russia),
                getString(R.string.rwanda),
                getString(R.string.saint_kitts_and_nevis),
                getString(R.string.saint_lucia),
                getString(R.string.saint_vincent_and_the_grenadines),
                getString(R.string.samoa),
                getString(R.string.san_marino),
                getString(R.string.sao_tome_and_principe),
                getString(R.string.saudi_arabia),
                getString(R.string.senegal),
                getString(R.string.serbia),
                getString(R.string.seychelles),
                getString(R.string.sierra_leone),
                getString(R.string.singapore),
                getString(R.string.slovakia),
                getString(R.string.slovenia),
                getString(R.string.solomon_islands),
                getString(R.string.somalia),
                getString(R.string.south_africa),
                getString(R.string.south_korea),
                getString(R.string.south_sudan),
                getString(R.string.spain),
                getString(R.string.sri_lanka),
                getString(R.string.sudan),
                getString(R.string.suriname),
                getString(R.string.sweden),
                getString(R.string.switzerland),
                getString(R.string.syria),
                getString(R.string.taiwan),
                getString(R.string.tajikistan),
                getString(R.string.tanzania),
                getString(R.string.thailand),
                getString(R.string.the_gambia),
                getString(R.string.togo),
                getString(R.string.tonga),
                getString(R.string.trinidad_and_tobago),
                getString(R.string.tunisia),
                getString(R.string.turkey),
                getString(R.string.turkmenistan),
                getString(R.string.tuvalu),
                getString(R.string.uganda),
                getString(R.string.ukraine),
                getString(R.string.united_arab_emirates),
                getString(R.string.united_kingdom),
                getString(R.string.united_states),
                getString(R.string.uruguay),
                getString(R.string.uzbekistan),
                getString(R.string.vanuatu),
                getString(R.string.vatican_city),
                getString(R.string.venezuela),
                getString(R.string.vietnam),
                getString(R.string.yemen),
                getString(R.string.zambia),
                getString(R.string.zimbabwe)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_dropdown_item, countriesList);
        countrySpinner.setAdapter(adapter);

        // Setup date pickers
        setupDatePicker(editDepartureDate);
        setupDatePicker(editReturnDate);

        // Add TextWatchers to validate and populate days when dates are entered
        setupDateValidation();

        // Create trip button
        buttonCreateTrip.setOnClickListener(v -> handleCreateTrip());

        back_button.setOnClickListener(v -> {
            // Handle click event
            requireActivity().onBackPressed();
        });
        return frgView;
    }

    private void setupDatePicker(EditText editText) {
        editText.setOnClickListener(v -> {
            Context context = new ContextThemeWrapper(requireActivity(), R.style.CustomDatePickerDialogTheme);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    context,
                    R.style.CustomDatePickerDialogTheme,
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        editText.setText(dateFormat.format(calendar.getTime()));
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });
    }

    private void setupDateValidation() {
        TextWatcher dateWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validateAndPopulateDays();
            }
        };

        editDepartureDate.addTextChangedListener(dateWatcher);
        editReturnDate.addTextChangedListener(dateWatcher);
    }

    private boolean validateAndPopulateDays() {
        String departureDateStr = editDepartureDate.getText().toString();
        String returnDateStr = editReturnDate.getText().toString();

        if (departureDateStr.isEmpty() || returnDateStr.isEmpty()) {
            gridDays.setVisibility(View.GONE); // Hide gridDays if dates are incomplete
            gridDays.removeAllViews();
            return false;
        }

        try {
            Calendar depCalendar = Calendar.getInstance();
            Calendar retCalendar = Calendar.getInstance();

            depCalendar.setTime(dateFormat.parse(departureDateStr));
            retCalendar.setTime(dateFormat.parse(returnDateStr));

            if (retCalendar.before(depCalendar)) {
                editReturnDate.setError(getString(R.string.trip_error));
                gridDays.setVisibility(View.GONE); // Hide gridDays if date range is invalid
                gridDays.removeAllViews();
                return false;
            }

            int days = (int) ((retCalendar.getTimeInMillis() - depCalendar.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;

            populateDaysGrid(days);
            gridDays.setVisibility(View.VISIBLE); // Show gridDays if dates are valid
            return true;

        } catch (ParseException e) {
            e.printStackTrace();
            gridDays.setVisibility(View.GONE); // Hide gridDays if there's an exception
            gridDays.removeAllViews();
            return false;
        }
    }

    @SuppressLint("SetTextI18n")
    private void populateDaysGrid(int days) {
        gridDays.removeAllViews();

        for (int i = 1; i <= days; i++) {
            LinearLayout dayLayout = new LinearLayout(requireActivity());
            dayLayout.setOrientation(LinearLayout.VERTICAL);
            dayLayout.setPadding(16, 16, 16, 16);
            dayLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            TextView dayLabel = new TextView(requireActivity());
            dayLabel.setText(getString(R.string.day) + " " + i);
            dayLabel.setPadding(8, 8, 8, 8);
            dayLabel.setTextColor(getResources().getColor(R.color.black));
            dayLabel.setTypeface(ResourcesCompat.getFont(requireActivity(), R.font.open_sans));

            EditText dayInput = new EditText(requireActivity());
            dayInput.setPadding(8, 8, 8, 8);
            dayInput.setTextColor(getResources().getColor(R.color.black));
            dayInput.setBackgroundResource(R.drawable.button_background);
            dayInput.setTypeface(ResourcesCompat.getFont(requireActivity(), R.font.open_sans));

            int newHeight = (int) (getResources().getDisplayMetrics().density * 45);

            // Set height using LayoutParams
            LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    newHeight // Set height to 100dp
            );
            dayInput.setLayoutParams(inputParams);

            dayLayout.addView(dayLabel);
            dayLayout.addView(dayInput);

            gridDays.addView(dayLayout);
        }
    }

    private void handleCreateTrip() {
        String country = countrySpinner.getSelectedItem().toString();
        String departureDate = editDepartureDate.getText().toString();
        String returnDate = editReturnDate.getText().toString();

        if (departureDate.isEmpty() || returnDate.isEmpty()) {
            Toast.makeText(requireActivity(), R.string.trip_error, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!validateDayInputs()) {
            return;
        }

        TripDatabaseHelper dbHelper = new TripDatabaseHelper(requireActivity());
        long tripId = dbHelper.addTrip(country, departureDate, returnDate);

        saveManualItinerary(tripId);

        Toast.makeText(requireActivity(), R.string.trip_successful, Toast.LENGTH_SHORT).show();
//        setResult(RESULT_OK);
//        finish();

        requireActivity().onBackPressed();
    }

    private boolean validateDayInputs() {
        for (int i = 0; i < gridDays.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) gridDays.getChildAt(i);
            EditText dayInput = (EditText) row.getChildAt(1);

            if (dayInput.getText().toString().isEmpty()) {
                dayInput.setError(getString(R.string.trip_error));
                dayInput.requestFocus();
                return false;
            }
        }
        return true;
    }

    private void saveManualItinerary(long tripId) {
        TripDatabaseHelper dbHelper = new TripDatabaseHelper(requireActivity());

        for (int i = 0; i < gridDays.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) gridDays.getChildAt(i);
            TextView dayLabel = (TextView) row.getChildAt(0);
            EditText dayInput = (EditText) row.getChildAt(1);

            dbHelper.addDayTrip(tripId, dayLabel.getText().toString(), dayInput.getText().toString());
        }
    }
}
