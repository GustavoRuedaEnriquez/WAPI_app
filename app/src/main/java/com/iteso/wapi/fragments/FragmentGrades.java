package com.iteso.wapi.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.iteso.wapi.ActivitySplashscreen;
import com.iteso.wapi.AdapterMateria;
import com.iteso.wapi.R;
import com.iteso.wapi.beans.Grade;
import com.iteso.wapi.beans.Period;
import com.iteso.wapi.beans.Schedule;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.GradeControl;
import com.iteso.wapi.database.PeriodControl;
import com.iteso.wapi.database.SubjectControl;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentGrades.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentGrades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGrades extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Subject> subjects;
    ArrayList<Period> periods;
    RecyclerView recyclerView;
    AdapterMateria adapterMateria;
    SubjectControl subjectControl;
    DataBaseHandler dh;
    Spinner spinner;
    TextView avarageFinal;


    public FragmentGrades() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGrades.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGrades newInstance(String param1, String param2) {
        FragmentGrades fragment = new FragmentGrades();
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
        View rootView = inflater.inflate(R.layout.fragment_grades, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_calificacion_recyclerView);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        dh = DataBaseHandler.getInstance(getContext());
        PeriodControl periodControl = new PeriodControl();
        periods = new ArrayList<>();
        periods = periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "UNKNOWN"),dh);
        ArrayList<String> namePeriods = new ArrayList<>();
        for (int x = 0; x < periods.size() ; x++){
            namePeriods.add(periods.get(x).getNamePeriod());
        }

        spinner = (Spinner) rootView.findViewById(R.id.fragment_calificacion_spinner);
        avarageFinal = rootView.findViewById(R.id.fragment_calificacion_promedio);

        spinner.setAdapter(new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_spinner_dropdown_item, namePeriods.toArray()));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        subjectControl = new SubjectControl();

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subjects.clear();
                subjects = subjectControl.getSubjectsByPeriod(periods.get(spinner.getSelectedItemPosition()).getIdPeriod(), dh);
                adapterMateria = new AdapterMateria(2, getActivity(), subjects);
                recyclerView.setAdapter(adapterMateria);
                updateAvaragePeriod();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    public void updateAvaragePeriod(){
        float avarage = 0;
        String show;
        Subject auxSubject;
        for(int x = 0; x<subjects.size();x++){
            auxSubject = subjects.get(x);
            avarage += (auxSubject.getAvarage());
        }
        avarage = avarage / subjects.size();
        show = "Promedio: " + avarage;
        avarageFinal.setText(show);
    }

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Subject materia = data.getParcelableExtra("Subject");
        Iterator<Subject> iterator = subjects.iterator();
        int position = 0;
        while(iterator.hasNext()){
            Subject item = iterator.next();
            if(item.getIdSubject() == materia.getIdSubject()){
                subjects.set(position, materia);
                break;
            }
            position++;
        }
        adapterMateria.notifyDataSetChanged();
    }
*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(periods.size()>0){
            subjects = subjectControl.getSubjectsByPeriod(periods.get(spinner.getSelectedItemPosition()).getIdPeriod(), dh);
        }
        else{
            subjects = new ArrayList<>();
        }
        updateAvaragePeriod();
        adapterMateria = new AdapterMateria(2, getActivity(), subjects);
        recyclerView.setAdapter(adapterMateria);
    }
}
