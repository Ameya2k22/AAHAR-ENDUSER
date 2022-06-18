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
import com.myinnovation.customer.Customer;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.FragmentCalendarBinding;
import com.myinnovation.customer.databinding.FragmentExploreBinding;

import java.util.ArrayList;


public class ExploreFragment extends Fragment {

    RecyclerView recyclerView;
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

    private FragmentExploreBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentExploreBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        recyclerView = view.findViewById(R.id.recyclerView);
        customers = new ArrayList<>();

        messAdapter = new MessAdapter(customers, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(messAdapter);

        database.getReference().child("Customer").child("Mess-Info").child("mess-name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Customer customerInfo = snapshot1.getValue(Customer.class);
                    customers.add(customerInfo);
                }
                messAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.getReference().child("Customer").child("Mess-Info").child("mess-location").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Customer customerInfo = snapshot1.getValue(Customer.class);
                    customers.add(customerInfo);
                }
                messAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;


    }
}