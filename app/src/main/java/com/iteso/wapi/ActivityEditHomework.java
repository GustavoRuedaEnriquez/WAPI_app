package com.iteso.wapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.iteso.wapi.beans.Homework;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.HomeworkControl;

public class ActivityEditHomework extends AppCompatActivity {

    Spinner spinner;
    EditText description, day, month, year, hour, min;
    ImageButton back;
    Button addHomework;
    HomeworkControl homeworkControl = new HomeworkControl();
    DataBaseHandler dh;

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
        back = findViewById(R.id.activity_edit_homework_back);
        addHomework = findViewById(R.id.activity_edit_homework_agregar);

        dh = DataBaseHandler.getInstance(this);
        ArrayAdapter<CharSequence> adapterPeriodo = ArrayAdapter.createFromResource(this,
                R.array.periodo, android.R.layout.simple_spinner_item);
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapterPeriodo);

        addHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Homework homework = new Homework();
                homework.setDescriptionHomework(description.getText().toString());
                homework.setFk_subject(1);
                homework.setDeliveryDay(Integer.valueOf(day.getText().toString()));
                homework.setDeliveryMonth(Integer.valueOf(month.getText().toString()));
                homework.setDeliveryYear(Integer.valueOf(year.getText().toString()));
                homework.setDeliveryHour(Integer.valueOf(hour.getText().toString()));
                homework.setDeliveryMin(Integer.valueOf(min.getText().toString()));

                homeworkControl.addHomework(homework,dh);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ActivityEditHomework.this, ActivityHome.class);
                //startActivity(intent);
                //finish();
                onBackPressed();
            }
        });
    }
}
