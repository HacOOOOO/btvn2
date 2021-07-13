package com.example.lichamcuoikyjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Query extends SQLiteOpenHelper {


    public static void QueryInsert(String noidung)
    {
        String IsQuery = "insert into "+DatabaseGhiChu.TEN_BANG+ "(noidung,ngay,time) values("+noidung+",Time('now','localtime'),'aaa' )";
        Log.e("aaa",IsQuery);
//        SQLiteDatabase db = null;
//        db.execSQL(IsQuery);
    }
    public Query(Context context){
        super(context, DatabaseGhiChu.DB_NAME, null, DatabaseGhiChu.VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
