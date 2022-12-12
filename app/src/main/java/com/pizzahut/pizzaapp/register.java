package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    Button b1;

    sqlhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        e1 = findViewById(R.id.register_username);
        e2 = findViewById(R.id.register_email);
        e3 = findViewById(R.id.register_contactNumber);
        e4 = findViewById(R.id.register_password);
        e5 = findViewById(R.id.register_confirmPassword);

        b1 = findViewById(R.id.signUp_btn);

        db = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        db.queryData("CREATE TABLE IF NOT EXISTS user(username text primary key, email text, contact text, password text)");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();


                //validations
                //check whether all fields are empty
                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){

                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();

                }

                //email validation
                else if(!Patterns.EMAIL_ADDRESS.matcher(e2.getText().toString()).matches()){
                    e2.setError("Please Enter Valid Mail.hint:-'@','.'");

                }

                //mobile number validation
                else if(!e3.getText().toString().matches("[0-9]{10}")){
                    e3.setError("Enter Only 10 Digit Mobile Number.eg:-0716406450");
                }

                //re-check password
                else {
                    if (s4.equals(s5)){

                        Boolean checkUsername = db.checkUsername(s1);

                        if (checkUsername == true){

                            Boolean insert =db.insertUser(s1,s2,s3,s4);//insert all the values to the database

                            if (insert == true){

                                Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();//print toast msg
                                Intent intent = new Intent(register.this, Login.class);//navigate to login page
                                startActivity(intent);

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Username Already Have", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}