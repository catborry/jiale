package com.cat.jiale.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.MediaSession;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    Context context;
    public MyDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "jiale.db", null, 1);
        this.context=context;
        Toast.makeText(context,"数据库创建",Toast.LENGTH_LONG).show();

    }


    //数据库第一次创建时被调用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Toast.makeText(context,"数据库创建",Toast.LENGTH_LONG).show();
    }
    //数据库更新的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
