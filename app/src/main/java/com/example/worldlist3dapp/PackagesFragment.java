package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
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

public class PackagesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_packages, container, false);

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