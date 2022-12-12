package com.pizzahut.pizzaapp.Model;

import java.io.Serializable;
import java.util.Date;

public class DeliveryDetailsModel implements Serializable {


    public DeliveryDetailsModel() {
    }


    private String fName;
    private String lName;
    private String email;
    private String contact;
    private String address;
    Date date;
    //private String cardOnName;
//    private String cardNumber;
//    private String expDate;
//    private String securityCode;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//   public String getCardOnName() {
//       return cardOnName;
//   }
//
//    public void setCardOnName(String cardOnName) {
//       this.cardOnName = cardOnName;
//    }
//
//    public String getCardNumber() {
//        return cardNumber;
//    }
//
//    public void setCardNumber(String cardNumber) {
//        this.cardNumber = cardNumber;
//    }
//
//    public String getExpDate() {
//        return expDate;
//    }
//
//    public void setExpDate(String expDate) {
//        this.expDate = expDate;
//    }
//
//    public String getSecurityCode() {
//        return securityCode;
//    }
//
//    public void setSecurityCode(String securityCode) {
//        this.securityCode = securityCode;
//    }


}
