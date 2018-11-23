package com.iteso.wapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iteso.wapi.beans.Schedule;
import com.iteso.wapi.beans.Subject;

import java.util.ArrayList;

public class ActivityMateria extends AppCompatActivity {

    ArrayList<Subject> subjects =new ArrayList<>();
    RecyclerView recyclerView;
    AdapterMateria adapterMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        Schedule dias = new Schedule();

        subjects = new ArrayList<>();
        subjects.add(new Subject(1,1, "Micros", (float) 5.3));
        subjects.add(new Subject(2, 1, "Moviles", (float)3.3));
        subjects.add(new Subject(3, 1, "GBD", (float)9.0));


        recyclerView = findViewById(R.id.activity_materia_recyclerView);
        adapterMateria = new AdapterMateria(subjects);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapterMateria);
    }

}
