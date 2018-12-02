package com.iteso.wapi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.wapi.beans.Homework;

import java.util.ArrayList;

public class HomeworkControl {
    public static final String TABLE_HOMEWORK       = "Homework";
    public static final String HOMEWORK_ID          = "Homework_Id";
    public static final String HOMEWORK_FK_SUBJECT  = "Homework_FK_Subject";
    public static final String HOMEWORK_DESCRIPTION = "Homework_Description";
    public static final String HOMEWORK_DATE        = "Homework_Date";
    public static final String HOMEWORK_HOUR        = "Homework_Hour";


    public void addHomework(Homework homework, DataBaseHandler dh){
        int nextId = maxIdHomework(dh) + 1;

        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseHandler.HOMEWORK_ID, nextId);
        values.put(DataBaseHandler.HOMEWORK_FK_SUBJECT, homework.getFk_subject());
        values.put(DataBaseHandler.HOMEWORK_DESCRIPTION, homework.getDescriptionHomework());
        values.put(DataBaseHandler.HOMEWORK_DAY, homework.getDeliveryDay());
        values.put(DataBaseHandler.HOMEWORK_MONTH, homework.getDeliveryMonth());
        values.put(DataBaseHandler.HOMEWORK_YEAR, homework.getDeliveryYear());
        values.put(DataBaseHandler.HOMEWORK_HOUR, homework.getDeliveryHour());
        values.put(DataBaseHandler.HOMEWORK_MIN, homework.getDeliveryMin());
        db.insert(DataBaseHandler.TABLE_HOMEWORK, null, values);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public ArrayList<Homework> getHomeworks(DataBaseHandler dh){
        ArrayList<Homework> homeworks = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.HOMEWORK_ID + ", " +
                DataBaseHandler.HOMEWORK_FK_SUBJECT+ ", " +
                DataBaseHandler.HOMEWORK_DESCRIPTION + ", " +
                DataBaseHandler.HOMEWORK_DAY + ", " +
                DataBaseHandler.HOMEWORK_MONTH + ", " +
                DataBaseHandler.HOMEWORK_YEAR + ", " +
                DataBaseHandler.HOMEWORK_HOUR + ", " +
                DataBaseHandler.HOMEWORK_MIN +
                " FROM " + DataBaseHandler.TABLE_HOMEWORK;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Homework homework = new Homework();
            homework.setIdHomework(cursor.getInt(0));
            homework.setFk_subject(cursor.getInt(1));
            homework.setDescriptionHomework(cursor.getString(2));
            homework.setDeliveryDay(cursor.getInt(3));
            homework.setDeliveryMonth(cursor.getInt(4));
            homework.setDeliveryYear(cursor.getInt(5));
            homework.setDeliveryHour(cursor.getInt(6));
            homework.setDeliveryMin(cursor.getInt(7));
            homeworks.add(homework);
        }
        try{
          //  cursor.close();
          //  db.close();
        }catch(Exception e){

        }
        return homeworks;
    }

    public ArrayList<Homework> getHomeworksByPeriod(int period, DataBaseHandler dh){
        ArrayList<Homework> homeworks = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.HOMEWORK_ID + ", " +
                DataBaseHandler.HOMEWORK_FK_SUBJECT+ ", " +
                DataBaseHandler.HOMEWORK_DESCRIPTION + ", " +
                DataBaseHandler.HOMEWORK_DAY + ", " +
                DataBaseHandler.HOMEWORK_MONTH + ", " +
                DataBaseHandler.HOMEWORK_YEAR + ", " +
                DataBaseHandler.HOMEWORK_HOUR + ", " +
                DataBaseHandler.HOMEWORK_MIN +
                " FROM " + DataBaseHandler.TABLE_HOMEWORK +
                " JOIN " + DataBaseHandler.TABLE_SUBJECT +
                " ON " + DataBaseHandler.HOMEWORK_FK_SUBJECT + " = " + DataBaseHandler.SUBJECT_ID +
                " WHERE " + DataBaseHandler.SUBJECT_FK_PERIOD + " = " + period;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Homework homework = new Homework();
            homework.setIdHomework(cursor.getInt(0));
            homework.setFk_subject(cursor.getInt(1));
            homework.setDescriptionHomework(cursor.getString(2));
            homework.setDeliveryDay(cursor.getInt(3));
            homework.setDeliveryMonth(cursor.getInt(4));
            homework.setDeliveryYear(cursor.getInt(5));
            homework.setDeliveryHour(cursor.getInt(6));
            homework.setDeliveryMin(cursor.getInt(7));
            homeworks.add(homework);
        }
        try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        return homeworks;
    }

    public ArrayList<Homework> getHomeworksByStudent(String student, DataBaseHandler dh){
        ArrayList<Homework> homeworks = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.HOMEWORK_ID + ", " +
                DataBaseHandler.HOMEWORK_FK_SUBJECT+ ", " +
                DataBaseHandler.HOMEWORK_DESCRIPTION + ", " +
                DataBaseHandler.HOMEWORK_DAY + ", " +
                DataBaseHandler.HOMEWORK_MONTH + ", " +
                DataBaseHandler.HOMEWORK_YEAR + ", " +
                DataBaseHandler.HOMEWORK_HOUR + ", " +
                DataBaseHandler.HOMEWORK_MIN +
                " FROM " + DataBaseHandler.TABLE_HOMEWORK +
                " JOIN " + DataBaseHandler.TABLE_SUBJECT +
                " ON " + DataBaseHandler.HOMEWORK_FK_SUBJECT + " = " + DataBaseHandler.SUBJECT_ID +
                " JOIN " + DataBaseHandler.TABLE_PERIOD +
                " ON " + DataBaseHandler.PERIOD_ID + " = " + DataBaseHandler.SUBJECT_FK_PERIOD +
                " WHERE " + DataBaseHandler.PERIOD_FK_STUDENT + " = '" + student + "'";
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Homework homework = new Homework();
            homework.setIdHomework(cursor.getInt(0));
            homework.setFk_subject(cursor.getInt(1));
            homework.setDescriptionHomework(cursor.getString(2));
            homework.setDeliveryDay(cursor.getInt(3));
            homework.setDeliveryMonth(cursor.getInt(4));
            homework.setDeliveryYear(cursor.getInt(5));
            homework.setDeliveryHour(cursor.getInt(6));
            homework.setDeliveryMin(cursor.getInt(7));
            homeworks.add(homework);
        }
        try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        return homeworks;
    }


    public void updateHomework(Homework updatedHomework, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String updateQuery = "UPDATE " + DataBaseHandler.TABLE_HOMEWORK
                + " SET " + DataBaseHandler.HOMEWORK_FK_SUBJECT + " = " + updatedHomework.getFk_subject()
                + " , " + DataBaseHandler.HOMEWORK_DESCRIPTION + " = " + updatedHomework.getDescriptionHomework()
                + " , " + DataBaseHandler.HOMEWORK_DAY + " = " + updatedHomework.getDeliveryDay()
                + " , " + DataBaseHandler.HOMEWORK_MONTH + " = " + updatedHomework.getDeliveryMonth()
                + " , " + DataBaseHandler.HOMEWORK_YEAR + " = " + updatedHomework.getDeliveryYear()
                + " , " + DataBaseHandler.HOMEWORK_HOUR + " = " + updatedHomework.getDeliveryHour()
                + " , " + DataBaseHandler.HOMEWORK_MIN + " = " + updatedHomework.getDeliveryMin()
                + " WHERE " + DataBaseHandler.HOMEWORK_ID + " = " + updatedHomework.getIdHomework();
        db.execSQL(updateQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public void deleteHomework(int id_homework, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String deleteQuery = "DELETE FROM "
                + DataBaseHandler.TABLE_HOMEWORK
                + " WHERE " + DataBaseHandler.HOMEWORK_ID + " = " + id_homework;
        db.execSQL(deleteQuery);
        try{
          //  db.close();
        }catch(Exception e){

        }
    }

    public int maxIdHomework(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        int result = 0;

        String select = "Select MAX("+DataBaseHandler.HOMEWORK_ID+") " + " From "+DataBaseHandler.TABLE_HOMEWORK+
                " GROUP BY "+DataBaseHandler.HOMEWORK_ID;

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

}
