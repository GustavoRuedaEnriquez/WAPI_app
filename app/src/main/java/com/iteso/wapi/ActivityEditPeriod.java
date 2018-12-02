package com.iteso.wapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iteso.wapi.beans.Period;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.PeriodControl;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityEditPeriod extends AppCompatActivity {

    private EditText nameEditText;
    private Button saveButton;
    PeriodControl periodControl = new PeriodControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_period);

        final DataBaseHandler dh = DataBaseHandler.getInstance(ActivityEditPeriod.this);
        final SharedPreferences sharedPreferences = this.getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        nameEditText = findViewById(R.id.edit_period_et);
        saveButton = findViewById(R.id.edit_period_save_btn);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveButton.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
                saveButton.setTextColor(Color.WHITE);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        saveButton.setBackground(getDrawable(R.drawable.custom_blue_light_btn));
                        saveButton.setTextColor(getColor(R.color.colorPrimary));
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 190);

                if (nameEditText.getText().toString().trim().equalsIgnoreCase(""))
                    Toast.makeText(ActivityEditPeriod.this, "Por favor, lléne el campo.", Toast.LENGTH_LONG).show();
                else {
                    Period newPeriod = new Period(periodControl.maxIdPeriod(dh), nameEditText.getText().toString(), sharedPreferences.getString("NAME", "Default name"));
                    periodControl.addPeriod(newPeriod, dh);

                    Intent intent = new Intent(ActivityEditPeriod.this, ActivityEditSchedule.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityEditPeriod.this, ActivityEditSchedule.class);
        startActivity(intent);
        finish();
    }
}
