package com.iteso.wapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class ActivityEditarTarea extends AppCompatActivity {

    Spinner spinner;
    EditText descripcion, fecha, hora;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea);
        spinner = findViewById(R.id.activity_editar_tarea_materias);
        descripcion = findViewById(R.id.activity_editar_tarea_descr);
        fecha = findViewById(R.id.activity_editar_tarea_fecha);
        hora = findViewById(R.id.activity_editar_tarea_hora);
        back = findViewById(R.id.activity_editar_tarea_back);

        ArrayAdapter<CharSequence> adapterPeriodo = ArrayAdapter.createFromResource(this,
                R.array.periodo, android.R.layout.simple_spinner_item);
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapterPeriodo);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEditarTarea.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
