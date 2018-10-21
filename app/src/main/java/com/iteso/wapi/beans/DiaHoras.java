package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class DiaHoras implements Parcelable{

    private Integer idDiaHoras;
    private Integer inicioLunes;
    private Integer finLunes;
    private Integer inicioMartes;
    private Integer finMartes;
    private Integer inicioMiercoles;
    private Integer finMiercoles;
    private Integer inicioJueves;
    private Integer finJueves;
    private Integer inicioViernes;
    private Integer finViernes;
    private Integer inicioSabado;
    private Integer finSabado;


    public DiaHoras(Integer idDiaHoras, Integer inicioLunes, Integer finLunes, Integer inicioMartes,
                    Integer finMartes, Integer inicioMiercoles, Integer finMiercoles,
                    Integer inicioJueves, Integer finJueves, Integer inicioViernes,
                    Integer finViernes, Integer inicioSabado, Integer finSabado) {
        this.idDiaHoras = idDiaHoras;
        this.inicioLunes = inicioLunes;
        this.finLunes = finLunes;
        this.inicioMartes = inicioMartes;
        this.finMartes = finMartes;
        this.inicioMiercoles = inicioMiercoles;
        this.finMiercoles = finMiercoles;
        this.inicioJueves = inicioJueves;
        this.finJueves = finJueves;
        this.inicioViernes = inicioViernes;
        this.finViernes = finViernes;
        this.inicioSabado = inicioSabado;
        this.finSabado = finSabado;
    }

    public DiaHoras() {
        this.idDiaHoras = null;
        this.inicioLunes = null;
        this.finLunes = null;
        this.inicioMartes = null;
        this.finMartes = null;
        this.inicioMiercoles = null;
        this.finMiercoles = null;
        this.inicioJueves = null;
        this.finJueves = null;
        this.inicioViernes = null;
        this.finViernes = null;
        this.inicioSabado = null;
        this.finSabado = null;
    }

    public int getIdDiaHoras() {
        return idDiaHoras;
    }

    public void setIdDiaHoras(int idDiaHoras) {
        this.idDiaHoras = idDiaHoras;
    }

    public int getInicioLunes() {
        return inicioLunes;
    }

    public void setInicioLunes(int inicioLunes) {
        this.inicioLunes = inicioLunes;
    }

    public int getFinLunes() {
        return finLunes;
    }

    public void setFinLunes(int finLunes) {
        this.finLunes = finLunes;
    }

    public int getInicioMartes() {
        return inicioMartes;
    }

    public void setInicioMartes(int inicioMartes) {
        this.inicioMartes = inicioMartes;
    }

    public int getFinMartes() {
        return finMartes;
    }

    public void setFinMartes(int finMartes) {
        this.finMartes = finMartes;
    }

    public int getInicioMiercoles() {
        return inicioMiercoles;
    }

    public void setInicioMiercoles(int inicioMiercoles) {
        this.inicioMiercoles = inicioMiercoles;
    }

    public int getFinMiercoles() {
        return finMiercoles;
    }

    public void setFinMiercoles(int finMiercoles) {
        this.finMiercoles = finMiercoles;
    }

    public int getInicioJueves() {
        return inicioJueves;
    }

    public void setInicioJueves(int inicioJueves) {
        this.inicioJueves = inicioJueves;
    }

    public int getFinJueves() {
        return finJueves;
    }

    public void setFinJueves(int finJueves) {
        this.finJueves = finJueves;
    }

    public int getInicioViernes() {
        return inicioViernes;
    }

    public void setInicioViernes(int inicioViernes) {
        this.inicioViernes = inicioViernes;
    }

    public int getFinViernes() {
        return finViernes;
    }

    public void setFinViernes(int finViernes) {
        this.finViernes = finViernes;
    }

    public int getInicioSabado() {
        return inicioSabado;
    }

    public void setInicioSabado(int inicioSabado) {
        this.inicioSabado = inicioSabado;
    }

    public int getFinSabado() {
        return finSabado;
    }

    public void setFinSabado(int finSabado) {
        this.finSabado = finSabado;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idDiaHoras);
        dest.writeValue(this.inicioLunes);
        dest.writeValue(this.finLunes);
        dest.writeValue(this.inicioMartes);
        dest.writeValue(this.finMartes);
        dest.writeValue(this.inicioMiercoles);
        dest.writeValue(this.finMiercoles);
        dest.writeValue(this.inicioJueves);
        dest.writeValue(this.finJueves);
        dest.writeValue(this.inicioViernes);
        dest.writeValue(this.finViernes);
        dest.writeValue(this.inicioSabado);
        dest.writeValue(this.finSabado);
    }

    protected DiaHoras(Parcel in) {
        this.idDiaHoras = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inicioLunes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.finLunes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inicioMartes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.finMartes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inicioMiercoles = (Integer) in.readValue(Integer.class.getClassLoader());
        this.finMiercoles = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inicioJueves = (Integer) in.readValue(Integer.class.getClassLoader());
        this.finJueves = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inicioViernes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.finViernes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inicioSabado = (Integer) in.readValue(Integer.class.getClassLoader());
        this.finSabado = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<DiaHoras> CREATOR = new Creator<DiaHoras>() {
        @Override
        public DiaHoras createFromParcel(Parcel source) {
            return new DiaHoras(source);
        }

        @Override
        public DiaHoras[] newArray(int size) {
            return new DiaHoras[size];
        }
    };
}
