package com.iteso.wapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.iteso.wapi.beans.Homework;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.HomeworkControl;
import com.iteso.wapi.database.SubjectControl;

import java.util.ArrayList;

public class ActivityEditHomework extends AppCompatActivity {

    Spinner spinner;
    EditText description, day, month, year, hour, min;
    Button addHomework;
    HomeworkControl homeworkControl = new HomeworkControl();
    DataBaseHandler dh;
    SubjectControl subjectControl = new SubjectControl();
    ArrayList<Subject> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_homework);
        spinner = findViewById(R.id.activity_edit_homework_materias);
        description = findViewById(R.id.activity_edit_homework_descr);
        day = findViewById(R.id.activity_edit_homework_day);
        month = findViewById(R.id.activity_edit_homework_month);
        year = findViewById(R.id.activity_edit_homework_year);
        hour = findViewById(R.id.activity_edit_homework_hour);
        min = findViewById(R.id.activity_edit_homework_min);
        addHomework = findViewById(R.id.activity_edit_homework_agregar);

        dh = DataBaseHandler.getInstance(this);
        SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        subjects = subjectControl.getSubjectsByStudent(sharedPreferences.getString("NAME", "UNKNOWN"),dh);
        ArrayList<String> nameSubjects = new ArrayList<>();
        for (int x = 0; x < subjects.size() ; x++){
            nameSubjects.add(subjects.get(x).getNameSubject());
        }

        spinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, nameSubjects.toArray()));

        addHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Integer.valueOf(day.getText().toString()) <= 31 & Integer.valueOf(day.getText().toString()) >= 0 )
                    &(Integer.valueOf(month.getText().toString()) <=12 & Integer.valueOf(month.getText().toString())>=0)
                    &(Integer.valueOf(hour.getText().toString()) <=23 & Integer.valueOf(hour.getText().toString())>=0)
                    &(Integer.valueOf(min.getText().toString()) <=59 & Integer.valueOf(min.getText().toString())>=0)){
                        Homework homework = new Homework();
                        homework.setDescriptionHomework(description.getText().toString());
                        homework.setFk_subject(1);
                        homework.setDeliveryDay(Integer.valueOf(day.getText().toString()));
                        homework.setDeliveryMonth(Integer.valueOf(month.getText().toString()));
                        homework.setDeliveryYear(Integer.valueOf(year.getText().toString()));
                        homework.setDeliveryHour(Integer.valueOf(hour.getText().toString()));
                        homework.setDeliveryMin(Integer.valueOf(min.getText().toString()));

                        homeworkControl.addHomework(homework,dh);
                        onBackPressed();

                }
                else{
                    Toast.makeText(ActivityEditHomework.this, "Error en datos de hora o fecha", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
