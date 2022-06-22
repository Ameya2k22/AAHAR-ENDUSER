package com.myinnovation.customer.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Models.Customer;
import com.myinnovation.customer.Models.Notification;
import com.myinnovation.customer.Models.StudentInfo;
import com.myinnovation.customer.Models.User;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.ActivityMessDetailBinding;

import java.util.Objects;

public class MessDetailActivity extends AppCompatActivity {

    ActivityMessDetailBinding binding;

    TextView mess_email, mess_location, mess_name, monthlyPrice, owner_name, specialDishes, mess_mobile;
    Button Join;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        InitializeFields();

        Intent startIntent = getIntent();
        String key = startIntent.getStringExtra("KEY");

        binding.complaintsAndReviews.setOnClickListener(v -> startActivity(new Intent(this, ReviewsActivity.class)));


        database.getReference().child("Customer").child("Mess-Info").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    if (Objects.equals(snapshot1.getKey(), key)) {
                        Customer customer = snapshot1.getValue(Customer.class);
                        assert customer != null;
                        mess_email.setText(customer.getMess_email());
                        mess_name.setText(customer.getMess_name());
                        mess_mobile.setText(customer.getPhone_no());
                        mess_location.setText(customer.getMess_location());
                        monthlyPrice.setText(customer.getMonthlyPrice());
                        owner_name.setText(customer.getOwner_name());
                        specialDishes.setText(customer.getSpecialDishes());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                        for (DataSnapshot snapshot1 : snapshot2.getChildren()) {
                            if (Objects.equals(FirebaseAuth.getInstance().getUid(), snapshot1.getKey())) {
                                User user = snapshot1.getValue(User.class);
                                assert user != null;
                                user.setMess_id(key);
                                StudentInfo studentInfo = new StudentInfo();
                                studentInfo.setStudentID(FirebaseAuth.getInstance().getUid());
                                studentInfo.setDayRemaining(30);
                                database.getReference().child("EndUser").child("Details").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).setValue(user);
                                database.getReference().child("Customer").child("Students").child(key).push().setValue(studentInfo);
                                String rating = "0";
                                database.getReference().child("Customer").child("Ratings").child(key).setValue(rating);
                                Toast.makeText(MessDetailActivity.this, "Joined", Toast.LENGTH_SHORT).show();
                                Notification notification = new Notification();
                                notification.setNotificationType("Joined");
                                notification.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                FirebaseDatabase.getInstance().getReference("Customer").child("Notification").child(key).push().setValue(notification);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void InitializeFields(){
        mess_email = findViewById(R.id.mess_email);
        mess_location = findViewById(R.id.mess_location);
        mess_name = findViewById(R.id.mess_name);
        monthlyPrice = findViewById(R.id.monthlyPrice);
        owner_name = findViewById(R.id.owner_name);
        specialDishes = findViewById(R.id.specialDishes);
        mess_mobile = findViewById(R.id.mess_mobile);
        Join = findViewById(R.id.Join);
    }
}