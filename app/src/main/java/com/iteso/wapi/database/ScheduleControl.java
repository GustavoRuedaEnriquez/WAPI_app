package com.iteso.wapi.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.wapi.beans.Schedule;

import java.util.ArrayList;

public class ScheduleControl {

    public void addSchedule(Schedule schedule, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.SCHEDULE_ID, maxIdSchedule(dh) + 1);
        values.put(DataBaseHandler.SCHEDULE_START_HOUR, schedule.getInitialTime());
        values.put(DataBaseHandler.SCHEDULE_END_HOUR, schedule.getFinalTime());
        values.put(DataBaseHandler.SCHEDULE_DAY, schedule.getDay());
        values.put(DataBaseHandler.SCHEDULE_FK_SUBJECT, schedule.getFk_subject());
        db.insert(DataBaseHandler.TABLE_SCHEDULE, null, values);
        try{
          //  db.close();
        }catch(Exception e){

        }
    }

    public ArrayList<Schedule> getSchedules(DataBaseHandler dh){
        ArrayList<Schedule> schedules = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.SCHEDULE_ID + ", " +
                DataBaseHandler.SCHEDULE_DAY + ", " +
                DataBaseHandler.SCHEDULE_START_HOUR + ", " +
                DataBaseHandler.SCHEDULE_END_HOUR + ", " +
                DataBaseHandler.SCHEDULE_FK_SUBJECT +
                " FROM " + DataBaseHandler.TABLE_SCHEDULE;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Schedule schedule = new Schedule();
            schedule.setIdSchedule(cursor.getInt(0));
            schedule.setDay(cursor.getInt(1));
            schedule.setInitialTime(cursor.getInt(2));
            schedule.setFinalTime(cursor.getInt(3));
            schedule.setFk_subject(cursor.getInt(4));
            schedules.add(schedule);
        }
        try{
           // cursor.close();
           // db.close();
        }catch(Exception e){

        }
        return schedules;
    }

    public ArrayList<Schedule> getSchedulesBySubject(int subject, DataBaseHandler dh){
        ArrayList<Schedule> schedules = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT " +
                DataBaseHandler.SCHEDULE_ID + ", " +
                DataBaseHandler.SCHEDULE_DAY + ", " +
                DataBaseHandler.SCHEDULE_START_HOUR + ", " +
                DataBaseHandler.SCHEDULE_END_HOUR + ", " +
                DataBaseHandler.SCHEDULE_FK_SUBJECT +
                " FROM " + DataBaseHandler.TABLE_SCHEDULE +
                " WHERE " + DataBaseHandler.SCHEDULE_FK_SUBJECT + " = '" + subject + "'";
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()){
            Schedule schedule = new Schedule();
            schedule.setIdSchedule(cursor.getInt(0));
            schedule.setDay(cursor.getInt(1));
            schedule.setInitialTime(cursor.getInt(2));
            schedule.setFinalTime(cursor.getInt(3));
            schedule.setFk_subject(cursor.getInt(4));
            schedules.add(schedule);
        }
        try{
            //cursor.close();
            //db.close();
        }catch(Exception e){

        }
        return schedules;
    }

    public void updateSchedule(Schedule updatedSchedule, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String updateQuery = "UPDATE " + DataBaseHandler.TABLE_SCHEDULE
                + " SET " + DataBaseHandler.SCHEDULE_DAY + " = " + updatedSchedule.getDay()
                + " , " + DataBaseHandler.SCHEDULE_START_HOUR + " = " + updatedSchedule.getInitialTime()
                + " , " + DataBaseHandler.SCHEDULE_END_HOUR + " = " + updatedSchedule.getFinalTime()
                + " , " + DataBaseHandler.SCHEDULE_FK_SUBJECT + " = " + updatedSchedule.getFk_subject()
                + " WHERE " + DataBaseHandler.SCHEDULE_ID + " = '" + updatedSchedule.getIdSchedule() + "'";
        db.execSQL(updateQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public void deleteSchedule(int id_schedule, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        String deleteQuery = "DELETE FROM "
                + DataBaseHandler.TABLE_SCHEDULE
                + " WHERE " + DataBaseHandler.SCHEDULE_ID + " = '" + id_schedule + "'";
        db.execSQL(deleteQuery);
        try{
           // db.close();
        }catch(Exception e){

        }
    }

    public int maxIdSchedule(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        int result = 0;

        String select = "SELECT MAX("+DataBaseHandler.SCHEDULE_ID+") " + " From "+DataBaseHandler.TABLE_SCHEDULE+
                " GROUP BY "+DataBaseHandler.SCHEDULE_ID;

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

    public ArrayList<Integer> getDaysBySubject(int fkSubject, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Integer> days = new ArrayList<>();
        String selectQuery = "SELECT " +
                DataBaseHandler.SCHEDULE_DAY +
                " FROM " + DataBaseHandler.TABLE_SCHEDULE +
                " WHERE " + DataBaseHandler.SCHEDULE_FK_SUBJECT + " = '" + fkSubject + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while(cursor.moveToNext()){
            days.add(cursor.getInt(0));
        }
        return  days;
    }

}
