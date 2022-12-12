package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class update_user_profile extends AppCompatActivity {

    EditText e2,e3,e4,e5;
    TextView e1;
    Button b1;
    ImageView i1;

    sqlhelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        db = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        e1 = findViewById(R.id.update_username);
        e2 = findViewById(R.id.update_email);
        e3 = findViewById(R.id.update_contactNumber);
        e4 = findViewById(R.id.update_password);
        e5 = findViewById(R.id.update_confirmPassword);
        b1 = findViewById(R.id.update_profile_btn);
        i1 = findViewById(R.id.back_btn);

        //catch the values come from userProfile
        String take_username = getIntent().getStringExtra("username11");
        String take_email = getIntent().getStringExtra("email11");
        String take_contact = getIntent().getStringExtra("contact11");
        String take_pass = getIntent().getStringExtra("password11");

        //assign the values
        e1.setText(take_username);
        e2.setText(take_email);
        e3.setText(take_contact);
        e4.setText(take_pass);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String s1 = e1.getText().toString(); // username
                    String s2 = e2.getText().toString(); //email
                    String s3 = e3.getText().toString(); //contact
                    String s4 = e4.getText().toString(); //password
                    String s5 = e5.getText().toString();


                    //validations
                    if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){

                        Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();

                    }

                    else if(!Patterns.EMAIL_ADDRESS.matcher(e2.getText().toString()).matches()){
                        e2.setError("Please Enter Valid Mail.hint:-'@','.'");

                    }

                    else if(!e3.getText().toString().matches("[0-9]{10}")){
                        e3.setError("Enter Only 10 Digit Mobile Number.eg:-0716406450");
                    }


                    else {
                        if (s4.equals(s5)){

                            try {

                                startup_screen.sqliteHelper.updateUserData(s2,s3,s4,s1);
                                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(update_user_profile.this, Login.class);//navigate to login page
                                startActivity(intent);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }

            }
        });

        //back to the login by clicking back button
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(update_user_profile.this,Login.class);//navigate to login UI
                startActivity(intent);

            }
        });
    }

}