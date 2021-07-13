package com.example.lichamcuoikyjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseGhiChu extends SQLiteOpenHelper {
    public static final String TEN_BANG = "DSghichu";
    public static final String ND_GC = "noidung";
    public static final String DAY_KEY = "ngay";
    public static final String DAY_TIME = "time";
    public static final String ID_GC = "id";

    public static final String DB_NAME = "ghichu1.db";
    public static final int VERSION = 1;

    public static final String CREATE_DB =
            "create table " + TEN_BANG + "("
                    + ID_GC + " integer primary key autoincrement , "
                    + ND_GC + " text not null, "
                    + DAY_TIME + " text not null, "
                    + DAY_KEY + " text not null )";

    public static final String SELECT_GC = "SELECT " + ND_GC + " FROM " + TEN_BANG + " WHERE " +  ID_GC+" = "+MainActivity.chonSelect+" ;";

    public DatabaseGhiChu(Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(DatabaseGhiChu.class.getName(),
                "Upgrading database from version "+oldVersion+" to "+newVersion+", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+TEN_BANG);
        onCreate(db);
    }
}
