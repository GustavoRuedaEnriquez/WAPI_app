package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Grade implements Parcelable {

    private Integer idTarea;
    private Integer fk_materia;
    private String descripcionTarea;
    private String nombreMateria;
    private String fecha;
    private String hora;

    public Grade(Integer idTarea, Integer fk_materia, String descripcionTarea, String nombreMateria, String fecha, String hora) {
        this.idTarea = idTarea;
        this.fk_materia = fk_materia;
        this.descripcionTarea = descripcionTarea;
        this.nombreMateria = nombreMateria;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Grade() {
    }

    @Override
    public String toString() {
        return "Grade{" +
                "idTarea=" + idTarea +
                ", fk_materia=" + fk_materia +
                ", descripcionTarea='" + descripcionTarea + '\'' +
                ", nombreMateria='" + nombreMateria + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Integer getFk_materia() {
        return fk_materia;
    }

    public void setFk_materia(Integer fk_materia) {
        this.fk_materia = fk_materia;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idTarea);
        dest.writeValue(this.fk_materia);
        dest.writeString(this.descripcionTarea);
        dest.writeString(this.nombreMateria);
        dest.writeString(this.fecha);
        dest.writeString(this.hora);
    }

    protected Grade(Parcel in) {
        this.idTarea = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fk_materia = (Integer) in.readValue(Integer.class.getClassLoader());
        this.descripcionTarea = in.readString();
        this.nombreMateria = in.readString();
        this.fecha = in.readString();
        this.hora = in.readString();
    }

    public static final Creator<Grade> CREATOR = new Creator<Grade>() {
        @Override
        public Grade createFromParcel(Parcel source) {
            return new Grade(source);
        }

        @Override
        public Grade[] newArray(int size) {
            return new Grade[size];
        }
    };
}
