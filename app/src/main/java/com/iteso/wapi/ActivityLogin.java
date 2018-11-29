package com.iteso.wapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.wapi.beans.Student;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.StudentControl;

import java.util.ArrayList;

public class ActivityLogin extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView register_link;
    StudentControl studentControl = new StudentControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DataBaseHandler dh = DataBaseHandler.getInstance(ActivityLogin.this);

        username = findViewById(R.id.activity_login_user_et);
        password = findViewById(R.id.activity_login_password_et);
        login = findViewById(R.id.activity_login_access_btn);
        register_link = findViewById(R.id.activity_login_register_link);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                login.setBackground(getDrawable(R.drawable.custom_selected_blue_light_btn));
                login.setTextColor(Color.WHITE);

                if(username.getText().toString().trim().equalsIgnoreCase("") || password.getText().toString().trim().equalsIgnoreCase("")){
                    Toast.makeText(ActivityLogin.this, "Se tienen que llenar todos los campos.", Toast.LENGTH_LONG).show();
                    login.setBackground(getDrawable(R.drawable.custom_blue_light_btn));
                    login.setTextColor(getColor(R.color.colorPrimary));
                }else{
                    Student student = studentControl.getStudentByUsername(username.getText().toString(),dh);
                    if(student.getUserName().equals(""))
                        Toast.makeText(ActivityLogin.this, "Lo sentimos, el usuario no existe en la base de datos.", Toast.LENGTH_LONG).show();
                    else{
                        if(studentControl.isPasswordCorrect(username.getText().toString(), password.getText().toString(),dh)){
                            savePreferences();
                            Intent intent = new Intent(ActivityLogin.this,ActivityHome.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(ActivityLogin.this, "Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });

        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_link.setTextColor(getColor(R.color.colorPrimaryDark));
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void savePreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", username.getText().toString());
        editor.putString("PWD", password.getText().toString());
        editor.putBoolean("LOGGED", true);
        editor.apply();
    }
}
