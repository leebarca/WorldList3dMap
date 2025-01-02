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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

        EditText destination_string = view.findViewById(R.id.destination);
        Button button_search = view.findViewById(R.id.button_search);

        // Search button with validation
        button_search.setOnClickListener(v -> {
            try {
                if (validateFields(destination_string)) {
                    String destination = destination_string.getText().toString().trim();

                    Random random = new Random();
                    int session = random.nextInt(1000000);

                    String url = constructUrl(destination, session);
                    openUrl(url);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        return view;
    }

    private boolean validateFields(EditText destinationField) throws ParseException {
        boolean isValid = true;

        if (destinationField.getText().toString().trim().isEmpty()) {
            destinationField.setError(getString(R.string.please_enter_the_destination));
            isValid = false;
        }

        return isValid;
    }

    private String constructUrl(String destination, int session) {
        return
                "https://www.expedia.com/things-to-do/search" +
                        "?location=" + destination +
                        "&sort=RECOMMENDED&swp=on&session=" + session;
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}