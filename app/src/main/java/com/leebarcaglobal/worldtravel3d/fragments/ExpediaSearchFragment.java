package com.leebarcaglobal.worldtravel3d.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.fragments.subFragments.ActivitiesFragment;
import com.leebarcaglobal.worldtravel3d.fragments.subFragments.FlightFragment;
import com.leebarcaglobal.worldtravel3d.fragments.subFragments.HotelsFragment;


public class ExpediaSearchFragment extends Fragment {


    private View flightsButton;
    private View hotelsButton;
    private View activitiesButton;
    private ImageButton back_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.country_details_planning_frg,container,false);

        // Initialize button references
        flightsButton = frgView.findViewById(R.id.flights_element);
        hotelsButton = frgView.findViewById(R.id.hotels_element);
        activitiesButton = frgView.findViewById(R.id.activities_element);
        back_button = frgView.findViewById(R.id.back_button);

        flightsButton.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.new_button_bg));
        hotelsButton.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.new_button_bg));
        activitiesButton.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.new_button_bg));

        highlightActiveButton(flightsButton);

        // Load the Flights fragment by default
        replaceFragment(new FlightFragment(), flightsButton);

        // Set up button click listeners for GridLayout buttons
        frgView.findViewById(R.id.flights_element).setOnClickListener(v -> replaceFragment(new FlightFragment(), flightsButton));
        frgView.findViewById(R.id.hotels_element).setOnClickListener(v -> replaceFragment(new HotelsFragment(), hotelsButton));
        frgView.findViewById(R.id.activities_element).setOnClickListener(v -> replaceFragment(new ActivitiesFragment(), activitiesButton));

        back_button.setOnClickListener(v -> {
            // Handle click event
            requireActivity().onBackPressed();
        });

        return frgView;
    }


    private void replaceFragment(Fragment fragment, View activeButton) {

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,
                fragment);
        transaction.commit();

        // Highlight the active button and reset others
        resetButtonColors();
        highlightActiveButton(activeButton);
    }

    private void resetButtonColors() {
        // Reset all buttons to default background color
        flightsButton.setSelected(false);
        hotelsButton.setSelected(false);
        activitiesButton.setSelected(false);
    }

    private void highlightActiveButton(View button) {
        // Set the active button to a dark gray background
        resetButtonColors(); // Ensure only one button is highlighted
        button.setSelected(true);
    }
}
