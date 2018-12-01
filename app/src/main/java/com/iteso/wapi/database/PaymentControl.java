package com.iteso.wapi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.wapi.beans.Payment;
import java.util.ArrayList;

public class PaymentControl {

    public void addPayment(Payment payment, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.PAYMENT_ID, maxIdPayment(dh) +1);
        values.put(DataBaseHandler.PAYMENT_NAME, payment.getName());
        values.put(DataBaseHandler.PAYMENT_DESCRIPTION, payment.getDescription());
        values.put(DataBaseHandler.PAYMENT_AMOUNT, payment.getAmount());
        values.put(DataBaseHandler.PAYMENT_TIMESTAMP, payment.getTimestamp());
        values.put(DataBaseHandler.PAYMENT_FK_STUDENT, payment.getStudentUsername());
        db.insert(DataBaseHandler.TABLE_PAYMENT, null, values);
        try{
          //  db.close();
        }catch(Exception e){

        }
    }

    public ArrayList<Payment> getPayments(DataBaseHandler dh){
        ArrayList<Payment> payments = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " + DataBaseHandler.PAYMENT_ID + ", "
                + DataBaseHandler.PAYMENT_NAME + ", "
                + DataBaseHandler.PAYMENT_DESCRIPTION + ", "
                + DataBaseHandler.PAYMENT_AMOUNT + ", "
                + DataBaseHandler.PAYMENT_TIMESTAMP + ", "
                + DataBaseHandler.PAYMENT_FK_STUDENT
                + " FROM " + DataBaseHandler.TABLE_PAYMENT;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Payment payment = new Payment();
            payment.setPaymentId(cursor.getInt(0));
            payment.setName(cursor.getString(1));
            payment.setDescription(cursor.getString(2));
            payment.setAmount(cursor.getDouble(3));
            payment.setTimestamp(cursor.getString(4));
            payment.setStudentUsername(cursor.getString(5));
            payments.add(payment);
        }
        try{
            // cursor.close();
          //  db.close();
        }catch(Exception e){

        }
        return payments;
    }

    public ArrayList<Payment> getPaymentsByStudent(String fkStudentUsername, DataBaseHandler dh){
        ArrayList<Payment> payments = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " + DataBaseHandler.PAYMENT_ID + ", "
                + DataBaseHandler.PAYMENT_NAME + ", "
                + DataBaseHandler.PAYMENT_DESCRIPTION + ", "
                + DataBaseHandler.PAYMENT_AMOUNT + ", "
                + DataBaseHandler.PAYMENT_TIMESTAMP + ", "
                + DataBaseHandler.PAYMENT_FK_STUDENT
                + " FROM " + DataBaseHandler.TABLE_PAYMENT
                + " WHERE " + DataBaseHandler.PAYMENT_FK_STUDENT+ " = '" + fkStudentUsername + "'";
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Payment payment = new Payment();
            payment.setPaymentId(cursor.getInt(0));
            payment.setName(cursor.getString(1));
            payment.setDescription(cursor.getString(2));
            payment.setAmount(cursor.getDouble(3));
            payment.setTimestamp(cursor.getString(4));
            payment.setStudentUsername(cursor.getString(5));
            payments.add(payment);
        }
        try{
           // cursor.close();
           // db.close();
        }catch(Exception e){

        }
        return payments;
    }

    public void updatePayment(int paymentId,  Payment updatedPayment, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String updateQuery = "UPDATE " + DataBaseHandler.TABLE_PAYMENT
                            + " SET " + DataBaseHandler.PAYMENT_NAME + " = '" + updatedPayment.getName() + "', "
                            + DataBaseHandler.PAYMENT_DESCRIPTION + " = '" + updatedPayment.getDescription() + "', "
                            + DataBaseHandler.PAYMENT_AMOUNT + " = " + updatedPayment.getAmount() + ", "
                            + DataBaseHandler.PAYMENT_TIMESTAMP + " = " + updatedPayment.getTimestamp() + ", "
                            + DataBaseHandler.PAYMENT_FK_STUDENT + " = '" + updatedPayment.getStudentUsername() + "'"
                            + " WHERE " + DataBaseHandler.PAYMENT_ID + " = " + paymentId;
        db.execSQL(updateQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public void deletePayment(int paymentId, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String deleteQuery = "DELETE FROM "
                + DataBaseHandler.TABLE_PAYMENT
                + " WHERE " + DataBaseHandler.PAYMENT_ID + " = '" + paymentId + "'";
        db.execSQL(deleteQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public int maxIdPayment(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        int result = 0;
        String select = "SELECT MAX("+DataBaseHandler.PAYMENT_ID + ") "
                + " FROM " + DataBaseHandler.TABLE_PAYMENT
                + " GROUP BY "+DataBaseHandler.PAYMENT_ID;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            result = cursor.getInt(0);
        }
        try{
           // cursor.close();
           // db.close();
        }catch(Exception e){

        }
        return result;
    }

    public void updateFKUsername(String fkUsername, String fkUsernameNew, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ArrayList<Payment> payments = this.getPaymentsByStudent(fkUsername, dh);
        for(Payment index: payments){
            index.setStudentUsername(fkUsernameNew);
            this.updatePayment(index.getPaymentId(),index,dh);
        }
    }

}
