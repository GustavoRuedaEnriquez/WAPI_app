package com.iteso.wapi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.wapi.beans.Subject;

import java.util.ArrayList;

public class SubjectControl {

    public void addSubject(Subject subject, DataBaseHandler dh){
        int nextId = maxIdSubject(dh) + 1;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.SUBJECT_ID, nextId);
        values.put(DataBaseHandler.SUBJECT_FK_PERIOD, subject.getFk_period());
        values.put(DataBaseHandler.SUBJECT_NAME, subject.getNameSubject());
        values.put(DataBaseHandler.SUBJECT_AVERAGE, subject.getAvarage());
        db.insert(DataBaseHandler.TABLE_SUBJECT, null, values);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public ArrayList<Subject> getSubjects(DataBaseHandler dh){
        ArrayList<Subject> subjects = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.SUBJECT_ID + ", " +
                DataBaseHandler.SUBJECT_FK_PERIOD + ", " +
                DataBaseHandler.SUBJECT_NAME + ", " +
                DataBaseHandler.SUBJECT_AVERAGE +
                " FROM " + DataBaseHandler.TABLE_SUBJECT;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Subject subject = new Subject();
            subject.setIdSubject(cursor.getInt(0));
            subject.setFk_period(cursor.getInt(1));
            subject.setNameSubject(cursor.getString(2));
            subjects.add(subject);
        }
        try{
           // cursor.close();
           // db.close();
        }catch(Exception e){

        }
        return subjects;
    }

    public Subject getSubjectById(int subjectId, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.SUBJECT_ID + ", " +
                DataBaseHandler.SUBJECT_FK_PERIOD + ", " +
                DataBaseHandler.SUBJECT_NAME + ", " +
                DataBaseHandler.SUBJECT_AVERAGE +
                " FROM " + DataBaseHandler.TABLE_SUBJECT +
                " WHERE " + DataBaseHandler.SUBJECT_ID + " = " + subjectId;

        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToNext();
        Subject newSubject = new Subject();
        newSubject.setIdSubject(cursor.getInt(0));
        newSubject.setFk_period(cursor.getInt(1));
        newSubject.setNameSubject(cursor.getString(2));

        return newSubject;
    }

    public ArrayList<Subject> getSubjectsByPeriod(int period, DataBaseHandler dh){
        ArrayList<Subject> subjects = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.SUBJECT_ID + ", " +
                DataBaseHandler.SUBJECT_FK_PERIOD + ", " +
                DataBaseHandler.SUBJECT_NAME + ", " +
                DataBaseHandler.SUBJECT_AVERAGE +
                " FROM " + DataBaseHandler.TABLE_SUBJECT +
                " WHERE " + DataBaseHandler.SUBJECT_FK_PERIOD + " = " + period;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Subject subject = new Subject();
            subject.setIdSubject(cursor.getInt(0));
            subject.setFk_period(cursor.getInt(1));
            subject.setNameSubject(cursor.getString(2));
            subject.setAvarage(cursor.getFloat(3));
            subjects.add(subject);
        }
        try{
           // cursor.close();
            //db.close();
        }catch(Exception e){

        }
        return subjects;
    }

    public void updateSubject(Subject updatedSubject, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String updateQuery = "UPDATE " + DataBaseHandler.TABLE_SUBJECT
                + " SET " + DataBaseHandler.SUBJECT_FK_PERIOD + " = " + updatedSubject.getFk_period()
                + " , " + DataBaseHandler.SUBJECT_NAME + " = '" + updatedSubject.getNameSubject() + "'"
                + " , " + DataBaseHandler.SUBJECT_AVERAGE + " = " + updatedSubject.getAvarage()
                + " WHERE " + DataBaseHandler.SUBJECT_ID + " = " + updatedSubject.getIdSubject();
        db.execSQL(updateQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public void deleteSubject(int id_subject, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String deleteQuery = "DELETE FROM "
                + DataBaseHandler.TABLE_SUBJECT
                + " WHERE " + DataBaseHandler.SUBJECT_ID + " = " + id_subject;
        db.execSQL(deleteQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public int maxIdSubject(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        int result = 0;

        String select = "Select MAX("+DataBaseHandler.SUBJECT_ID+") " + " From "+DataBaseHandler.TABLE_SUBJECT+
                " GROUP BY "+DataBaseHandler.SUBJECT_ID;

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

}
