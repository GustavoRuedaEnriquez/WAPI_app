package com.iteso.wapi.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iteso.wapi.ActivitySplashscreen;
import com.iteso.wapi.AdapterUserHomework;
import com.iteso.wapi.R;
import com.iteso.wapi.beans.Homework;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.HomeworkControl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;


public class FragmentUsers extends Fragment {

   TextView greeting;
   RecyclerView homeworkView;

    public FragmentUsers() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_users, container, false);
        greeting = v.findViewById(R.id.fragment_users_greeting);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        greeting.setText(String.format("Bienvenido %s", sharedPreferences.getString("NAME", "Default name")));

        homeworkView = v.findViewById(R.id.fragment_users_homework);
        if(homeworkView == null)
            Log.e("WAPI", "Es nulo");
        homeworkView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeworkView.setHasFixedSize(true);

        DataBaseHandler dh = DataBaseHandler.getInstance(getContext());
        HomeworkControl homeworkControl = new HomeworkControl();

        ArrayList<Homework> homeworks = new ArrayList<>();
        ArrayList<Homework> allHomework = homeworkControl.getHomeworksByStudent(sharedPreferences.getString("NAME", "Default name"), dh);

        Calendar today = Calendar.getInstance();

        for(Homework homework: allHomework) {
            if(homework.getDeliveryDay().equals(today.get(Calendar.DAY_OF_MONTH)))
                homeworks.add(homework);
        }

        Collections.sort(homeworks);

        if(homeworks.size() > 0) {
            AdapterUserHomework adapterUserHomework = new AdapterUserHomework(getContext(), homeworks);
            homeworkView.setAdapter(adapterUserHomework);
        }

        return v;
    }

}
