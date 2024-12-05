package com.example.worldlist3dapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class ViewEditTripActivity extends AppCompatActivity {

    private EditText editDepartureDate, editReturnDate;
    private TextView tripCountry;
    private Button saveButton;
    private ImageView editIcon;
    private LinearLayout itineraryContainer;

    private TripDatabaseHelper dbHelper;
    private long tripId;

    private boolean isEditable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_trip);

        tripCountry = findViewById(R.id.view_trip_country);
        editDepartureDate = findViewById(R.id.edit_trip_departure_date);
        editReturnDate = findViewById(R.id.edit_trip_return_date);
        itineraryContainer = findViewById(R.id.itinerary_container);
        saveButton = findViewById(R.id.save_trip_button);
        editIcon = findViewById(R.id.edit_icon);

        dbHelper = new TripDatabaseHelper(this);

        tripId = getIntent().getLongExtra("trip_id", -1);
        if (tripId != -1) {
            loadTripDetails(tripId);
        }

        setEditable(false);

        editIcon.setOnClickListener(v -> {
            isEditable = !isEditable;
            setEditable(isEditable);
        });

        saveButton.setOnClickListener(v -> {
            if (saveTripDetails() && saveItineraryDetails()) {
                Toast.makeText(this, R.string.trip_successful, Toast.LENGTH_SHORT).show();
                setEditable(false);
            } else {
                Toast.makeText(this, R.string.trip_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTripDetails(long tripId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor tripCursor = db.rawQuery("SELECT * FROM trips WHERE id = ?", new String[]{String.valueOf(tripId)});
        Cursor daysCursor = db.rawQuery("SELECT * FROM day_trips WHERE trip_id = ?", new String[]{String.valueOf(tripId)});

        if (tripCursor.moveToFirst()) {
            String country = tripCursor.getString(tripCursor.getColumnIndexOrThrow("country"));
            String departureDate = tripCursor.getString(tripCursor.getColumnIndexOrThrow("departure_date"));
            String returnDate = tripCursor.getString(tripCursor.getColumnIndexOrThrow("return_date"));

            tripCountry.setText(country);
            editDepartureDate.setText(departureDate);
            editReturnDate.setText(returnDate);
        }
        tripCursor.close();

        itineraryContainer.removeAllViews();
        while (daysCursor.moveToNext()) {
            String dayLabel = daysCursor.getString(daysCursor.getColumnIndexOrThrow("day"));
            String details = daysCursor.getString(daysCursor.getColumnIndexOrThrow("details"));

            addDayItineraryView(dayLabel, details, false);
        }
        daysCursor.close();
    }

    private void addDayItineraryView(String dayLabel, String content, boolean editable) {
        LinearLayout dayLayout = new LinearLayout(this);
        dayLayout.setOrientation(LinearLayout.VERTICAL);
        dayLayout.setPadding(16, 16, 16, 16);
        dayLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView dayLabelView = new TextView(this);
        dayLabelView.setText(dayLabel);
        dayLabelView.setTextSize(16);
        dayLabelView.setTextColor(getResources().getColor(R.color.black));
        dayLabelView.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));

        View inputView;
        if (editable) {
            EditText dayInput = new EditText(this);
            dayInput.setText(content);
            dayInput.setTextSize(16);
            dayInput.setTextColor(getResources().getColor(R.color.black));
            dayInput.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
            inputView = dayInput;
        } else {
            TextView dayContent = new TextView(this);
            dayContent.setText(content);
            dayContent.setTextSize(16);
            dayContent.setTextColor(getResources().getColor(R.color.black));
            dayContent.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
            inputView = dayContent;
        }

        dayLayout.addView(dayLabelView);
        dayLayout.addView(inputView);
        itineraryContainer.addView(dayLayout);
    }

    private void setEditable(boolean editable) {
        editDepartureDate.setEnabled(editable);
        editReturnDate.setEnabled(editable);
        saveButton.setVisibility(editable ? View.VISIBLE : View.GONE);

        for (int i = 0; i < itineraryContainer.getChildCount(); i++) {
            LinearLayout dayLayout = (LinearLayout) itineraryContainer.getChildAt(i);
            TextView dayLabel = (TextView) dayLayout.getChildAt(0);
            dayLabel.setTextSize(16);
            dayLabel.setTextColor(getResources().getColor(R.color.black));
            dayLabel.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
            View inputView = dayLayout.getChildAt(1);

            if (editable && inputView instanceof TextView) {
                String content = ((TextView) inputView).getText().toString();
                dayLayout.removeView(inputView);

                EditText dayInput = new EditText(this);
                dayInput.setText(content);
                dayInput.setTextSize(16);
                dayInput.setTextColor(getResources().getColor(R.color.black));
                dayInput.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
                dayLayout.addView(dayInput);
            } else if (!editable && inputView instanceof EditText) {
                String content = ((EditText) inputView).getText().toString();
                dayLayout.removeView(inputView);

                TextView dayContent = new TextView(this);
                dayContent.setText(content);
                dayContent.setTextSize(16);
                dayContent.setTextColor(getResources().getColor(R.color.black));
                dayContent.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
                dayLayout.addView(dayContent);

            }
        }
    }

    private boolean saveTripDetails() {
        String updatedDepartureDate = editDepartureDate.getText().toString();
        String updatedReturnDate = editReturnDate.getText().toString();

        if (!updatedDepartureDate.isEmpty() && !updatedReturnDate.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("departure_date", updatedDepartureDate);
            values.put("return_date", updatedReturnDate);

            db.update("trips", values, "id = ?", new String[]{String.valueOf(tripId)});
            db.close();
            return true;
        }
        return false;
    }

    private boolean saveItineraryDetails() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("day_trips", "trip_id = ?", new String[]{String.valueOf(tripId)});

        for (int i = 0; i < itineraryContainer.getChildCount(); i++) {
            LinearLayout dayLayout = (LinearLayout) itineraryContainer.getChildAt(i);
            TextView dayLabel = (TextView) dayLayout.getChildAt(0);
            EditText dayInput = (EditText) dayLayout.getChildAt(1);

            ContentValues values = new ContentValues();
            values.put("trip_id", tripId);
            values.put("day", dayLabel.getText().toString());
            values.put("details", dayInput.getText().toString());

            db.insert("day_trips", null, values);
        }
        db.close();
        return true;
    }
}