package com.iteso.wapi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.wapi.beans.Period;

import java.util.ArrayList;
import java.util.logging.Logger;

public class PeriodControl {

    public void addPeriod(Period period, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.PERIOD_ID, maxIdPeriod(dh) + 1);
        values.put(DataBaseHandler.PERIOD_NAME, period.getNamePeriod());
        values.put(DataBaseHandler.PERIOD_FK_STUDENT, period.getUserName());
        db.insert(DataBaseHandler.TABLE_PERIOD, null, values);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public ArrayList<Period> getPeriods(DataBaseHandler dh){
        ArrayList<Period> periods = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " + DataBaseHandler.PERIOD_ID + ", "
                + DataBaseHandler.PERIOD_NAME + ", "
                + DataBaseHandler.PERIOD_FK_STUDENT
                + " FROM " + DataBaseHandler.TABLE_PERIOD;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Period period = new Period();
            period.setIdPeriod(cursor.getInt(0));
            period.setNamePeriod(cursor.getString(1));
            period.setUserName(cursor.getString(2));
            periods.add(period);
        }
        try{
            //cursor.close();
            //db.close();
        }catch(Exception e){

        }
        return periods;
    }

    public ArrayList<Period> getPeriodsByStudent(String fkStudentUsername, DataBaseHandler dh) {
        ArrayList<Period> periods = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " + DataBaseHandler.PERIOD_ID + ", "
                + DataBaseHandler.PERIOD_NAME + ", "
                + DataBaseHandler.PERIOD_FK_STUDENT
                + " FROM " + DataBaseHandler.TABLE_PERIOD
                + " WHERE " + DataBaseHandler.PERIOD_FK_STUDENT + " = '" + fkStudentUsername + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while(cursor.moveToNext()){
            Period period = new Period();
            period.setIdPeriod(cursor.getInt(0));
            period.setNamePeriod(cursor.getString(1));
            period.setUserName(cursor.getString(2));
            periods.add(period);
        }
        try{
           // cursor.close();
           // db.close();
        }catch(Exception e){

        }
        return periods;
    }

    public void updatePeriod(int periodId,  Period updatedPeriod, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String updateQuery = "UPDATE " + DataBaseHandler.TABLE_PERIOD
                + " SET " + DataBaseHandler.PERIOD_NAME + " = '" + updatedPeriod.getNamePeriod() + "',"
                + DataBaseHandler.PERIOD_FK_STUDENT + " = '" + updatedPeriod.getUserName() + "'"
                + " WHERE " + DataBaseHandler.PERIOD_ID + " = " + periodId;
        db.execSQL(updateQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public void deletePeriod(int periodId, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String deleteQuery = "DELETE FROM "
                + DataBaseHandler.TABLE_PERIOD
                + " WHERE " + DataBaseHandler.PERIOD_ID + " = " + periodId;
        db.execSQL(deleteQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public int maxIdPeriod(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        int result = 0;
        String select = "SELECT MAX("+DataBaseHandler.PERIOD_ID + ") "
                        + " FROM " + DataBaseHandler.TABLE_PERIOD
                        + " GROUP BY "+DataBaseHandler.PERIOD_ID;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            result = cursor.getInt(0);
        }
        try{
          //  cursor.close();
          //  db.close();
        }catch(Exception e){

        }
        return result;
    }

    public void updateFKUsername(String fkUsername, String fkUsernameNew, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ArrayList<Period> payments = this.getPeriodsByStudent(fkUsername, dh);
        for(Period index: payments){
            index.setUserName(fkUsernameNew);
            this.updatePeriod(index.getIdPeriod(),index,dh);
        }
    }

}
