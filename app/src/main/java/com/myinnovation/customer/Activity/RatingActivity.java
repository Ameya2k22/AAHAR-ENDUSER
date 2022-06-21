package com.myinnovation.customer.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myinnovation.customer.databinding.ActivityRatingBinding;

public class RatingActivity extends AppCompatActivity {

    ActivityRatingBinding binding;
    private float ratingValue = 0;
    private float messRatingValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ratingBar.setRating(3.75f);
        // get rating value from database and set to rating bar.
        // store this value to messRatingValue variable.
        messRatingValue = 4.5f;

        binding.editRatingBtn.setOnClickListener(v -> {
            ratingValue = binding.ratingBar.getRating();

            // now take average of messRatingValue and ratingValue
            float avg_rating = (messRatingValue + ratingValue)/2.0f;

            if(avg_rating > 5.0){
                avg_rating = 5.0F;
            }
            String rating = String.valueOf(avg_rating);

            // store this value in the mess database.
            Toast.makeText(RatingActivity.this, "Rating = " + rating, Toast.LENGTH_SHORT).show();

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