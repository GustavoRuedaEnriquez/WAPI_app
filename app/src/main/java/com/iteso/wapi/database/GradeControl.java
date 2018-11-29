package com.iteso.wapi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.wapi.beans.Grade;

import java.util.ArrayList;

public class GradeControl {

    public void addGrade(Grade grade, DataBaseHandler dh){
        int nextId = maxIdGrade(dh) + 1;

        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseHandler.GRADE_ID, nextId);
        values.put(DataBaseHandler.GRADE_FK_SUBJECT, grade.getFk_subject());
        values.put(DataBaseHandler.GRADE_DESCRIPTION, grade.getDescriptionGrade());
        values.put(DataBaseHandler.GRADE_PERCENTAGE, grade.getPercentage());
        values.put(DataBaseHandler.GRADE_VALUE, grade.getGrade());
        db.insert(DataBaseHandler.TABLE_GRADE, null, values);
        try{
            db.close();
        }catch(Exception e){

        }
    }

    public ArrayList<Grade> getGrades(DataBaseHandler dh){
        ArrayList<Grade> grades = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.GRADE_ID + ", " +
                DataBaseHandler.GRADE_FK_SUBJECT+ ", " +
                DataBaseHandler.GRADE_DESCRIPTION + ", " +
                DataBaseHandler.GRADE_PERCENTAGE + ", " +
                DataBaseHandler.GRADE_VALUE +
                " FROM " + DataBaseHandler.TABLE_GRADE;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Grade grade = new Grade();
            grade.setIdGrade(cursor.getInt(0));
            grade.setFk_subject(cursor.getInt(1));
            grade.setDescriptionGrade(cursor.getString(2));
            grade.setPercentage(cursor.getFloat(3));
            grade.setGrade(cursor.getFloat(4));
            grades.add(grade);
        }
        try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        return grades;
    }

    public ArrayList<Grade> getGradesBySubject(int subject, DataBaseHandler dh){
        ArrayList<Grade> grades = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.GRADE_ID + ", " +
                DataBaseHandler.GRADE_FK_SUBJECT+ ", " +
                DataBaseHandler.GRADE_DESCRIPTION + ", " +
                DataBaseHandler.GRADE_PERCENTAGE + ", " +
                DataBaseHandler.GRADE_VALUE +
                " FROM " + DataBaseHandler.TABLE_GRADE +
                " WHERE " + DataBaseHandler.GRADE_FK_SUBJECT + " = " + subject;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Grade grade = new Grade();
            grade.setIdGrade(cursor.getInt(0));
            grade.setFk_subject(cursor.getInt(1));
            grade.setDescriptionGrade(cursor.getString(2));
            grade.setPercentage(cursor.getFloat(3));
            grade.setGrade(cursor.getFloat(4));
            grades.add(grade);
        }
        try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        return grades;
    }


    public void updateGrade(Grade updatedGrade, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String updateQuery = "UPDATE " + DataBaseHandler.TABLE_GRADE
                + " SET " + DataBaseHandler.GRADE_FK_SUBJECT + " = " + updatedGrade.getFk_subject()
                + " , " + DataBaseHandler.GRADE_DESCRIPTION + " = " + updatedGrade.getDescriptionGrade()
                + " , " + DataBaseHandler.GRADE_PERCENTAGE + " = " + updatedGrade.getPercentage()
                + " , " + DataBaseHandler.GRADE_VALUE + " = " + updatedGrade.getGrade()
                + " WHERE " + DataBaseHandler.GRADE_ID + " = " + updatedGrade.getIdGrade();
        db.execSQL(updateQuery);
        try{
            db.close();
        }catch(Exception e){

        }
    }

    public void deleteGrade(int id_grade, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String deleteQuery = "DELETE FROM "
                + DataBaseHandler.TABLE_GRADE
                + " WHERE " + DataBaseHandler.GRADE_ID + " = " + id_grade;
        db.execSQL(deleteQuery);
        try{
            db.close();
        }catch(Exception e){

        }
    }

    public int maxIdGrade(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        int result = 0;

        String select = "Select MAX("+DataBaseHandler.GRADE_ID+") " + " From "+DataBaseHandler.TABLE_GRADE+
                " GROUP BY "+DataBaseHandler.GRADE_ID;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            result = cursor.getInt(0);
        }
        try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        return result;
    }

}
