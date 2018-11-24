package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Subject implements Parcelable {

    private Integer idSubject;
    private Integer fk_period;
    private String nameSubject;
    private float avarage;

    @Override
    public String toString() {
        return "Subject{" +
                "idSubject=" + idSubject +
                ", fk_period=" + fk_period +
                ", nameSubject='" + nameSubject + '\'' +
                ", avarage=" + avarage +
                '}';
    }

    public Subject() {

    }

    public Subject(Integer idSubject, Integer fk_period, String nameSubject, float avarage) {
        this.idSubject = idSubject;
        this.fk_period = fk_period;
        this.nameSubject = nameSubject;
        this.avarage = avarage;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public Integer getFk_period() {
        return fk_period;
    }

    public void setFk_period(Integer fk_period) {
        this.fk_period = fk_period;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public float getAvarage() {
        return avarage;
    }

    public void setAvarage(float avarage) {
        this.avarage = avarage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idSubject);
        dest.writeValue(this.fk_period);
        dest.writeString(this.nameSubject);
        dest.writeFloat(this.avarage);
    }

    protected Subject(Parcel in) {
        this.idSubject = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fk_period = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nameSubject = in.readString();
        this.avarage = in.readFloat();
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel source) {
            return new Subject(source);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };
}
