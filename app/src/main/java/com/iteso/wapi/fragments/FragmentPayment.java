package com.iteso.wapi.fragments;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.iteso.wapi.ActivityHome;
import com.iteso.wapi.ActivitySplashscreen;
import com.iteso.wapi.R;
import com.iteso.wapi.beans.Payment;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.PaymentControl;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;


public class FragmentPayment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    long timestamp;

    private EditText title, description, amount, date, hour;
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
        date = v.findViewById(R.id.fragment_payment_date);
        hour = v.findViewById(R.id.fragment_payment_hour);
        remind = v.findViewById(R.id.fragment_payment_pay_button);

        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remind.setBackground(getResources().getDrawable(R.drawable.custom_selected_blue_light_btn));
                remind.setTextColor(Color.WHITE);
                TimerTask task= new TimerTask(){
                    @Override
                    public void run(){
                        remind.setBackground(getResources().getDrawable(R.drawable.custom_blue_light_btn));
                        remind.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 500);
                if (title.getText().toString().trim().equalsIgnoreCase("") ||
                        description.getText().toString().trim().equalsIgnoreCase("") ||
                        amount.getText().toString().trim().equalsIgnoreCase("") ||
                        date.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.fragment_payment_warning_toast), Toast.LENGTH_LONG).show();
                } else {
                    String[] dateTrimmed = date.getText().toString().split("/");
                    String[] hourTrimmed = hour.getText().toString().split(":");
                    Calendar c = Calendar.getInstance();
                    c.set(Integer.parseInt(dateTrimmed[2]), Integer.parseInt(dateTrimmed[1]) - 1, Integer.parseInt(dateTrimmed[0]), Integer.parseInt(hourTrimmed[0]), Integer.parseInt(hourTrimmed[1]), Integer.parseInt(hourTrimmed[2]));
                    timestamp = c.getTimeInMillis();

                    Payment payment = new Payment(paymentControl.maxIdPayment(dh), title.getText().toString(), description.getText().toString(), Double.parseDouble(amount.getText().toString()), Long.toString(timestamp), sharedPreferences.getString("NAME", "Default name"));
                    paymentControl.addPayment(payment, dh);
                    createNotification(payment);
                }
            }
        });
        return v;
    }

    public void createNotification(Payment payment){
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("WAPI_Channel", "WAPI_CH", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(notificationChannel);

        Intent intent = new Intent(getActivity(), ActivityHome.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), (int) Long.parseLong(payment.getTimestamp()), intent, 0);
        Notification n = new Notification.Builder(getActivity())
                .setContentTitle(getResources().getString(R.string.fragment_payment_reminder_title) + " " + payment.getName())
                .setContentText(payment.getAmount() + "\n" + payment.getDescription())
                .setSmallIcon(R.drawable.logo_wapi)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setChannelId("WAPI_Channel")
                .build();

        notificationManager.notify(0,n);
        Toast.makeText(getActivity(),getResources().getString(R.string.fragment_payment_reminder_toast), Toast.LENGTH_LONG).show();
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
