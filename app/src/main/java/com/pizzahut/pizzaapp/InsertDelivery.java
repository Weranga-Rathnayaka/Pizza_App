package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.pizzahut.pizzaapp.Model.DeliveryDetailsModel;
import com.pizzahut.pizzaapp.Model.FoodDetailsModel;

import java.io.Serializable;

public class InsertDelivery extends AppCompatActivity {

    Button confirm,back;
    EditText fName,lName,email,contact,address;
    TextView name;

    AwesomeValidation awesomevalidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_delivery);

        init();

        Intent i = getIntent();
        String name= i.getStringExtra("foodName1");
        String price =i.getStringExtra("foodPrice1");
        String type = i.getStringExtra("foodType1");
        String qty = i.getStringExtra("qty");

        //style
        awesomevalidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomevalidation.addValidation(this, R.id.fName, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomevalidation.addValidation(this, R.id.lName, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomevalidation.addValidation(this, R.id.email,android.util.Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomevalidation.addValidation(this, R.id.contactNumber, RegexTemplate.TELEPHONE, R.string.err_tel);
        awesomevalidation.addValidation(this, R.id.address, RegexTemplate.NOT_EMPTY,R.string.invalid_address);





        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (awesomevalidation.validate()) {


                    DeliveryDetailsModel obj = new DeliveryDetailsModel();
                    FoodDetailsModel model = new FoodDetailsModel();

                    obj.setfName(fName.getText().toString().trim());
                    obj.setlName(lName.getText().toString().trim());
                    obj.setEmail(email.getText().toString().trim());
                    obj.setContact(contact.getText().toString().trim());
                    obj.setAddress(address.getText().toString().trim());

                    Intent i = new Intent(InsertDelivery.this, InsertPayment.class);

                    i.putExtra("MyClass", (Serializable) obj);
                    i.putExtra("foodName2", name);
                    i.putExtra("foodPrice2", price);
                    i.putExtra("foodType2", type);
                    i.putExtra("qty1",qty);


                    startActivity(i);
                }
            }



        });


    }

    private void init(){
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        email = findViewById(R.id.email);
        contact= findViewById(R.id.contactNumber);
        address = findViewById(R.id.address);
        confirm = findViewById(R.id.dConfirm);
        name = findViewById(R.id.page_title_delivery);

    }

}