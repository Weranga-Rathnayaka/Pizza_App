package com.pizzahut.pizzaapp;

public class Order {

    private int orderId;
    private String productID;
    private  String qty;
    private String address;
    private  String contactNumber;
    private String date;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order(int orderId, String productID, String qty, String address, String contactNumber, String date) {
        this.orderId = orderId;
        this.productID = productID;
        this.qty = qty;
        this.address = address;
        this.contactNumber = contactNumber;
        this.date = date;

    }


}
