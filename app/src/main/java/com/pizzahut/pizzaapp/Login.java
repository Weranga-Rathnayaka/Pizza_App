package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    EditText e1,e2;
    Button b1,b2;

    public static sqlhelper db;

    public static sqlhelper sqliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = findViewById(R.id.login_username);
        e2 = findViewById(R.id.login_password);
        b1 = findViewById(R.id.login_btn);
        b2 = findViewById(R.id.direct_signUp);


        db = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        sqliteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        //navigate to register UI
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,register.class);
                startActivity(intent);

            }
        });

        //navigate to home UI
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = e1.getText().toString();//username
                String s2 = e2.getText().toString();//password

                Boolean checkUsernamePassword = db.usernamePassword(s1,s2);

                if (checkUsernamePassword == true){

                    Intent intent = new Intent(Login.this,user_Home_Page.class);//navigate to home page
                    intent.putExtra("username1",s1);
                    startActivity(intent);

                }
                else if (s1.equals("admin") && s2.equals("admin123")){//admin username and password

                    Intent intent = new Intent(Login.this,MainActivity.class);//admin UI
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();//toast msg
                    //reset text fields
                    e1.setText("");
                    e2.setText("");
                }

            }
        });
    }

}