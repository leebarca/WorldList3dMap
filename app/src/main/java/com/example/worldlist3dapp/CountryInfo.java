package com.example.worldlist3dapp;

public class CountryInfo {
    private String name;
    private int imageResId;
    private String capital;
    private String population;
    private String language;
    private String description;

    // Constructor
    public CountryInfo(String name, int imageResId, String capital, String population, String language, String description) {
        this.name = name;
        this.imageResId = imageResId;
        this.capital = capital;
        this.population = population;
        this.language = language;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }
}