package com.iteso.wapi.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iteso.wapi.ActivitySplashscreen;
import com.iteso.wapi.R;
import com.iteso.wapi.beans.Payment;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.PaymentControl;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class FragmentPayment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private EditText title, description, amount, day, month, year;
    private Button remind;

    PaymentControl paymentControl = new PaymentControl();

    public FragmentPayment() {
        // Required empty public constructor
    }

    public static FragmentPayment newInstance(String param1, String param2) {
        FragmentPayment fragment = new FragmentPayment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ActivitySplashscreen.MY_PREFERENCES, MODE_PRIVATE);

        View v = inflater.inflate(R.layout.fragment_payment, container, false);
        title = v.findViewById(R.id.fragment_payment_name);
        description = v.findViewById(R.id.fragment_payment_description);
        amount = v.findViewById(R.id.fragment_payment_amount);
        day = v.findViewById(R.id.fragment_payment_day);
        month = v.findViewById(R.id.fragment_payment_month);
        year = v.findViewById(R.id.fragment_payment_year);
        remind = v.findViewById(R.id.fragment_payment_pay_button);

        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().trim().equalsIgnoreCase("") ||
                        description.getText().toString().trim().equalsIgnoreCase("") ||
                        amount.getText().toString().trim().equalsIgnoreCase("") ||
                        day.getText().toString().trim().equalsIgnoreCase("") ||
                        month.getText().toString().trim().equalsIgnoreCase("") ||
                        year.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Se tienen que llenar todos los campos.", Toast.LENGTH_LONG).show();
                } else {
                    double realAmount = Double.parseDouble(amount.getText().toString());
                    int realDay = Integer.parseInt(day.getText().toString());
                    int realMonth = Integer.parseInt(month.getText().toString());
                    int realYear = Integer.parseInt(year.getText().toString());
                    Payment payment = new Payment(paymentControl.maxIdPayment(dh),title.getText().toString(),description.getText().toString(),realAmount,realDay,realMonth,realYear, sharedPreferences.getString("NAME", "Default name"));
                    Log.e("PAYMENT", "Payment Object: " + payment.toString());
                    paymentControl.addPayment(payment, dh);

                    ArrayList<Payment> payments = new ArrayList<>();
                    payments = paymentControl.getPayments(dh);
                    Log.e("PAYMENT", "Payment Table: " + payment);
                }
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
