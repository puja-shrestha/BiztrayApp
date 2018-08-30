package com.example.puza.mobileui.models;

public class MostPopularItems {

    private int image;
    private String description;
    private String name;
    private String price;

    public MostPopularItems(int image, String description, String name, String price) {
        this.image = image;
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
