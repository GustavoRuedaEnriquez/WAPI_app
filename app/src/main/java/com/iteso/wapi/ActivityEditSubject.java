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
import java.util.Objects;

public class ActivityEditSubject extends AppCompatActivity {
    private TextView name;
    private Spinner period;
    private Button save;
    private SharedPreferences sharedPreferences;
    private Subject subject;
    private ArrayList<Period> periods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);

        sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        name    = findViewById(R.id.activity_edit_subject_name);
        period  = findViewById(R.id.activity_edit_subject_period);
        save    = findViewById(R.id.activity_edit_subject_save);
        subject = getIntent().getExtras().getParcelable("SUBJECT");

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        PeriodControl periodControl = new PeriodControl();

        periods = periodControl.getPeriodsByStudent(sharedPreferences.getString("NAME", "UNKNOWN"), dh);
        ArrayList<String> periodNames = new ArrayList<>();
        for (Period period : periods)
            periodNames.add(period.getNamePeriod());

        period.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,
                Objects.requireNonNull(periodNames.toArray())));

    }

    public void modifySubject(View v) {
        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        SubjectControl subjectControl = new SubjectControl();

        subject.setNameSubject(name.getText().toString());
        String periodName = period.getSelectedItem().toString();
        for(Period period: periods)
            if(period.getNamePeriod().equals(periodName))
                subject.setFk_period(period.getIdPeriod());

        subjectControl.updateSubject(subject, dh);

        //TODO: Make the intent to de past activity
    }

    public void cancelSubject(View v){
        //TODO: Implement this method
    }
}
