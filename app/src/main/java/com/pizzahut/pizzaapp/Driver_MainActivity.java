package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Driver_MainActivity extends AppCompatActivity {

    EditText newdriverid,newdrivername,newdrivernumber,newdriveraddress,newdriveremail,newdrivervehiclemodel,newdrivervehiclenumber,newdriverdob;
    Button btnnewdriveradd,btnnewdriverslist;

    public static Driver_SQLiteHelper sqLiteHelper;
    public static sqlhelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_activity_main);

        init();

        sqLiteHelper = new Driver_SQLiteHelper(this,"DriverDetailsDB.sqlite",null,1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS DRIVERDETAILS(ID INTEGER PRIMARY KEY AUTOINCREMENT,newdriverid VARCHAR, newdrivername VARCHAR,newdrivernumber VERCHAR,newdriveraddress VARCHAR,newdriveremail VARCHAR,newdrivervehiclemodel VARCHAR, newdrivervehiclenumber VARCHAR ,newdriverdob VARCHAR )");


        btnnewdriveradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1 = newdriverid.getText().toString();
                String s2 =  newdrivername.getText().toString();
                String s3 =  newdrivernumber.getText().toString();
                String s4 =  newdriveraddress.getText().toString();
                String s5 =  newdriveremail.getText().toString();
                String s6 =  newdrivervehiclemodel.getText().toString();
                String s7 =  newdrivervehiclenumber.getText().toString();
                String s8 =  newdriverdob.getText().toString();

                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")||s7.equals("")||s8.equals("")){
                    Toast.makeText(getApplicationContext(), "Field Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        sqLiteHelper.insertDataDriver(
                                newdriverid.getText().toString().trim(),
                                newdrivername.getText().toString().trim(),
                                newdrivernumber.getText().toString().trim(),
                                newdriveraddress.getText().toString().trim(),
                                newdriveremail.getText().toString().trim(),
                                newdrivervehiclemodel.getText().toString().trim(),
                                newdrivervehiclenumber.getText().toString().trim(),
                                newdriverdob.getText().toString().trim()




                        );
                        emailValidaion(newdriveremail);
                        Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
                        newdriverid.setText("");
                        newdrivername.setText("");
                        newdrivernumber.setText("");
                        newdriveraddress.setText("");
                        newdriveremail.setText("");
                        newdrivervehiclemodel.setText("");
                        newdrivervehiclenumber.setText("");
                        newdriverdob.setText("");

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }


            }
        });


        btnnewdriverslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Driver_MainActivity.this, Driver_OngoingList.class);
                startActivity(intent);
            }
        });
    }

    private Boolean emailValidaion(EditText newdriveremail){

        String emailInput = newdriveremail.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(Driver_MainActivity.this, "", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(Driver_MainActivity.this, "Email validation is failed.Update it again", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void init(){
        newdriverid = (EditText) findViewById(R.id.newdriverid);
        newdrivername = (EditText) findViewById(R.id.newdrivername);
        newdrivernumber = (EditText) findViewById(R.id.newdrivernumber);
        newdriveraddress = (EditText) findViewById(R.id.newdriveraddress);
        newdriveremail = (EditText) findViewById(R.id.newdriveremail);
        newdrivervehiclemodel = (EditText) findViewById(R.id.newdrivervehiclemodel);
        newdrivervehiclenumber = (EditText) findViewById(R.id.newdrivervehiclenumber);
        newdriverdob = (EditText) findViewById(R.id.newdriverdob);
        btnnewdriveradd = (Button) findViewById(R.id.btnnewdriveradd);
        btnnewdriverslist = (Button) findViewById(R.id.btnnewdriverslist);
    }
}
