package com.myinnovation.customer.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.myinnovation.customer.databinding.ActivityRatingBinding;

public class RatingActivity extends AppCompatActivity {

    ActivityRatingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}