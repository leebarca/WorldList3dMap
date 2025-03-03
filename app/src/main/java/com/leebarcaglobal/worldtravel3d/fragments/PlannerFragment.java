package com.leebarcaglobal.worldtravel3d.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.adapters.TripAdapter;
import com.leebarcaglobal.worldtravel3d.customViews.NonScrollListView;
import com.leebarcaglobal.worldtravel3d.utils.FragmentTags;
import com.leebarcaglobal.worldtravel3d.utils.TripDatabaseHelper;

import java.util.List;

public class PlannerFragment extends Fragment {

    private NonScrollListView tripListView;
    private Button addTripButton, expediaSearchButton;
    private TextView empty_trip_text;
    private LinearLayout iconsBottom, home_layout_button, explore_layout_button, planner_layout_button, language_layout_button;

    private TripAdapter tripAdapter;
    private TripDatabaseHelper dbHelper;


    private static final int ADD_TRIP_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.planner_frg,container,false);

        tripListView = frgView.findViewById(R.id.trip_list_view);
        addTripButton = frgView.findViewById(R.id.add_trip_button);
        expediaSearchButton = frgView.findViewById(R.id.expedia_search);
        empty_trip_text = frgView.findViewById(R.id.empty_trip_text);


        dbHelper = new TripDatabaseHelper(requireActivity());

        loadTrips();

        // Add Trip button
        addTripButton.setOnClickListener(v -> {
//            Intent intent = new Intent(TripPlannerActivity.this, AddTrip.class);
//            startActivityForResult(intent, ADD_TRIP_REQUEST_CODE);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, new AddTripFragment(), FragmentTags.ADD_TRIP_FRAGMENT_TAG)
                    .addToBackStack(FragmentTags.ADD_TRIP_FRAGMENT_TAG)
                    .commit();

        });

        expediaSearchButton.setOnClickListener(v -> {
//            Intent intent = new Intent(TripPlannerActivity.this, ExpediaSearch.class);
//            startActivity(intent);



            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ExpediaSearchFragment(), FragmentTags.EXPEDIA_SEARCH_FRAGMENT_TAG)
                    .addToBackStack(FragmentTags.EXPEDIA_SEARCH_FRAGMENT_TAG)
                    .commit();
        });

        tripListView.setOnItemClickListener((parent, view, position, id) -> {
//            Intent intent = new Intent(TripPlannerActivity.this, ViewEditTripActivity.class);
//            long tripId = dbHelper.getAllTripIds().get(position);
//            intent.putExtra("trip_id", tripId);
//            startActivity(intent);

            ViewEditTripFragment viewEditTripFragment = new ViewEditTripFragment();
            Bundle args = new Bundle();

            long tripId = dbHelper.getAllTripIds().get(position);
            args.putLong("trip_id", tripId);

            viewEditTripFragment.setArguments(args);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, viewEditTripFragment, FragmentTags.VIEW_EDIT_TRIP_FRAGMENT_TAG)
                    .addToBackStack(FragmentTags.VIEW_EDIT_TRIP_FRAGMENT_TAG)
                    .commit();



        });


        return frgView;
    }

    private void loadTrips() {
        List<Long> tripIds = dbHelper.getAllTripIds();
        List<String> tripDetails = dbHelper.getAllTrips();

        if (tripDetails.isEmpty()) {
            empty_trip_text.setVisibility(View.VISIBLE);
            tripListView.setVisibility(View.GONE);
        } else {
            empty_trip_text.setVisibility(View.GONE);
            tripListView.setVisibility(View.VISIBLE);
            tripAdapter = new TripAdapter(requireActivity(), tripIds, tripDetails, this::loadTrips,PlannerFragment.this);
            tripListView.setAdapter(tripAdapter);
        }
    }

    public void callViewEditTripFragment(long tripId) {
        ViewEditTripFragment viewEditTripFragment = new ViewEditTripFragment();
        Bundle args = new Bundle();

        args.putLong("trip_id", tripId);

        viewEditTripFragment.setArguments(args);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, viewEditTripFragment, FragmentTags.VIEW_EDIT_TRIP_FRAGMENT_TAG)
                .addToBackStack(FragmentTags.VIEW_EDIT_TRIP_FRAGMENT_TAG)
                .commit();

    }
}

