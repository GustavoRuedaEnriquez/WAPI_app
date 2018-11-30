package com.iteso.wapi.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iteso.wapi.ActivityLogin;
import com.iteso.wapi.ActivitySplashscreen;
import com.iteso.wapi.R;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.StudentControl;

import static android.content.Context.MODE_PRIVATE;


public class FragmentConfiguration extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Button logout, deleteAccount;
    private EditText name_et, password_et;
    private StudentControl studentControl = new StudentControl();

    private String mParam1;
    private String mParam2;

    public FragmentConfiguration() {
        // Required empty public constructor
    }

    public static FragmentConfiguration newInstance(String param1, String param2) {
        FragmentConfiguration fragment = new FragmentConfiguration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        View v = inflater.inflate(R.layout.fragment_configuration, container, false);
        logout = v.findViewById(R.id.fragment_configuration_log_out_btn);
        name_et = v.findViewById(R.id.fragment_configuration_change_name_et);
        password_et = v.findViewById(R.id.fragment_configuration_change_password_et);
        deleteAccount = v.findViewById(R.id.fragment_configuration_erase_account_btn);


        name_et.setText(sharedPreferences.getString("NAME", "Default name"));
        password_et.setText(sharedPreferences.getString("PWD", "Default password"));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout.setBackground(getResources().getDrawable(R.drawable.custom_selected_blue_light_btn));
                logout.setTextColor(Color.WHITE);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NAME", "");
                editor.putString("PWD", "");
                editor.putBoolean("LOGGED", false);
                editor.apply();

                Intent logOutIntent = new Intent(getActivity(), ActivityLogin.class);
                startActivity(logOutIntent);
                getActivity().finish();
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount.setBackground(getResources().getDrawable(R.drawable.custom_selected_red_light_btn));
                deleteAccount.setTextColor(Color.WHITE);
                studentControl.deleteStudent(sharedPreferences.getString("NAME", "Default name"), dh);
                Intent logOutIntent = new Intent(getActivity(), ActivityLogin.class);
                startActivity(logOutIntent);
                getActivity().finish();

            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
