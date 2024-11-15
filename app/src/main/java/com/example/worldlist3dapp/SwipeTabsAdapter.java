package com.example.worldlist3dapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SwipeTabsAdapter extends FragmentStateAdapter {

    public SwipeTabsAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FlightsFragment();
        }
        else if (position == 1) {
            return new AccomodationFragment();
        }
        else if (position == 2) {
            return new PackagesFragment();
        }
        else if (position == 3) {
            return new ActivitiesFragment();
        }
        else throw new IllegalStateException("Invalid position: " + position);
    }

    @Override
    public int getItemCount() {
        return 4; // Number of tabs
    }
}