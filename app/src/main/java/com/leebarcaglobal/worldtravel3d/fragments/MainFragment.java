package com.leebarcaglobal.worldtravel3d.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leebarcaglobal.worldtravel3d.MainActivity;
import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.utils.UtilClass;

import java.util.Objects;

public class MainFragment extends Fragment {


    BottomNavigationView bottomNavigationView;


    public void showRemoveAdsDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.remove_ads_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnBuyNow = dialog.findViewById(R.id.btnBuyNow);


        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnBuyNow.setOnClickListener(v -> {


            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SettingsFragment())
                    .commit();

            bottomNavigationView.setSelectedItemId(R.id.nav_setting);

            dialog.dismiss();
        });



        dialog.show();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frgView = inflater.inflate(R.layout.main_frg,container,false);

        bottomNavigationView = frgView.findViewById(R.id.bottom_navigation);


        if (!UtilClass.isAdRemovalPurchased(requireActivity())) {
            //not Purchased Show Ads
            showRemoveAdsDialog(requireActivity());
        }

        if (savedInstanceState == null) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();

            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }


        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if(item.getItemId()==R.id.nav_home){
                selectedFragment = new HomeFragment();
            }else if(item.getItemId()==R.id.nav_explore){
                selectedFragment = new ExploreFragment();
            }else if(item.getItemId()==R.id.nav_planner){
                selectedFragment = new PlannerFragment();
            }else if(item.getItemId()==R.id.nav_language){
                selectedFragment = new LanguageFragment();
            }else if(item.getItemId()==R.id.nav_setting){
                selectedFragment = new SettingsFragment();
            }


            if (selectedFragment != null) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .addToBackStack(null)
                        .commit();


            }

            return true;
        });


        return frgView;
    }


    public void updateBottomNavigationView(int menuItemId) {
        bottomNavigationView.setSelectedItemId(menuItemId);
    }


}
