package com.iteso.wapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.wapi.beans.Grade;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.GradeControl;
import com.iteso.wapi.database.SubjectControl;

import java.util.ArrayList;

public class ActivityEditGrade extends AppCompatActivity {

    Subject subject;
    SubjectControl subjectControl;
    Grade grade;
    TextView nomMateria;
    ArrayList<Grade> calificaciones;
    RecyclerView recyclerView;
    AdapterGrade adapterGrade;
    Button addGrade;
    EditText editTitle, editPorc, editGrade;
    DataBaseHandler dh;
    float avarage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grade);
        nomMateria = findViewById(R.id.activity_edit_grade_materia);
        editTitle = findViewById(R.id.activity_edit_grade_materia_edit_desc);
        editPorc = findViewById(R.id.activity_edit_grade_materia_edit_porc);
        editGrade = findViewById(R.id.activity_edit_grade_materia_edit_cali);
        addGrade = findViewById(R.id.activity_edit_grade_materia_agregar);
        subjectControl = new SubjectControl();

        dh = DataBaseHandler.getInstance(this);
        final GradeControl gradeControl = new GradeControl();

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

        addGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Float.valueOf(editPorc.getText().toString()) >=0.0 & Float.valueOf(editPorc.getText().toString()) <=100.0)
                        & (Float.valueOf(editGrade.getText().toString()) >=0.0 & Float.valueOf(editGrade.getText().toString()) <=100.0)){
                    if(validatePercentage(Float.valueOf(editPorc.getText().toString()))){
                        avarage = 0;
                        grade = new Grade();
                        grade.setGrade(Float.valueOf(editGrade.getText().toString()));
                        grade.setPercentage(Float.valueOf(editPorc.getText().toString()));
                        grade.setDescriptionGrade(editTitle.getText().toString());
                        grade.setFk_subject(subject.getIdSubject());
                        gradeControl.addGrade(grade,dh);
                        editTitle.setText("");
                        editPorc.setText("");
                        editGrade.setText("");

                        calificaciones.clear();
                        calificaciones = gradeControl.getGradesBySubject(subject.getIdSubject(),dh);
                        adapterGrade = new AdapterGrade(2, ActivityEditGrade.this, calificaciones);
                        recyclerView.setAdapter(adapterGrade);

                        for(int x =0; x<calificaciones.size();x++){
                            avarage += (calificaciones.get(x).getGrade()*(calificaciones.get(x).getPercentage() / 100.0));
                        }
                        subject.setAvarage(avarage);
                        subjectControl.updateSubject(subject, dh);
                    }
                    else{
                        Toast.makeText(ActivityEditGrade.this, "Total de porcentajes erroneos", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(ActivityEditGrade.this, "Error en datos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean validatePercentage(float percentageExtra){
        float totalPercentage = percentageExtra;
        Grade auxGrade;
        for(int x = 0; x<calificaciones.size();x++){
            auxGrade = calificaciones.get(x);
            totalPercentage += auxGrade.getPercentage();
        }
        if(totalPercentage<=100) return true;
        else return false;
    }
}
