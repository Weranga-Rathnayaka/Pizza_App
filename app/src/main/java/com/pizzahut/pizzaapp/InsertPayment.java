package com.pizzahut.pizzaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.pizzahut.pizzaapp.Model.DeliveryDetailsModel;
import com.pizzahut.pizzaapp.Model.FoodDetailsModel;

import java.util.Date;
import java.util.Locale;

public class InsertPayment extends AppCompatActivity {

    EditText cardName,cardNumber,exp,security;
    Button confirm;
    TextView name,name2;
    AwesomeValidation awesomevalidation;

    public static sqlhelper sqliteHelper;

    DeliveryDetailsModel deliveryModel;
    FoodDetailsModel foodDetailsModel;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_payment);

        sqlhelper obj = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        init();
        String cardOnName = cardName.getText().toString();
        Intent i = getIntent();

        String Name= i.getStringExtra("foodName2");
        String price =i.getStringExtra("foodPrice2");
        String type = i.getStringExtra("foodType2");
        String qty = i.getStringExtra("qty1");
        name.setText(Name);
        name2.setText(qty);
        //style
        awesomevalidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomevalidation.addValidation(this, R.id.cardOnName, RegexTemplate.NOT_EMPTY,R.string.invalid_card_name);
        awesomevalidation.addValidation(this, R.id.cNumber, RegexTemplate.NOT_EMPTY,R.string.invalid_card_number);
        awesomevalidation.addValidation(this, R.id.exp,RegexTemplate.NOT_EMPTY, R.string.invalid_exp);
        awesomevalidation.addValidation(this, R.id.sCode, RegexTemplate.NOT_EMPTY, R.string.invalid_pin);


        try {
            deliveryModel = (DeliveryDetailsModel) i.getSerializableExtra("MyClass");
            foodDetailsModel = (FoodDetailsModel) i.getSerializableExtra("abc");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        confirm.setOnClickListener(v -> {
            if(awesomevalidation.validate()){
                insertTODB();}
        });

    }
    private void init(){

        cardName = (EditText) findViewById(R.id.cardOnName);
        cardNumber = (EditText) findViewById(R.id.cNumber);
        exp =(EditText)  findViewById(R.id.exp);
        security = (EditText) findViewById(R.id.sCode);
        confirm =findViewById(R.id.pConfirm);
        name = findViewById(R.id.empty2);
        name2 = findViewById(R.id.empty1);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertTODB() {

       sqlhelper obj = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        obj.queryData("CREATE TABLE IF NOT EXISTS Orders (orderId INTEGER PRIMARY KEY AUTOINCREMENT, firstName text, lastName text, email text, contactNumber INTEGER, address text, cardName text, cardNumber text, expDate text, securityCode text, productId text, qty text, date text)");



        if( obj.insertData(deliveryModel.getfName(),deliveryModel.getlName(),
                deliveryModel.getEmail(),deliveryModel.getContact(),deliveryModel.getAddress(),
                cardName.getText().toString(),cardNumber.getText().toString(),exp.getText().toString(),security.getText().toString(),
                name.getText().toString(),name2.getText().toString(),getDateTime())){

            Toast.makeText(getApplicationContext(), "Order Added Successfully...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InsertPayment.this,user_Home_Page.class);
            startActivity(intent);

        }  else{
            alertBox("Inset Failed  ");
        }

    }

    private void alertBox(String txt) {


        AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("Are you sure to Exit")
//set message
                .setMessage(txt)
//set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        finish();
                    }
                })
//set negative button

                .show();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);

    }


}