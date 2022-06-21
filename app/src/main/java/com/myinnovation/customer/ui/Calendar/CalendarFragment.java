package com.myinnovation.customer.ui.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.FragmentCalendarBinding;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.OnNavigationButtonClickedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CalendarFragment extends Fragment implements OnNavigationButtonClickedListener {

    private FragmentCalendarBinding binding;

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
        descHashMap.put("present", presentProperty);

        binding.customCalendar.setMapDescToProp(descHashMap);

        // Initialize date hashmap
        HashMap<Integer,Object> dateHashmap=new HashMap<>();

        // initialize calendar
        Calendar calendar=  Calendar.getInstance();

        // Put values
        dateHashmap.put(calendar.get(Calendar.DAY_OF_MONTH),"current");
        dateHashmap.put(1,"present");
        dateHashmap.put(3,"present");
        dateHashmap.put(20,"present");
        dateHashmap.put(5,"present");
        dateHashmap.put(12,"present");
        dateHashmap.put(21,"present");


        // set date
        binding.customCalendar.setDate(calendar,dateHashmap);

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


        if (Calendar.getInstance().get(Calendar.YEAR) == newMonth.get(Calendar.YEAR)) {
            switch (newMonth.get(Calendar.MONTH)) {
                case Calendar.JANUARY:
                    dateMap[0] = new HashMap<>();
                    dateMap[0].put(28, "present");
                    dateMap[0].put(1, "present");
                    dateMap[0].put(3, "present");
                    dateMap[0].put(5, "present");
                    dateMap[0].put(8, "present");
                    dateMap[0].put(12, "present");
                    dateMap[0].put(21, "present");
                    dateMap[0].put(31, "present");
                    dateMap[1] = null;
                    break;
                case Calendar.FEBRUARY:
                    dateMap[0] = new HashMap<>();
                    dateMap[0].put(1, "present");
                    dateMap[0].put(24, "present");
                    dateMap[0].put(3, "present");
                    dateMap[0].put(5, "present");
                    dateMap[0].put(8, "present");
                    dateMap[0].put(12, "present");
                    dateMap[0].put(21, "present");
                    dateMap[0].put(31, "present");
                    dateMap[1] = null;
                    break;
                case Calendar.MARCH:
                    dateMap[0] = new HashMap<>();
                    dateMap[0].put(1, "present");
                    dateMap[0].put(3, "present");
                    dateMap[0].put(5, "present");
                    dateMap[0].put(8, "present");
                    dateMap[0].put(12, "present");
                    dateMap[0].put(21, "present");
                    dateMap[0].put(31, "present");
                    dateMap[1] = null;
                    break;
                case Calendar.APRIL:
                    dateMap[0] = new HashMap<>();
                    dateMap[0].put(30, "present");
                    dateMap[0].put(1, "present");
                    dateMap[0].put(3, "present");
                    dateMap[0].put(5, "present");
                    dateMap[0].put(8, "present");
                    dateMap[0].put(12, "present");
                    dateMap[0].put(21, "present");
                    dateMap[0].put(31, "present");
                    dateMap[1] = null;
                    break;

                case Calendar.MAY:
                    dateMap[0] = new HashMap<>();
                    dateMap[0].put(1, "present");
                    dateMap[0].put(3, "present");
                    dateMap[0].put(9, "present");
                    dateMap[0].put(16, "present");
                    dateMap[0].put(26, "present");
                    dateMap[0].put(5, "present");
                    dateMap[0].put(8, "present");
                    dateMap[0].put(12, "present");
                    dateMap[0].put(21, "present");
                    dateMap[0].put(31, "present");

                    dateMap[1] = null;
                    break;

                case Calendar.JULY:

                    break;
                case Calendar.AUGUST:

                    break;
                case Calendar.SEPTEMBER:

                    break;
                case Calendar.OCTOBER:

                    break;
                case Calendar.NOVEMBER:

                    break;
                case Calendar.DECEMBER:

                    break;


                default:
                    dateMap[0] = null;
                    dateMap[1] = null;
            }


        } else {
            Toast.makeText(getContext(), "Yet to come/ Already passed", Toast.LENGTH_LONG).show();
        }

        return dateMap;

    }
}