package com.example.worldlist3dapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DayTripAdapter extends BaseAdapter {

    private Context context;
    private List<String> days;
    private List<String> descriptions;

    public DayTripAdapter(Context context, List<String> days, List<String> descriptions) {
        this.context = context;
        this.days = days;
        this.descriptions = descriptions;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.last_item_day_trip, parent, false);
        }

        TextView dayLabel = convertView.findViewById(R.id.day_label);
        TextView dayDescription = convertView.findViewById(R.id.day_description);

        dayLabel.setText(days.get(position));
        dayDescription.setText(descriptions.get(position));

        return convertView;
    }
}