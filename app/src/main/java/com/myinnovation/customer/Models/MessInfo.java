package com.myinnovation.customer.Models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.R;

public class MessInfo extends AppCompatActivity {
    TextView mess_email, mess_location, mess_name, monthlyPrice, owner_name, specialDishes;
    Button Join;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_info);

        Intent startIntent = getIntent();
        String key = startIntent.getStringExtra("Key");

        mess_location = findViewById(R.id.mess_location);
        mess_name = findViewById(R.id.mess_name);
        monthlyPrice = findViewById(R.id.monthlyPrice);
        owner_name = findViewById(R.id.owner_name);
        specialDishes = findViewById(R.id.specialDishes);
        Join = findViewById(R.id.Join);

        database.getReference().child("Customer").child("Mess-Info").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    if(snapshot1.getKey().equals(key)){
                        Customer customer = snapshot1.getValue(Customer.class);
//                        mess_email.setText(customer.getMess_email());
                        mess_name.setText(customer.getMess_name());
                        mess_location.setText(customer.getMess_location());
                        monthlyPrice.setText(customer.getMonthlyPrice());
                        owner_name.setText(customer.getOwner_name());
                        specialDishes.setText(customer.getSpecialDishes());
                    }
                }
                
                Join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        database.getReference().child("EndUser").child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot snapshot1:snapshot.getChildren()){
                                    if(FirebaseAuth.getInstance().getUid().equals(snapshot1.getKey())){
                                        User user = snapshot1.getValue(User.class);
                                        user.setMess_id(key);
                                        StudentInfo studentInfo = new StudentInfo();
                                        studentInfo.setStudentID(FirebaseAuth.getInstance().getUid());
                                        studentInfo.setDayRemaining(30);
                                        database.getReference().child("EndUser").child("Details").child(FirebaseAuth.getInstance().getUid()).setValue(user);
                                        database.getReference().child("Customer").child("Students").child(key).push().setValue(studentInfo);
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}