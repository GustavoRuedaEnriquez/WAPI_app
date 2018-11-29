package com.iteso.wapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iteso.wapi.database.PeriodControl;

public class ActivityEditPeriod extends AppCompatActivity {

    EditText nameEditText;
    Button saveButton;

    PeriodControl periodControl = new PeriodControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_period);
        nameEditText = findViewById(R.id.edit_period_et);
        saveButton = findViewById(R.id.edit_period_save_btn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameEditText.getText().toString().trim().equalsIgnoreCase(""))
                    Toast.makeText(ActivityEditPeriod.this, "Por favor, lléne el campo.", Toast.LENGTH_LONG).show();
                else{

                }
            }
        });
    }
}
