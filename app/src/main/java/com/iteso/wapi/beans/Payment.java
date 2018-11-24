package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable {

    private int paymentId;
    private String name;
    private String description;
    private double amount;
    private int date;
    private String studentUsername;

    public Payment(int paymentId, String name, String description, double amount, int date, String studentUsername) {
        this.paymentId = paymentId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.studentUsername = studentUsername;
    }

    protected Payment(Parcel in) {
        this.paymentId = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.amount = in.readDouble();
        this.date = in.readInt();
        this.studentUsername = in.readString();
    }

    public int getPaymentId() {
        return paymentId;
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
    public int getDate() {
        return date;
    }
    public void setDate(int date) {
        this.date = date;
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
                ", date=" + date +
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
        dest.writeInt(this.date);
        dest.writeString(this.studentUsername);
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
