package com.example.app.sqlite.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.app.sqlite.data.DataConstants;
import com.example.app.sqlite.data.DatabaseCreator;

/**
 * Created by amme on 2.6.2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static DatabaseOpenHelper mInstance = null;
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public static DatabaseOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseOpenHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private DatabaseOpenHelper(Context context) {
        super(context, DataConstants.DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseCreator.NOTE.MY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("TAG DB", ""+ oldVersion + " = " + newVersion);
    }


    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
}
