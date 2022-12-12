package com.pizzahut.pizzaapp.Model;

import java.io.Serializable;

public class FoodDetailsModel implements Serializable {

    public FoodDetailsModel() {


    }

    private String fName;
    private String fId;
    private String fType;
    private String fUnitPrice;
    private String fQty;

    public String getfName(String foodName) {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfType(String foodNType) {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfUnitPrice(String foodPrice) {
        return fUnitPrice;
    }

    public void setfUnitPrice(String fUnitPrice) {
        this.fUnitPrice = fUnitPrice;
    }

    public String getfQty() {
        return fQty;
    }

    public void setfQty(String fQty) {
        this.fQty = fQty;
    }
}
