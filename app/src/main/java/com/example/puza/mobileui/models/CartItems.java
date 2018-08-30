package com.example.puza.mobileui.models;

public class CartItems {

    private int Image;
    private String name;
    private String price;
    private String size;
    private String quantity;

    public CartItems(int image, String name, String price, String size, String quantity) {
        Image = image;
        this.name = name;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
