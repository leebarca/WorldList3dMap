package com.example.worldlist3dapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TripDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "trips.db";
    private static final int DATABASE_VERSION = 2;

    // Table and column names
    private static final String TABLE_TRIPS = "trips";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_DEPARTURE_DATE = "departure_date";
    private static final String COLUMN_RETURN_DATE = "return_date";

    public TripDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRIPS_TABLE = "CREATE TABLE " + TABLE_TRIPS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_COUNTRY + " TEXT,"
                + COLUMN_DEPARTURE_DATE + " TEXT,"
                + COLUMN_RETURN_DATE + " TEXT)";
        db.execSQL(CREATE_TRIPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        onCreate(db);
    }

    public long addTrip(String country, String departureDate, String returnDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COUNTRY, country);
        values.put(COLUMN_DEPARTURE_DATE, departureDate);
        values.put(COLUMN_RETURN_DATE, returnDate);
        long tripId = db.insert(TABLE_TRIPS, null, values);
        db.close();
        return tripId;
    }

    @SuppressLint("Range")
    public List<Long> getAllTripIds() {
        List<Long> tripIds = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Fetch only trip IDs from the main trips table
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_TRIPS, null);

        if (cursor.moveToFirst()) {
            do {
                tripIds.add(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tripIds;
    }

    public List<String> getAllTrips() {
        List<String> trips = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Fetch only trips from the main trips table
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TRIPS, null);

        if (cursor.moveToFirst()) {
            do {
                String country = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY));
                String departureDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEPARTURE_DATE));
                String returnDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RETURN_DATE));
                trips.add(country + " (" + departureDate + " - " + returnDate + ")");
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return trips;
    }

    public void deleteTrip(long tripId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIPS, COLUMN_ID + " = ?", new String[]{String.valueOf(tripId)});
        db.close();
    }
}