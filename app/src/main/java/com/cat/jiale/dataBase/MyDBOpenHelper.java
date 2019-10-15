package com.cat.jiale.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    //数据库名
    private static final String DATABASE_NAME="jiale.db";
    //版本号
    private static int DATABASE_VERSION=1;

    public MyDBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Toast.makeText(context,"数据库创建",Toast.LENGTH_LONG).show();

    }


    //数据库第一次创建时被调用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Purchase(id integer primary key autoincrement not null,name text)");
        Log.d("数据库创建","success");
    }
    //数据库更新的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
