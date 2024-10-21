package com.example.worldlist3dapp;

public class CountryInfo {
    private String name;
    private int imageResId;
    private String description;

    // Constructor
    public CountryInfo(String name, int imageResId, String description) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}