package com.iteso.wapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.util.Calendar;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class ActivityCreateSubject extends AppCompatActivity {
    private TextView name;
    private Spinner period;
    private Button save;
    private ArrayList<Period> periods;
    private EditText startTimeHr, startTimeMin;
    private EditText endTimeHr, endTimeMin;
    private CheckBox[] days;
    private static final int MONDAY = 0, TUESDAY = 1, WEDNESDAY = 2, THURSDAY = 3, FRIDAY = 4, SATURDAY = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);

        SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        name = findViewById(R.id.activity_create_subject_name);
        period = findViewById(R.id.activity_create_subject_period);
        save = findViewById(R.id.activity_create_subject_save);
        startTimeHr = findViewById(R.id.activity_create_subject_start_time_txt);
        startTimeMin = findViewById(R.id.activity_create_subject_start_time_txt_min);
        endTimeHr = findViewById(R.id.activity_create_subject_end_time_txt_hr);
        endTimeMin = findViewById(R.id.activity_create_subject_end_time_txt_min);

        days = new CheckBox[6];
        days[MONDAY] = findViewById(R.id.activity_create_subject_monday);
        days[TUESDAY] = findViewById(R.id.activity_create_subject_tuesday);
        days[WEDNESDAY] = findViewById(R.id.activity_create_subject_wednesday);
        days[THURSDAY] = findViewById(R.id.activity_create_subject_thursday);
        days[FRIDAY] = findViewById(R.id.activity_create_subject_friday);
        days[SATURDAY] = findViewById(R.id.activity_create_subject_friday);

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        PeriodControl periodControl = new PeriodControl();

        periods = periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "UNKNOWN"), dh);
        ArrayList<String> periodNames = new ArrayList<>();
        for (Period p : periods)
            periodNames.add(p.getNamePeriod());

        if (periodNames.size() == 0) {
            Toast.makeText(this, "Debes agregar periodos primero", Toast.LENGTH_SHORT)
                    .show();
            onBackPressed();
        } else
            period.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                    Objects.requireNonNull(periodNames.toArray())));

    }

    public void addSubject(View v) {
        save.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
        save.setTextColor(Color.WHITE);

        try {
            if (Integer.parseInt(startTimeHr.getText().toString()) > 11 || Integer.parseInt(endTimeHr.getText().toString()) > 11
                    || Integer.parseInt(startTimeMin.getText().toString()) > 59
                    || Integer.parseInt(endTimeMin.getText().toString()) > 59) {
                Toast.makeText(this, "Ingrese horas correctas", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Ingrese horas correctas", Toast.LENGTH_SHORT).show();
            return;
        }

        //Add subject to db
        String subjectName = name.getText().toString();
        String periodName = period.getSelectedItem().toString();
        Period subjectPeriod = new Period();
        for (Period period : periods)
            if (periodName.equals(period.getNamePeriod()))
                subjectPeriod = period;

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        SubjectControl subjectControl = new SubjectControl();
        int idSubject = subjectControl.maxIdSubject(dh) + 1;

        Subject subject = new Subject(idSubject, subjectPeriod.getIdPeriod(), subjectName, 6.0f);

        subjectControl.addSubject(subject, dh);

        //Add schedule to db
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

        ScheduleControl scheduleControl = new ScheduleControl();
        for(Schedule schedule: schedules)
            scheduleControl.addSchedule(schedule, dh);

//        for(Schedule schedule: scheduleControl.getSchedules(dh))
//            Log.e("WAPI", schedule.toString());

        Intent intent = new Intent(this, ActivityEditSchedule.class);
        startActivity(intent);
        finish();
    }
}
