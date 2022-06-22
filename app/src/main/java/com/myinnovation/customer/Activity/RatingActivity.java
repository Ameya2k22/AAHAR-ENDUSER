package com.myinnovation.customer.Activity;

import android.os.Bundle;
import android.view.View;
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
import com.myinnovation.customer.Models.Notification;
import com.myinnovation.customer.databinding.ActivityRatingBinding;

import java.util.Objects;

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
                if(snapshot.exists()){
                    String id = snapshot.getValue(String.class);
<<<<<<< HEAD

                    FirebaseDatabase.getInstance().getReference().child("Customer").child("Ratings").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String rating1 = snapshot.getValue(String.class);
                            assert rating1 != null;
                            binding.ratingBar.setRating(Float.parseFloat(rating1));

                            messRatingValue = binding.ratingBar.getRating();

                            binding.editRatingBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ratingValue = binding.ratingBar.getRating();

=======
                    FirebaseDatabase.getInstance().getReference().child("Customer").child("Ratings").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                String rating1 = snapshot.getValue(String.class);
                                assert rating1 != null;
                                binding.ratingBar.setRating(Float.parseFloat(rating1));

                                messRatingValue = binding.ratingBar.getRating();

                                binding.editRatingBtn.setOnClickListener(v -> {
                                    ratingValue = binding.ratingBar.getRating();

>>>>>>> origin
                                    // now take average of messRatingValue and ratingValue
                                    float avg_rating = (messRatingValue + ratingValue)/2.0f;

                                    if(avg_rating > 5.0){
                                        avg_rating = 5.0F;
                                    }
                                    String rating = String.valueOf(avg_rating);
<<<<<<< HEAD

                                    // store this value in the mess database.
                                    FirebaseDatabase.getInstance().getReference().child("Customer").child("Ratings").child(id).setValue(rating);
                                    Notification notification = new Notification();
                                    notification.setNotificationType("Rating");
                                    notification.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                    FirebaseDatabase.getInstance().getReference("Customer").child("Notification").child(id).setValue(notification);
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
=======

                                    // store this value in the mess database.
                                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(FirebaseAuth.getInstance().getUid()).child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String id = snapshot.getValue(String.class);

                                            assert id != null;
                                            FirebaseDatabase.getInstance().getReference().child("Customer").child("Ratings").child(id).setValue(rating).addOnCompleteListener(task -> {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(RatingActivity.this, "Rating Updated Successfully", Toast.LENGTH_SHORT).show();
                                                    Notification notification = new Notification();
                                                    notification.setNotificationType("Rating");
                                                    notification.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                                    FirebaseDatabase.getInstance().getReference("Customer").child("Notification").child(id).setValue(notification).addOnCompleteListener(task1 -> {

                                                    });
                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                });
                            }
                            else{
                                Toast.makeText(RatingActivity.this, "This mess is not rated yet!!!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else{
                    Toast.makeText(RatingActivity.this, "This mess is not rated yet!!!", Toast.LENGTH_LONG).show();
                }

>>>>>>> origin
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


<<<<<<< HEAD
=======
    @Override
    protected void onStart() {
        super.onStart();

        // check whether user joined in this mess or not.

        // if yes
        binding.ratingBar.setEnabled(true);
        binding.editRatingBtn.setEnabled(true);

>>>>>>> origin
    }
}