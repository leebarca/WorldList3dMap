package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ActivitiesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

        // Retrieve the arguments passed to the fragment
        Bundle args = getArguments();
        if (args != null) {
            String country = args.getString("country");

            // Use the country value (e.g., set it as default text)
            @SuppressLint({"MissingInflatedId",
                    "LocalSuppress"}) EditText destinationEditText = view.findViewById(R.id.destination);
            destinationEditText.setText(country);
        }

        return view;
    }
}