package com.iteso.wapi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

public class ActivityRegister extends AppCompatActivity {

    EditText username, password, school;
    CheckBox terms;
    Button register;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.activity_register_name);
        password = findViewById(R.id.activity_register_pass);
        school   = findViewById(R.id.activity_register_school);
        terms    = findViewById(R.id.activity_register_terms);
        register = findViewById(R.id.activity_register_register);
        back     = findViewById(R.id.activity_register_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
                register.setTextColor(Color.WHITE);
                Intent intent = new Intent(ActivityRegister.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
