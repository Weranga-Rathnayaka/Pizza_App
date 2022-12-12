package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderConfirmation extends AppCompatActivity {

    TextView ViewFoodName,viewFoodType,unitPrice,viewFoodQuantity,viewTotal,viewFoodId;
    ImageView img;
    private Button qMinusBtn, qPlusBtn,btn;
    private int foodQuantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        init();


        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("foodImage");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        img.setImageBitmap(bmp);


        Intent i = getIntent();
        String Name= i.getStringExtra("foodName");
        String price =i.getStringExtra("foodPrice");
        String type = i.getStringExtra("foodNType");
        //startActivities(i);


        ViewFoodName.setText(Name);
        ViewFoodName.setText(Name);
        ViewFoodName.setText(Name);
        viewFoodType.setText(type);
        unitPrice.setText(price);
        viewTotal.setText(price);

        qMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecreaseQuantity();
            }
        });

        qPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncreaseQuantity();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),InsertDelivery.class);

                i.putExtra("foodName1",   ViewFoodName.getText().toString());
                i.putExtra("foodPrice1", unitPrice.getText().toString());
                i.putExtra("foodType1", viewFoodType.getText().toString());
                i.putExtra("qty",viewFoodQuantity.getText().toString());
                startActivity(i);

            }
        });

    }

    private void init(){
        ViewFoodName = findViewById(R.id.viewFoodName);
        viewFoodType = findViewById(R.id.viewFoodType);
        unitPrice = findViewById(R.id.viewUnitPrice);
        viewFoodQuantity =findViewById(R.id.food_quantity);
        qMinusBtn =findViewById(R.id.q_minus_btn);
        qPlusBtn =findViewById(R.id.q_plus_btn);
        viewTotal =findViewById(R.id.totalPriceView);
        btn = findViewById(R.id.btn_confirmation);
        img = findViewById(R.id.order_confirm_image);


    }
    private void DecreaseQuantity()
    { int unitP =Integer.parseInt(unitPrice.getText().toString());
        if(foodQuantity > 1)
        {
            foodQuantity = foodQuantity - 1;
            viewFoodQuantity.setText(String.valueOf(foodQuantity));
            int Total =unitP *foodQuantity;
            String setNewTotal =String.valueOf(Total);
            viewTotal.setText(setNewTotal);



        }
    }

    private void IncreaseQuantity(){
        int unitP =Integer.parseInt(unitPrice.getText().toString());
         {
             foodQuantity = foodQuantity + 1;
             viewFoodQuantity.setText(String.valueOf(foodQuantity));
             int Total =unitP *foodQuantity;
             String setNewTotal =String.valueOf(Total);
             viewTotal.setText(setNewTotal);

    }}

}