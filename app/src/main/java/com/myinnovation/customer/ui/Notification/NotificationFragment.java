package com.myinnovation.customer.ui.Notification;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Adapter.NotificationAdapter;
import com.myinnovation.customer.Models.Notification;
import com.myinnovation.customer.databinding.FragmentNotificationBinding;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;
    ArrayList<Notification> notifications = new ArrayList<>();
    NotificationAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        FirebaseThread thread = new FirebaseThread(getActivity());
        thread.start();
        RecyclerView recyclerView = binding.recyclerView;
        adapter = new NotificationAdapter(notifications, getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }


    private class FirebaseThread extends Thread{

        private Activity activity;
        FirebaseThread(Activity activity){
            this.activity = activity;
        }
        @Override
        public void run() {
            FirebaseDatabase.getInstance().getReference()
                    .child("Customer")
                    .child("Notification")
                    .child(FirebaseAuth.getInstance().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                    Notification notification = snapshot1.getValue(Notification.class);
                                    notification.setNotificationId(snapshot1.getKey());
                                    notifications.add(notification);
                                }
                                adapter.notifyDataSetChanged();
                            } else{
                                Toast.makeText(activity.getApplicationContext(), "No Notification Yet!", Toast.LENGTH_SHORT).show();
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