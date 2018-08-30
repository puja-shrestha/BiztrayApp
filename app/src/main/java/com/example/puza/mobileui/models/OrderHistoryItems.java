package com.example.puza.mobileui.models;

public class OrderHistoryItems {

    private String orderNo;
    private String orderDate;
    private String orderStatus;

    public OrderHistoryItems(String orderNo, String orderDate, String orderStatus) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
