package com.example.puza.mobileui.models;

public class FeaturedSortItems {

    private int image;
    private String title;
    private String description;
    private String discount;
    private String price;

    public FeaturedSortItems(int image, String title, String description, String discount, String price) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.discount = discount;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
