package com.iteso.wapi;

import android.content.Context;
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

public class AdapterFragmentSchedule  extends RecyclerView.Adapter<AdapterFragmentSchedule.MyViewHolder>{
    private Context context;
    private ArrayList<Subject> mDataSet;
    private int day;

    public AdapterFragmentSchedule(Context context, ArrayList<Subject> mDataSet, int day) {
        this.context = context;
        this.mDataSet = mDataSet;
        this.day = day;
    }

    @NonNull
    @Override
    public AdapterFragmentSchedule.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_fragment_schedule_subject, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        DataBaseHandler dh = DataBaseHandler.getInstance(context);
        ScheduleControl scheduleControl = new ScheduleControl();

        final Subject subject = mDataSet.get(i);
        ArrayList<Schedule> schedules = scheduleControl.getSchedulesBySubject(subject.getIdSubject(), dh);

        ArrayList<Integer> days = new ArrayList<>();
        for(Schedule index : schedules){
            days.add(index.getDay());
        }

        int int_index = 0;

        for(Integer index : days){
            if(index == this.day){
                holder.title.setText(subject.getNameSubject());
                holder.time.setText(schedules.get(int_index).getInitialTime() + " - " + schedules.get(int_index).getFinalTime());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, time;
        CardView root;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.item_fragment_schedule_subject_root);
            title = itemView.findViewById(R.id.item_fragment_schedule_subject_title);
            time = itemView.findViewById(R.id.item_fragment_schedule_subject_time);
        }
    }

}
