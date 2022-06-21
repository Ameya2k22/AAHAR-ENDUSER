package com.myinnovation.customer.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

        // get rating value from database and set to rating bar.

        binding.editRatingBtn.setOnClickListener(v -> {
            ratingValue = binding.ratingBar.getRating();
            Toast.makeText(RatingActivity.this, "Rating = " + ratingValue, Toast.LENGTH_SHORT).show();
        });


        FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(FirebaseAuth.getInstance().getUid()).child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id = snapshot.getValue(String.class);

                FirebaseDatabase.getInstance().getReference().child("Customer").child("Ratings").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String rating1 = snapshot.getValue(String.class);
                        binding.ratingBar.setRating(Float.valueOf(rating1));

                        messRatingValue = binding.ratingBar.getRating();

                        binding.editRatingBtn.setOnClickListener(v -> {
                            ratingValue = binding.ratingBar.getRating();

                            // now take average of messRatingValue and ratingValue
                            float avg_rating = (messRatingValue + ratingValue)/2.0f;

                            if(avg_rating > 5.0){
                                avg_rating = 5.0F;
                            }
                            String rating = String.valueOf(avg_rating);

                            // store this value in the mess database.
                            FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(FirebaseAuth.getInstance().getUid()).child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String id = snapshot.getValue(String.class);

                                    FirebaseDatabase.getInstance().getReference().child("Customer").child("Ratings").child(id).setValue(rating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RatingActivity.this, "Rating Updated Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
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
        binding.ratingBar.setEnabled(true);
        binding.editRatingBtn.setEnabled(true);
    }
}