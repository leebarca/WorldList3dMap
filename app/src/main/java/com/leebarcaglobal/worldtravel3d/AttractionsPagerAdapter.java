package com.leebarcaglobal.worldtravel3d;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AttractionsPagerAdapter extends RecyclerView.Adapter<AttractionsPagerAdapter.ViewHolder> {

    private final String[] names;
    private final String[] details;
    private final int[] images;
    private final OnBackButtonClickListener backButtonClickListener;

    // Constructor with back button click listener
    public AttractionsPagerAdapter(String[] names, String[] details, int[] images, OnBackButtonClickListener listener) {
        this.names = names;
        this.details = details;
        this.images = images;
        this.backButtonClickListener = listener;
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

        // Set back button click listener
        holder.backButton.setOnClickListener(v -> {
            if (backButtonClickListener != null) {
                backButtonClickListener.onBackButtonClicked();
            }
        });

        // Control the visibility of swipe hints
        if (position == 0) {
            holder.swipeHintLeft.setVisibility(View.GONE);
            holder.swipeHintRight.setVisibility(View.VISIBLE);
        } else if (position == names.length - 1) {
            holder.swipeHintLeft.setVisibility(View.VISIBLE);
            holder.swipeHintRight.setVisibility(View.GONE);
        } else {
            holder.swipeHintLeft.setVisibility(View.VISIBLE);
            holder.swipeHintRight.setVisibility(View.VISIBLE);
        }

        ScrollView scrollView = holder.itemView.findViewById(R.id.scroll_view);
        if (scrollView != null) {
            scrollView.post(() -> scrollView.scrollTo(0, 0)); // Scroll to the top
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    // ViewHolder with back button
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView attractionName, attractionDetails;
        ImageView attractionImage, swipeHintLeft, swipeHintRight;
        ImageButton backButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            attractionName = itemView.findViewById(R.id.attraction_name);
            attractionDetails = itemView.findViewById(R.id.attraction_details);
            attractionImage = itemView.findViewById(R.id.attraction_image);
            swipeHintLeft = itemView.findViewById(R.id.swipe_hint_left);
            swipeHintRight = itemView.findViewById(R.id.swipe_hint_right);
            backButton = itemView.findViewById(R.id.back_button); // Reference to the back button
        }
    }

    // Interface for back button click
    public interface OnBackButtonClickListener {
        void onBackButtonClicked();
    }
}