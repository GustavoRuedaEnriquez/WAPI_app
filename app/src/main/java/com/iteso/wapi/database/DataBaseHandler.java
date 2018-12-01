package com.iteso.wapi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHandler dataBaseHandler;

    //Student Table
    public static final String TABLE_STUDENT    = "Students";
    public static final String STUDENT_USERNAME = "Student_UserName";
    public static final String STUDENT_PASSWORD = "Student_Password";

    //Period Table
    public static final String TABLE_PERIOD      = "Period";
    public static final String PERIOD_ID         = "Period_Id";
    public static final String PERIOD_NAME       = "Period_Name";
    public static final String PERIOD_FK_STUDENT = "Period_FK_Student";

    //Subject Table
    public static final String TABLE_SUBJECT        = "Subject";
    public static final String SUBJECT_ID           = "Subject_Id";
    public static final String SUBJECT_FK_PERIOD    = "Subject_FK_Period";
    public static final String SUBJECT_NAME         = "Subject_Name";
    public static final String SUBJECT_AVERAGE      = "Subject_Average";

    //Schedule Table
    public static final String TABLE_SCHEDULE      = "Schedule";
    public static final String SCHEDULE_ID         = "Schedule_Id";
    public static final String SCHEDULE_FK_SUBJECT = "Schedule_FK_Subject";
    public static final String SCHEDULE_DAY        = "Schedule_Day";
    public static final String SCHEDULE_START_HOUR = "Schedule_Start_Hour";
    public static final String SCHEDULE_END_HOUR   = "Schedule_End_Hour";

    //Grade Table
    public static final String TABLE_GRADE       = "Grade";
    public static final String GRADE_ID          = "Grade_Id";
    public static final String GRADE_FK_SUBJECT  = "Grade_FK_Subject";
    public static final String GRADE_DESCRIPTION = "Grade_Description";
    public static final String GRADE_PERCENTAGE  = "Grade_Percentage";
    public static final String GRADE_VALUE       = "Grade_Value";

    //Homework Table
    public static final String TABLE_HOMEWORK       = "Homework";
    public static final String HOMEWORK_ID          = "Homework_Id";
    public static final String HOMEWORK_FK_SUBJECT  = "Homework_FK_Subject";
    public static final String HOMEWORK_DESCRIPTION = "Homework_Description";
    public static final String HOMEWORK_DAY        = "Homework_Day";
    public static final String HOMEWORK_MONTH        = "Homework_Month";
    public static final String HOMEWORK_YEAR        = "Homework_Year";
    public static final String HOMEWORK_HOUR        = "Homework_Hour";
    public static final String HOMEWORK_MIN        = "Homework_Min";

    //Payment Table
    public static final String TABLE_PAYMENT       = "Payment";
    public static final String PAYMENT_ID          = "Payment_Id";
    public static final String PAYMENT_NAME        = "Payment_Name";
    public static final String PAYMENT_DESCRIPTION = "Payment_Description";
    public static final String PAYMENT_AMOUNT      = "Payment_Amount";
    public static final String PAYMENT_TIMESTAMP   = "Payment_Timestamp";
    public static final String PAYMENT_FK_STUDENT  = "Payment_FK_Student";

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
        String tableStudent = "CREATE TABLE " + TABLE_STUDENT + " ("
                            + STUDENT_USERNAME + " TEXT PRIMARY KEY, "
                            + STUDENT_PASSWORD + " TEXT)";

        String tablePeriod = "CREATE TABLE " + TABLE_PERIOD + " ("
                            + PERIOD_ID + " INTEGER PRIMARY KEY, "
                            + PERIOD_NAME + " TEXT, "
                            + PERIOD_FK_STUDENT +  " TEXT)";

        String tableSubject = "CREATE TABLE " + TABLE_SUBJECT + " ("
                            + SUBJECT_ID + " INTEGER PRIMARY KEY, "
                            + SUBJECT_FK_PERIOD + " INTEGER, "
                            + SUBJECT_NAME + " TEXT, "
                            + SUBJECT_AVERAGE + " REAL)";

        String tableSchedule = "CREATE TABLE " + TABLE_SCHEDULE + " ("
                            + SCHEDULE_ID + " INTEGER PRIMARY KEY, "
                            + SCHEDULE_FK_SUBJECT + " INTEGER, "
                            + SCHEDULE_DAY + " INTEGER, "
                            + SCHEDULE_START_HOUR + " INTEGER, "
                            + SCHEDULE_END_HOUR + " INTEGER) ";

        String tableGrade = "CREATE TABLE " + TABLE_GRADE + " ("
                            + GRADE_ID + " INTEGER PRIMARY KEY, "
                            + GRADE_FK_SUBJECT + "INTEGER, "
                            + GRADE_DESCRIPTION + " TEXT, "
                            + GRADE_PERCENTAGE + " REAL, "
                            + GRADE_VALUE + " REAL)";

        String tableHomework = "CREATE TABLE " + TABLE_HOMEWORK + " ("
                            + HOMEWORK_ID + " INTEGER PRIMARY KEY, "
                            + HOMEWORK_FK_SUBJECT + " INTEGER, "
                            + HOMEWORK_DESCRIPTION + " TEXT, "
                            + HOMEWORK_DAY + " INTEGER, "
                            + HOMEWORK_MONTH + " INTEGER, "
                            + HOMEWORK_YEAR + " INTEGER, "
                            + HOMEWORK_HOUR + " INTEGER, "
                            + HOMEWORK_MIN + " INTEGER)";

        String tablePayment = "CREATE TABLE " + TABLE_PAYMENT + " ("
                            + PAYMENT_ID + " INTEGER PRIMARY KEY, "
                            + PAYMENT_NAME + " TEXT, "
                            + PAYMENT_DESCRIPTION + " TEXT, "
                            + PAYMENT_AMOUNT + " REAL, "
                            + PAYMENT_TIMESTAMP + " TEXT,"
                            + PAYMENT_FK_STUDENT + " TEXT)";

        db.execSQL(tableStudent);
        db.execSQL(tablePeriod);
        db.execSQL(tableSubject);
        db.execSQL(tableSchedule);
        db.execSQL(tableGrade);
        db.execSQL(tableHomework);
        db.execSQL(tablePayment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
