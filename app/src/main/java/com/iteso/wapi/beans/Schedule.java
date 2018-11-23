package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;

public class Schedule implements Parcelable{

    private Integer idSchedule;
    private Integer day;
    private Time timeInit;
    private Integer fk_subject;

    public Schedule(Integer idSchedule, Integer day, Time timeInit, Integer fk_subject) {
        this.idSchedule = idSchedule;
        this.day = day;
        this.timeInit = timeInit;
        this.fk_subject = fk_subject;
    }

    public Schedule() {
        this.idSchedule = null;
        this.day = null;
        this.timeInit = null;
        this.fk_subject = null;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Time getTimeInit() {
        return timeInit;
    }

    public void setTimeInit(Time timeInit) {
        this.timeInit = timeInit;
    }

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
        dest.writeSerializable(this.timeInit);
        dest.writeValue(this.fk_subject);
    }

    protected Schedule(Parcel in) {
        this.idSchedule = (Integer) in.readValue(Integer.class.getClassLoader());
        this.day = (Integer) in.readValue(Integer.class.getClassLoader());
        this.timeInit = (Time) in.readSerializable();
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
