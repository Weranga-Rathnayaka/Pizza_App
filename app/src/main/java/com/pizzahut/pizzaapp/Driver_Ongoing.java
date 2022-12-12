package com.pizzahut.pizzaapp;

public class Driver_Ongoing {


    private int id;
    private String newdriverid;
    private String newdrivername;
    private String newdrivernumber;
    private String newdriveraddress;
    private String newdriveremail;
    private String newdrivervehiclemodel;
    private String newdrivervehiclenumber;
    private String newdriverdob;

    public Driver_Ongoing(String newdriverid, String newdrivername, String newdrivernumber, String newdriveraddress, String newdriveremail, String newdrivervehiclemodel, String newdrivervehiclenumber, String newdriverdob, int id) {

        this.newdriverid = newdriverid;
        this.newdrivername = newdrivername;
        this.newdrivernumber = newdrivernumber;
        this.newdriveraddress = newdriveraddress;
        this.newdriveremail = newdriveremail;
        this.newdrivervehiclemodel = newdrivervehiclemodel;
        this.newdrivervehiclenumber = newdrivervehiclenumber;
        this.newdriverdob = newdriverdob;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNewdriverid() {
        return newdriverid;
    }
    public void setNewdriverid(String newdriverid) {
        this.newdriverid = newdriverid;
    }

    public String getNewdrivername() {
        return newdrivername;
    }
    public void setNewdrivername(String newdrivername) {
        this.newdrivername = newdrivername;
    }

    public String getNewdrivernumber() {
        return newdrivernumber;
    }
    public void setNewdrivernumber(String newdrivernumber) { this.newdriveraddress = newdrivernumber;
    }

    public String getNewdriveraddress() {
        return newdriveraddress;
    }
    public void setNewdriveraddress(String newdriveraddress) { this.newdriveraddress = newdriveraddress;
    }

    public String getNewdriveremail() {
        return newdriveremail;
    }
    public void setNewdriveremail(String newdriveremail) {
        this.newdriveremail = newdriveremail;
    }

    public String getNewdrivervehiclemodel() {
        return newdrivervehiclemodel;
    }
    public void setNewdrivervehiclemodel(String newdrivervehiclemodel) { this.newdrivervehiclemodel = newdrivervehiclemodel;
    }

    public String getNewdrivervehiclenumber() {
        return newdrivervehiclenumber;
    }
    public void setNewdrivervehiclenumber(String newdrivervehiclenumber) { this.newdrivervehiclenumber = newdrivervehiclenumber;
    }

    public String getNewdriverdob() {
        return newdriverdob;
    }
    public void setNewdriverdob(String newdriverdob) {
        this.newdriverdob = newdriverdob;
    }

}
