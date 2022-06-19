package com.myinnovation.customer.ui.Attendance;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Customer;
import com.myinnovation.customer.R;
import com.myinnovation.customer.User;
import com.myinnovation.customer.databinding.FragmentAttendanceBinding;

public class AttendanceFragment extends Fragment {

    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, rButton;
    private Button submit;

    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private FragmentAttendanceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        radioGroup = view.findViewById(R.id.attendance);
        radioButton1 = view.findViewById(R.id.yes);
        radioButton2 = view.findViewById(R.id.no);
        submit = view.findViewById(R.id.submit);

        radioButton1.setEnabled(false);
        radioButton2.setEnabled(false);
        submit.setEnabled(false);


        FirebaseDatabase.getInstance().getReference("EndUser").child("Details").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    if(snapshot1.getKey().equals(FirebaseAuth.getInstance().getUid())){
                        User user = snapshot1.getValue(User.class);
                        String id = user.getMess_id();

                        FirebaseDatabase.getInstance().getReference("Customer").child("Attendance").child(id).child("SessionOn").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                boolean ans = snapshot.getValue(Boolean.class);
                                if(ans){
                                    radioButton1.setEnabled(true);
                                    radioButton2.setEnabled(true);
                                    submit.setEnabled(true);

                                    submit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            int selectedID = radioGroup.getCheckedRadioButtonId();
                                            rButton =  (RadioButton) view.findViewById(selectedID);
                                            if(selectedID == R.id.yes){
                                                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                                                FirebaseDatabase.getInstance().getReference().child("Customer").child("Day Wise Attendance").child(date).
                                            }
                                            else if(selectedID == R.id.no){

                                            }

                                        }
                                    });
                                }
                                else{
                                    radioButton1.setEnabled(false);
                                    radioButton2.setEnabled(false);
                                    submit.setEnabled(false);
                                }
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

        return view;
    }
}