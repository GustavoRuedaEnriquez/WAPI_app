package com.iteso.wapi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iteso.wapi.beans.Schedule;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.ScheduleControl;

import java.util.ArrayList;

public class AdapterEditSchedule extends RecyclerView.Adapter<AdapterEditSchedule.MyViewHolder> {
    private Context context;
    private ArrayList<Subject> mDataSet;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_edit_schedule_subject, viewGroup, false);

        return new MyViewHolder(view);
    }

    public AdapterEditSchedule(Context context, ArrayList<Subject> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        DataBaseHandler dh = DataBaseHandler.getInstance(context);
        ScheduleControl scheduleControl = new ScheduleControl();

        final Subject subject = mDataSet.get(i);
        ArrayList<Schedule> schedules = scheduleControl.getSchedulesBySubject(subject.getIdSubject(), dh);

        holder.title.setText(subject.getNameSubject());

        if (schedules.size() > 0) {
            //Time
            StringBuilder sc = new StringBuilder();
            sc.append(" ");
            int initialTime = schedules.get(0).getInitialTime();
            sc.append(initialTime / 100);
            sc.append(":");
            sc.append(String.format("%02d", initialTime % 100));

            sc.append(" - ");

            int finalTime = schedules.get(0).getFinalTime();
            sc.append(finalTime / 100);
            sc.append(":");
            sc.append(String.format("%02d", finalTime % 100));
            holder.time.setText(sc.toString());

            //Days
            sc = new StringBuilder();
            sc.append(" ");
            for (Schedule schedule : schedules) {
                switch (schedule.getDay()) {
                    case 0:
                        sc.append(context.getString(R.string.activity_subject_monday));
                        break;
                    case 1:
                        sc.append(context.getString(R.string.activity_subject_tuesday));
                        break;
                    case 2:
                        sc.append(context.getString(R.string.activity_subject_wednesday));
                        break;
                    case 3:
                        sc.append(context.getString(R.string.activity_subject_thursday));
                        break;
                    case 4:
                        sc.append(context.getString(R.string.activity_subject_friday));
                        break;
                    case 5:
                        sc.append(context.getString(R.string.activity_subject_saturday));
                        break;
                }

                sc.append(", ");
            }
            sc.deleteCharAt(sc.length() - 2);
            holder.days.setText(sc.toString());
        }

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityEditSubject.class);
                intent.putExtra("SUBJECT", subject);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, time, days;
        CardView root;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_edit_schedule_subject_root);
            title = itemView.findViewById(R.id.item_edit_schedule_subject_title);
            time = itemView.findViewById(R.id.item_edit_schedule_subject_time);
            days = itemView.findViewById(R.id.item_edit_schedule_subject_days);
        }
    }
}
