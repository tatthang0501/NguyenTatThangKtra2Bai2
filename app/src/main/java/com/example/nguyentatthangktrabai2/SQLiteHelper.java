package com.example.nguyentatthangktrabai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.nguyentatthangktrabai2.model.Course;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="HocOnline.db";
    private static final int DATABASE_VERSION=1;
    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE course (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "date TEXT,"+
                "major TEXT," +
                "activate INTEGER DEFAULT 0)";
        db.execSQL(sql);
    }

    public long addCourse(Course c){
        ContentValues v = new ContentValues();
        v.put("name", c.getName());
        v.put("date", c.getDate());
        v.put("major", c.getMajor());
        v.put("activate", c.isActivate());
        SQLiteDatabase sld = getWritableDatabase();
        return sld.insert("course",null,v);
    }

    public List<Course> getAll(){
        List<Course> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor c = statement.rawQuery("SELECT * FROM course",null);
        while((c!=null) && c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String date = c.getString(2);
            String major = c.getString(3);
            boolean act = c.getInt(4) == 1;
            Course ce = new Course();
            ce.setId(id);
            ce.setName(name);
            ce.setDate(date);
            ce.setMajor(major);
            ce.setActivate(act);
            list.add(ce);
        }
        return list;
    }

    public int updateCourse(Course c){
        System.out.println(c.isActivate());
        ContentValues v = new ContentValues();
        v.put("name", c.getName());
        v.put("date", c.getDate());
        v.put("major", c.getMajor());
        v.put("activate", c.isActivate());
        SQLiteDatabase slq = getWritableDatabase();
        String wClause = "id=?";
        String[] wArgs = {String.valueOf(c.getId())};
        return slq.update("course",v,wClause,wArgs);
    }

    public int deleteQuyenGop(int id){
        String wClause = "id=?";
        String[] wArgs = {String.valueOf(id)};
        SQLiteDatabase sql = getWritableDatabase();
        return sql.delete("course",wClause,wArgs);
    }

    public List<Course> getByName(String name){
        String[] args = {"%" + name + "%"};
        SQLiteDatabase slq = getReadableDatabase();
        Cursor c = slq.rawQuery("SELECT * FROM course WHERE name like ?",args);
        List<Course> list = new ArrayList<>();
        while((c!=null) && c.moveToNext()){
            int id = c.getInt(0);
            String named = c.getString(1);
            String date = c.getString(2);
            String major = c.getString(3);
            boolean act = c.getInt(4) == 1;
            Course ce = new Course();
            ce.setId(id);
            ce.setName(named);
            ce.setDate(date);
            ce.setMajor(major);
            ce.setActivate(act);
            list.add(ce);
        }
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
