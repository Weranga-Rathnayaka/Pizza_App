package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static sqlhelper sqliteHelper;

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);


        b1 = findViewById(R.id.b1);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_product.class);
                startActivity(intent);
            }
        });

        b3 = findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, admin_food_list.class);
                startActivity(intent);
            }
        });


        b2 = findViewById(R.id.check_order_details);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderList.class);
                startActivity(intent);
            }
        });



        b4 = findViewById(R.id.admin_logout_btn);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, startup_screen.class);
                startActivity(intent);
            }
        });



        b5 = findViewById(R.id.add_driver);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, driver_Add.class);
                startActivity(intent);
            }
        });

        b6 = findViewById(R.id.btnnewdriverslist);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Driver_OngoingList.class);
                startActivity(intent);
            }
        });

        b7 = findViewById(R.id.assign_to_delivery_btn);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, assign_order.class);
                startActivity(intent);
            }
        });

        b8 = findViewById(R.id.assign_to_delivery_btn);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Ongoing_get_order_details.class);
                startActivity(intent);
            }
        });

        b9 = findViewById(R.id.view_delivery_btn);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, OngoingList.class);
                startActivity(intent);
            }
        });
    }
}