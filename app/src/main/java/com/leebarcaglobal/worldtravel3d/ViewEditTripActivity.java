package com.leebarcaglobal.worldtravel3d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.core.content.res.ResourcesCompat;
import android.app.DatePickerDialog;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ViewEditTripActivity extends BaseActivity {

    private EditText editDepartureDate, editReturnDate;
    private TextView tripCountry;
    private Button saveButton;
    private ImageView editIcon;
    private LinearLayout itineraryContainer;
    private ImageButton back_button;

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
        back_button = findViewById(R.id.back_button);

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

        back_button.setOnClickListener(v -> {
            // Handle click event
            finish();
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
        dayLayout.setOrientation(LinearLayout.HORIZONTAL);
        dayLayout.setPadding(16, 16, 16, 16);
        dayLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        // Label (Day 1, Day 2, etc.) on the left
        TextView dayLabelView = new TextView(this);
        dayLabelView.setText(dayLabel);
        dayLabelView.setTextSize(16);
        dayLabelView.setTextColor(getResources().getColor(R.color.black));
        dayLabelView.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
        dayLabelView.setPadding(8, 8, 8, 8);
        dayLabelView.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f // Weight to occupy one-third of the row width
        ));

        // Content (Editable or non-editable) on the right
        View inputView;
        if (editable) {
            EditText dayInput = new EditText(this);
            dayInput.setText(content);
            dayInput.setTextSize(16);
            dayInput.setTextColor(getResources().getColor(R.color.black));
            dayInput.setBackgroundResource(R.drawable.button_background); // Button-like background
            dayInput.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
            dayInput.setPadding(8, 8, 8, 8);
            dayInput.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    2f // Weight to occupy two-thirds of the row width
            ));
            inputView = dayInput;
        } else {
            TextView dayContent = new TextView(this);
            dayContent.setText(content);
            dayContent.setTextSize(16);
            dayContent.setTextColor(getResources().getColor(R.color.black));
            dayContent.setBackgroundResource(R.drawable.button_background); // Button-like background
            dayContent.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
            dayContent.setPadding(8, 8, 8, 8);
            dayContent.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    2f // Weight to occupy two-thirds of the row width
            ));
            inputView = dayContent;
        }

        // Add views to the day layout
        dayLayout.addView(dayLabelView);
        dayLayout.addView(inputView);

        // Add the row to the container
        itineraryContainer.addView(dayLayout);
    }

    private void setupDatePicker(EditText editText) {
        // Disable keyboard input
        editText.setFocusable(false);
        editText.setClickable(true);
        editText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        editText.setText(dateFormat.format(calendar.getTime()));
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });
    }

    private void setEditable(boolean editable) {
        editDepartureDate.setEnabled(editable);
        editReturnDate.setEnabled(editable);

        if (editable) {
            // Attach date pickers
            setupDatePicker(editDepartureDate);
            setupDatePicker(editReturnDate);
        } else {
            // Remove focusability
            editDepartureDate.setOnClickListener(null);
            editReturnDate.setOnClickListener(null);
        }

        saveButton.setVisibility(editable ? View.VISIBLE : View.GONE);

        for (int i = 0; i < itineraryContainer.getChildCount(); i++) {
            LinearLayout dayLayout = (LinearLayout) itineraryContainer.getChildAt(i);
            TextView dayLabel = (TextView) dayLayout.getChildAt(0); // Day label on the left
            View inputView = dayLayout.getChildAt(1); // Input field on the right

            if (editable && inputView instanceof TextView) {
                String content = ((TextView) inputView).getText().toString();
                dayLayout.removeView(inputView);

                EditText dayInput = new EditText(this);
                dayInput.setText(content);
                dayInput.setTextSize(16);
                dayInput.setTextColor(getResources().getColor(R.color.black));
                dayInput.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
                dayInput.setBackgroundResource(R.drawable.button_background);
                dayInput.setPadding(8, 8, 8, 8);
                dayInput.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        2f
                ));
                dayLayout.addView(dayInput);
            } else if (!editable && inputView instanceof EditText) {
                String content = ((EditText) inputView).getText().toString();
                dayLayout.removeView(inputView);

                TextView dayContent = new TextView(this);
                dayContent.setText(content);
                dayContent.setTextSize(16);
                dayContent.setTextColor(getResources().getColor(R.color.black));
                dayContent.setTypeface(ResourcesCompat.getFont(this, R.font.open_sans));
                dayContent.setBackgroundResource(R.drawable.button_background);
                dayContent.setPadding(8, 8, 8, 8);
                dayContent.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        2f
                ));
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