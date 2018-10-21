package com.iteso.wapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMain extends AppCompatActivity {

    /*******************Botón TEMPORAL*******************/
    Button logout;
    /****************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*********************PARTE TEMPORAL************************/
        logout = findViewById(R.id.activity_main_log_out_btn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NAME", "");
                editor.putString("PWD", "");
                editor.putBoolean("LOGGED", false);
                editor.apply();

                Intent logOutIntent = new Intent(ActivityMain.this, ActivityLogin.class);
                startActivity(logOutIntent);
                finish();
            }
        });
        /***********************************************************/

    }
}
