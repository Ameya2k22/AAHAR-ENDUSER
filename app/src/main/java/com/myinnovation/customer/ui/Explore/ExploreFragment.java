package com.myinnovation.customer.ui.Explore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Adapter.MessAdapter;
import com.myinnovation.customer.Models.Customer;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.FragmentExploreBinding;

import java.util.ArrayList;


public class ExploreFragment extends Fragment {

    MessAdapter messAdapter;
    ArrayList<Customer> customers;
    FirebaseAuth auth;
    FirebaseDatabase database;

    public ExploreFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentExploreBinding binding = FragmentExploreBinding.inflate(inflater, container, false);
        FirebaseThread thread = new FirebaseThread();
        thread.start();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        customers = new ArrayList<>();

        messAdapter = new MessAdapter(customers, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setNestedScrollingEnabled(true);
        binding.recyclerView.setAdapter(messAdapter);

        return binding.getRoot();


    }

    private class FirebaseThread extends Thread{
        @Override
        public void run() {
            FirebaseDatabase.getInstance().getReference().child("Customer").child("Mess-Info").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            Customer customerInfo = snapshot1.getValue(Customer.class);
                            assert customerInfo != null;
                            customerInfo.setCustomer_id(snapshot1.getKey());
                            customers.add(customerInfo);
                        }
                        messAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}