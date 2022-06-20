package com.myinnovation.customer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myinnovation.customer.Models.Customer;
import com.myinnovation.customer.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public ArrayList<Customer> list;
    Context context;

    public HomeAdapter(ArrayList<Customer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_list, parent, false);
        ViewHolder nVH = new ViewHolder(view);
        return nVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer customer = list.get(position);
        holder.messName.setText(customer.getMess_name());
        holder.messLocation.setText(customer.getMess_location());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView messName, messLocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            messName = itemView.findViewById(R.id.MessName);
            messLocation = itemView.findViewById(R.id.Location);
        }
    }
}
