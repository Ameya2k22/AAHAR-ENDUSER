package com.myinnovation.customer.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Models.Notification;
import com.myinnovation.customer.Models.User;
import com.myinnovation.customer.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.VH> {
    ArrayList<Notification> notifications;
    Context context;

    public NotificationAdapter(ArrayList<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.notifivations, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Notification notification = notifications.get(position);

        String type = notification.getNotificationType();
        FirebaseDatabase.getInstance().getReference().child("Customer").child("Notification").child(notification.getNotificationType()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (type.equals("Joined")) {
                    holder.notification.setText(user.getName() + " joined the mess");
                } else if (type.equals("Review")) {
                    holder.notification.setText(user.getName() + " reviewed the mess");
                } else if (type.equals("Rating")) {
                    holder.notification.setText(user.getName() + " gives rating to the mess");
                } else if (type.equals("Payment")) {
                    holder.notification.setText(user.getName() + " has done the payment");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView notification;
        public VH(@NonNull View itemView) {
            super(itemView);

            notification = itemView.findViewById(R.id.notificationText);

        }
    }
}
