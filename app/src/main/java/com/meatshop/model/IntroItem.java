package com.meatshop.model;

public class IntroItem {
    private String title;
    private String description;
    private String imageURL;

    public IntroItem(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.imageURL = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }
}
