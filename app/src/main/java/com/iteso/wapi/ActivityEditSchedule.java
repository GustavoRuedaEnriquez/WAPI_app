package com.iteso.wapi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iteso.wapi.database.DataBaseHandler;

public class ActivityEditSchedule extends AppCompatActivity {

    private Button addPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);

        addPeriod = findViewById(R.id.edit_schedule_add_period_btn);

        addPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPeriod.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
                addPeriod.setTextColor(Color.WHITE);
                Intent intent = new Intent(ActivityEditSchedule.this, ActivityEditPeriod.class);
                startActivity(intent);
            }
        });
    }
}
