package com.example.worldlist3dapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FlightFragment extends Fragment {

    private int adultsCount = 1;
    private int childrenCount = 0;
    private long departureDateInMillis = -1; // Track the selected departure date

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight, container, false);

        EditText departure_string = view.findViewById(R.id.origin);
        EditText destination_string = view.findViewById(R.id.destination);
        EditText departure_date = view.findViewById(R.id.edit_departure_date);
        EditText return_date = view.findViewById(R.id.edit_return_date);
        Button button_adults_minus = view.findViewById(R.id.button_adults_minus);
        Button button_adults_plus = view.findViewById(R.id.button_adults_plus);
        TextView text_adults_count = view.findViewById(R.id.text_adults_count);
        Button button_children_minus = view.findViewById(R.id.button_children_minus);
        Button button_children_plus = view.findViewById(R.id.button_children_plus);
        TextView text_children_count = view.findViewById(R.id.text_children_count);
        Button button_search = view.findViewById(R.id.button_search);

        text_adults_count.setText(String.valueOf(adultsCount));
        text_children_count.setText(String.valueOf(childrenCount));

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

        // Adults and children count handlers
        button_adults_plus.setOnClickListener(v -> {
            if (adultsCount < 10) {
                adultsCount++;
                text_adults_count.setText(String.valueOf(adultsCount));
            }
        });

        button_adults_minus.setOnClickListener(v -> {
            if (adultsCount > 1) {
                adultsCount--;
                text_adults_count.setText(String.valueOf(adultsCount));
            }
        });

        button_children_plus.setOnClickListener(v -> {
            if (childrenCount < 10) {
                childrenCount++;
                text_children_count.setText(String.valueOf(childrenCount));
            }
        });

        button_children_minus.setOnClickListener(v -> {
            if (childrenCount > 0) {
                childrenCount--;
                text_children_count.setText(String.valueOf(childrenCount));
            }
        });

        // Search button with validation
        button_search.setOnClickListener(v -> {
            try {
                if (validateFields(departure_string, destination_string, departure_date, return_date)) {
                    String departure = departure_string.getText().toString().trim();
                    String destination = destination_string.getText().toString().trim();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
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
                    int adults = Integer.parseInt(text_adults_count.getText().toString());
                    int children = Integer.parseInt(text_children_count.getText().toString());

                    String url = constructUrl(departure, destination, departureDate, returnDate, adults, children);
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

    private boolean validateFields(EditText departureField, EditText destinationField, EditText departureDateField, EditText returnDateField) throws ParseException {
        boolean isValid = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        if (departureField.getText().toString().trim().isEmpty()) {
            departureField.setError("Please enter the origin.");
            isValid = false;
        }

        if (destinationField.getText().toString().trim().isEmpty()) {
            destinationField.setError("Please enter the destination.");
            isValid = false;
        }

        if (departureDateField.getText().toString().trim().isEmpty()) {
            departureDateField.setError("Please select a departure date.");
            isValid = false;
        }

        if (returnDateField.getText().toString().trim().isEmpty()) {
            returnDateField.setError("Please select a return date.");
            isValid = false;
        }
        else if (!departureDateField.getText().toString().trim().isEmpty()) {
            Date departureDate = dateFormat.parse(departureDateField.getText().toString());
            Date returnDate = dateFormat.parse(returnDateField.getText().toString());

            if (returnDate.before(departureDate)) {
                returnDateField.setError("Return date must be after departure date.");
                isValid = false;
            }
        }

        return isValid;
    }

    private String constructUrl(String origin, String destination, String departureDate, String returnDate, int adults, int children) {
        return
                "https://www.expedia.com/Flights-Search?flight-type=on&mode=search&trip=roundtrip" +
                "&leg1=from:" + origin +
                ",to:" + destination +
                ",departure:" + departureDate +
                "TANYT,fromType:U,toType:U&leg2=from:" + destination +
                ",to:" + origin +
                ",departure:" + returnDate +
                "TANYT,fromType:U,toType:U&options=cabinclass:economy&passengers=adults:" + adults +
                ",children:" + children +
                ",infantinlap:N";
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