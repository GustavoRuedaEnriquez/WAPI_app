package com.iteso.wapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iteso.wapi.beans.Tarea;

import java.util.List;

public class AdapterTarea extends RecyclerView.Adapter<AdapterTarea.MyViewHolder>{

    public List<Tarea> tareaList;
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

    public AdapterTarea(List<Tarea> tareas){
        this.tareaList = tareas;
    }

    public AdapterTarea(int fragment, Context context, List<Tarea> tareas){
        this.fragment = fragment;
        this.context = context;
        this.tareaList = tareas;
    }

    public List<Tarea> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tarea> tareas) {
        this.tareaList = tareas;
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
    public AdapterTarea.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new AdapterTarea.MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterTarea.MyViewHolder myViewHolder, int position){
        final Tarea tarea = tareaList.get(position);
        //myViewHolder.nombre.setText(materiaList.get(myViewHolder.getAdapterPosition()).getNombreMateria());
        //myViewHolder.promedio.setText(Float.toString(materiaList.get(myViewHolder.getAdapterPosition()).getPromedio()));
        myViewHolder.descripcion.setText(tarea.getDescripcionTarea());
        myViewHolder.materia.setText(tarea.getNombreMateria());
        myViewHolder.fecha.setText(tarea.getFecha());
        myViewHolder.hora.setText(tarea.getHora());

    }

    @Override
    public int getItemCount() {
        return tareaList.size();
    }
}
