package com.myinnovation.customer.ui.Attendance;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.R;
import com.myinnovation.customer.Models.StudentInfo;
import com.myinnovation.customer.Models.User;
import com.myinnovation.customer.databinding.FragmentAttendanceBinding;

public class AttendanceFragment extends Fragment {

    TextView yesButton, noButton;

    public AttendanceFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private FragmentAttendanceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);

//        radioGroup = view.findViewById(R.id.attendance);
//        radioButton1 = view.findViewById(R.id.yes);
//        radioButton2 = view.findViewById(R.id.no);
//        submit = view.findViewById(R.id.submit);
//
//        radioButton1.setEnabled(false);
//        radioButton2.setEnabled(false);
//        submit.setEnabled(false);
//
//
//        FirebaseDatabase.getInstance().getReference("EndUser").child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot snapshot1: snapshot.getChildren()){
//                    if(snapshot1.getKey().equals(FirebaseAuth.getInstance().getUid())){
//                        User user = snapshot1.getValue(User.class);
//                        String id = user.getMess_id();
//
//                        FirebaseDatabase.getInstance().getReference("Customer").child("Attendance").child(id).child("SessionOn").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                boolean ans = snapshot.getValue(Boolean.class);
//                                if(ans){
//                                    radioButton1.setEnabled(true);
//                                    radioButton2.setEnabled(true);
//                                    submit.setEnabled(true);
//
//                                    submit.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            int selectedID = radioGroup.getCheckedRadioButtonId();
//                                            rButton =  (RadioButton) view.findViewById(selectedID);
//                                            if(selectedID == R.id.yes){
//                                                FirebaseDatabase.getInstance().getReference()
//                                                        .child("EndUser")
//                                                        .child("Details")
//                                                        .child(FirebaseAuth.getInstance().getUid())
//                                                        .child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
//                                                    @Override
//                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                        String mess_id = snapshot.getValue(String.class);
//                                                        FirebaseDatabase.getInstance().getReference()
//                                                                .child("Customer")
//                                                                .child("Students")
//                                                                .child(mess_id)
//                                                                .addListenerForSingleValueEvent(new ValueEventListener() {
//                                                                    @Override
//                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                                        for(DataSnapshot snapshot2:snapshot.getChildren()){
//                                                                            StudentInfo studentInfo = snapshot2.getValue(StudentInfo.class);
//                                                                            if(studentInfo.getStudentID().equals(FirebaseAuth.getInstance().getUid())){
//                                                                                long day = studentInfo.getDayRemaining();
//                                                                                day = day-1;
//                                                                                studentInfo.setDayRemaining(day);
//                                                                                FirebaseDatabase.getInstance().getReference()
//                                                                                        .child("Customer")
//                                                                                        .child("Students")
//                                                                                        .child(mess_id)
//                                                                                        .child(snapshot2.getKey())
//                                                                                        .setValue(studentInfo);
//                                                                            }
//                                                                        }
//                                                                    }
//
//                                                                    @Override
//                                                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                                                    }
//                                                                });
//                                                    }
//
//                                                    @Override
//                                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                                    }
//                                                });
//                                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//                                                FirebaseDatabase.getInstance().getReference()
//                                                        .child("EndUser")
//                                                        .child("Attendance")
//                                                        .child(FirebaseAuth.getInstance().getUid())
//                                                        .child(date).setValue("Present");
//                                            }
//                                            else if(selectedID == R.id.no){
//                                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//                                                FirebaseDatabase.getInstance().getReference()
//                                                        .child("EndUser")
//                                                        .child("Attendance")
//                                                        .child(FirebaseAuth.getInstance().getUid())
//                                                        .child(date).setValue("False");
//                                            }
//                                        }
//                                    });
//                                }
//                                else{
//                                    radioButton1.setEnabled(false);
//                                    radioButton2.setEnabled(false);
//                                    submit.setEnabled(false);
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        yesButton = binding.yes;
        noButton = binding.no;

        yesButton.setEnabled(false);
        noButton.setEnabled(false);

        FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    User user = snapshot1.getValue(User.class);
                    String id = user.getMess_id();

                    FirebaseDatabase.getInstance().getReference().child("Customer").child("Attendance").child(id).child("SessionOn").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean ans = snapshot.getValue(Boolean.class);
                            if(ans){
                                yesButton.setEnabled(true);
                                noButton.setEnabled(true);
                                yesButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("EndUser")
                                                .child("Details")
                                                .child(FirebaseAuth.getInstance().getUid())
                                                .child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String mess_id = snapshot.getValue(String.class);
                                                FirebaseDatabase.getInstance().getReference()
                                                        .child("Customer")
                                                        .child("Students")
                                                        .child(mess_id)
                                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                for(DataSnapshot snapshot2:snapshot.getChildren()){
                                                                    StudentInfo studentInfo = snapshot2.getValue(StudentInfo.class);
                                                                    if(studentInfo.getStudentID().equals(FirebaseAuth.getInstance().getUid())){
                                                                        long day = studentInfo.getDayRemaining();
                                                                        day = day-1;
                                                                        studentInfo.setDayRemaining(day);
                                                                        FirebaseDatabase.getInstance().getReference()
                                                                                .child("Customer")
                                                                                .child("Students")
                                                                                .child(mess_id)
                                                                                .child(snapshot2.getKey())
                                                                                .setValue(studentInfo);
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("EndUser")
                                                .child("Attendance")
                                                .child(FirebaseAuth.getInstance().getUid())
                                                .child(date).setValue("Present");

                                        Toast.makeText(getActivity(), "Attendance Submitted Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                noButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("EndUser")
                                                .child("Attendance")
                                                .child(FirebaseAuth.getInstance().getUid())
                                                .child(date).setValue("Absent");
                                    }
                                });
                            }
                            else{
                                yesButton.setEnabled(false);
                                noButton.setEnabled(false);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}