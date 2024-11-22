package com.example.worldlist3dapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AttractionsPagerAdapter extends RecyclerView.Adapter<AttractionsPagerAdapter.ViewHolder> {

    private final String[] names;
    private final String[] details;
    private final int[] images;

    public AttractionsPagerAdapter(String[] names, String[] details, int[] images) {
        this.names = names;
        this.details = details;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attraction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.attractionName.setText(names[position]);
        holder.attractionDetails.setText(details[position]);
        holder.attractionImage.setImageResource(images[position]);

        // Control the visibility of swipe hints
        if (position == 0) {
            holder.swipeHintLeft.setVisibility(View.GONE);  // No hint at the top on the first page
            holder.swipeHintRight.setVisibility(View.VISIBLE); // Show hint at the bottom to swipe up
        } else if (position == names.length - 1) {
            holder.swipeHintLeft.setVisibility(View.VISIBLE);  // Show hint to swipe down
            holder.swipeHintRight.setVisibility(View.GONE);  // No hint at the bottom on the last page
        } else {
            holder.swipeHintLeft.setVisibility(View.VISIBLE);  // Show hint to swipe down
            holder.swipeHintRight.setVisibility(View.VISIBLE); // Show hint to swipe up
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView attractionName, attractionDetails;
        ImageView attractionImage, swipeHintLeft, swipeHintRight;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            attractionName = itemView.findViewById(R.id.attraction_name);
            attractionDetails = itemView.findViewById(R.id.attraction_details);
            attractionImage = itemView.findViewById(R.id.attraction_image);
            swipeHintLeft = itemView.findViewById(R.id.swipe_hint_left);
            swipeHintRight = itemView.findViewById(R.id.swipe_hint_right);
        }
    }
}