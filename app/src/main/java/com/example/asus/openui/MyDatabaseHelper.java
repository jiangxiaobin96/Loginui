package com.example.asus.openui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ASUS on 2017/6/29.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String USERS = "create table User ("
            + "id integer primary key autoincrement,"
            + "nameTable text,"
            + "keyTable text)";
    private Context mcontext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS);
        Toast.makeText(mcontext,"success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
