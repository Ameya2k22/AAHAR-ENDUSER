package com.myinnovation.customer.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.myinnovation.customer.databinding.ActivityMessDetailBinding;

public class MessDetailActivity extends AppCompatActivity {

    ActivityMessDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}