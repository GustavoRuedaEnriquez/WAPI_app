package com.iteso.wapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iteso.wapi.beans.Homework;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterHomework extends RecyclerView.Adapter<AdapterHomework.MyViewHolder>{

    public List<Homework> homeworkList;
    private Context context;
    private int fragment;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView descripcion, materia, fecha, hora;

        MyViewHolder(View view){
            super(view);
            descripcion = view.findViewById(R.id.item_tarea_descripcion);
            materia = view.findViewById(R.id.item_tarea_materia);
            fecha = view.findViewById(R.id.item_tarea_fecha);
            hora = view.findViewById(R.id.item_tarea_hora);
        }
    }

    public AdapterHomework(List<Homework> homework){
        this.homeworkList = homework;
    }

    public AdapterHomework(int fragment, Context context, List<Homework> homework){
        this.fragment = fragment;
        this.context = context;
        this.homeworkList = homework;
    }

    public List<Homework> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<Homework> homework) {
        this.homeworkList = homework;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getFragment() {
        return fragment;
    }

    public void setFragment(int fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterHomework.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homework, parent, false);
        return new AdapterHomework.MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterHomework.MyViewHolder myViewHolder, int position){
        final Homework homework = homeworkList.get(position);
        Date deliveryDay = Calendar.getInstance().getTime();
        String dayString;
        //myViewHolder.nombre.setText(subjectList.get(myViewHolder.getAdapterPosition()).getNameSubject());
        //myViewHolder.promedio.setText(Float.toString(subjectList.get(myViewHolder.getAdapterPosition()).getAvarage()));
        myViewHolder.descripcion.setText(homework.getDescriptionHomework());
        switch(deliveryDay.getDay()){
            case 1:
                dayString = "Monday";
                break;
            case 2:
                dayString = "Tuesday";
                break;
            case 3:
                dayString = "Wendsday";
                break;
            case 4:
                dayString = "Thursday";
                break;
            case 5:
                dayString = "Friday";
                break;
            default:
                dayString = "Saturday";
                break;
        }
        myViewHolder.materia.setText("Materiaa");
        String dateToShow = dayString + " " +homework.getDeliveryDay()+"/"+homework.getDeliveryMonth();
        myViewHolder.fecha.setText(dateToShow);
        String hourToShow =homework.getDeliveryHour()+":"+homework.getDeliveryMin();
        myViewHolder.hora.setText(hourToShow);

    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }
}
