package com.pizzahut.pizzaapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Driver_SQLiteHelper extends SQLiteOpenHelper {
    public Driver_SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData( String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

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
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

