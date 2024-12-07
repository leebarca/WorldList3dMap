package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.core.content.res.ResourcesCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTrip extends BaseActivity {

    private Spinner countrySpinner;
    private EditText editDepartureDate, editReturnDate;
    private LinearLayout gridDays;
    private Button buttonCreateTrip;

    private final Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        // Initialize views
        countrySpinner = findViewById(R.id.category_spinner);
        editDepartureDate = findViewById(R.id.edit_departure_date);
        editReturnDate = findViewById(R.id.edit_return_date);
        gridDays = findViewById(R.id.grid_days);
        buttonCreateTrip = findViewById(R.id.button_search);

        // Set gridDays visibility to GONE initially
        gridDays.setVisibility(View.GONE);

        String[] countriesList = {getString(R.string.afghanistan), getString(R.string.albania), getString(R.string.algeria)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, countriesList);
        countrySpinner.setAdapter(adapter);

        // Setup date pickers
        setupDatePicker(editDepartureDate);
        setupDatePicker(editReturnDate);

        // Add TextWatchers to validate and populate days when dates are entered
        setupDateValidation();

        // Create trip button
        buttonCreateTrip.setOnClickListener(v -> handleCreateTrip());
    }

    private void setupDatePicker(EditText editText) {
        editText.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddTrip.this,
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
            LinearLayout dayLayout = new LinearLayout(this);
            dayLayout.setOrientation(LinearLayout.VERTICAL);
            dayLayout.setPadding(16, 16, 16, 16);
            dayLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            TextView dayLabel = new TextView(this);
            dayLabel.setText(getString(R.string.day) + " " + i);
            dayLabel.setPadding(8, 8, 8, 8);
            dayLabel.setTextColor(getResources().getColor(R.color.black));
            dayLabel.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));

            EditText dayInput = new EditText(this);
            dayInput.setPadding(8, 8, 8, 8);
            dayInput.setTextColor(getResources().getColor(R.color.black));
            dayInput.setBackgroundResource(R.drawable.button_background);
            dayInput.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));

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
            Toast.makeText(this, R.string.trip_error, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!validateDayInputs()) {
            return;
        }

        TripDatabaseHelper dbHelper = new TripDatabaseHelper(this);
        long tripId = dbHelper.addTrip(country, departureDate, returnDate);

        saveManualItinerary(tripId);

        Toast.makeText(this, R.string.trip_successful, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
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
        TripDatabaseHelper dbHelper = new TripDatabaseHelper(this);

        for (int i = 0; i < gridDays.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) gridDays.getChildAt(i);
            TextView dayLabel = (TextView) row.getChildAt(0);
            EditText dayInput = (EditText) row.getChildAt(1);

            dbHelper.addDayTrip(tripId, dayLabel.getText().toString(), dayInput.getText().toString());
        }
    }
}