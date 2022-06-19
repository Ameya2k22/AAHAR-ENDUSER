package com.myinnovation.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Activity.SignUp;

import java.util.ArrayList;

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

        mess_email = findViewById(R.id.mess_email);
        mess_location = findViewById(R.id.mess_location);
        mess_name = findViewById(R.id.mess_name);
        monthlyPrice = findViewById(R.id.monthlyPrice);
        owner_name = findViewById(R.id.owner_name);
        specialDishes = findViewById(R.id.specialDishes);
        Join = findViewById(R.id.Join);

        database.getReference().child("Customer").child("Mess-Info").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Customer customerInfo = snapshot1.getValue(Customer.class);
                    mess_email.setText(customerInfo.getMess_email());
                    mess_location.setText(customerInfo.getMess_location());
                    mess_name.setText(customerInfo.getMess_name());
                    monthlyPrice.setText(customerInfo.getMonthlyPrice());
                    owner_name.setText(customerInfo.getOwner_name());
                    specialDishes.setText(customerInfo.getSpecialDishes());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    Join.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            database.getReference().child("Customer").child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String customer_id = snapshot1.getKey();
                        user.setCustomer_id(customer_id);
                    }
                    String id = auth.getUid();
                    database.getReference().child("EndUser").child("Details").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MessInfo.this, "Joined", Toast.LENGTH_SHORT).show();
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