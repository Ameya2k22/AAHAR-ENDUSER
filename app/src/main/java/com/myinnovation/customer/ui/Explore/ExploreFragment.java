package com.myinnovation.customer.ui.Explore;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Adapter.MessAdapter;
import com.myinnovation.customer.Customer;
import com.myinnovation.customer.MessInfo;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.FragmentExploreBinding;

import java.util.ArrayList;


public class ExploreFragment extends Fragment implements MessAdapter.RecyclerViewClickListner {

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

        messAdapter = new MessAdapter(customers, getContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(messAdapter);

        database.getReference().child("Customer").child("Mess-Info").addListenerForSingleValueEvent(new ValueEventListener() {
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

    @Override
    public void onClick(View v, int position) {
        Intent intent = new Intent(getContext(), MessInfo.class);
        startActivity(intent);
    }
}