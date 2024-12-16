package com.leebarcaglobal.worldtravel3d;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ActivitiesFragment extends Fragment {

    private long departureDateInMillis = -1; // Track the selected departure date

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

        EditText destination_string = view.findViewById(R.id.destination);
        EditText departure_date = view.findViewById(R.id.edit_departure_date);
        EditText return_date = view.findViewById(R.id.edit_return_date);
        Button button_search = view.findViewById(R.id.button_search);

        // Departure Date Picker
        departure_date.setOnClickListener(v -> {
            showDatePicker(departure_date, Calendar.getInstance().getTimeInMillis(),
                           (selectedDateMillis) -> {
                               departureDateInMillis = selectedDateMillis; // Store selected departure date
                           });
        });

        // Return Date Picker
        return_date.setOnClickListener(v -> {
            long minDate = departureDateInMillis > 0 ? departureDateInMillis + (24 * 60 * 60 * 1000) : Calendar.getInstance().getTimeInMillis();
            showDatePicker(return_date, minDate, null);
        });

        // Search button with validation
        button_search.setOnClickListener(v -> {
            try {
                if (validateFields(destination_string, departure_date, return_date)) {
                    String destination = destination_string.getText().toString().trim();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String departureDate = departure_date.getText().toString().trim();
                    String returnDate = return_date.getText().toString().trim();
                    try {
                        // Parse and format the departure date
                        Date parsedDepartureDate = inputFormat.parse(departureDate);
                        departureDate = outputFormat.format(parsedDepartureDate);

                        // Parse and format the return date
                        Date parsedReturnDate = inputFormat.parse(returnDate);
                        returnDate = outputFormat.format(parsedReturnDate);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Random random = new Random();
                    int session = random.nextInt(1000000);

                    String url = constructUrl(destination, departureDate, returnDate, session);
                    openUrl(url);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        Bundle args = getArguments();
        if (args != null) {
            String country = args.getString("country");
            destination_string.setText(country);
        }

        return view;
    }

    private void showDatePicker(EditText dateField, long minDate, DateSelectionCallback callback) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                                                                 (view, selectedYear, selectedMonth, selectedDay) -> {
                                                                     String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                                                                     dateField.setText(formattedDate);

                                                                     // Pass the selected date back via callback
                                                                     if (callback != null) {
                                                                         Calendar selectedDate = Calendar.getInstance();
                                                                         selectedDate.set(selectedYear, selectedMonth, selectedDay);
                                                                         callback.onDateSelected(selectedDate.getTimeInMillis());
                                                                     }
                                                                 },
                                                                 year, month, day);

        // Disable dates before the minimum date
        datePickerDialog.getDatePicker().setMinDate(minDate);
        datePickerDialog.show();
    }

    private boolean validateFields(EditText destinationField, EditText departureDateField, EditText returnDateField) throws ParseException {
        boolean isValid = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        if (destinationField.getText().toString().trim().isEmpty()) {
            destinationField.setError(getString(R.string.please_enter_the_destination));
            isValid = false;
        }

        if (departureDateField.getText().toString().trim().isEmpty()) {
            departureDateField.setError(getString(R.string.please_select_a_departure_date));
            isValid = false;
        }

        if (returnDateField.getText().toString().trim().isEmpty()) {
            returnDateField.setError(getString(R.string.please_select_a_return_date));
            isValid = false;
        }
        else if (!departureDateField.getText().toString().trim().isEmpty()) {
            Date departureDate = dateFormat.parse(departureDateField.getText().toString());
            Date returnDate = dateFormat.parse(returnDateField.getText().toString());

            if (returnDate.before(departureDate)) {
                returnDateField.setError(getString(R.string.please_select_a_departure_date));
                isValid = false;
            }
        }

        return isValid;
    }

    private String constructUrl(String destination, String departureDate, String returnDate, int session) {
        return
                "https://www.expedia.com/things-to-do/search" +
                        "?location=" + destination +
                        "&d1=" + departureDate +
                        "&d2=" + returnDate +
                        "&sort=RECOMMENDED&swp=on&session=" + session;
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // Callback interface for date selection
    interface DateSelectionCallback {
        void onDateSelected(long selectedDateInMillis);
    }
}