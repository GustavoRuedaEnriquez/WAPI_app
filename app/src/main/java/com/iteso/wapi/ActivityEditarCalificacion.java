package com.iteso.wapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iteso.wapi.beans.Calificacion;
import com.iteso.wapi.beans.Materia;

import java.util.ArrayList;

public class ActivityEditarCalificacion extends AppCompatActivity {

    Materia materia;
    TextView nomMateria;
    ArrayList<Calificacion> calificaciones;
    RecyclerView recyclerView;
    AdapterCalificacion adapterCalificacion;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_calificacion);
        nomMateria = findViewById(R.id.activity_editar_calificacion_materia);
        back = findViewById(R.id.activity_editar_calificacion_back);

        calificaciones = new ArrayList<>();
        calificaciones.add(new Calificacion(1, 1, "Examen 1", (float) 20, (float) 100));
        calificaciones.add(new Calificacion(2, 1, "Examen 2", (float) 20, (float) 90));
        calificaciones.add(new Calificacion(3, 1, "Examen 3", (float) 20, (float) 90));
        calificaciones.add(new Calificacion(4, 1, "Tareas", (float) 10, (float) 70));
        calificaciones.add(new Calificacion(5, 1, "Proyecto", (float) 30, (float) 90));

        recyclerView = findViewById(R.id.activity_editar_calificacion_recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        adapterCalificacion = new AdapterCalificacion(2, this, calificaciones);

        recyclerView.setAdapter(adapterCalificacion);

        if (getIntent().getExtras() != null) {
            materia = getIntent().getParcelableExtra("Materia");
            if (materia != null) {
                nomMateria.setText(materia.getNombreMateria());
            }
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEditarCalificacion.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
