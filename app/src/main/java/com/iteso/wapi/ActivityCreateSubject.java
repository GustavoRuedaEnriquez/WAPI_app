package com.iteso.wapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.iteso.wapi.beans.Period;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.PeriodControl;
import com.iteso.wapi.database.SubjectControl;

import java.util.ArrayList;

public class ActivityCreateSubject extends AppCompatActivity {
    private TextView name;
    private Spinner period;
    private Button save;
    private ArrayList<Period> periods;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);

        sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        name = findViewById(R.id.activity_edit_subject_name);
        period = findViewById(R.id.activity_edit_subject_period);
        save = findViewById(R.id.activity_edit_subject_save);

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        PeriodControl periodControl = new PeriodControl();

        periods = periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "UNKNOWN"), dh);
        ArrayList<String> periodNames = new ArrayList<>();
        for (Period period : periods)
            periodNames.add(period.getNamePeriod());

        period.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                periodNames.toArray()));
    }

    public void addSubject(View v) {
        String subjectName = name.getText().toString();
        String periodName = period.getSelectedItem().toString();
        Period subjectPeriod = new Period();
        for (Period period : periods)
            if (periodName.equals(period.getNamePeriod()))
                subjectPeriod = period;

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        SubjectControl subjectControl = new SubjectControl();
        int idSubject = subjectControl.maxIdSubject(dh);

        Subject subject = new Subject(idSubject, subjectPeriod.getIdPeriod(), subjectName, 6.0f);

        subjectControl.addSubject(subject, dh);
    }
}
