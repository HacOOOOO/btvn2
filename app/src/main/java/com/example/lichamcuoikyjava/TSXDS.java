package com.example.lichamcuoikyjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TSXDS {
    private SQLiteDatabase database;
    private DatabaseGhiChu dbghichu;
    private String[] caccot = {DatabaseGhiChu.ID_GC,DatabaseGhiChu.ND_GC,DatabaseGhiChu.DAY_TIME,DatabaseGhiChu.DAY_KEY};
    public static String Time = "TIME('now','localtime')";

    public TSXDS(Context context){
        dbghichu = new DatabaseGhiChu(context);
    }

    public void open() throws SQLException{
        database = dbghichu.getWritableDatabase();
    }

    public void close(){
        dbghichu.close();
    }

    public DanhSachGC insert(String nd){
        Log.e("date1",MainActivity.Date_Choose);
//        database.execSQL("insert into ghichu1.DSghichu(noidung,ngay,time) values('aa','aaa','aaa') ");
        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        Log.e("time",timeStamp);

        ContentValues values = new ContentValues();
        values.put(DatabaseGhiChu.ND_GC,nd);
        values.put(DatabaseGhiChu.DAY_TIME,timeStamp);
        values.put(DatabaseGhiChu.DAY_KEY,MainActivity.Date_Choose);
        long insertID = database.insert(DatabaseGhiChu.TEN_BANG,null,values);
        Cursor cursor = database.query(DatabaseGhiChu.TEN_BANG,caccot,DatabaseGhiChu.ID_GC + " = " + insertID,
                null,null,null,null);
        cursor.moveToFirst();
        DanhSachGC newDanhSachGC = cursorToDanhSachGC(cursor);
        cursor.close();
        return newDanhSachGC;
    }
// insert into db values(nd,dayke:
    private DanhSachGC cursorToDanhSachGC(Cursor cursor){
        DanhSachGC danhSachGC = new DanhSachGC();
        danhSachGC.setId(cursor.getLong(0));
        danhSachGC.setNoidung(cursor.getString(1));
        danhSachGC.setDaykey(cursor.getString(3));
        danhSachGC.setDaytime(cursor.getString(2));
        return danhSachGC;
    }

    public void delete(DanhSachGC danhSachGC){
        long id = danhSachGC.getId();
        Log.e("SQLite", "xoa ghi chu id: "+id);
        database.delete(DatabaseGhiChu.TEN_BANG,DatabaseGhiChu.ID_GC +" = "+ id, null);
    }

    public List<DanhSachGC> danhSach(){
        List<DanhSachGC> ghiChu = new ArrayList<DanhSachGC>();
        Cursor cursor = database.query(DatabaseGhiChu.TEN_BANG,caccot,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DanhSachGC danhSachGC = cursorToDanhSachGC(cursor);
            if(danhSachGC.getDaykey().equals(MainActivity.Date_Choose)){

                ghiChu.add(danhSachGC);
            }

            cursor.moveToNext();
        }
        cursor.close();
        return ghiChu;
    }
}
