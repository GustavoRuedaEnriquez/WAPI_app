package com.iteso.wapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.iteso.wapi.beans.Student;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.StudentControl;

public class ActivityRegister extends AppCompatActivity {

    EditText username, password, school;
    CheckBox terms;
    Button register;

    StudentControl studentControl = new StudentControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DataBaseHandler dh = DataBaseHandler.getInstance(ActivityRegister.this);

        username = findViewById(R.id.activity_register_name);
        password = findViewById(R.id.activity_register_pass);
        terms    = findViewById(R.id.activity_register_terms);
        register = findViewById(R.id.activity_register_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
                register.setTextColor(Color.WHITE);
                Student student = new Student(username.getText().toString(), password.getText().toString(), true);
                studentControl.addStudent(student, dh);
                savePreferences();
                Intent intent = new Intent(ActivityRegister.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void savePreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", username.getText().toString());
        editor.putString("PWD", password.getText().toString());
        editor.putBoolean("LOGGED", true);
        editor.apply();
    }
}
