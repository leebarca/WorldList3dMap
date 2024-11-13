package com.example.worldlist3dapp;

import java.util.ArrayList;
import java.util.List;

public class CountryInfo {
    private String name;
    private int flagResId;
    private int imageResId;
    private String capital;
    private String population;
    private String language;
    private String religion;
    private String continent;
    private String area;
    private String description;
    private String firstAttractionName;
    private String firstAttractionDetails;
    private int firstAttractionImage;
    private String secondAttractionName;
    private String secondAttractionDetails;
    private int secondAttractionImage;
    private String thirdAttractionName;
    private String thirdAttractionDetails;
    private int thirdAttractionImage;
    private int[] bestTimeVisit;
    private String fact;
    private String website;
    private String[] weather;
    private String[] cuisine;
    private String[] safety;

    // Constructor
    public CountryInfo(String name, int flagResId, int imageResId, String capital, String population, String language, String religion, String continent, String area, String description, String firstAttractionName, String firstAttractionDetails, int firstAttractionImage, String secondAttractionName, String secondAttractionDetails, int secondAttractionImage, String thirdAttractionName, String thirdAttractionDetails, int thirdAttractionImage, int[] bestTimeVisit, String fact, String website, String[] weather, String[] cuisine, String[] safety) {
        this.name = name;
        this.flagResId = flagResId;
        this.imageResId = imageResId;
        this.capital = capital;
        this.population = population;
        this.language = language;
        this.description = description;
        this.religion = religion;
        this.continent = continent;
        this.area = area;
        this.firstAttractionName = firstAttractionName;
        this.firstAttractionDetails = firstAttractionDetails;
        this.firstAttractionImage = firstAttractionImage;
        this.secondAttractionName = secondAttractionName;
        this.secondAttractionDetails = secondAttractionDetails;
        this.secondAttractionImage = secondAttractionImage;
        this.thirdAttractionName = thirdAttractionName;
        this.thirdAttractionDetails = thirdAttractionDetails;
        this.thirdAttractionImage = thirdAttractionImage;
        this.bestTimeVisit = bestTimeVisit;
        this.fact = fact;
        this.website = website;
        this.weather = weather;
        this.cuisine = cuisine;
        this.safety = safety;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getFlagResId() {
        return flagResId;
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

    public String getReligion() {
        return religion;
    }

    public String getContinent() {
        return continent;
    }

    public String getArea() {
        return area;
    }

    public String getFirstAttractionName() {
        return firstAttractionName;
    }

    public String getFirstAttractionDetails() {
        return firstAttractionDetails;
    }

    public int getFirstAttractionImage() {
        return firstAttractionImage;
    }

    public String getSecondAttractionName() {
        return secondAttractionName;
    }

    public String getSecondAttractionDetails() {
        return secondAttractionDetails;
    }

    public int getSecondAttractionImage() {
        return secondAttractionImage;
    }

    public String getThirdAttractionName() {
        return thirdAttractionName;
    }

    public String getThirdAttractionDetails() {
        return thirdAttractionDetails;
    }

    public int getThirdAttractionImage() {
        return thirdAttractionImage;
    }

    public int[] getBestTimeVisit() {
        return bestTimeVisit;
    }

    public String getFact() {
        return fact;
    }

    public String getWebsite() {
        return website;
    }

    public String[] getWeather() {
        return weather;
    }

    public String[] getCuisine() {
        return cuisine;
    }

    public String[] getSafety() {
        return safety;
    }
}