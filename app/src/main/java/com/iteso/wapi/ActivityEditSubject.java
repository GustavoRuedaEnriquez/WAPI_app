package com.iteso.wapi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.wapi.beans.Period;
import com.iteso.wapi.beans.Schedule;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.PeriodControl;
import com.iteso.wapi.database.ScheduleControl;
import com.iteso.wapi.database.SubjectControl;

import java.util.ArrayList;
import java.util.Objects;

public class ActivityEditSubject extends AppCompatActivity {
    private TextView name;
    private Spinner period;
    private Button save, cancel, delete;
    private SharedPreferences sharedPreferences;
    private Subject subject;
    private ArrayList<Period> periods;
    private EditText startTimeHr, startTimeMin;
    private EditText endTimeHr, endTimeMin;
    private CheckBox[] days;
    private static final int MONDAY = 0, TUESDAY = 1, WEDNESDAY = 2, THURSDAY = 3, FRIDAY = 4, SATURDAY = 5;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);

        sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        name    = findViewById(R.id.activity_edit_subject_name);
        period  = findViewById(R.id.activity_edit_subject_period);
        save    = findViewById(R.id.activity_edit_subject_save);
        cancel  = findViewById(R.id.activity_edit_subject_cancel);
        delete  = findViewById(R.id.activity_edit_subject_delete);
        subject = getIntent().getExtras().getParcelable("SUBJECT");

        startTimeHr  = findViewById(R.id.activity_edit_subject_start_time_txt);
        startTimeMin = findViewById(R.id.activity_edit_subject_start_time_txt_min);
        endTimeHr    = findViewById(R.id.activity_edit_subject_end_time_txt_hr);
        endTimeMin   = findViewById(R.id.activity_edit_subject_end_time_txt_min);

        days = new CheckBox[6];
        days[MONDAY]    = findViewById(R.id.activity_edit_subject_monday);
        days[TUESDAY]   = findViewById(R.id.activity_edit_subject_tuesday);
        days[WEDNESDAY] = findViewById(R.id.activity_edit_subject_wednesday);
        days[THURSDAY]  = findViewById(R.id.activity_edit_subject_thursday);
        days[FRIDAY]    = findViewById(R.id.activity_edit_subject_friday);
        days[SATURDAY]  = findViewById(R.id.activity_edit_subject_friday);

        name.setText(subject.getNameSubject());

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        PeriodControl periodControl = new PeriodControl();
        ScheduleControl scheduleControl = new ScheduleControl();

        periods = periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "UNKNOWN"), dh);
        ArrayList<String> periodNames = new ArrayList<>();
        for (Period period : periods)
            periodNames.add(period.getNamePeriod());

        period.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                Objects.requireNonNull(periodNames.toArray())));

        ArrayList<Schedule> schedules = scheduleControl.getSchedulesBySubject(subject.getIdSubject(), dh);

        if (schedules != null && schedules.size() > 0) {
            startTimeHr.setText(String.format("%d", schedules.get(0).getInitialTime() / 100));
            startTimeMin.setText(String.format("%02d", schedules.get(0).getInitialTime() % 100));

            endTimeHr.setText(String.format("%d", schedules.get(0).getFinalTime() / 100));
            endTimeMin.setText(String.format("%02d", schedules.get(0).getFinalTime() % 100));

            for (Schedule schedule : schedules)
                days[schedule.getDay()].setChecked(true);

        }
    }

    @SuppressLint("SetTextI18n")
    public void modifySubject(View v) {
        save.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
        save.setTextColor(Color.WHITE);

        if(startTimeMin.getText().toString().equals(""))
            startTimeMin.setText("00");

        if(endTimeMin.getText().toString().equals(""))
            endTimeMin.setText("00");

        try {
            if (Integer.parseInt(startTimeHr.getText().toString()) > 11 || Integer.parseInt(endTimeHr.getText().toString()) > 11
                    || Integer.parseInt(startTimeMin.getText().toString()) > 59
                    || Integer.parseInt(endTimeMin.getText().toString()) > 59) {
                Toast.makeText(this, getString(R.string.activity_subject_incorrect_time_warn), Toast.LENGTH_SHORT).show();
                startTimeHr.setText("");
                startTimeMin.setText("");
                endTimeHr.setText("");
                endTimeMin.setText("");

                return;
            }
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.activity_subject_incorrect_time_warn), Toast.LENGTH_SHORT).show();
            return;
        }

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        SubjectControl subjectControl = new SubjectControl();
        ScheduleControl scheduleControl = new ScheduleControl();

        subject.setNameSubject(name.getText().toString());
        String periodName = period.getSelectedItem().toString();
        for (Period period : periods)
            if (period.getNamePeriod().equals(periodName))
                subject.setFk_period(period.getIdPeriod());

        subjectControl.updateSubject(subject, dh);

        //Look if a change were made
        boolean change;
        ArrayList<Schedule> currentSchedule = scheduleControl.getSchedulesBySubject(subject.getIdSubject(), dh);

        int startTime = Integer.parseInt(startTimeHr.getText().toString()) * 100;
        startTime += Integer.parseInt(startTimeMin.getText().toString());

        int endTime = Integer.parseInt(endTimeHr.getText().toString()) * 100;
        endTime += Integer.parseInt(endTimeMin.getText().toString());

        ArrayList<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < days.length; i++) {
            if (days[i].isChecked()) {
                Schedule schedule = new Schedule(0, i, startTime, endTime, subject.getIdSubject());
                schedules.add(schedule);
            }
        }

        change = currentSchedule.size() != schedules.size();
        if(!change){
            for(Schedule current: currentSchedule){
                boolean flag = false;
                for(Schedule schedule: schedules)
                    if(current.equals(schedule)){
                        flag = true;
                        break;
                    }
                if(!flag){
                    change = true;
                    break;
                }
            }
        }

        if(change){
            for(Schedule current: currentSchedule)
                scheduleControl.deleteSchedule(current.getIdSchedule(), dh);

            for(Schedule current: schedules)
                scheduleControl.addSchedule(current, dh);
        }

        Intent result = new Intent(this, ActivityEditSchedule.class);
        startActivity(result);
        finish();
    }

    public void cancelSubject(View v) {
        cancel.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
        cancel.setTextColor(Color.WHITE);

        Intent result = new Intent(this, ActivityEditSchedule.class);
        startActivity(result);
        finish();
    }

    public void deleteSubject(View v) {
        delete.setBackground(getResources().getDrawable(R.drawable.custom_selected_red_light_btn));
        delete.setTextColor(Color.WHITE);
        Toast.makeText(this, getString(R.string.activity_edit_subject_delete_message), Toast.LENGTH_SHORT)
                .show();

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        SubjectControl subjectControl = new SubjectControl();
        ScheduleControl scheduleControl = new ScheduleControl();

        ArrayList<Schedule> schedules = scheduleControl.getSchedulesBySubject(subject.getIdSubject(), dh);

        for(Schedule schedule: schedules)
            scheduleControl.deleteSchedule(schedule.getIdSchedule(), dh);

        subjectControl.deleteSubject(subject.getIdSubject(), dh);

        Intent result = new Intent(this, ActivityEditSchedule.class);
        startActivity(result);
        finish();
    }
}
