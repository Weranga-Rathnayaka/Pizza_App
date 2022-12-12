package com.pizzahut.pizzaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class sqlhelper extends SQLiteOpenHelper {


    public sqlhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void queryData(String sql){

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //sawishka

    public void insertdata(String name, String price,String type,String description, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD VALUES(NULL, ?, ?, ?, ?, ? )";

        SQLiteStatement statement= database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindString(3, type);
        statement.bindString(4, description);
        statement.bindBlob(5, image);

        statement.executeInsert();
    }

    //sawishka
    public void updateData(String name, String price, String type, String description, byte[] image, int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String sql = "UPDATE FOOD SET name = ?, price = ?, type = ?, description = ?, image = ? WHERE  Id = ?";
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);

        sqLiteStatement.bindString(1, name);
        sqLiteStatement.bindString(2, price);
        sqLiteStatement.bindString(3, type);
        sqLiteStatement.bindString(4, description);
        sqLiteStatement.bindBlob(5, image);
        sqLiteStatement.bindDouble(6,(double)id);

        sqLiteStatement.execute();
        sqLiteDatabase.close();

    }


    //sawishka
    public void deleteData(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String sql = "DELETE FROM FOOD WHERE Id = ?";
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindDouble(1,(double)id);

        sqLiteStatement.execute();
        sqLiteDatabase.close();
    }


    //sawishka

    public Cursor getdata(String sql){

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //sangeeth

    public boolean insertUser(String username,String email, String contact, String password){

        SQLiteDatabase db = this.getWritableDatabase();//get registration values
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("contact",contact);
        contentValues.put("password",password);

        long ins = db.insert("user",null,contentValues);//add value to database

        if (ins == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username = ?",new String[] {username});


        if (cursor.getCount()> 0){
            return false;
        }else {
            return true;
        }
    }


    public boolean usernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();//get email and password
        Cursor cursor = db.rawQuery("select * from user where username= ? and password= ?",new String[]{username,password});


        if (cursor.getCount()> 0){
            return true;
        }else {
            return false;
        }
    }





    public Cursor getUserData(String username){

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("select * from user where username= ?",new String[]{username});

    }

    public void updateUserData(String email, String contact, String password, String username){

        SQLiteDatabase db =this.getWritableDatabase();

        String sql = "UPDATE user SET email = ?, contact = ?, password = ? WHERE  username = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.bindString(1, email);
        sqLiteStatement.bindString(2, contact);
        sqLiteStatement.bindString(3, password);
        sqLiteStatement.bindString(4, username);

        sqLiteStatement.execute();
        db.close();


    }




    public void deleteUserData(String username){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String sql = "DELETE FROM user WHERE username = ?";

        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,username);

        sqLiteStatement.execute();
        sqLiteDatabase.close();
    }





    //savindu

    public Boolean insertData(String firstName,String lastName,String email,String contactNumber,
                              String address,String cardName,String cardNumber,String expDate,
                              String securityCode,String productId,String qty,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        //for our values
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);
        contentValues.put("contactNumber", contactNumber);
        contentValues.put("address", address);
        contentValues.put("cardName", cardName);
        contentValues.put("cardNumber", cardNumber);
        contentValues.put("expDate", expDate);
        contentValues.put("securityCode", securityCode);
        contentValues.put("productId", productId);
        contentValues.put("qty", qty);
        contentValues.put("date", date);

        long results = db.insert("Orders", null,contentValues);

        if(results == -1){
            return false;
        }else {
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from Orders",null);
        return  cursor;


    }


    public void deleteOrderData(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    /*


        long results = db.delete("Orders", "orderId = ?", new int[]{id});

        if (results == -1) {
            return false;
        } else {
            return true;
        }

     */
        String sql = "DELETE FROM Orders WHERE OrderId = ?";
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindDouble(1,(double)id);

        sqLiteStatement.execute();
        sqLiteDatabase.close();

    }



    //alwis

    public void insertDataDriver(String newdriverid,String newdrivername, String newdrivernumber, String newdriveraddress, String newdriveremail, String newdrivervehiclemodel,String newdrivervehiclenumber ,String newdriverdob){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO DRIVERDETAILS VALUES(NULL,?,?,?,?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,newdriverid);
        statement.bindString(2,newdrivername);
        statement.bindString(3,newdrivernumber);
        statement.bindString(4,newdriveraddress);
        statement.bindString(5,newdriveremail);
        statement.bindString(6,newdrivervehiclemodel);
        statement.bindString(7,newdrivervehiclenumber);
        statement.bindString(8,newdriverdob);


        statement.executeInsert();
    }


    public void updateDataDriver(String newdriverid,String newdrivername,String newdrivernumber,String newdriveraddress,String newdriveremail ,String newdrivervehiclemodel,String newdrivervehiclenumber,String newdriverdob,int id ){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE DRIVERDETAILS SET newdriverid = ? ,newdrivername = ? ,newdrivernumber = ?,newdriveraddress = ?,newdriveremail = ?,newdrivervehiclemodel = ?,newdrivervehiclenumber = ?,newdriverdob = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);


        statement.bindString(1,newdriverid );
        statement.bindString(2,newdrivername);
        statement.bindString(3,newdrivernumber);
        statement.bindString(4,newdriveraddress);
        statement.bindString(5,newdriveremail);
        statement.bindString(6,newdrivervehiclemodel);
        statement.bindString(7,newdrivervehiclenumber);
        statement.bindString(8,newdriverdob);
        statement.bindDouble(9, (double)id);

        statement.execute();
        database.close();

    }

    public void deleteDataDriver(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM DRIVERDETAILS WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);
        statement.execute();
        database.close();

    }
    public Cursor getDriverData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }



    public void insertDataDelivery(String edtoderid,String edtname, String edtnumber, String edtaddress,String edtprice, String edtdriverid, String edtdrivername,String edtcomplete ){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO ONGOING VALUES(NULL,?,?,?,?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,edtoderid);
        statement.bindString(2,edtname);
        statement.bindString(3,edtnumber);
        statement.bindString(4,edtaddress);
        statement.bindString(5,edtprice);
        statement.bindString(6,edtdriverid);
        statement.bindString(7,edtdrivername);
        statement.bindString(8,edtcomplete);



        statement.executeInsert();
    }


    public void updateDataDelivery(String edtoderid,String edtname,String edtnumber,String edtaddress,String edtprice ,String edtdriverid,String edtdrivername,String edtcomplete,int id ){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE ONGOING SET edtoderid = ? ,edtname = ? ,edtnumber = ?,edtaddress = ?,edtprice = ?,edtdriverid = ?,edtdrivername = ?,edtcomplete = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);


        statement.bindString(1,edtoderid );
        statement.bindString(2,edtname);
        statement.bindString(3,edtnumber);
        statement.bindString(4,edtaddress);
        statement.bindString(5,edtprice);
        statement.bindString(6,edtdriverid);
        statement.bindString(7,edtdrivername);
        statement.bindString(8,edtcomplete);
        statement.bindDouble(9, (double)id);

        statement.execute();
        database.close();

    }

    public void deleteDataDelivery(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM ONGOING WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);
        statement.execute();
        database.close();

    }
    public Cursor getDataDelivery(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public Cursor getOrderDetailsToDelivery(String id){

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("select * from Orders where orderId= ?",new String[]{id});


    }

//    public Cursor getDriverDetails(String id){
//
//        SQLiteDatabase database = getReadableDatabase();
//        return database.rawQuery("select * from DRIVERDETAILS where ID= ?",new String[]{id});
//
//
//    }

    public Cursor getOrderIDList()
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("select OrderId from Orders",null);
    }

}
