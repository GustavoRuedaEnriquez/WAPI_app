package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Schedule implements Parcelable {

    private Integer idSchedule;
    private Integer day;
    private Integer initialTime;
    private Integer finalTime;
    private Integer fk_subject;

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + idSchedule +
                ", day=" + day +
                ", initialTime=" + initialTime +
                ", finalTime=" + finalTime +
                ", fk_subject=" + fk_subject +
                '}';
    }

    public Schedule(Integer idSchedule, Integer day, Integer initialTime, Integer finalTime, Integer fk_subject) {
        this.idSchedule = idSchedule;
        this.day = day;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.fk_subject = fk_subject;
    }

    public Schedule() {
        this.idSchedule = null;
        this.day = null;
        this.initialTime = null;
        this.finalTime = null;
        this.fk_subject = null;
    }

    public Integer getIdSchedule() {
        return idSchedule;
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

    public Integer getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Integer initialTime) {
        this.initialTime = initialTime;
    }

    public Integer getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Integer finalTime) {
        this.finalTime = finalTime;
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
        dest.writeValue(this.initialTime);
        dest.writeValue(this.finalTime);
        dest.writeValue(this.fk_subject);
    }

    protected Schedule(Parcel in) {
        this.idSchedule = (Integer) in.readValue(Integer.class.getClassLoader());
        this.day = (Integer) in.readValue(Integer.class.getClassLoader());
        this.initialTime = (Integer) in.readValue(Integer.class.getClassLoader());
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

    public boolean equals(Schedule other) {
        if (!day.equals(other.day))
            return false;
        if (!initialTime.equals(other.initialTime))
            return false;
        if (!finalTime.equals(other.finalTime))
            return false;
        if (!fk_subject.equals(other.fk_subject))
            return false;

        return true;
    }
}
