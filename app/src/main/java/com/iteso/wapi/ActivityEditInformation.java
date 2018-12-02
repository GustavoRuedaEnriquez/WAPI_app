package com.iteso.wapi;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iteso.wapi.beans.Period;
import com.iteso.wapi.beans.Student;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.PaymentControl;
import com.iteso.wapi.database.PeriodControl;
import com.iteso.wapi.database.StudentControl;

import java.util.ArrayList;

public class ActivityEditInformation extends AppCompatActivity {

    private EditText name, newPassword, confirmation;
    private Button save;
    private Student newStudent;

    private String oldStudent;

    private StudentControl studentControl =  new StudentControl();
    private PeriodControl  periodControl  =  new PeriodControl();
    private PaymentControl paymentControl =  new PaymentControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DataBaseHandler dh = DataBaseHandler.getInstance(ActivityEditInformation.this);
        final SharedPreferences sharedPreferences = this.getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        setContentView(R.layout.activity_edit_information);
        name = findViewById(R.id.activit_edit_info_name_et);
        newPassword = findViewById(R.id.activit_edit_info_password_et);
        confirmation = findViewById(R.id.activit_edit_info_confirm_password_et);
        save = findViewById(R.id.activity_edit_info_save_btn);

        name.setText(sharedPreferences.getString("NAME", "Default name"));
        oldStudent = sharedPreferences.getString("NAME", "Default name");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save.setBackground(getResources().getDrawable(R.drawable.custom_selected_blue_light_btn));
                save.setTextColor(Color.WHITE);
                newStudent = new Student("","", true);

                if(!name.getText().toString().trim().equalsIgnoreCase(""))
                    newStudent.setUserName(name.getText().toString());
                else
                    newStudent.setUserName(sharedPreferences.getString("NAME", "Default name"));

                if(!newPassword.getText().toString().trim().equalsIgnoreCase("")){
                    if(newPassword.getText().toString().equals(confirmation.getText().toString())){
                        newStudent.setPassword(newPassword.getText().toString());
                    }
                    else{
                        save.setBackground(getResources().getDrawable(R.drawable.custom_blue_light_btn));
                        save.setTextColor(getResources().getColor(R.color.colorPrimary));
                        Toast.makeText(ActivityEditInformation.this, getResources().getString(R.string.activity_edit_info_toast),Toast.LENGTH_LONG).show();
                        newStudent.setPassword(sharedPreferences.getString("PWD", "Default name"));
                    }

                }else{
                    newStudent.setPassword(sharedPreferences.getString("PWD", "Default name"));
                }
                studentControl.updateStudent(oldStudent, newStudent, dh);
                paymentControl.updateFKUsername(oldStudent, newStudent.getUserName(),dh);
                periodControl.updateFKUsername(oldStudent, newStudent.getUserName(),dh);
                savePreferences(newStudent);
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void savePreferences(Student student) {
        SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", student.getUserName());
        editor.putString("PWD", student.getPassword());
        editor.putBoolean("LOGGED", true);
        editor.apply();
    }
}
