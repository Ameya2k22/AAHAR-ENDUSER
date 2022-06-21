package com.myinnovation.customer.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.ActivityMyReviewBinding;

public class MyReviewActivity extends AppCompatActivity {

    ActivityMyReviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyReviewBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_my_review);

        // if user is already in the mess and already given a review then load original review first and store in appropriate fields.


        binding.addReview.setOnClickListener(v -> {
            // store review in the database.
            Toast.makeText(MyReviewActivity.this, "Review added successfully", Toast.LENGTH_SHORT).show();
        });
    }
}