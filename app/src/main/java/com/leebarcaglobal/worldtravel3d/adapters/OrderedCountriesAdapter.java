package com.leebarcaglobal.worldtravel3d.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.leebarcaglobal.worldtravel3d.R;
import com.leebarcaglobal.worldtravel3d.models.CountryInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderedCountriesAdapter extends ArrayAdapter<CountryInfo> {

    private final List<CountryInfo> countries;

    public OrderedCountriesAdapter(@NonNull Context context, @NonNull List<CountryInfo> countries) {
        super(context, 0, countries);
        this.countries = new ArrayList<>(countries);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Nullable
    @Override
    public CountryInfo getItem(int position) {
        return countries.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_ordered_country_list_adapter, parent, false);
        }

        CountryInfo country = getItem(position);

        TextView orderNumber = convertView.findViewById(R.id.order_number);
        ImageView countryFlag = convertView.findViewById(R.id.country_flag);
        TextView countryName = convertView.findViewById(R.id.country_name);

        if (country != null) {
            // Set order number
            orderNumber.setText(String.valueOf(position + 1));

            // Set country flag and name
            countryFlag.setImageResource(country.getFlagResId());
            countryName.setText(country.getName());
        }

        return convertView;
    }

    public void updateCountries(List<CountryInfo> newCountries) {
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();
    }
}