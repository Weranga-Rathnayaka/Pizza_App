package com.pizzahut.pizzaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class add_product extends AppCompatActivity {

    private EditText edtname,edtprice,edttype,edtdescription;
    private ImageView imageView;
    private Button btnChoose,btnAdd;


 //   AwesomeValidation awesomeValidation;

    final int REQUEST_CODE_GALLERY = 999;

    public static sqlhelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);



        init();

        sqliteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        sqliteHelper.queryData("CREATE TABLE IF NOT EXISTS FOOD (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR, type VARCHAR, description VARCHAR, image BLOG)");



        //NOW GET IMAGE IN CHOOSE BUTTON

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions
                        (add_product.this,
                                new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_CODE_GALLERY   );
            }
        });

   //     awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
/*
        awesomeValidation.addValidation(this, R.id.admin_food_name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.admin_food_price, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.admin_food_type, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.admin_food_description, RegexTemplate.NOT_EMPTY,R.string.invalid_name);

*/


            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String s1 = edtname.getText().toString();
                    String s2 = edtprice.getText().toString();
                    String s3 = edttype.getText().toString();
                    String s4 = edtdescription.getText().toString();



                    if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")) {
                        Toast.makeText(getApplicationContext(), "Field Empty", Toast.LENGTH_SHORT).show();
                    }else {
                        if (s3.equals("Large")||s3.equals("Medium")||s3.equals("Small")) {
                            try {
                                sqliteHelper.insertdata(
                                        edtname.getText().toString().trim(),
                                        edtprice.getText().toString().trim(),
                                        edttype.getText().toString().trim(),
                                        edtdescription.getText().toString().trim(),
                                        imageViewToByte(imageView));

                                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

                                edtname.setText("");
                                edtprice.setText("");
                                edttype.setText("");
                                edtdescription.setText("");
                                imageView.setImageResource(R.drawable.burger);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Food Type Should Be Large, Medium or Small", Toast.LENGTH_SHORT).show();
                            edttype.setText("");
                        }
                    }
                }
            });



    }



    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else{

                Toast.makeText(getApplicationContext(),"you do not have permission to access location", Toast.LENGTH_SHORT).show();
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }



    private void init(){

        edtname = findViewById(R.id.admin_food_name);
        edtprice = findViewById(R.id.admin_food_price);
        edttype = findViewById(R.id.admin_food_type);
        edtdescription = findViewById(R.id.admin_food_description);
        btnChoose = findViewById(R.id.admin_food_image_button);
        btnAdd = findViewById(R.id.admin_add);
        imageView = findViewById(R.id.admin_food_image);
    }




}