package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Grade implements Parcelable {

    private Integer idGrade;
    private Integer fk_subject;
    private String descriptionGrade;
    private float percentage;
    private float grade;


    @Override
    public String toString() {
        return "Grade{" +
                "idGrade=" + idGrade +
                ", fk_subject=" + fk_subject +
                ", descriptionGrade='" + descriptionGrade + '\'' +
                ", percentage=" + percentage +
                ", grade=" + grade +
                '}';
    }

    public Grade(Integer idGrade, Integer fk_subject, String descriptionGrade, float percentage, float grade) {
        this.idGrade = idGrade;
        this.fk_subject = fk_subject;
        this.descriptionGrade = descriptionGrade;
        this.percentage = percentage;
        this.grade = grade;
    }

    public Grade() {

    }

    public Integer getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(Integer idGrade) {
        this.idGrade = idGrade;
    }

    public String getDescriptionGrade() {
        return descriptionGrade;
    }

    public void setDescriptionGrade(String descriptionGrade) {
        this.descriptionGrade = descriptionGrade;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
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
        dest.writeInt(this.idGrade);
        dest.writeInt(this.fk_subject);
        dest.writeString(this.descriptionGrade);
        dest.writeFloat(this.percentage);
        dest.writeFloat(this.grade);
    }

    protected Grade(Parcel in) {
        this.idGrade = in.readInt();
        this.fk_subject = in.readInt();
        this.descriptionGrade = in.readString();
        this.percentage = in.readFloat();
        this.grade = in.readFloat();
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
