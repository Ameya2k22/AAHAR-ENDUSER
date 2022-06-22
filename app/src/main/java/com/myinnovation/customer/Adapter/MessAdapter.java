package com.myinnovation.customer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myinnovation.customer.Activity.MessDetailActivity;
import com.myinnovation.customer.Models.Customer;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.SingleMessLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.VH> {
    private ArrayList<Customer> customerList;
    Context context;

    public MessAdapter(ArrayList<Customer> customer, Context context) {
        this.customerList = customer;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_mess_layout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Customer customer = customerList.get(position);
        holder.binding.MessName.setText(customer.getMess_name());
        holder.binding.Location.setText(customer.getMess_location());

        Picasso.get()
                .load(customer.getImage())
                .placeholder(R.drawable.aahar_logo)
                .into(holder.binding.image);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MessDetailActivity.class);
            intent.putExtra("KEY", customer.getCustomer_id());
            view.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return customerList.size();
    }


    public static class VH extends RecyclerView.ViewHolder{
        SingleMessLayoutBinding binding;
        public VH(@NonNull View itemView) {
            super(itemView);
            binding = SingleMessLayoutBinding.bind(itemView);
        }
    }
}
