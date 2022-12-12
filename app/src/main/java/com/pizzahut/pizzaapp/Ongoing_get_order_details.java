package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ongoing_get_order_details extends AppCompatActivity {

    EditText getOrderId;
    Button goBtn,viewoderIDlist;


    sqlhelper sqlhelper;
    sqlhelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_get_order_details);

        sqlhelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        getOrderId = findViewById(R.id.get_order_id_);
        goBtn = findViewById(R.id.xxxxxxxxxxxxxxxxxxxx);
        viewoderIDlist = findViewById(R.id.viewoderIDlist);

        db = new sqlhelper(this, "FoodDB.sqlite",null, 1);







        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String o1 = getOrderId.getText().toString();
                if(o1.equals("")){
                    Toast.makeText(getApplicationContext(), "Field Empty", Toast.LENGTH_SHORT).show();
                }else{
                    String orderID = getOrderId.getText().toString();
                    Intent intent = new Intent(Ongoing_get_order_details.this, assign_order.class);
                    intent.putExtra("orderID",orderID);
                    startActivity(intent);
                }
            }
        });
        viewoderIDlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getOrderIDList();
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("orderID:"+res.getString(0)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Ongoing_get_order_details.this);
                builder.setCancelable(true);
                builder.setTitle("Oder IDs of oders");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });





    }
}