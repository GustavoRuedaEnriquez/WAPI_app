package com.iteso.wapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.iteso.wapi.beans.Student;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashscreen extends AppCompatActivity {

    public static final String MY_PREFERENCES = "com.iteso.wapi.PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splashscreen);

        TimerTask task= new TimerTask(){
            @Override
            public void run() {
                Student student = loadPreferences();
                if(student.isLogged()){
                    Intent intent = new Intent(ActivitySplashscreen.this,ActivityHome.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(ActivitySplashscreen.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }

    public Student loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
        Student student = new Student();
        student.setName(sharedPreferences.getString("NAME","UNKNOWN"));
        student.setPassword(sharedPreferences.getString("PWD","1234"));
        student.setLogged(sharedPreferences.getBoolean("LOGGED",false));
        return student;
    }
}
