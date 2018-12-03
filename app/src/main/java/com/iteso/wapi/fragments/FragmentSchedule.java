package com.iteso.wapi.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.iteso.wapi.ActivityEditSchedule;
import com.iteso.wapi.ActivityLogin;
import com.iteso.wapi.ActivitySplashscreen;
import com.iteso.wapi.AdapterEditSchedule;
import com.iteso.wapi.AdapterFragmentSchedule;
import com.iteso.wapi.R;
import com.iteso.wapi.beans.Period;
import com.iteso.wapi.beans.Schedule;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.PeriodControl;
import com.iteso.wapi.database.ScheduleControl;
import com.iteso.wapi.database.SubjectControl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;


public class FragmentSchedule extends Fragment {

    private Button editSchedule;
    private ImageButton prev, next;
    private Spinner weekDays, periodsSpinner;
    private PeriodControl periodControl;
    private SubjectControl subjectControl;
    private ScheduleControl scheduleControl;
    private RecyclerView subjectsOfDay;
    private ArrayList<Subject> subjects;
    private AdapterFragmentSchedule adapterFragmentSchedule;
    private ArrayList<String> days = new ArrayList<>();
    private Period currentPeriod;
    ArrayList<Period> periods;

    public FragmentSchedule() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        days.add(getResources().getString(R.string.fragment_schedule_monday));
        days.add(getResources().getString(R.string.fragment_schedule_tuesday));
        days.add(getResources().getString(R.string.fragment_schedule_wednesday));
        days.add(getResources().getString(R.string.fragment_schedule_thursday));
        days.add(getResources().getString(R.string.fragment_schedule_friday));
        days.add(getResources().getString(R.string.fragment_schedule_saturday));

        final DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        periodControl = new PeriodControl();
        subjectControl = new SubjectControl();
        scheduleControl = new ScheduleControl();
        subjects = new ArrayList<>();

        periods = periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "Default name"), dh);
        ArrayList<String> periodsName = new ArrayList<>();
        for (Period index : periods)
            periodsName.add(index.getNamePeriod());

        if (periods.size() > 0) {
            currentPeriod = periods.get(0);
        }

        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        editSchedule = v.findViewById(R.id.fragment_schedule_edit_schedule);
        prev = v.findViewById(R.id.fragment_schedule_previous_btn);
        next = v.findViewById(R.id.fragment_schedule_next_btn);
        weekDays = v.findViewById(R.id.fragment_schedule_day_spinner);
        periodsSpinner = v.findViewById(R.id.fragment_schedule_period_spinner);
        subjectsOfDay = v.findViewById(R.id.fragment_schedule_subjects);

        weekDays.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.custom_spinner, days));
        periodsSpinner.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, periodsName));

        subjectsOfDay.setHasFixedSize(true);
        subjectsOfDay.setLayoutManager(new LinearLayoutManager(getActivity()));

        editSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editSchedule.setBackground(getResources().getDrawable(R.drawable.custom_selected_blue_light_btn));
                editSchedule.setTextColor(Color.WHITE);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        editSchedule.setBackground(getResources().getDrawable(R.drawable.custom_blue_light_btn));
                        editSchedule.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 190);
                Intent intent = new Intent(getActivity(), ActivityEditSchedule.class);
                startActivity(intent);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prev.setBackground(getResources().getDrawable(R.drawable.custom_selected_blue_light_btn));
                prev.setImageDrawable(getResources().getDrawable(R.drawable.custom_preview_selected_btn));
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        prev.setBackground(getResources().getDrawable(R.drawable.custom_blue_light_btn));
                        prev.setImageDrawable(getResources().getDrawable(R.drawable.custom_preview_btn));
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 190);
                previousDay(weekDays);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setBackground(getResources().getDrawable(R.drawable.custom_selected_blue_light_btn));
                next.setImageDrawable(getResources().getDrawable(R.drawable.custom_next_selected_btn));
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        next.setBackground(getResources().getDrawable(R.drawable.custom_blue_light_btn));
                        next.setImageDrawable(getResources().getDrawable(R.drawable.custom_next_btn));
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 190);
                nextDay(weekDays);
            }
        });

        weekDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapterFragmentSchedule = new AdapterFragmentSchedule(getActivity(), subjects, 0);
        subjectsOfDay.setAdapter(adapterFragmentSchedule);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        periodControl = new PeriodControl();
        subjectControl = new SubjectControl();
        scheduleControl = new ScheduleControl();

        periods = periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "Default name"), dh);
        ArrayList<String> periodsName = new ArrayList<>();
        for (Period index : periods)
            periodsName.add(index.getNamePeriod());

        periodsSpinner.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, periodsName));

        if (periods.size() == 0) {
            subjects.clear();
            adapterFragmentSchedule.notifyDataSetChanged();
            return;
        }

        currentPeriod = periods.get(0);
        updateAdapter();

    }

    public void previousDay(Spinner spinner) {
        int position = spinner.getSelectedItemPosition();
        if (position == 0)
            spinner.setSelection(5);
        else
            spinner.setSelection(position - 1);
    }

    public void nextDay(Spinner spinner) {
        int position = spinner.getSelectedItemPosition();
        if (position == 5)
            spinner.setSelection(0);
        else
            spinner.setSelection(position + 1);
    }

    private void updateAdapter() {
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());

        if (periods.size() > 0 && !currentPeriod.getNamePeriod().equals(periodsSpinner.getSelectedItem().toString())) {
            for (Period period : periods)
                if (period.getNamePeriod().equals(periodsSpinner.getSelectedItem().toString()))
                    currentPeriod = period;
        }

        subjects.clear();
        if (subjectControl.getSubjectsByPeriod(currentPeriod.getIdPeriod(), dh).size() > 0) {
            ArrayList<Subject> allSubjects = subjectControl.getSubjectsByPeriodAndDayOrdered(currentPeriod.getIdPeriod(), weekDays.getSelectedItemPosition(), dh);
            subjects.addAll(allSubjects);
        }
        adapterFragmentSchedule.notifyDataSetChanged();
    }

}
