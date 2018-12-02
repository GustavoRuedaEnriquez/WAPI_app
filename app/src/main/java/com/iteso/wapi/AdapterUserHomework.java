package com.iteso.wapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iteso.wapi.beans.Homework;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.SubjectControl;

import java.util.ArrayList;

public class AdapterUserHomework extends RecyclerView.Adapter<AdapterUserHomework.MyViewHolder> {

    private Context context;
    private ArrayList<Homework> mDataSet;

    public AdapterUserHomework(Context context, ArrayList<Homework> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_user_homework, viewGroup, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DataBaseHandler dh = DataBaseHandler.getInstance(context);
        SubjectControl subjectControl = new SubjectControl();
        Homework homework = mDataSet.get(i);
        if(homework == null)
            Log.e("WAPI", "Es nulo");
        Subject subject = subjectControl.getSubjectById(homework.getFk_subject(), dh);

        myViewHolder.subjectTV.setText(subject.getNameSubject());
        myViewHolder.timeTV.setText(String.format("%d:%02d", homework.getDeliveryHour(), homework.getDeliveryMin()));
        myViewHolder.descriptionTV.setText(homework.getDescriptionHomework());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subjectTV, timeTV, descriptionTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTV = itemView.findViewById(R.id.item_user_homework_subject);
            timeTV = itemView.findViewById(R.id.item_user_homework_time);
            descriptionTV = itemView.findViewById(R.id.item_user_homework_description);
        }
    }
}
