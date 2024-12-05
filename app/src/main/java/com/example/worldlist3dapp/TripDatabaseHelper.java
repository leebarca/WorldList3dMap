package com.example.worldlist3dapp;

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

    // Trips table
    private static final String TABLE_TRIPS = "trips";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_DEPARTURE_DATE = "departure_date";
    private static final String COLUMN_RETURN_DATE = "return_date";

    // Day trips table
    private static final String TABLE_DAY_TRIPS = "day_trips";
    private static final String COLUMN_TRIP_ID = "trip_id";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_DETAILS = "details";

    public TripDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRIPS_TABLE = "CREATE TABLE " + TABLE_TRIPS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COUNTRY + " TEXT, " +
                COLUMN_DEPARTURE_DATE + " TEXT, " +
                COLUMN_RETURN_DATE + " TEXT)";
        db.execSQL(CREATE_TRIPS_TABLE);

        String CREATE_DAY_TRIPS_TABLE = "CREATE TABLE " + TABLE_DAY_TRIPS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TRIP_ID + " INTEGER, " +
                COLUMN_DAY + " TEXT, " +
                COLUMN_DETAILS + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_TRIP_ID + ") REFERENCES " + TABLE_TRIPS + "(" + COLUMN_ID + ") ON DELETE CASCADE)";
        db.execSQL(CREATE_DAY_TRIPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAY_TRIPS);
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

    public void addDayTrip(long tripId, String day, String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRIP_ID, tripId);
        values.put(COLUMN_DAY, day);
        values.put(COLUMN_DETAILS, details);
        db.insert(TABLE_DAY_TRIPS, null, values);
        db.close();
    }

    public List<String> getAllTrips() {
        List<String> trips = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
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

    public List<Long> getAllTripIds() {
        List<Long> tripIds = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_TRIPS, null);

        if (cursor.moveToFirst()) {
            do {
                tripIds.add(cursor.getLong(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tripIds;
    }

    public List<String> getDayTrips(long tripId) {
        List<String> dayTrips = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DAY_TRIPS + " WHERE " + COLUMN_TRIP_ID + " = ?", new String[]{String.valueOf(tripId)});

        if (cursor.moveToFirst()) {
            do {
                String day = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DAY));
                String details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS));
                dayTrips.add(day + ": " + details);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dayTrips;
    }

    public void deleteTrip(long tripId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DAY_TRIPS, COLUMN_TRIP_ID + " = ?", new String[]{String.valueOf(tripId)});
        db.delete(TABLE_TRIPS, COLUMN_ID + " = ?", new String[]{String.valueOf(tripId)});
        db.close();
    }

    public void updateTrip(long tripId, String departureDate, String returnDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEPARTURE_DATE, departureDate);
        values.put(COLUMN_RETURN_DATE, returnDate);
        db.update(TABLE_TRIPS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(tripId)});
        db.close();
    }
}