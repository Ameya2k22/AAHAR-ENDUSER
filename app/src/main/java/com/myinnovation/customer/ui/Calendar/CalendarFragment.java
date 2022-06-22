package com.myinnovation.customer.ui.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.FragmentCalendarBinding;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnNavigationButtonClickedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CalendarFragment extends Fragment implements OnNavigationButtonClickedListener {

    private FragmentCalendarBinding binding;
    private HashMap<Integer, Object> dateHashMap1 = new HashMap<>();
    HashMap<Object, Property> descHashMap = new HashMap<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);

        binding.customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.PREVIOUS, this);
        binding.customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.NEXT, this);

        Property defaultProperty = new Property();
        defaultProperty.layoutResource = R.layout.absent_view;
        defaultProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("default", defaultProperty);

        Property currentProperty = new Property();
        currentProperty.layoutResource = R.layout.current_view;
        currentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("current", currentProperty);

        Property presentProperty = new Property();
        presentProperty.layoutResource = R.layout.present_view;
        presentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("Present", presentProperty);

        binding.customCalendar.setMapDescToProp(descHashMap);

        // Initialize date hashmap
        HashMap<Integer,Object> dateHashmap=new HashMap<>();

        // initialize calendar
        Calendar calendar=  Calendar.getInstance();
        dateHashmap.put(calendar.get(Calendar.DAY_OF_MONTH),"current");
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));

        binding.presentDay.setText(String.valueOf(0));
        FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(year).child(month).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    String ans = snapshot1.getKey();
                    assert ans != null;
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(year).child(month).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            String ans1 = snapshot2.getValue(String.class);
                            dateHashMap1.put(Integer.valueOf(ans), ans1);
                            binding.customCalendar.setDate(calendar, dateHashMap1);
                            binding.presentDay.setText(String.valueOf(dateHashMap1.size()));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public Map<Integer, Object>[] onNavigationButtonClicked(int whichButton, Calendar newMonth) {

        Map<Integer, Object>[] dateMap = new Map[2];

//        FirebaseDatabase.getInstance().getReference().child("Customer").child("Attendance").child(FirebaseAuth.getInstance().getUid())
        Calendar calendar2 = Calendar.getInstance();
        if (Calendar.getInstance().get(Calendar.YEAR) == newMonth.get(Calendar.YEAR)) {
            switch (newMonth.get(Calendar.MONTH)) {
                case Calendar.JANUARY:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.JANUARY);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.JANUARY)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.JANUARY)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.FEBRUARY:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.FEBRUARY);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.FEBRUARY)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.FEBRUARY)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.MARCH:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.MARCH);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.MARCH)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.MARCH)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.APRIL:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.APRIL);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.APRIL)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.APRIL)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.MAY:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.MAY);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.MAY)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.MAY)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.JUNE:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.JUNE);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.JUNE)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.JUNE)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        if(dateMap[0].size() == 0){
                                            binding.presentDay.setText(String.valueOf(0));
                                        } else{
                                            binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.JULY:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.JULY);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.JULY)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.JULY)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        if(dateMap[0].size() == 0){
                                            binding.presentDay.setText(String.valueOf(0));
                                        } else{
                                            binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.AUGUST:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.AUGUST);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.AUGUST)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.AUGUST)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        Toast.makeText(getActivity(), String.valueOf(dateMap[0].size()), Toast.LENGTH_LONG).show();
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.SEPTEMBER:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.SEPTEMBER);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.SEPTEMBER)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.SEPTEMBER)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.OCTOBER:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.OCTOBER);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.OCTOBER)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.OCTOBER)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.NOVEMBER:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.NOVEMBER);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.NOVEMBER)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.NOVEMBER)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;

                case Calendar.DECEMBER:
                    dateMap[0] = new HashMap<>();
                    calendar2.set(Calendar.MONTH, Calendar.DECEMBER);
                    binding.presentDay.setText(String.valueOf(0));
                    FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.DECEMBER)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                String ans = snapshot1.getKey();
                                assert ans != null;
                                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Attendance").child(FirebaseAuth.getInstance().getUid()).child(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))).child(String.valueOf(Calendar.DECEMBER)).child(ans).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        Object val = snapshot2.getValue(String.class);
                                        dateMap[0].put(Integer.valueOf(ans), val);
                                        binding.customCalendar.setDate(calendar2, dateMap[0]);
                                        binding.presentDay.setText(String.valueOf(dateMap[0].size()));
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
                    dateMap[1] = null;
                    break;


                default:
                    dateMap[0] = null;
                    dateMap[1] = null;
            }


        }

        return dateMap;
    }
}