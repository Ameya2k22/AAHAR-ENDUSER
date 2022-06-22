package com.myinnovation.customer.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Activity.PaymentActivity;
import com.myinnovation.customer.Activity.RatingActivity;
import com.myinnovation.customer.Activity.ReviewsActivity;
import com.myinnovation.customer.Models.Customer;
import com.myinnovation.customer.databinding.FragmentHomeBinding;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    TextView mess_email, mess_location, mess_name, monthlyPrice, owner_name, specialDishes, mess_mobile;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        FirebaseThread thread = new FirebaseThread();
        thread.start();
        binding.complaintsAndReviews.setOnClickListener(v -> startActivity(new Intent(getContext(), ReviewsActivity.class)));

        binding.PayAmount.setOnClickListener(v -> startActivity(new Intent(getContext(), PaymentActivity.class)));
        binding.ratings.setOnClickListener(v -> startActivity(new Intent(getContext(), RatingActivity.class)));

        mess_email = binding.messEmail;
        mess_location = binding.messLocation;
        mess_name = binding.messName;
        mess_mobile = binding.messMobile;
        owner_name = binding.ownerName;
        monthlyPrice = binding.monthlyPrice;
        specialDishes = binding.specialDishes;


        return binding.getRoot();
    }

    private class FirebaseThread extends Thread{

        @Override
        public void run() {
            FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String id = snapshot.getValue(String.class);
                        database.getReference().child("Customer").child("Mess-Info").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                                        if(Objects.requireNonNull(snapshot1.getKey()).equals(id)){
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

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}