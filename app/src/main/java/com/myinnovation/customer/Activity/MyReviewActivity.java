package com.myinnovation.customer.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import com.myinnovation.customer.Models.Reviews;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.ActivityMyReviewBinding;

public class MyReviewActivity extends AppCompatActivity {

    ActivityMyReviewBinding binding;
    EditText reviewTitle, reviewBody;
    TextView addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyReviewBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_my_review);

        // if user is already in the mess and already given a review then load original review first and store in appropriate fields.

        reviewTitle = findViewById(R.id.review_title);
        reviewBody = findViewById(R.id.review_message);
        addButton = findViewById(R.id.add_review);

        FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(FirebaseAuth.getInstance().getUid()).child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id = snapshot.getValue(String.class);

                FirebaseDatabase.getInstance().getReference().child("Customer").child("Reviews").child(id).child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Reviews reviews = snapshot.getValue(Reviews.class);
                            reviewTitle.setText(reviews.getReviewTitle());
                            reviewBody.setText(reviews.getReviewBody());
                        }
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(FirebaseAuth.getInstance().getUid()).child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String id = snapshot.getValue(String.class);
                        String title = reviewTitle.getText().toString();
                        String body = reviewBody.getText().toString();

                        Reviews reviews = new Reviews(title, body);
                        FirebaseDatabase.getInstance().getReference().child("Customer").child("Reviews").child(id).child(FirebaseAuth.getInstance().getUid()).setValue(reviews).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MyReviewActivity.this, "Review Added successfully", Toast.LENGTH_SHORT).show();
                                    Notification notification = new Notification();
                                    notification.setNotificationType("Review");
                                    notification.setNotificationBy(FirebaseAuth.getInstance().getUid());

                                    FirebaseDatabase.getInstance().getReference("Customer").child("Notification").child(id).push().setValue(notification).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}