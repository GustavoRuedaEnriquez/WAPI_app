package com.iteso.wapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.wapi.beans.Calificacion;

import java.util.List;

public class AdapterGrade extends RecyclerView.Adapter<AdapterGrade.MyViewHolder>{

public List<Calificacion> calificacionList;
private Context context;
private int fragment;

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView nombre, porcentaje, calificacion;
    ImageView borrar;

    MyViewHolder(View view){
        super(view);
        nombre = view.findViewById(R.id.item_calificacion_nombre);
        porcentaje = view.findViewById(R.id.item_calificacion_porcentaje);
        calificacion = view.findViewById(R.id.item_calificacion_calif);
        borrar = view.findViewById(R.id.item_calificacion_borrar);
    }
}

    public AdapterGrade(List<Calificacion> calificacions){
        this.calificacionList = calificacions;
    }

    public AdapterGrade(int fragment, Context context, List<Calificacion> calificacions){
        this.fragment = fragment;
        this.context = context;
        this.calificacionList = calificacions;
    }

    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setMateriaList(List<Calificacion> calificacions) {
        this.calificacionList = calificacions;
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
    public AdapterGrade.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grade, parent, false);
        return new AdapterGrade.MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterGrade.MyViewHolder myViewHolder, int position){
        final Calificacion calificacion = calificacionList.get(position);
        //myViewHolder.nombre.setText(materiaList.get(myViewHolder.getAdapterPosition()).getNombreMateria());
        //myViewHolder.promedio.setText(Float.toString(materiaList.get(myViewHolder.getAdapterPosition()).getPromedio()));
        myViewHolder.nombre.setText(calificacion.getDescripcionCalificacion());
        myViewHolder.porcentaje.setText(Float.toString(calificacion.getPorcentaje()));
        myViewHolder.calificacion.setText(Float.toString(calificacion.getCalificacion()));

        myViewHolder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calificacionList.remove(myViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return calificacionList.size();
    }

}
