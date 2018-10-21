package com.iteso.wapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iteso.wapi.beans.DiaHoras;
import com.iteso.wapi.beans.Materia;

import java.util.ArrayList;

public class ActivityMateria extends AppCompatActivity {

    ArrayList<Materia> materias=new ArrayList<>();
    RecyclerView recyclerView;
    AdapterMateria adapterMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        DiaHoras dias = new DiaHoras();

        materias = new ArrayList<>();
        materias.add(new Materia(1,1, "Micros", dias, (float) 5.3));
        materias.add(new Materia(2, 1, "Moviles", dias, (float)3.3));
        materias.add(new Materia(3, 1, "GBD", dias, (float)9.0));


        recyclerView = findViewById(R.id.activity_materia_recyclerView);
        adapterMateria = new AdapterMateria(materias);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapterMateria);
    }

}
