package com.iteso.wapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.wapi.beans.Grade;
import com.iteso.wapi.beans.Subject;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.GradeControl;
import com.iteso.wapi.database.SubjectControl;

import java.util.List;

public class AdapterGrade extends RecyclerView.Adapter<AdapterGrade.MyViewHolder>{

    public List<Grade> gradeList;
    private Context context;
    private int fragment;
    private Grade grade;
    private DataBaseHandler dh;
    private GradeControl gradeControl;
    private SubjectControl subjectControl;

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

    public AdapterGrade(List<Grade> grades){
        this.gradeList = grades;
    }

    public AdapterGrade(int fragment, Context context, List<Grade> grades){
        this.fragment = fragment;
        this.context = context;
        this.gradeList = grades;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setMateriaList(List<Grade> grades) {
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
    public AdapterGrade.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grade, parent, false);
        dh = DataBaseHandler.getInstance(getContext());
        gradeControl = new GradeControl();
        subjectControl = new SubjectControl();

        return new AdapterGrade.MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterGrade.MyViewHolder myViewHolder, int position){
        final Grade[] gradeShow = {gradeList.get(position)};
        //myViewHolder.nombre.setText(subjectList.get(myViewHolder.getAdapterPosition()).getNameSubject());
        //myViewHolder.promedio.setText(Float.toString(subjectList.get(myViewHolder.getAdapterPosition()).getPromedio()));
        myViewHolder.nombre.setText(gradeShow[0].getDescriptionGrade());
        myViewHolder.porcentaje.setText(Float.toString(gradeShow[0].getPercentage()));
        myViewHolder.calificacion.setText(Float.toString(gradeShow[0].getGrade()));

        myViewHolder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grade = gradeList.get(myViewHolder.getAdapterPosition());
                gradeControl.deleteGrade(grade.getIdGrade(),dh);
                gradeList.remove(myViewHolder.getAdapterPosition());
                notifyDataSetChanged();
                updateAvarageSubject();
            }
        });

        myViewHolder.nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                updateGrade(myViewHolder);
            }
        });
        myViewHolder.porcentaje.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                updateGrade(myViewHolder);
            }
        });
        myViewHolder.calificacion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                updateGrade(myViewHolder);
            }
        });
    }

    public void updateGrade(MyViewHolder myViewHolder){
        if((Float.valueOf(myViewHolder.porcentaje.getText().toString()) >=0.0 & Float.valueOf(myViewHolder.porcentaje.getText().toString()) <=100.0)
                & (Float.valueOf(myViewHolder.porcentaje.getText().toString()) >=0.0 & Float.valueOf(myViewHolder.porcentaje.getText().toString()) <=100.0)){
            if(validatePercentage()){
            grade = gradeList.get(myViewHolder.getAdapterPosition());
            grade.setDescriptionGrade(myViewHolder.nombre.getText().toString());
            grade.setPercentage(Float.valueOf(myViewHolder.porcentaje.getText().toString()));
            grade.setGrade(Float.valueOf(myViewHolder.calificacion.getText().toString()));
            gradeControl.updateGrade(grade, dh);
            gradeList.set(myViewHolder.getAdapterPosition(),grade);
            updateAvarageSubject();
            }
            else {
                Toast.makeText(getContext(), "Total de porcentajes erroneos", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getContext(), "Error en datos ingresados", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateAvarageSubject(){
        float avarage = 0;
        Grade auxGrade = new Grade();
        for(int x = 0; x<gradeList.size();x++){
            auxGrade = gradeList.get(x);
            avarage += (auxGrade.getGrade() * (auxGrade.getPercentage()/100));
        }
        subjectControl.updateSubjectById(auxGrade.getFk_subject(),avarage, dh);
    }

    public boolean validatePercentage(){
        float totalPercentage = 0;
        Grade auxGrade = new Grade();
        for(int x = 0; x<gradeList.size();x++){
            auxGrade = gradeList.get(x);
            totalPercentage += auxGrade.getPercentage();
        }
        if(totalPercentage<=100) return true;
        else return false;
    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }

}
