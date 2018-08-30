package com.example.puza.mobileui.models;

import android.media.Image;

public class FeaturedItems {

//    private int Image;
//    private String name;
//    private String price;

    private String id;
    private int image1;
    private String title;
    private String price;

    public FeaturedItems(String id, int image1, String title, String price) {
        this.id = id;
        this.image1 = image1;
        this.title = title;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
