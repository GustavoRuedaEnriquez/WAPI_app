package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Materia implements Parcelable {

    private Integer idMateria;
    private Integer fk_periodo;
    private String nombreMateria;
    private DiaHoras diaHoras;

    public Materia() {

    }

    public Materia(Integer idMateria, Integer fk_periodo, String nombreMateria, DiaHoras diaHoras) {
        this.idMateria = idMateria;
        this.fk_periodo = fk_periodo;
        this.nombreMateria = nombreMateria;
        this.diaHoras = diaHoras;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getFk_periodo() {
        return fk_periodo;
    }

    public void setFk_periodo(Integer fk_periodo) {
        this.fk_periodo = fk_periodo;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public DiaHoras getDiaHoras() {
        return diaHoras;
    }

    public void setDiaHoras(DiaHoras diaHoras) {
        this.diaHoras = diaHoras;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idMateria);
        dest.writeValue(this.fk_periodo);
        dest.writeString(this.nombreMateria);
        dest.writeParcelable(this.diaHoras, flags);
    }

    protected Materia(Parcel in) {
        this.idMateria = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fk_periodo = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombreMateria = in.readString();
        this.diaHoras = in.readParcelable(DiaHoras.class.getClassLoader());
    }

    public static final Creator<Materia> CREATOR = new Creator<Materia>() {
        @Override
        public Materia createFromParcel(Parcel source) {
            return new Materia(source);
        }

        @Override
        public Materia[] newArray(int size) {
            return new Materia[size];
        }
    };
}
