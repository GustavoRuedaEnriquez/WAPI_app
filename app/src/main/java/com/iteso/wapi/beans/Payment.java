package com.iteso.wapi.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable {

    private int paymentId;
    private String name;
    private String description;
    private double amount;
    //private int day;
    //private int month;
    //private int year;
    private String timestamp;
    private String studentUsername;

    public Payment() {
        this.paymentId = 0;
        this.name = "";
        this.description = "";
        this.amount = 0;
        this.timestamp = "";
        this.studentUsername = "";
    }

    public Payment(int paymentId, String name, String description, double amount, String timestamp, String studentUsername) {
        this.paymentId = paymentId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.timestamp = timestamp;
        this.studentUsername = studentUsername;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", timestamp='" + timestamp + '\'' +
                ", studentUsername='" + studentUsername + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.paymentId);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeDouble(this.amount);
        dest.writeString(this.timestamp);
        dest.writeString(this.studentUsername);
    }

    protected Payment(Parcel in) {
        this.paymentId = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.amount = in.readDouble();
        this.timestamp = in.readString();
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


