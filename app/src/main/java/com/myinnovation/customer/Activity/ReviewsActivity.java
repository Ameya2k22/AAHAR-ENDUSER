package com.myinnovation.customer.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.myinnovation.customer.databinding.ActivityReviewsBinding;

public class ReviewsActivity extends AppCompatActivity {

    ActivityReviewsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}