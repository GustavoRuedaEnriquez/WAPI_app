package com.iteso.wapi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHandler dataBaseHandler;

    //Tabla Student
    public static final String TABLE_STUDENTS    = "Students";
    public static final String STUDENT_ID       = "Student_ID";
    public static final String STUDENT_NAME     = "Student_Name";
    public static final String STUDENT_EMAIL    = "Student_Email";
    public static final String STUDENT_PASSWORD = "Student_Password";

    private DataBaseHandler(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context){
        if(dataBaseHandler == null)
            dataBaseHandler = new DataBaseHandler(context);
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
