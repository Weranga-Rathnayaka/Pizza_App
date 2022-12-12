package com.pizzahut.pizzaapp;

public class Ongoing {
    private int id;
    private String edtoderid;
    private String edtname;
    private String edtnumber;
    private String edtaddress;
    private String edtprice;
    private String edtdriverid;
    private String edtdrivername;
    private String edtcomplete;

    public Ongoing(String edtoderid, String edtname, String edtnumber, String edtaddress, String edtprice, String edtdriverid, String  edtdrivername, String edtcomplete, int id) {

        this.edtoderid = edtoderid;
        this.edtname = edtname;
        this.edtnumber = edtnumber;
        this.edtaddress = edtaddress;
        this.edtprice = edtprice;
        this.edtdriverid = edtdriverid;
        this.edtdrivername =  edtdrivername;
        this.edtcomplete = edtcomplete;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEdtoderid() {
        return edtoderid;
    }
    public void setEdtoderid(String edtoderid) {
        this.edtoderid = edtoderid;
    }

    public String getEdtname() {
        return edtname;
    }
    public void setEdtname(String edtname) {
        this.edtname = edtname;
    }

    public String getEdtnumber() {
        return edtnumber;
    }
    public void setEdtnumber(String edtnumber) { this.edtnumber = edtnumber;
    }

    public String getEdtaddress() {
        return edtaddress;
    }
    public void setEdtaddress(String edtaddress) { this.edtaddress = edtaddress;
    }

    public String getEdtprice() {
        return edtprice;
    }
    public void setEdtprice(String edtprice) {
        this.edtprice = edtprice;
    }

    public String getEdtdriverid() {
        return edtdriverid;
    }
    public void setEdtdriverid(String edtdriverid) { this.edtdriverid = edtdriverid;
    }

    public String getEdtdrivername() {
        return edtdrivername;
    }
    public void setEdtdrivername(String edtdrivername) { this.edtdrivername = edtdrivername;
    }

    public String getEdtcomplete() {
        return edtcomplete;
    }
    public void setEdtcomplete(String edtcomplete) {
        this.edtcomplete = edtcomplete;
    }
}
