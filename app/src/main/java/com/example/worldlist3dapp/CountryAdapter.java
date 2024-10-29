package com.example.worldlist3dapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<CountryInfo> {
    public CountryAdapter(Context context, List<CountryInfo> countries) {
        super(context, 0, countries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_list_item, parent, false);
        }

        CountryInfo country = getItem(position);

        ImageView flagImage = convertView.findViewById(R.id.flag_image);
        TextView countryName = convertView.findViewById(R.id.country_name);

        // Set flag image and country name
        flagImage.setImageResource(country.getFlagResId());
        countryName.setText(country.getName());

        return convertView;
    }
}
