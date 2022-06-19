package com.myinnovation.customer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.myinnovation.customer.Customer;
import com.myinnovation.customer.R;

import java.util.ArrayList;

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.VH> {
    private ArrayList<Customer> customerList;
    Context context;
    private RecyclerViewClickListner recyclerViewClickListner;

    public MessAdapter(ArrayList<Customer> customer, Context context, RecyclerViewClickListner recyclerViewClickListner) {
        this.customerList = customer;
        this.context = context;
        this.recyclerViewClickListner = recyclerViewClickListner;
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
        Customer customer = customerList.get(position);
        holder.MessName.setText(customer.getMess_name());
        holder.Location.setText(customer.getMess_location());

    }


    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView MessName, Location, Email, monthlyPrice, owner_name, specialDishes;
        ImageView image;
        ConstraintLayout layout;
        public VH(@NonNull View itemView) {
            super(itemView);
            MessName = itemView.findViewById(R.id.MessName);
            Location = itemView.findViewById(R.id.Location);
            image = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.layout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListner.onClick(view, getAdapterPosition());
        }
    }
}
