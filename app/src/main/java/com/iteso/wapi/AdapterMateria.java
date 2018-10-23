package com.iteso.wapi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.wapi.beans.Materia;

import java.util.List;

public class AdapterMateria extends RecyclerView.Adapter<AdapterMateria.MyViewHolder>{

    public List<Materia> materiaList;
    private Context context;
    private int fragment;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, promedio;
        ImageView editar;

        MyViewHolder(View view){
            super(view);
            nombre = view.findViewById(R.id.item_materia_nombre);
            promedio = view.findViewById(R.id.item_materia_promedio);
            editar = view.findViewById(R.id.item_materia_editar);
        }
    }

    public AdapterMateria(List<Materia> materias){
        this.materiaList = materias;
    }

    public AdapterMateria(int fragment, Context context, List<Materia> materias){
        this.fragment = fragment;
        this.context = context;
        this.materiaList = materias;
    }

    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materia, parent, false);
        return new MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position){
        Materia materia = materiaList.get(position);
        //myViewHolder.nombre.setText(materiaList.get(myViewHolder.getAdapterPosition()).getNombreMateria());
        //myViewHolder.promedio.setText(Float.toString(materiaList.get(myViewHolder.getAdapterPosition()).getPromedio()));
        myViewHolder.nombre.setText(materia.getNombreMateria());
        myViewHolder.promedio.setText(Float.toString(materia.getPromedio()));

        myViewHolder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityEditGrade.class);
                intent.putExtra("Materia", materiaList.get(myViewHolder.getAdapterPosition()));
                intent.putExtra("Fragment", fragment);
                ((ActivityHome) context).startActivityForResult(intent, 999);
            }
        });
    }

    @Override
    public int getItemCount() {
        return materiaList.size();
    }



}
