package com.iteso.wapi;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.iteso.wapi.beans.Materia;

import java.util.List;

public class AdapterMateria extends RecyclerView.Adapter<AdapterMateria.MyViewHolder>{

    public List<Materia> materiaList;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, promedio;

        MyViewHolder(View view){
            super(view);
            nombre = view.findViewById(R.id.item_materia_nombre);
            promedio = view.findViewById(R.id.item_materia_promedio);
        }
    }

    public AdapterMateria(List<Materia> materias){
        this.materiaList = materias;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materia, parent, false);
        return new MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,int position){
        Materia materia = materiaList.get(position);
        myViewHolder.nombre.setText(materia.getNombreMateria());
        myViewHolder.promedio.setText(Float.toString(materia.getPromedio()));
    }

    @Override
    public int getItemCount() {
        return materiaList.size();
    }



}
