package com.example.puza.mobileui.models;

public class DecorativeItems {

    private int Image;
    private String heading;
    private String subHeading;
    private String price;

    public DecorativeItems(int image, String heading, String subHeading, String price) {
        Image = image;
        this.heading = heading;
        this.subHeading = subHeading;
        this.price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
