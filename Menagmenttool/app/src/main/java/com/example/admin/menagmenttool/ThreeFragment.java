package com.example.admin.menagmenttool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


public class ThreeFragment extends Fragment {

    public int sendYear,sendMonth,sendDay;
    String userEmail;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // onDateClick();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_three, null);
        CalendarView  cl = (CalendarView) v.findViewById(R.id.calendarView);
        userEmail = getArguments().getString("User");
      //  Toast.makeText(getActivity(), "!"+userEmail +"!", Toast.LENGTH_LONG).show();
        cl.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                 Intent intent = new Intent(getActivity(),ListOfThing.class);
                // someEventListener.someEvent(day+""+month+""+year);
                intent.putExtra("Message",day+""+month+""+year);
                intent.putExtra("ID",userEmail);
                startActivity(intent);

            }
        });
        return v;
    }
    public void onDateClick(){

    }
}
