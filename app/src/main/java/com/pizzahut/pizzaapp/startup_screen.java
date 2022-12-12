package com.pizzahut.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class startup_screen extends AppCompatActivity {

    Animation topanim,bottomanim;
    ImageView imageView;
    TextView textView1,textView2;

    private static int SPLASH_SCREEN = 5000;


    public static sqlhelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_startup_screen);


        sqliteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);


        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        imageView = findViewById(R.id.startup_image);
        textView1 = findViewById(R.id.startup_text1);
        textView2 = findViewById(R.id.startup_text2);

        imageView.setAnimation(topanim);
        textView1.setAnimation(bottomanim);
        textView2.setAnimation(bottomanim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(startup_screen.this, Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}