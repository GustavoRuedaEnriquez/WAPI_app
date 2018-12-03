package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Homework implements Parcelable, Comparable<Homework> {

    private Integer idHomework;
    private Integer fk_subject;
    private String descriptionHomework;
    private Integer deliveryYear;
    private Integer deliveryMonth;
    private Integer deliveryDay;
    private Integer deliveryHour;
    private Integer deliveryMin;


    public Homework(Integer idHomework, Integer fk_subject, String descriptionHomework, Integer deliveryYear, Integer deliveryMonth, Integer deliveryDay, Integer deliveryHour, Integer deliveryMin) {
        this.idHomework = idHomework;
        this.fk_subject = fk_subject;
        this.descriptionHomework = descriptionHomework;
        this.deliveryYear = deliveryYear;
        this.deliveryMonth = deliveryMonth;
        this.deliveryDay = deliveryDay;
        this.deliveryHour = deliveryHour;
        this.deliveryMin = deliveryMin;
    }

    public Homework() {
    }

    @Override
    public String toString() {
        return "Homework{" +
                "idHomework=" + idHomework +
                ", fk_subject=" + fk_subject +
                ", descriptionHomework='" + descriptionHomework + '\'' +
                ", deliveryYear=" + deliveryYear +
                ", deliveryMonth=" + deliveryMonth +
                ", deliveryDay=" + deliveryDay +
                ", deliveryHour=" + deliveryHour +
                ", deliveryMin=" + deliveryMin +
                '}';
    }

    public Integer getIdHomework() {
        return idHomework;
    }

    public void setIdHomework(Integer idHomework) {
        this.idHomework = idHomework;
    }

    public Integer getFk_subject() {
        return fk_subject;
    }

    public void setFk_subject(Integer fk_subject) {
        this.fk_subject = fk_subject;
    }

    public String getDescriptionHomework() {
        return descriptionHomework;
    }

    public void setDescriptionHomework(String descriptionHomework) {
        this.descriptionHomework = descriptionHomework;
    }

    public Integer getDeliveryYear() {
        return deliveryYear;
    }

    public void setDeliveryYear(Integer deliveryYear) {
        this.deliveryYear = deliveryYear;
    }

    public Integer getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(Integer deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }

    public Integer getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(Integer deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public Integer getDeliveryHour() {
        return deliveryHour;
    }

    public void setDeliveryHour(Integer deliveryHour) {
        this.deliveryHour = deliveryHour;
    }

    public Integer getDeliveryMin() {
        return deliveryMin;
    }

    public void setDeliveryMin(Integer deliveryMin) {
        this.deliveryMin = deliveryMin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idHomework);
        dest.writeValue(this.fk_subject);
        dest.writeString(this.descriptionHomework);
        dest.writeValue(this.deliveryYear);
        dest.writeValue(this.deliveryMonth);
        dest.writeValue(this.deliveryDay);
        dest.writeValue(this.deliveryHour);
        dest.writeValue(this.deliveryMin);
    }

    protected Homework(Parcel in) {
        this.idHomework = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fk_subject = (Integer) in.readValue(Integer.class.getClassLoader());
        this.descriptionHomework = in.readString();
        this.deliveryYear = (Integer) in.readValue(Integer.class.getClassLoader());
        this.deliveryMonth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.deliveryDay = (Integer) in.readValue(Integer.class.getClassLoader());
        this.deliveryHour = (Integer) in.readValue(Integer.class.getClassLoader());
        this.deliveryMin = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Homework> CREATOR = new Creator<Homework>() {
        @Override
        public Homework createFromParcel(Parcel source) {
            return new Homework(source);
        }

        @Override
        public Homework[] newArray(int size) {
            return new Homework[size];
        }
    };

    @Override
    public int compareTo(@NonNull Homework o) {
        if(!this.deliveryDay.equals(o.deliveryYear))
            return this.deliveryYear.compareTo(o.deliveryYear);

        if(!this.deliveryMonth.equals(o.deliveryMonth))
            return this.deliveryMonth.compareTo(o.deliveryMonth);

        if(!this.deliveryDay.equals(o.deliveryDay))
            return this.deliveryDay.compareTo(o.deliveryDay);

        if(!this.deliveryHour.equals(o.deliveryHour))
            return this.deliveryHour.compareTo(o.deliveryHour);

        return this.deliveryMin.compareTo(o.deliveryMin);
    }
}
