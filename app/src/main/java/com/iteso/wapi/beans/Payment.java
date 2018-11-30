package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable {

    private int paymentId;
    private String name;
    private String description;
    private double amount;
    private int day;
    private int month;
    private int year;
    private String studentUsername;

    public Payment(){
        this.paymentId = 0;
        this.name = "";
        this.description = "";
        this.amount = 0;
        this.studentUsername = "";
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Payment(int paymentId, String name, String description, double amount, int day, int month, int year, String studentUsername) {
        this.paymentId = paymentId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.day = day;
        this.month = month;
        this.year = year;
        this.studentUsername = studentUsername;
    }

    public int getPaymentId() {
        return paymentId;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getStudentUsername() {
        return studentUsername;
    }
    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", studentUsername='" + studentUsername + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.paymentId);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeDouble(this.amount);
        dest.writeInt(this.day);
        dest.writeInt(this.month);
        dest.writeInt(this.year);
        dest.writeString(this.studentUsername);
    }

    protected Payment(Parcel in) {
        this.paymentId = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.amount = in.readDouble();
        this.day = in.readInt();
        this.month = in.readInt();
        this.year = in.readInt();
        this.studentUsername = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel source) {
            return new Payment(source);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };
}
