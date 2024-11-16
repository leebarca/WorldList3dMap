package com.example.worldlist3dapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Locale;

public class ActivitiesFragment extends Fragment {

    private int adultsCount = 1;
    private int childrenCount = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

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

        departure_date.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (dialogView, selectedYear, selectedMonth, selectedDay) -> {
                // Format date as DD/MM/YYYY
                String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                departure_date.setText(formattedDate);
            }, year, month, day);

            datePickerDialog.show();
        });

        return_date.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (dialogView, selectedYear, selectedMonth, selectedDay) -> {
                // Format date as DD/MM/YYYY
                String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                return_date.setText(formattedDate);
            }, year, month, day);

            datePickerDialog.show();
        });

        button_adults_plus.setOnClickListener(v -> {
            if (adultsCount < 10) { // Max limit of 10
                adultsCount++;
                text_adults_count.setText(String.valueOf(adultsCount));
            }
        });

        // Handle "-" button click
        button_adults_minus.setOnClickListener(v -> {
            if (adultsCount > 1) { // Ensure minimum count stays at 1 or above
                adultsCount--;
                text_adults_count.setText(String.valueOf(adultsCount));
            }
        });

        button_children_plus.setOnClickListener(v -> {
            if (childrenCount < 10) { // Max limit of 10
                childrenCount++;
                text_children_count.setText(String.valueOf(childrenCount));
            }
        });

        // Handle "-" button click
        button_children_minus.setOnClickListener(v -> {
            if (childrenCount > 1) { // Ensure minimum count stays at 1 or above
                childrenCount--;
                text_children_count.setText(String.valueOf(childrenCount));
            }
        });

        // Retrieve the arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            String country = args.getString("country");

            // Use the country value (e.g., set it as default text)
            destination_string.setText(country);
        }

        return view;
    }
}