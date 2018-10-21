package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Calificacion implements Parcelable {

    private Integer idCalificacion;
    private Integer fk_materia;
    private String descripcionCalificacion;
    private float porcentaje;
    private float calificacion;



    public Calificacion(Integer idCalificacion, Integer fk_materia, String descripcionCalificacion, float porcentaje, float calificacion) {
        this.idCalificacion = idCalificacion;
        this.fk_materia = fk_materia;
        this.descripcionCalificacion = descripcionCalificacion;
        this.porcentaje = porcentaje;
        this.calificacion = calificacion;
    }

    public Calificacion() {

    }

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getDescripcionCalificacion() {
        return descripcionCalificacion;
    }

    public void setDescripcionCalificacion(String descripcionCalificacion) {
        this.descripcionCalificacion = descripcionCalificacion;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getFk_materia() {
        return fk_materia;
    }

    public void setFk_materia(Integer fk_materia) {
        this.fk_materia = fk_materia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idCalificacion);
        dest.writeInt(this.fk_materia);
        dest.writeString(this.descripcionCalificacion);
        dest.writeFloat(this.porcentaje);
        dest.writeFloat(this.calificacion);
    }

    protected Calificacion(Parcel in) {
        this.idCalificacion = in.readInt();
        this.fk_materia = in.readInt();
        this.descripcionCalificacion = in.readString();
        this.porcentaje = in.readFloat();
        this.calificacion = in.readFloat();
    }

    public static final Creator<Calificacion> CREATOR = new Creator<Calificacion>() {
        @Override
        public Calificacion createFromParcel(Parcel source) {
            return new Calificacion(source);
        }

        @Override
        public Calificacion[] newArray(int size) {
            return new Calificacion[size];
        }
    };
}
