package com.iteso.wapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iteso.wapi.beans.Grade;

import java.util.List;

public class AdapterHomework extends RecyclerView.Adapter<AdapterHomework.MyViewHolder>{

    public List<Grade> gradeList;
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

    public AdapterHomework(List<Grade> grades){
        this.gradeList = grades;
    }

    public AdapterHomework(int fragment, Context context, List<Grade> grades){
        this.fragment = fragment;
        this.context = context;
        this.gradeList = grades;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> grades) {
        this.gradeList = grades;
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
        final Grade grade = gradeList.get(position);
        //myViewHolder.nombre.setText(materiaList.get(myViewHolder.getAdapterPosition()).getNombreMateria());
        //myViewHolder.promedio.setText(Float.toString(materiaList.get(myViewHolder.getAdapterPosition()).getPromedio()));
        myViewHolder.descripcion.setText(grade.getDescripcionTarea());
        myViewHolder.materia.setText(grade.getNombreMateria());
        myViewHolder.fecha.setText(grade.getFecha());
        myViewHolder.hora.setText(grade.getHora());

    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }
}
