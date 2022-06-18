package com.myinnovation.customer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.myinnovation.customer.Customer;
import com.myinnovation.customer.MessInfo;
import com.myinnovation.customer.R;

import java.util.ArrayList;

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.VH> {
    private ArrayList<Customer> customer;
    Context context;

    public MessAdapter(ArrayList<Customer> customer, Context context) {
        this.customer = customer;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.mess_list, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String mess_name, loc;
        holder.MessName.setText(customer.get(position).getMess_name());
        holder.Location.setText(customer.get(position).getLocation());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MessInfo.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customer.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView MessName, Location, Email, monthlyPrice, owner_name, specialDishes;
        ImageView image;
        ConstraintLayout layout;
        public VH(@NonNull View itemView) {
            super(itemView);
            MessName = itemView.findViewById(R.id.MessName);
            Location = itemView.findViewById(R.id.Location);
            image = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
