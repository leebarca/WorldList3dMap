package com.leebarcaglobal.worldtravel3d.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.adapters.AttractionsPagerAdapter;


public class CountryDetailsAttractionsFragment extends Fragment {

    private ViewPager2 attractionsViewPager;
    private LinearLayout dotsLayout;
    private ImageButton backButton;
    private TextView toolbar_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.country_details_attractions_frg, container, false);

        attractionsViewPager = frgView.findViewById(R.id.attractions_view_pager);
        backButton = frgView.findViewById(R.id.back_button);
        dotsLayout = frgView.findViewById(R.id.dots_layout);
        toolbar_title = frgView.findViewById(R.id.toolbar_title);

        // Retrieve attraction data from the arguments
        String[] attractionNames = {
                getArguments().getString("firstAttractionName"),
                getArguments().getString("secondAttractionName"),
                getArguments().getString("thirdAttractionName")
        };
        String[] attractionDetails = {
                getArguments().getString("firstAttractionDetails"),
                getArguments().getString("secondAttractionDetails"),
                getArguments().getString("thirdAttractionDetails")
        };
        int[] attractionImages = {
                getArguments().getInt("firstAttractionImage", 0),
                getArguments().getInt("secondAttractionImage", 0),
                getArguments().getInt("thirdAttractionImage", 0)
        };

        String country_name = getArguments().getString("country_name");
        toolbar_title.setText(country_name);

        // Set up the adapter with a back button listener
        AttractionsPagerAdapter adapter = new AttractionsPagerAdapter(
                attractionNames,
                attractionDetails,
                attractionImages,
                () -> requireActivity().onBackPressed()
        );

        attractionsViewPager.setAdapter(adapter);
        attractionsViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        // Set up dots indicator
        setupDotsIndicator(attractionNames.length);
        attractionsViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateDotsIndicator(position);
            }
        });

        // Handle back button click
        backButton.setOnClickListener(v -> requireActivity().onBackPressed());

        return frgView;
    }

    private void setupDotsIndicator(int dotsCount) {
        dotsLayout.removeAllViews(); // Clear existing dots
        ImageView[] dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(requireContext());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.dot_inactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            dotsLayout.addView(dots[i], params);
        }

        // Set the first dot as active initially
        if (dots.length > 0) {
            dots[0].setImageDrawable(getResources().getDrawable(R.drawable.dot_active));
        }
    }

    private void updateDotsIndicator(int position) {
        int dotsCount = dotsLayout.getChildCount();
        for (int i = 0; i < dotsCount; i++) {
            ImageView dot = (ImageView) dotsLayout.getChildAt(i);
            if (i == position) {
                dot.setImageDrawable(getResources().getDrawable(R.drawable.dot_active));
            } else {
                dot.setImageDrawable(getResources().getDrawable(R.drawable.dot_inactive));
            }
        }
    }
}

//package com.example.newworldlist3dapp.fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.example.newworldlist3dapp.R;
//import com.example.newworldlist3dapp.adapters.AttractionsPagerAdapter;
//
//public class CountryDetailsAttractionsFragment extends Fragment {
//
//    private ViewPager2 attractionsViewPager;
//    private LinearLayout dotsLayout;
//    ImageButton back_button;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View frgView = inflater.inflate(R.layout.country_details_attractions_frg,container,false);
//
//
//        attractionsViewPager = frgView.findViewById(R.id.attractions_view_pager);
//        back_button = frgView.findViewById(R.id.back_button);
//        dotsLayout = frgView.findViewById(R.id.dotsLayout);
//
//        // Retrieve attraction data from the Intent
//        String[] attractionNames = {
//                getArguments().getString("firstAttractionName"),
//                getArguments().getString("secondAttractionName"),
//                getArguments().getString("thirdAttractionName")
//        };
//        String[] attractionDetails = {
//                getArguments().getString("firstAttractionDetails"),
//                getArguments().getString("secondAttractionDetails"),
//                getArguments().getString("thirdAttractionDetails")
//        };
//        int[] attractionImages = {
//                getArguments().getInt("firstAttractionImage", 0),
//                getArguments().getInt("secondAttractionImage", 0),
//                getArguments().getInt("thirdAttractionImage", 0)
//        };
//
//        // Set up the adapter with a back button listener
//        AttractionsPagerAdapter adapter = new AttractionsPagerAdapter(
//                attractionNames,
//                attractionDetails,
//                attractionImages,
//                () -> requireActivity().onBackPressed()
//        );
//
//        attractionsViewPager.setAdapter(adapter);
//        attractionsViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
//
//        back_button.setOnClickListener(v -> {
//            // Handle click event
//            requireActivity().onBackPressed();
//        });
//
//
//        return frgView;
//    }
//}
