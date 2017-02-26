package com.example.shallwhite.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2017/2/26.
 */

public class UsersDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_USER = "create table User("
            + "id integer primary key autoincrement,"
            + "account text,"
            + "password text)";
    private Context mContext;
    public UsersDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
