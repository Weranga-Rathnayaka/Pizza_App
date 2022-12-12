package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class userProfile extends AppCompatActivity {


    TextView t1,t2,t3,t4;
    Button b1,b2,logout_btn;
    AlertDialog.Builder builder;

    sqlhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        db = new sqlhelper(this, "FoodDB.sqlite",null, 1);


        String takeusername = getIntent().getStringExtra("username1");


        t1 = findViewById(R.id.profile_username);
        t2 = findViewById(R.id.profile_email);
        t3 = findViewById(R.id.profile_contactNumber);
        t4 = findViewById(R.id.profile_password);
        logout_btn = findViewById(R.id.logout_btn);

   //     t1.setText(takeusername);


        //assign profile details according to the username
        Cursor cursor1 = db.getUserData(takeusername);

        while (cursor1.moveToNext()){
            String user = cursor1.getString(0);
            String email = cursor1.getString(1);
            String con = cursor1.getString(2);
            String pass = cursor1.getString(3);

            t1.setText(user);
            t2.setText(email);
            t3.setText(con);
            t4.setText(pass);

        }

        String username1 = t1.getText().toString();
        String email1 = t2.getText().toString();
        String contact1 = t3.getText().toString();
        String password1 = t4.getText().toString();

        b1 = findViewById(R.id.profile_edit_btn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //send values to update UI
                Intent intent = new Intent(userProfile.this, update_user_profile.class);
                intent.putExtra("username11", username1);
                intent.putExtra("email11", email1);
                intent.putExtra("contact11", contact1);
                intent.putExtra("password11", password1);
                startActivity(intent);

            }
        });


        b2 = findViewById(R.id.profile_delete_btn);
        builder = new AlertDialog.Builder(this);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Alert!!!")
                        .setMessage("Confirm Delete")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startup_screen.sqliteHelper.deleteUserData(username1);
                                Toast.makeText(getApplicationContext(), "Account Deleted Successfully!!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(userProfile.this,startup_screen.class);
                                startActivity(intent);
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                    .show();
            }
        });


        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userProfile.this,startup_screen.class);
                startActivity(intent);
            }
        });
    }

}