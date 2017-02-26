package com.example.shallwhite.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2017/2/26.
 */

public class MissionDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_MISSION = "create table Mission("
            + "id integer primary key autoincrement,"
            + "date text,"
            + "text text,"
            + "is_solve integer)";
    private Context mContext;
    public MissionDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MISSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
