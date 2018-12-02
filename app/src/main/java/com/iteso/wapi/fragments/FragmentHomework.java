package com.iteso.wapi.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.iteso.wapi.ActivityEditHomework;
import com.iteso.wapi.ActivitySplashscreen;
import com.iteso.wapi.AdapterHomework;
import com.iteso.wapi.R;
import com.iteso.wapi.beans.Homework;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.HomeworkControl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentHomework.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHomework#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHomework extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Homework> homeworksWeek;
    ArrayList<Homework> homeworksNext;
    ArrayList<Homework> homeworks;
    SharedPreferences sharedPreferences;
    HomeworkControl homeworkControl = new HomeworkControl();
    DataBaseHandler dh;
    RecyclerView recyclerView;
    RecyclerView recyclerViewProximas;
    AdapterHomework adapterHomework;
    Button agregar;
    View rootView;
    Date today;

    public FragmentHomework() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHomework.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHomework newInstance(String param1, String param2) {
        FragmentHomework fragment = new FragmentHomework();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_homework, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_tarea_recyclerView_semana_actual);
        recyclerViewProximas = rootView.findViewById(R.id.fragment_tarea_recyclerView_proximas_semanas);
        agregar = rootView.findViewById(R.id.fragment_tarea_agregar);
        today = new Date(Calendar.getInstance().getTime().getYear(),Calendar.getInstance().getTime().getMonth(),Calendar.getInstance().getTime().getDay());
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Date date;
        long diff;
        int daysDiff;
        sharedPreferences = getActivity().getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        dh = DataBaseHandler.getInstance(getContext());

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), ActivityEditHomework.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        Date date;
        long diff;
        int daysDiff;

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerViewProximas.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity());
        recyclerViewProximas.setLayoutManager(mLayoutManager2);

        homeworks = homeworkControl.getHomeworksByStudent(sharedPreferences.getString("NAME", "UNKNOWN"), dh);
        homeworksNext = new ArrayList<>();
        homeworksWeek = new ArrayList<>();

        for(int x = 0; x<homeworks.size(); x++){
            date = new Date(homeworks.get(x).getDeliveryYear()- 1900,homeworks.get(x).getDeliveryMonth()-1,homeworks.get(x).getDeliveryDay());
            if (today.compareTo(date)<0){
                diff = date.getTime() - today.getTime();
                daysDiff =(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                Log.e("Date", Integer.toString(daysDiff));

                if(daysDiff<=7){
                    homeworksWeek.add(homeworks.get(x));
                }
                else{
                    homeworksNext.add(homeworks.get(x));
                }
            }
        }

        adapterHomework = new AdapterHomework(4, getActivity(), homeworksWeek);
        recyclerView.setAdapter(adapterHomework);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        adapterHomework = new AdapterHomework(4, getActivity(), homeworksNext);
        recyclerViewProximas.setAdapter(adapterHomework);
        DividerItemDecoration dividerItemDecorationProximas = new DividerItemDecoration(recyclerViewProximas.getContext(), mLayoutManager.getOrientation());
        recyclerViewProximas.addItemDecoration(dividerItemDecorationProximas);

    }
}
