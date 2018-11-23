package com.iteso.wapi.database;

import android.app.admin.DeviceAdminInfo;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.wapi.beans.Student;

import java.util.ArrayList;

public class StudentControl {

    public void addStudent(Student student, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.STUDENT_USERNAME, student.getUserName());
        values.put(DataBaseHandler.STUDENT_PASSWORD, student.getPassword());
        db.insert(DataBaseHandler.TABLE_STUDENT, null, values);
        try{
            db.close();
        }catch(Exception e){

        }
    }

    public ArrayList<Student> getStudents(DataBaseHandler dh){
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " + DataBaseHandler.STUDENT_USERNAME + ", "
                            + DataBaseHandler.STUDENT_PASSWORD
                            + " FROM " + DataBaseHandler.TABLE_STUDENT;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Student student = new Student();
            student.setUserName(cursor.getString(0));
            student.setPassword(cursor.getString(1));
            students.add(student);
        }
        try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        return students;
    }

    public Student getStudentByUsername(String username, DataBaseHandler dh){
        Student student = new Student();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " + DataBaseHandler.STUDENT_USERNAME + ", "
                            + DataBaseHandler.STUDENT_PASSWORD
                            + " FROM " + DataBaseHandler.TABLE_STUDENT
                            + " WHERE " + DataBaseHandler.STUDENT_USERNAME + " = " + username;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToNext();
        student.setUserName(cursor.getString(0));
        student.setPassword(cursor.getString(1));
        try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }
        return student;
    }

    public void updateStudent(String username, Student updatedStudent, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String updateQuery = "UPDATE " + DataBaseHandler.TABLE_STUDENT
                            + " SET " + DataBaseHandler.STUDENT_USERNAME + " = " + updatedStudent.getUserName()
                            + " , " + DataBaseHandler.STUDENT_PASSWORD + " = " + updatedStudent.getPassword()
                            + " WHERE " + DataBaseHandler.STUDENT_USERNAME + " = " + username;
        db.execSQL(updateQuery);
        try{
            db.close();
        }catch(Exception e){

        }
    }

    public void deleteStudent(String username, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String deleteQuery = "DELETE FROM "
                + DataBaseHandler.TABLE_STUDENT
                + " WHERE " + DataBaseHandler.STUDENT_USERNAME + " = " + username;
        db.execSQL(deleteQuery);
        try{
            db.close();
        }catch(Exception e){

        }
    }

}
