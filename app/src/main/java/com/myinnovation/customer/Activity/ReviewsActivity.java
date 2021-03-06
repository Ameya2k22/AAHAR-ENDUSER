package com.myinnovation.customer.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.databinding.ActivityReviewsBinding;

public class ReviewsActivity extends AppCompatActivity {

    TextView mess_name;
    ActivityReviewsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mess_name = binding.name;
        // get all reviews and add to recyclerview.



        // if user is in registered in this mess then only he/she can give review.
        binding.myReview.setOnClickListener(v -> {
            startActivity(new Intent(ReviewsActivity.this, MyReviewActivity.class));
        });


    }

    private class FirebaseThread extends Thread{

        @Override
        public void run() {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // check whether user is registered in this mess or not.
        // if yes
        binding.myReview.setEnabled(true);

        // else
//        binding.myReview.setEnabled(false);
    }
}