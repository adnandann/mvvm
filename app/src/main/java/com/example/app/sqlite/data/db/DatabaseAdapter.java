package com.example.app.sqlite.data.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {

    private final SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public DatabaseAdapter(SQLiteOpenHelper databaseOpenHelper) {
        this.helper = databaseOpenHelper;
    }

    public synchronized SQLiteDatabase open() throws SQLException {
        try {
            this.db = this.helper.getWritableDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.db;
    }
    public void close() {
        if(this.db != null && this.db.isOpen()){
            this.helper.close();
        }
    }
}

