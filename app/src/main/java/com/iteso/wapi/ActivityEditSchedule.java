package com.iteso.wapi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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

public class ActivityEditSchedule extends AppCompatActivity {

    private Button addPeriod, addSubject, go, delete;
    private Spinner periodsSpinner;
    private RecyclerView subjectsViews;
    private ArrayList<Subject> subjects;
    private AdapterEditSchedule adapterEditSchedule;
    private Period currentPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);
        addPeriod = findViewById(R.id.edit_schedule_add_period_btn);
        addSubject = findViewById(R.id.edit_schedule_add_subject_btn);
        periodsSpinner = findViewById(R.id.activity_edit_schedule_periods);
        subjectsViews = findViewById(R.id.activity_edit_schedule_subjects);
        go = findViewById(R.id.activity_edit_schedule_go);
        delete = findViewById(R.id.activity_edit_schedule_delete);

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        PeriodControl periodControl = new PeriodControl();
        SubjectControl subjectControl = new SubjectControl();
        SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        addPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPeriod.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
                addPeriod.setTextColor(Color.WHITE);
                Intent intent = new Intent(ActivityEditSchedule.this, ActivityEditPeriod.class);
                startActivity(intent);
                finish();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        addPeriod.setBackground(getDrawable(R.drawable.custom_blue_light_btn));
                        addPeriod.setTextColor(getColor(R.color.colorPrimary));
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 2000);
            }
        });

        addSubject = findViewById(R.id.edit_schedule_add_subject_btn);
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubject.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
                addSubject.setTextColor(Color.WHITE);
                Intent intent = new Intent(ActivityEditSchedule.this, ActivityCreateSubject.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayList<Period> periods = periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "UNKNOWN"), dh);
        ArrayList<String> periodNames = new ArrayList<>();
        for (Period p : periods)
            periodNames.add(p.getNamePeriod());

        subjectsViews.setHasFixedSize(true);
        subjectsViews.setLayoutManager(new LinearLayoutManager(this));

        if (periodNames.size() > 0) {
            periodsSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, periodNames));
            subjects = subjectControl.getSubjectsByPeriod(periods.get(0).getIdPeriod(), dh);
            adapterEditSchedule = new AdapterEditSchedule(this, subjects);
            subjectsViews.setAdapter(adapterEditSchedule);
            currentPeriod = periods.get(0);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityEditSchedule.this.currentPeriod == null)
                    return;

                new AlertDialog.Builder(ActivityEditSchedule.this)
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .setTitle(getString(R.string.activity_edit_schedule_delete_dialog_title))
                        .setMessage(R.string.activity_edit_schedule_delete_dialog_message)
                        .setPositiveButton(getString(R.string.activity_edit_schedule_delete_dialog_ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        deletePeriod();
                                    }
                                })
                        .setNegativeButton(getString(R.string.activity_edit_schedule_delete_dialog_cancel), null)
                        .show();
            }
        });
    }

    public void deletePeriod() {
        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        PeriodControl periodControl = new PeriodControl();
        SubjectControl subjectControl = new SubjectControl();
        ScheduleControl scheduleControl = new ScheduleControl();

        ArrayList<Subject> subjects = subjectControl.getSubjectsByPeriod(currentPeriod.getIdPeriod(), dh);

        for (Subject subject : subjects) {
            ArrayList<Schedule> schedules = scheduleControl.getSchedulesBySubject(subject.getIdSubject(), dh);
            for (Schedule schedule : schedules)
                scheduleControl.deleteSchedule(schedule.getIdSchedule(), dh);

            subjectControl.deleteSubject(subject.getIdSubject(), dh);
        }

        periodControl.deletePeriod(currentPeriod.getIdPeriod(), dh);

        Intent reload = new Intent(this, ActivityEditSchedule.class);
        startActivity(reload);
        finish();
    }

    public void updatePeriod(View v) {
        DataBaseHandler dh = DataBaseHandler.getInstance(ActivityEditSchedule.this);
        PeriodControl periodControl = new PeriodControl();
        SubjectControl subjectControl = new SubjectControl();

        SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        sharedPreferences.getString("NAME", "UNKNOWN");
        String periodName = periodsSpinner.getSelectedItem().toString();

        Period currentPeriod = new Period();

        for (Period period : periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "UNKNOWN"), dh)) {
            if (period.getNamePeriod().equals(periodName)) {
                currentPeriod = period;
                break;
            }
        }

        this.currentPeriod = currentPeriod;

        ArrayList<Subject> auxiliar = subjectControl.getSubjectsByPeriod(currentPeriod.getIdPeriod(), dh);
        subjects.clear();
        subjects.addAll(auxiliar);

        if (adapterEditSchedule != null) {
            adapterEditSchedule.notifyDataSetChanged();
        }
    }
}
