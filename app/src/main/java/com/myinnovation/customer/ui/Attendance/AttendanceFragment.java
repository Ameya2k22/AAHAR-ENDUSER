package com.myinnovation.customer.ui.Attendance;

import android.os.Build;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);

        yesButton = binding.yes;
        noButton = binding.no;

        binding.attendanceDate2.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        binding.attendanceDay2.setText(LocalDate.now().getDayOfWeek().name());

        binding.attendanceDate.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        binding.attendanceDay.setText(LocalDate.now().getDayOfWeek().name());

        yesButton.setEnabled(false);
        noButton.setEnabled(false);

        FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    assert user != null;
                    String id = user.getMess_id();

                    FirebaseDatabase.getInstance().getReference().child("Customer").child("Attendance").child(id).child("SessionOn").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                boolean ans = snapshot.getValue(Boolean.class);
                                if(ans){
                                    binding.AttendanceVerificationCardview.setVisibility(View.GONE);
                                    binding.AttendanceCardView.setVisibility(View.VISIBLE);
                                    yesButton.setEnabled(true);
                                    noButton.setEnabled(true);
                                    yesButton.setOnClickListener(view -> {
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("EndUser")
                                                .child("Details")
                                                .child(FirebaseAuth.getInstance().getUid())
                                                .child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot12) {
                                                if(snapshot12.exists()){
                                                    String mess_id = snapshot12.getValue(String.class);
                                                    assert mess_id != null;
                                                    FirebaseDatabase.getInstance().getReference()
                                                            .child("Customer")
                                                            .child("Students")
                                                            .child(mess_id)
                                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot12) {
                                                                    if(snapshot12.exists()){
                                                                        for(DataSnapshot snapshot2: snapshot12.getChildren()){
                                                                            StudentInfo studentInfo = snapshot2.getValue(StudentInfo.class);
                                                                            assert studentInfo != null;
                                                                            if(studentInfo.getStudentID().equals(FirebaseAuth.getInstance().getUid())){
                                                                                long day = studentInfo.getDayRemaining();
                                                                                day = day-1;
                                                                                studentInfo.setDayRemaining(day);
                                                                                FirebaseDatabase.getInstance().getReference()
                                                                                        .child("Customer")
                                                                                        .child("Students")
                                                                                        .child(mess_id)
                                                                                        .child(Objects.requireNonNull(snapshot2.getKey()))
                                                                                        .setValue(studentInfo);
                                                                            }
                                                                        }
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

                                        Calendar calendar = Calendar.getInstance();
                                        String year = String.valueOf(calendar.get(Calendar.YEAR));
                                        String month = String.valueOf(calendar.get(Calendar.MONTH));
                                        String day = String.valueOf(calendar.get(Calendar.DATE));
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("EndUser")
                                                .child("Attendance")
                                                .child(FirebaseAuth.getInstance().getUid())
                                                .child(year).child(month).child(day).setValue("Present");

                                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                        FirebaseDatabase.getInstance().getReference().child("Customer").child("AttendanceByDay").child(id).child(date).child(FirebaseAuth.getInstance().getUid()).setValue(true);

                                        Toast.makeText(getActivity(), "Attendance Submitted Successfully", Toast.LENGTH_SHORT).show();
                                        binding.AttendanceVerificationCardview.setVisibility(View.VISIBLE);
                                        binding.AttendanceCardView.setVisibility(View.GONE);
                                    });

                                    noButton.setOnClickListener(view -> {

                                        Calendar calendar = Calendar.getInstance();
                                        String year = String.valueOf(calendar.get(Calendar.YEAR));
                                        String month = String.valueOf(calendar.get(Calendar.MONTH));
                                        String day = String.valueOf(calendar.get(Calendar.DATE));
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("EndUser")
                                                .child("Attendance")
                                                .child(FirebaseAuth.getInstance().getUid())
                                                .child(year).child(month).child(day).setValue("Absent");
                                    });
                                }
                                else{
                                    yesButton.setEnabled(false);
                                    noButton.setEnabled(false);
                                }

                                FirebaseDatabase.getInstance().getReference().child("Customer").child("Students").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.O)
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot4) {
                                        if(snapshot4.exists()){
                                            for(DataSnapshot snapshot1:snapshot4.getChildren()){
                                                StudentInfo studentInfo = snapshot1.getValue(StudentInfo.class);
                                                assert studentInfo != null;
                                                binding.daysRemaining.setText(String.valueOf(studentInfo.getDayRemaining()-1));
                                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(studentInfo.getStudentID()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        String name1 = snapshot.getValue(String.class);
                                                        binding.username.setText(name1);
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                            else{
                                Toast.makeText(getActivity(), "You aren't joined to any mess yet", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else{
                    Toast.makeText(getActivity(), "You aren't joined to any mess yet",Toast.LENGTH_LONG).show();
                    binding.AttendanceCardView.setVisibility(View.INVISIBLE);
                    binding.AttendanceVerificationCardview.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}