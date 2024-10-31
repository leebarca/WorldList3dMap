package com.example.worldlist3dapp;

public class CountryInfo {
    private String name;
    private int flagResId;
    private int imageResId;
    private String capital;
    private String population;
    private String language;
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

    // Constructor
    public CountryInfo(String name, int flagResId, int imageResId, String capital, String population, String language, String description, String firstAttractionName, String firstAttractionDetails, int firstAttractionImage, String secondAttractionName, String secondAttractionDetails, int secondAttractionImage, String thirdAttractionName, String thirdAttractionDetails, int thirdAttractionImage) {
        this.name = name;
        this.flagResId = flagResId;
        this.imageResId = imageResId;
        this.capital = capital;
        this.population = population;
        this.language = language;
        this.description = description;
        this.firstAttractionName = firstAttractionName;
        this.firstAttractionDetails = firstAttractionDetails;
        this.firstAttractionImage = firstAttractionImage;
        this.secondAttractionName = secondAttractionName;
        this.secondAttractionDetails = secondAttractionDetails;
        this.secondAttractionImage = secondAttractionImage;
        this.thirdAttractionName = thirdAttractionName;
        this.thirdAttractionDetails = thirdAttractionDetails;
        this.thirdAttractionImage = thirdAttractionImage;
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
}