package com.myinnovation.customer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.myinnovation.customer.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        image = findViewById(R.id.image);
        image.animate().translationY((float) (height/3.0)).setDuration(2000);


        new Handler().postDelayed(() -> {
            Intent i = new Intent(getApplicationContext(),  LoginActivity.class);
            startActivity(i);
        },4000);
    }
}