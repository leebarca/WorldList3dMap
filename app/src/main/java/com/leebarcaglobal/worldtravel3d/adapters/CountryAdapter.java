package com.leebarcaglobal.worldtravel3d.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.models.CountryInfo;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<CountryInfo> {

    private List<CountryInfo> countries;

    public CountryAdapter(Context context, List<CountryInfo> countries) {
        super(context, 0, countries);
        this.countries = new ArrayList<>(countries); // Initialize with a copy of the list
    }

    @Override
    public int getCount() {
        return countries.size(); // Ensure ListView updates with the correct item count
    }

    @Override
    public CountryInfo getItem(int position) {
        return countries.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_list_item, parent, false);
        }

        CountryInfo country = getItem(position);

        ImageView flagImage = convertView.findViewById(R.id.flag_image);
        TextView countryName = convertView.findViewById(R.id.country_name);

        if (country != null) {
            flagImage.setImageResource(country.getFlagResId());
            countryName.setText(country.getName());
        }

        return convertView;
    }

    public void updateCountries(List<CountryInfo> newCountries) {
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged(); // Notify the adapter that data has changed
    }
}
