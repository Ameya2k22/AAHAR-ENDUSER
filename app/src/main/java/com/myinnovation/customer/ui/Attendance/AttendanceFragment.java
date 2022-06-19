package com.myinnovation.customer.ui.Attendance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.R;
import com.myinnovation.customer.User;
import com.myinnovation.customer.databinding.FragmentAttendanceBinding;

public class AttendanceFragment extends Fragment {

    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private FragmentAttendanceBinding binding;
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Button yes, no;
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);

        yes.setEnabled(false);
        no.setEnabled(false);

        Toast.makeText(getActivity(), "Hello World", Toast.LENGTH_SHORT).show();



        return view;
    }
}