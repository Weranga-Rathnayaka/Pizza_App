package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class driver_Add extends AppCompatActivity {

    EditText newdriverid,newdrivername,newdrivernumber,newdriveraddress,newdriveremail,newdrivervehiclemodel,newdrivervehiclenumber,newdriverdob;
    Button btnnewdriveradd,btnnewdriverslist;
    AwesomeValidation awesomevalidation;

    public static sqlhelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__add);

        awesomevalidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomevalidation.addValidation(this, R.id.newdriveremail,android.util.Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomevalidation.validate();


        init();

        sqLiteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS DRIVERDETAILS (ID INTEGER PRIMARY KEY AUTOINCREMENT,newdriverid VARCHAR, newdrivername VARCHAR,newdrivernumber VERCHAR,newdriveraddress VARCHAR,newdriveremail VARCHAR,newdrivervehiclemodel VARCHAR, newdrivervehiclenumber VARCHAR ,newdriverdob VARCHAR )");


        btnnewdriveradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1 = newdriverid.getText().toString();
                String n2 =newdrivername.getText().toString();
                String n3 =newdrivernumber.getText().toString();
                String n4 = newdriveraddress.getText().toString();
                String n5 =newdriveremail.getText().toString();
                String n6 =newdrivervehiclemodel.getText().toString();
                String n7 =newdrivervehiclenumber.getText().toString();
                String n8 =newdriverdob.getText().toString();
                if(n1.equals("")||n2.equals("")||n3.equals("")||n4.equals("")||n5.equals("")||n6.equals("")||n7.equals("")||n8.equals("")){
                    Toast.makeText(getApplicationContext(), "Field Empty", Toast.LENGTH_SHORT).show();
                }else{
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


    }

    private Boolean emailValidaion(EditText newdriveremail){

        String emailInput = newdriveremail.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(driver_Add.this, "", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(driver_Add.this, "Email validation is failed.Update it again", Toast.LENGTH_SHORT).show();
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