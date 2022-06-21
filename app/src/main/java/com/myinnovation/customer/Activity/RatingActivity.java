package com.myinnovation.customer.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myinnovation.customer.databinding.ActivityRatingBinding;

public class RatingActivity extends AppCompatActivity {

    ActivityRatingBinding binding;
    private float ratingValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get rating value from database and set to rating bar.

        binding.editRatingBtn.setOnClickListener(v -> {
            ratingValue = binding.ratingBar.getRating();
            Toast.makeText(RatingActivity.this, "Rating = " + ratingValue, Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        // check whether user joined in this mess or not.

        // if yes
        binding.ratingBar.setEnabled(true);
        binding.editRatingBtn.setEnabled(true);
        // else
//        binding.ratingBar.setEnabled(false);
//        binding.editRatingBtn.setEnabled(false);
    }
}