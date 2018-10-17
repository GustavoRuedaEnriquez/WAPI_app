package com.iteso.wapi;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.iteso.wapi.beans.Materia;

import java.util.List;

public class AdapterMateria extends RecyclerView.Adapter<AdapterMateria.MyViewHolder>{

    public List<Materia> materiaList;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView fullname,email;
        ImageView avatar;

        MyViewHolder(View view){
            super(view);
            fullname = view.findViewById(R.id.item_fullname);
            email = view.findViewById(R.id.item_user_mail);
            avatar = view.findViewById(R.id.item_avatar);
        }
    }

    public AdapterMateria(List<Materia> materias){
        this.materiaList = materias;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,int position){
        Materia materia = materiaList.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
