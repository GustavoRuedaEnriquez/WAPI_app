package com.iteso.wapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iteso.wapi.beans.Grade;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.GradeControl;

import java.util.ArrayList;

public class ActivityEditGrade extends AppCompatActivity {

    Subject subject;
    TextView nomMateria;
    ArrayList<Grade> calificaciones;
    RecyclerView recyclerView;
    AdapterGrade adapterGrade;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grade);
        nomMateria = findViewById(R.id.activity_edit_grade_materia);
        back = findViewById(R.id.activity_edit_grade_back);
        DataBaseHandler dh = DataBaseHandler.getInstance(this);
        GradeControl gradeControl = new GradeControl();

        if (getIntent().getExtras() != null) {
            subject = getIntent().getParcelableExtra("Subject");
            if (subject != null) {
                nomMateria.setText(subject.getNameSubject());
            }
        }

        calificaciones = gradeControl.getGradesBySubject(subject.getIdSubject(),dh);

        recyclerView = findViewById(R.id.activity_edit_grade_recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        adapterGrade = new AdapterGrade(2, this, calificaciones);

        recyclerView.setAdapter(adapterGrade);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEditGrade.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
