package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;

public class Schedule implements Parcelable{

    private Integer idSchedule;
    private Integer day;
    private Integer inicialTime;
    private Integer finalTime;
    private Integer fk_subject;

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + idSchedule +
                ", day=" + day +
                ", inicialTime=" + inicialTime +
                ", finalTime=" + finalTime +
                ", fk_subject=" + fk_subject +
                '}';
    }

    public Schedule(Integer idSchedule, Integer day, Integer inicialTime, Integer finalTime, Integer fk_subject) {
        this.idSchedule = idSchedule;
        this.day = day;
        this.inicialTime = inicialTime;
        this.finalTime = finalTime;
        this.fk_subject = fk_subject;
    }

    public Schedule() {
        this.idSchedule = null;
        this.day = null;
        this.inicialTime = null;
        this.finalTime = null;
        this.fk_subject = null;
    }

    public Integer getIdSchedule() { return idSchedule; }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getInicialTime() {
        return inicialTime;
    }

    public void setInicialTime(Integer inicialTime) {
        this.inicialTime = inicialTime;
    }

    public Integer getFinalTime() { return finalTime; }

    public void setFinalTime(Integer finalTime) { this.finalTime = finalTime; }

    public Integer getFk_subject() {
        return fk_subject;
    }

    public void setFk_subject(Integer fk_subject) {
        this.fk_subject = fk_subject;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idSchedule);
        dest.writeValue(this.day);
        dest.writeValue(this.inicialTime);
        dest.writeValue(this.finalTime);
        dest.writeValue(this.fk_subject);
    }

    protected Schedule(Parcel in) {
        this.idSchedule = (Integer) in.readValue(Integer.class.getClassLoader());
        this.day = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inicialTime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.finalTime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fk_subject = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}
