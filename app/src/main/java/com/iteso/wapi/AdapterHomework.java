package com.iteso.wapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.iteso.wapi.beans.Homework;
import com.iteso.wapi.database.DataBaseHandler;
import com.iteso.wapi.database.HomeworkControl;
import java.util.Date;
import java.util.List;

public class AdapterHomework extends RecyclerView.Adapter<AdapterHomework.MyViewHolder>{

    public List<Homework> homeworkList;
    public HomeworkControl homeworkControl;
    public Homework homework;
    public DataBaseHandler dh;
    private Context context;
    private int fragment;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView descripcion, materia, fecha, hora;
        ImageView erase;

        MyViewHolder(View view){
            super(view);
            descripcion = view.findViewById(R.id.item_tarea_descripcion);
            materia = view.findViewById(R.id.item_tarea_materia);
            fecha = view.findViewById(R.id.item_tarea_fecha);
            hora = view.findViewById(R.id.item_tarea_hora);
            erase = view.findViewById(R.id.item_tarea_erase);
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
        homework = homeworkList.get(position);
        homeworkControl = new HomeworkControl();
        dh = DataBaseHandler.getInstance(getContext());
        Date deliveryDay = new Date(homework.getDeliveryYear()- 1900,homework.getDeliveryMonth()-1,homework.getDeliveryDay());

        String dayString;
        //myViewHolder.nombre.setText(subjectList.get(myViewHolder.getAdapterPosition()).getNameSubject());
        //myViewHolder.promedio.setText(Float.toString(subjectList.get(myViewHolder.getAdapterPosition()).getAvarage()));
        myViewHolder.descripcion.setText(homework.getDescriptionHomework());
        switch(deliveryDay.getDay()){
            case 0:
                dayString = "Sunday";
                break;
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
        myViewHolder.materia.setText(homeworkControl.getHomeworksSubjectName(homework.getFk_subject(), dh));
        String dateToShow = dayString + " " +homework.getDeliveryDay()+"/"+homework.getDeliveryMonth();
        myViewHolder.fecha.setText(dateToShow);
        String hourToShow =homework.getDeliveryHour()+":";
        if(homework.getDeliveryMin()<10){
            hourToShow += "0";
        }
        hourToShow += homework.getDeliveryMin();
        myViewHolder.hora.setText(hourToShow);

        myViewHolder.erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homework = homeworkList.get(myViewHolder.getAdapterPosition());
                homeworkControl.deleteHomework(homework.getIdHomework(),dh);
                homeworkList.remove(myViewHolder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }
}
