package com.myinnovation.customer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.myinnovation.customer.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        image = findViewById(R.id.image);
        image.animate().translationY(1200).setDuration(2000);


        new Handler().postDelayed(() -> {
            Intent i = new Intent(getApplicationContext(),  SignUpActivity.class);
            startActivity(i);
        },4000);
    }
}